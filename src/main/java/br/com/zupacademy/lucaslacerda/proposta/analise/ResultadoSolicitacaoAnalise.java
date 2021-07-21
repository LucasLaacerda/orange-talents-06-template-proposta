package br.com.zupacademy.lucaslacerda.proposta.analise;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class ResultadoSolicitacaoAnalise {

	private String documento;
	private String nome;
	
	@Enumerated
	private RestricaoAnalise resultadoSolicitacao;
	
	private String idProposta;

	public ResultadoSolicitacaoAnalise() {
	
	}
	
	public ResultadoSolicitacaoAnalise(String documento, String nome, RestricaoAnalise resultadoSolicitacao,
			String idProposta) {
		super();
		this.documento = documento;
		this.nome = nome;
		this.resultadoSolicitacao = resultadoSolicitacao;
		this.idProposta = idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public RestricaoAnalise getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	public String getIdProposta() {
		return idProposta;
	}
	
	
	
}
