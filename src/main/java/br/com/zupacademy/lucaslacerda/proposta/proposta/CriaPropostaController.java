package br.com.zupacademy.lucaslacerda.proposta.proposta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.lucaslacerda.proposta.analise.RestricaoAnalise;
import br.com.zupacademy.lucaslacerda.proposta.analise.ResultadoSolicitacaoAnalise;
import br.com.zupacademy.lucaslacerda.proposta.analise.SolicitacaoAnaliseClient;
import br.com.zupacademy.lucaslacerda.proposta.analise.SolicitacaoAnaliseForm;
import feign.FeignException;
import feign.FeignException.FeignClientException;

import java.net.URI;

@RestController
@RequestMapping("/proposta")
public class CriaPropostaController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	SolicitacaoAnaliseClient encaminhaSolicitacaoAnalise;
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> criaNovaProposta(@RequestBody @Valid PropostaForm form,UriComponentsBuilder uriBuilder){

		Proposta proposta = form.toModel(EstadoProposta.NAO_ELEGIVEL);	
		manager.persist(proposta);
					
		try {	
			proposta.atualizaEstado(enviaSolicitacao(proposta)
									.getResultadoSolicitacao(),
									manager);
			
		}catch (FeignException.UnprocessableEntity e) {
				proposta.atualizaEstado(
					RestricaoAnalise.COM_RESTRICAO,
					manager);
				
			return ResponseEntity.status(e.status()).build();
		}
		
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(uri).body(uri);
	}
	
	
	private ResultadoSolicitacaoAnalise enviaSolicitacao(Proposta proposta) {
		return proposta.executaAnalise(encaminhaSolicitacaoAnalise);
	}
	
}
