package br.com.zupacademy.lucaslacerda.proposta.proposta;

import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.zupacademy.lucaslacerda.proposta.analise.RestricaoAnalise;
import br.com.zupacademy.lucaslacerda.proposta.analise.ResultadoSolicitacaoAnalise;
import br.com.zupacademy.lucaslacerda.proposta.analise.SolicitacaoAnaliseClient;
import feign.FeignException;
import javassist.tools.rmi.ObjectNotFoundException;

import java.net.URI;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	SolicitacaoAnaliseClient encaminhaSolicitacaoAnalise;
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> criaNovaProposta(@RequestBody @Valid PropostaForm form,UriComponentsBuilder uriBuilder){

		Proposta proposta = form.toModel(EstadoProposta.NAO_ELEGIVEL);	
		propostaRepository.save(proposta);
					
		try {	
			proposta.atualizaEstado(enviaSolicitacao(proposta)
									.getResultadoSolicitacao(),
									propostaRepository);
			
		}catch (FeignException.UnprocessableEntity e) {
				proposta.atualizaEstado(
					RestricaoAnalise.COM_RESTRICAO,
					propostaRepository);
				
			return ResponseEntity.status(e.status()).build();
		}
		
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(uri).body(uri);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscaProposta(@PathVariable("id") Long idProposta) throws ObjectNotFoundException {
		
		
		Proposta proposta = propostaRepository.findById(idProposta).orElseThrow(
				()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proposta não encontrada.")
				);
		
		return ResponseEntity.ok(new AcompanhamentoPropostaDto(proposta));
	}
	
	
	private ResultadoSolicitacaoAnalise enviaSolicitacao(Proposta proposta) {
		return proposta.executaAnalise(encaminhaSolicitacaoAnalise);
	}
	
}
