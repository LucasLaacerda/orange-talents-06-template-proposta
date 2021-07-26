package br.com.zupacademy.lucaslacerda.proposta.cartao.bloqueio;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.lucaslacerda.proposta.cartao.Cartao;

@Entity
public class Bloqueio {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Cartao cartao; 
	
	private LocalDateTime instante = LocalDateTime.now();
	
	@NotNull
	@Column(nullable = false)
	private String ipCliente;
	
	@NotNull
	@Column(nullable = false)
	private String userAgent;

	@Deprecated
	public Bloqueio() {
		
	}
	
	public Bloqueio(Cartao cartao,@NotNull String ipCliente,
			@NotNull String userAgent) {
		super();
		this.cartao = cartao;
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
	}
	
	
	
}
