package br.com.zupacademy.lucaslacerda.proposta.biometria;

import java.time.LocalDateTime;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.lucaslacerda.proposta.cartao.Cartao;

@Entity
public class Biometria {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime dataAssociacao = LocalDateTime.now(); 
	
	@NotNull
	@Column(nullable = false)
	private String fingerprint;
	
	
	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Biometria() {
		
	}
	
	public Biometria(LocalDateTime dataAssociacao, @NotNull String fingerprint, Cartao cartao) {
		super();
		this.dataAssociacao = dataAssociacao;
		this.fingerprint = fingerprint;
		this.cartao = cartao;
	}

	public Biometria(BiometriaForm biometriaForm,Cartao cartao) {
		this.cartao = cartao;
		this.fingerprint = biometriaForm.getFingerprint();
	}

	public Long getId() {
		return id;
	}
	
	
}
