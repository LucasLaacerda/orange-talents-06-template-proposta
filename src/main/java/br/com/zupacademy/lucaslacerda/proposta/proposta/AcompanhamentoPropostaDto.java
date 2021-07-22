package br.com.zupacademy.lucaslacerda.proposta.proposta;



public class AcompanhamentoPropostaDto {

	
	private String estadoProposta;

	public AcompanhamentoPropostaDto() {
		
	}
	
	public AcompanhamentoPropostaDto(Proposta proposta) {
		super();
		this.estadoProposta = proposta.getEstadoProposta()
				.toString().replace("_"," ");
	}

	public String getEstadoProposta() {
		return estadoProposta;
	}
	
	
	
	
}
