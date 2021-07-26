package br.com.zupacademy.lucaslacerda.proposta.cartao.bloqueio;

import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "bloqueioCartoes", url = "${api-contas.host}")
public interface BloqueioCartaoClient {

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/bloqueios", consumes = "application/json")
    String bloqueiaCartao(@PathVariable String id,@RequestBody Map<String,String> sistema);
	   
}
