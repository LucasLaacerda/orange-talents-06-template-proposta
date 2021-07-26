package br.com.zupacademy.lucaslacerda.proposta.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{
	
	
	
	//Configuracoes de autorizacao url e ets
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			
	
		http.authorizeRequests(authorizeRequests ->
        authorizeRequests
		.antMatchers(HttpMethod.POST,"/propostas/**").hasAuthority("SCOPE_propostas:write")
		.antMatchers(HttpMethod.GET,"/actuator/**").hasAuthority("SCOPE_actuator:read")
		.antMatchers(HttpMethod.POST,"/cartoes/**").hasAuthority("SCOPE_cartao:write")
		.antMatchers(HttpMethod.GET,"/propostas/**").hasAuthority("SCOPE_propostas:read")
		.anyRequest().authenticated()
		 
		).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
		//.and().csrf().disable();
	}
	
	//Configuracoes de recursos estaticos(js,css,imagens,etc.)
	@Override
	public void configure(WebSecurity web) throws Exception {
		  web.ignoring()
	        .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
		
	}

}
