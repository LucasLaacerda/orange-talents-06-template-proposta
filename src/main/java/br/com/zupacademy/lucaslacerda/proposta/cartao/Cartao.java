package br.com.zupacademy.lucaslacerda.proposta.cartao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.lucaslacerda.proposta.biometria.Biometria;
import br.com.zupacademy.lucaslacerda.proposta.cartao.bloqueio.Bloqueio;
import br.com.zupacademy.lucaslacerda.proposta.proposta.Proposta;

@Entity
public class Cartao {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(nullable = false)
	private String numero;
	
	@OneToOne(mappedBy = "cartao")
	Proposta proprosta;
	
	@OneToMany(mappedBy = "cartao")
	private Set<Biometria> biometria = new HashSet<Biometria>();
	
	@OneToMany(mappedBy = "cartao",cascade = CascadeType.MERGE)
	private Set<Bloqueio> bloqueio = new HashSet<Bloqueio>();
	
	
	@Deprecated
	public Cartao() {
		
	}
	
	public Cartao(@NotNull String numero, Proposta proprosta, Set<Biometria> biometria) {
		super();
		this.numero = numero;
		this.proprosta = proprosta;
		this.biometria = biometria;
	}


	public Cartao(String numero) {
		this.numero = numero;
	}


	public Long getId() {
		return id;
	}


	public Proposta getProprosta() {
		return proprosta;
	}


	public Set<Biometria> getBiometria() {
		return biometria;
	}


	public String getNumero() {
		return numero;
	}

	
	public void verificaBloqueio(String ip, String userAgent) {
		
		if(ip!=null && ip.length()>0 &&
				userAgent!=null && userAgent.length()>0 &&
				bloqueado()) {
				
					this.bloqueio.add(new Bloqueio(this, ip, userAgent));
				
		}else if(!bloqueado())
			throw new ResponseStatusException(
					HttpStatus.UNPROCESSABLE_ENTITY, 
					"Cartão já se encontra bloqueado.");
		
		 else
			 throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, 
					"Dados incorretos");
	}

	
	
	private boolean bloqueado() {
		return this.bloqueio.isEmpty();
	}
	
	
	
}
