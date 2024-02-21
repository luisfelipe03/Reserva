package br.com.reserva.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("API de reserva de equipamentos e laboratorios da UFAPE")
                    .version("1.0")
                    .description("API para gerenciar reservas de equipamentos e laboratorios da UFAPE")
                        .contact(new Contact()
                                .name("Equipe de Desenvolvimento")
                                .email("dev@ufape.edu.br")
                                .url("https://www.ufape.edu.br"))
                        .license(new License()
                                .name("Licença UFAPE")
                                .url("https://www.ufape.edu.br/licenca")));
    }
}
