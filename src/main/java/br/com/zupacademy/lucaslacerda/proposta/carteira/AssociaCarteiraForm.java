package br.com.zupacademy.lucaslacerda.proposta.carteira;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.lucaslacerda.proposta.cartao.Cartao;

public class AssociaCarteiraForm {
	
	@Email @NotBlank
	private String email;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private CarteiraModelos carteira;
	
	@Deprecated
	public AssociaCarteiraForm() {
		
	}
	
	
	
	public AssociaCarteiraForm(@Email @NotBlank String email, @NotBlank CarteiraModelos carteira) {
		super();
		this.email = email;
		this.carteira = carteira;
	}



	public Carteira toModel(Cartao cartao,String numeroCarteira) {
		return new Carteira(numeroCarteira,email,carteira, cartao); 
	}

	public String getEmail() {
		return email;
	}

	public CarteiraModelos getCarteira() {
		return carteira;
	}

}
