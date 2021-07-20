package br.com.zupacademy.lucaslacerda.proposta.proposta;

import java.math.BigDecimal;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import br.com.zupacademy.lucaslacerda.proposta.validacao.CPFOrCNPJ;

public class PropostaForm {

	@NotBlank
	@CPFOrCNPJ
	private String documento;
	
	@Email @NotBlank
	private String email;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String endereco;
	
	@Positive
	private BigDecimal salario;

	public PropostaForm() {
		
	}
	
	public PropostaForm(@NotBlank String documento, @Email @NotBlank String email, @NotBlank String nome,
			@NotBlank String endereco, @Positive BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}
	
	
	public Proposta toModel() {
		return new Proposta(documento, email, nome, endereco, salario);
	}
	
	
}
