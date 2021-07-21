package br.com.zupacademy.lucaslacerda.proposta.analise;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "solicitacao", url = "http://localhost:9999/")
public interface SolicitacaoAnaliseClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao", consumes = "application/json")
    ResultadoSolicitacaoAnalise enviaSolicitacaoAnalise(@RequestBody @Valid SolicitacaoAnaliseForm form);
	
}
