package br.com.zupacademy.lucaslacerda.proposta.cartao.aviso;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zupacademy.lucaslacerda.proposta.cartao.Cartao;

public class AvisoViagemForm {

	@NotBlank
	private String destino;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Future(message = "A data do termino da viagem deve ser futura.")
	@NotNull
	private LocalDate validoAte;

	public AvisoViagemForm() {
		
	}
	
	public AvisoViagemForm(@NotBlank String destino,
			@NotBlank @Future(message = "A data do termino da viagem deve ser futura.") LocalDate validoAte) {
		super();
		this.destino = destino;
		this.validoAte = validoAte;
	}

	public AvisoViagem toModel(Cartao cartao,String ipClient,String userAgent) {
		return new AvisoViagem(cartao, destino, validoAte, ipClient, userAgent);
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}

	
}
