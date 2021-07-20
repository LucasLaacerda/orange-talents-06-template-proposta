package br.com.zupacademy.lucaslacerda.proposta.proposta;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
public class Proposta {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable=false)
	private String documento;
	
	@Email @NotBlank
	@Column(nullable=false)
	private String email;
	
	@NotBlank
	@Column(nullable=false)
	private String nome;
	
	@NotBlank
	@Column(nullable=false)
	private String endereco;
	
	@Positive
	@Column(nullable=false)
	private BigDecimal salario;

	public Proposta() {
		
	}
	
	public Proposta(@NotBlank String documento, @Email @NotBlank String email, @NotBlank String nome,
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

	public Long getId() {
		return id;
	}

}
