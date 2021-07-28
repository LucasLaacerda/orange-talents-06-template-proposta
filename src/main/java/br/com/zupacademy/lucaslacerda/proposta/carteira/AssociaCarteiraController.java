package br.com.zupacademy.lucaslacerda.proposta.carteira;

import java.net.URI;
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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.lucaslacerda.proposta.cartao.Cartao;
import br.com.zupacademy.lucaslacerda.proposta.proposta.AssociaPropostaCartao;
import feign.FeignException;

@RestController
@RequestMapping("/cartoes")
public class AssociaCarteiraController {
	
	@PersistenceContext
	private EntityManager manager;
    private final Logger logger = LoggerFactory.getLogger(AssociaPropostaCartao.class);

    @Autowired
    AssociaCarteiraClient associaCarteiraClient;
    
	@PostMapping("/{idCartao}/carteiras")
	@Transactional
	public ResponseEntity<?> bloqueiaCartao(@PathVariable(required = true,name = "idCartao") 
								 String idCartao,
								@RequestBody @Valid AssociaCarteiraForm form,
								 UriComponentsBuilder uriBuilder){

		
		Cartao cartao = Optional.ofNullable(manager.find(Cartao.class,Long.parseLong(idCartao)))
								.orElseThrow(()-> 					
									new ResponseStatusException(
										HttpStatus.NOT_FOUND, 
										"Cartão não encontrado."));

		AssociaCarteiraDto response;
		try {
			response = 
					associaCarteiraClient.
						associaCarteiraDigital(cartao.getNumero(), form);
			
		}catch (FeignException e) {
			logger.info("Não foi possivel associar carteira com o cartão.");

			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, //422
					"Cartão ja associado a carterira.");
		}
		
		
		CarteiraPaypal carteiraPaypal = form.toModel(cartao,response.getId());		
		manager.persist(carteiraPaypal);
		
		
		
		URI uri = uriBuilder.path("/carteiras/{id}").buildAndExpand(carteiraPaypal.getId()).toUri();
		return ResponseEntity.created(uri).body(uri);	
	}
		
}
