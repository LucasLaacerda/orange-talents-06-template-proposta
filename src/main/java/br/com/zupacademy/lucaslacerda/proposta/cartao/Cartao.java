package br.com.zupacademy.lucaslacerda.proposta.cartao;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.lucaslacerda.proposta.biometria.Biometria;
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
	
	
	
}
