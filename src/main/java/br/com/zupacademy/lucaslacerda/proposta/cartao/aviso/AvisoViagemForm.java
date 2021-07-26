package br.com.zupacademy.lucaslacerda.proposta.cartao.aviso;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zupacademy.lucaslacerda.proposta.cartao.Cartao;

public class AvisoViagemForm {

	@NotBlank
	private String destinoViagem;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Future(message = "A data do termino da viagem deve ser futura.")
	@NotNull
	private LocalDate dataTerminoViagem;

	public AvisoViagemForm() {
		
	}
	
	public AvisoViagemForm(@NotBlank String destinoViagem,
			@NotBlank @Future(message = "A data do termino da viagem deve ser futura.") LocalDate dataTerminoViagem) {
		super();
		this.destinoViagem = destinoViagem;
		this.dataTerminoViagem = dataTerminoViagem;
	}

	public AvisoViagem toModel(Cartao cartao,String ipClient,String userAgent) {
		return new AvisoViagem(cartao, destinoViagem, dataTerminoViagem, ipClient, userAgent);
	}

	public String getDestinoViagem() {
		return destinoViagem;
	}

	public LocalDate getDataTerminoViagem() {
		return dataTerminoViagem;
	}
	
	
	
}
