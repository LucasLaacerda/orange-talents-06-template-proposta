package br.com.zupacademy.lucaslacerda.proposta.proposta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/proposta")
public class CriaPropostaController {

	@PersistenceContext
	private EntityManager manager;
		
	@PostMapping
	@Transactional
	public ResponseEntity<?> criaNovaProposta(@RequestBody @Valid PropostaForm form,UriComponentsBuilder uriBuilder){
		
		Proposta proposta = form.toModel();	
		manager.persist(proposta);
		
		//retorna 201
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(uri).body(uri);
	}
	
}
