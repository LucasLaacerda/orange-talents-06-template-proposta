package br.com.zupacademy.lucaslacerda.proposta.cartao;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultadoAnaliseCartao {

	@JsonProperty("id")
	private String id;
	
	@JsonCreator(mode = Mode.PROPERTIES)
	public ResultadoAnaliseCartao(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public Cartao toModel() {
		return new Cartao(this.id);
	}
		
}
