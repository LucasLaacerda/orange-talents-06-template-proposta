package br.com.zupacademy.lucaslacerda.proposta.cartao.aviso;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.lucaslacerda.proposta.cartao.Cartao;
import br.com.zupacademy.lucaslacerda.proposta.info.request.DataClient;
import br.com.zupacademy.lucaslacerda.proposta.proposta.AssociaPropostaCartao;
import feign.FeignException;

@RestController
@RequestMapping("/cartoes/{idCartao}/aviso/viagem")
public class AvisoViagemController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	DataClient dataClient;
	
	@Autowired
	AvisoCartaoClient avisoCartaoClient;
    private final Logger logger = LoggerFactory.getLogger(AssociaPropostaCartao.class);

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastraAvisoViagem(@PathVariable(required = true,name = "idCartao") 
								 String idCartao,
								 HttpServletRequest request,
								 @RequestBody @Valid AvisoViagemForm form){
		
		
		
		Cartao cartao = Optional.ofNullable(manager.find(Cartao.class,Long.parseLong(idCartao)))
											.orElseThrow(()-> 					
											new ResponseStatusException(
													HttpStatus.NOT_FOUND, 
													"Cartão não encontrado."));
	
		AvisoViagem avisoViagem = form.toModel(cartao,
				dataClient.buscaIpClient(request),
				dataClient.buscaUserAgent(request));
		
		
		try {
			  avisoCartaoClient.avisoViagemCartao(cartao.getNumero(), form);

		} catch (FeignException e) {
			logger.info("Nao foi possivel cadastrar o aviso de viagem.");
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Nao foi possivel cadastrar o aviso de viagem.");
		}
	
	
	 
	  manager.persist(avisoViagem);
		
	  return ResponseEntity.ok().build();
	}
	
	
}
