package br.com.zupacademy.lucaslacerda.proposta.analise;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.lucaslacerda.proposta.proposta.Proposta;
import br.com.zupacademy.lucaslacerda.proposta.validacao.CPFOrCNPJ;

//Dados a serem enviados ao client
public class SolicitacaoAnaliseForm {

	@NotBlank
	@CPFOrCNPJ
	private String documento;
	
	private String nome;
	
	private String idProposta;

	public SolicitacaoAnaliseForm() {

	}
	
	public SolicitacaoAnaliseForm(Proposta proposta) {
		super();
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.idProposta = proposta.getId().toString();
	}
	
	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}
	

}
