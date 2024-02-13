package br.com.reserva.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Reservar")
                        .version("v1")
                        .description("API do sistema de reserva de equipamentos e laboratórios da UFAPE")
                        .contact(new Contact()
                                .name("Equipe de Desenvolvimento")
                                .email("dev@ufape.edu.br")
                                .url("https://www.ufape.edu.br"))
                        .license(new License()
                                .name("Licença UFAPE")
                                .url("https://www.ufape.edu.br/licenca")));
    }

}
