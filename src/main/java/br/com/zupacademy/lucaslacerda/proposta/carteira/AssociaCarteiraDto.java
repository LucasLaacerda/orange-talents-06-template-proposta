package br.com.zupacademy.lucaslacerda.proposta.carteira;

public class AssociaCarteiraDto {

	private String resultado;
	private String id;
	
	@Deprecated
	public AssociaCarteiraDto() {
		
	}
	
	public AssociaCarteiraDto(String resultado, String id) {
		super();
		this.resultado = resultado;
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
}
