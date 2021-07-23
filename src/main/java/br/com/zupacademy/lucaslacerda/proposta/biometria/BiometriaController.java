package br.com.zupacademy.lucaslacerda.proposta.biometria;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.lucaslacerda.proposta.cartao.Cartao;
import br.com.zupacademy.lucaslacerda.proposta.cartao.CartaoRepository;

import java.net.URI;

@RestController
@RequestMapping("/cartao/{idCartao}/biometria")
public class BiometriaController {

	@Autowired
	BiometriaRepository biometriaRepository;
	@Autowired
	CartaoRepository cartaoRepository;
	
	@PostMapping
	public ResponseEntity<?> cadastraBiometria(@RequestBody @Valid BiometriaForm form,
			@PathVariable(name = "idCartao") Long idCartao,
			UriComponentsBuilder uriBuilder){
			
		
		Biometria biometria = form.toModel(buscaCartao(idCartao));
		biometriaRepository.save(biometria);

		URI uri = uriBuilder.path("/biometria/{id}").buildAndExpand(biometria.getId()).toUri();
		return ResponseEntity.created(uri).body(uri);
	}

	
	private Cartao buscaCartao(Long idCartao) {
		
		return cartaoRepository.findById(idCartao)
				.orElseThrow(
				
				()-> new ResponseStatusException(
						HttpStatus.NOT_FOUND, 
						"Cartão não encontrado.")
				
		);
	}
	
	
}
