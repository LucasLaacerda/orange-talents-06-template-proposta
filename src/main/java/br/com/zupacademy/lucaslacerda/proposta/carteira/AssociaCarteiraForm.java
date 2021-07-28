package br.com.zupacademy.lucaslacerda.proposta.carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.lucaslacerda.proposta.cartao.Cartao;

public class AssociaCarteiraForm {
	
	@Email @NotBlank
	private String email;
	
	@NotBlank
	private String carteira;
	
	@Deprecated
	public AssociaCarteiraForm() {
		
	}
	
	
	
	public AssociaCarteiraForm(@Email @NotBlank String email, @NotBlank String carteira) {
		super();
		this.email = email;
		this.carteira = carteira;
	}



	public CarteiraPaypal toModel(Cartao cartao,String numeroCarteira) {
		return new CarteiraPaypal(numeroCarteira,email, cartao);
	}

	public String getEmail() {
		return email;
	}

	public String getCarteira() {
		return carteira;
	}

}
