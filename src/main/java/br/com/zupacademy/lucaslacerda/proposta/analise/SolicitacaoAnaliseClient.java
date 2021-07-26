package br.com.zupacademy.lucaslacerda.proposta.analise;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "solicitacao", url ="${api-analise.host}")
public interface SolicitacaoAnaliseClient {

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    ResultadoSolicitacaoAnalise enviaSolicitacaoAnalise(@RequestBody @Valid SolicitacaoAnaliseForm form);
	
}
