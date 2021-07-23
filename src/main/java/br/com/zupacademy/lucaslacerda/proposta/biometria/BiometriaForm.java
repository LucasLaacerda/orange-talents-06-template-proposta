package br.com.zupacademy.lucaslacerda.proposta.biometria;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.lucaslacerda.proposta.cartao.Cartao;
import br.com.zupacademy.lucaslacerda.proposta.validacao.Base64Valid;

public class BiometriaForm {

	@NotBlank @Base64Valid
	private String fingerprint;
	
	@Deprecated
	public BiometriaForm() {
	
	}

	public BiometriaForm(@NotBlank String fingerprint) {
		super();
		this.fingerprint = fingerprint;
	}

	

	public String getFingerprint() {
		return fingerprint;
	}

	public Biometria toModel(Cartao cartao) {
		return new Biometria(this,cartao);
	}

}
