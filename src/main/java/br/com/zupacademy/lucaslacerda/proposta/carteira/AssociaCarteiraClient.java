package br.com.zupacademy.lucaslacerda.proposta.carteira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "associaCarteira", url = "${api-contas.host}")
public interface AssociaCarteiraClient {

    @RequestMapping(method = RequestMethod.POST,consumes = "application/json",value = "/{id}/carteiras")
    AssociaCarteiraDto associaCarteiraDigital(@RequestParam(required = true) String id,
    										@RequestBody AssociaCarteiraForm form);
	
    
}
