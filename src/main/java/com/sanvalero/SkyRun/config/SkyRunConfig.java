package com.sanvalero.skyrun.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Creado por @ author: Pedro Orós
 * el 15/05/2021
 */

@Configuration
public class SkyRunConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("SkyRun")
                .description("API REST sobre carreras de montaña")
                .contact(new Contact()
                        .name("Pedro Oros")
                        .email("shadycreek@hotmail.com")
                        .url("https://github.com/PideroAntonio80/SkyRun.git"))
                .version("1.0"));
    }
}
