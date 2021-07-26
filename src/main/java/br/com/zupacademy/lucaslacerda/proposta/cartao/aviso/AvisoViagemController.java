package br.com.zupacademy.lucaslacerda.proposta.cartao.aviso;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

@RestController
@RequestMapping("/cartoes/{idCartao}/aviso/viagem")
public class AvisoViagemController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	DataClient dataClient;
	
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
		
		
	 
	  manager.persist(avisoViagem);
		
	  return ResponseEntity.ok().build();
	}
	
	
}
