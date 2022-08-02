package com.github.terravivaproject.terraviva;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TerraVivaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TerraVivaApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${project.version}") String appVersion) {
        return new OpenAPI()
                .components(
                        new Components())
                .info(
                        new Info()
                                .title("Terra Viva")
                                .version(appVersion)
                                .contact(new Contact()
                                        .url("https://github.com/Terra-Viva-Project")
                                        .name("Terra viva team")
                                        .email(""))
                                .description("Terra Viva is a backend project of a social for seed savers.")
                                .license(
                                        new License()
                                                .name("GPL3")
                                                .url("https://www.gnu.org/licenses/quick-guide-gplv3.pdf")));
    }
}