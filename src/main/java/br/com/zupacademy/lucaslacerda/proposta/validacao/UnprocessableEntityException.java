package br.com.zupacademy.lucaslacerda.proposta.validacao;

public class UnprocessableEntityException extends RuntimeException {
	
	public UnprocessableEntityException(String mensagem) {
		super(mensagem);
	}
	
}
