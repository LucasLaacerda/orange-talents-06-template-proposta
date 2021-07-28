package br.com.zupacademy.lucaslacerda.proposta.carteira;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.lucaslacerda.proposta.cartao.Cartao;

@Entity
public class CarteiraPaypal {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String numeroCarteira;
	
	@Column(nullable=false)
	private String email;
	
	@ManyToOne
	private Cartao cartao;

	public CarteiraPaypal() {
		
	}

	public CarteiraPaypal(String numeroCarteira, String email, Cartao cartao) {
		super();
		this.numeroCarteira = numeroCarteira;
		this.email = email;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}
	
	
}
