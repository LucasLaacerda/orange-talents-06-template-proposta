package br.com.zupacademy.lucaslacerda.proposta.cartao.aviso;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import br.com.zupacademy.lucaslacerda.proposta.cartao.Cartao;

@Entity
public class AvisoViagem {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	private Cartao cartao;
	
	@NotNull
	@Column(nullable = false)
	private String destino;
	
	private LocalDateTime instante = LocalDateTime.now();
	
	@Future @NotNull
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataTerminoViagem; 
	
	@NotNull
	@Column(nullable = false)
	private String ipCliente;
	
	@NotNull
	@Column(nullable = false)
	private String userAgent;

	public AvisoViagem() {
		
	}
	
	public AvisoViagem(Cartao cartao, @NotNull String destino,
			@Future @NotNull LocalDate dataTerminoViagem, @NotNull String ipCliente, @NotNull String userAgent) {
		super();
		this.cartao = cartao;
		this.destino = destino;
		this.dataTerminoViagem = dataTerminoViagem;
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
	}
	
	
	
	
}
