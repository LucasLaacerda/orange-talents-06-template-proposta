package br.com.zupacademy.lucaslacerda.proposta.carteira;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.lucaslacerda.proposta.cartao.Cartao;

@Entity
public class Carteira {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String numeroCarteira;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private CarteiraModelos modelo;
	
	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Carteira() {
		
	}	

	public Carteira(String numeroCarteira, String email, CarteiraModelos modelo, Cartao cartao) {
		super();
		this.numeroCarteira = numeroCarteira;
		this.email = email;
		this.modelo = modelo;
		this.cartao = cartao;
	}



	public Long getId() {
		return id;
	}
	
	
}
