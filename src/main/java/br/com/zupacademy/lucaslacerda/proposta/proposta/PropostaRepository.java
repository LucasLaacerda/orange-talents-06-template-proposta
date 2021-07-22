package br.com.zupacademy.lucaslacerda.proposta.proposta;


import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long>{

	Iterable<Proposta> findByEstadoPropostaAndCartaoIsNull(EstadoProposta estado);
	
}
