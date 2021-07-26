package br.com.zupacademy.lucaslacerda.proposta.info.request;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class DataClient {

	public String buscaUserAgent(HttpServletRequest request) {	
		return request.getHeader("USER-AGENT");
	}
	
	public String buscaIpClient(HttpServletRequest request) {	
		String ipClient = request.getHeader("X-FORWARDED-FOR");  
		if (ipClient == null) 
				ipClient = request.getRemoteAddr();  
		
		return ipClient;
	}
}
