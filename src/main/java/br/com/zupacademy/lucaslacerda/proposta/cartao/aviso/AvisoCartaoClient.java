package br.com.zupacademy.lucaslacerda.proposta.cartao.aviso;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "avisoCartoes", url = "${api-contas.host}")
public interface AvisoCartaoClient {

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "/{id}/avisos")
    String avisoViagemCartao(@PathVariable String id,@RequestBody AvisoViagemForm form);
	   
}
