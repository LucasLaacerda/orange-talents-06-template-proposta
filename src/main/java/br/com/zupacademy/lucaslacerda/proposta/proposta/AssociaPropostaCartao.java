package br.com.zupacademy.lucaslacerda.proposta.proposta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.lucaslacerda.proposta.cartao.AnaliseCartaoClient;
import br.com.zupacademy.lucaslacerda.proposta.cartao.ResultadoAnaliseCartao;
import feign.FeignException;

@Component
public class AssociaPropostaCartao {

	@Autowired
	PropostaRepository propostaRepository;
	@Autowired
	AnaliseCartaoClient analiseCartao;
	
    private final Logger logger = LoggerFactory.getLogger(AssociaPropostaCartao.class);

	@Scheduled(fixedRate = 100000)
	public void RealizaAssociaoCartaoComProposta()  throws Exception {
		
		Iterable<Proposta> propostasElegiveis = 
				propostaRepository.findByEstadoPropostaAndCartaoIsNull(EstadoProposta.ELEGIVEL);
		
		propostasElegiveis.forEach(proposta->{
			try {
				
			ResultadoAnaliseCartao result = 
					analiseCartao.buscaAnaliseCartao(proposta.getId().toString());
			proposta.associaCartao(result.getId());
			propostaRepository.save(proposta);
			logger.info("Cartao associado com proposta.");

			}catch (FeignException e) {
				logger.info("Nao foi possivel associar cartao com proposta.");
				throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Nao foi possivel associar cartao com proposta.");
			}
		});
		
	}
	
	
}
