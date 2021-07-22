package br.com.zupacademy.lucaslacerda.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cartao", url = "http://localhost:8888/")
public interface AnaliseCartaoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/cartoes", consumes = "application/json")
    ResultadoAnaliseCartao buscaAnaliseCartao(@RequestParam(required = true,name = "idProposta") String idProposta);
	
}
