package br.com.zupacademy.lucaslacerda.proposta.proposta;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import br.com.zupacademy.lucaslacerda.proposta.analise.RestricaoAnalise;
import br.com.zupacademy.lucaslacerda.proposta.analise.ResultadoSolicitacaoAnalise;
import br.com.zupacademy.lucaslacerda.proposta.analise.SolicitacaoAnaliseClient;
import br.com.zupacademy.lucaslacerda.proposta.analise.SolicitacaoAnaliseForm;

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

	@Enumerated
	@Column(nullable=false)
	private EstadoProposta estadoProposta;
	
	private String cartao;
	
	public Proposta() {
		
	}
	
	

	public Proposta(@NotBlank String documento, @Email @NotBlank String email, @NotBlank String nome,
			@NotBlank String endereco, @Positive BigDecimal salario, EstadoProposta estadoProposta) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
		this.estadoProposta = estadoProposta;
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

	public String getCartao() {
		return cartao;
	}



	public EstadoProposta getEstadoProposta() {
		return estadoProposta;
	}
	
	public void atualizaEstado(RestricaoAnalise restricaoAnalise, PropostaRepository propostaRepository) {
		this.estadoProposta = 
				restricaoAnalise==RestricaoAnalise.COM_RESTRICAO?
						estadoProposta.NAO_ELEGIVEL:estadoProposta.ELEGIVEL;
		propostaRepository.save(this);

	}
	public void associaCartao(String cartao) {
		this.cartao = cartao;
	}
	
	public ResultadoSolicitacaoAnalise executaAnalise(SolicitacaoAnaliseClient encaminhaSolicitacaoAnalise) {
		ResultadoSolicitacaoAnalise retornoAnalise = encaminhaSolicitacaoAnalise.
				enviaSolicitacaoAnalise(new SolicitacaoAnaliseForm(this));
		return retornoAnalise;
	}
	
}
