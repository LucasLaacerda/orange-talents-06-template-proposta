package br.com.zupacademy.lucaslacerda.proposta.cartao.bloqueio;


import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.lucaslacerda.proposta.cartao.Cartao;
import br.com.zupacademy.lucaslacerda.proposta.cartao.CartaoRepository;

@RestController
@RequestMapping("/cartoes")
public class BloqueioCartaoController {
		
	@Autowired
	BloqueioCartaoClient bloqueioCartaoClient;
	
	@PersistenceContext
	private EntityManager manager;
	

	@PostMapping("/{idCartao}/bloqueio")
	@Transactional
	public ResponseEntity<?> bloqueiaCartao(@PathVariable(required = true,name = "idCartao") 
								 String idCartao,
								 HttpServletRequest request){
		
		
		
		Cartao cartao = Optional.ofNullable(manager.find(Cartao.class,Long.parseLong(idCartao)))
											.orElseThrow(()-> 					
											new ResponseStatusException(
													HttpStatus.NOT_FOUND, 
													"Cartão não encontrado."));
	
		
	  cartao.verificaBloqueio(retornaIpClient(request),
			  				  request.getHeader("USER-AGENT"));
	  
	  bloqueioCartaoClient.bloqueiaCartao(cartao.getNumero(),Map.of("sistemaResponsavel", "desafioproposta"));
	  manager.merge(cartao);
		
	  return ResponseEntity.ok().build();
	}
	
	
	private String retornaIpClient(HttpServletRequest request) {
		
		String ipClient = request.getHeader("X-FORWARDED-FOR");  
		if (ipClient == null) 
				ipClient = request.getRemoteAddr();  
		
		return ipClient;
	}
	
	
}
