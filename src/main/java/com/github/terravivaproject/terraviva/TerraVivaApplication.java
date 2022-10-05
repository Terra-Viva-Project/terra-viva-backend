package com.github.terravivaproject.terraviva;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.MapSchema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;

/**
 * TerraVivaApplication class.
 *
 * @author giangi
 * @version $Id: $Id
 */
@SpringBootApplication
@EnableAsync
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class TerraVivaApplication {

    /**
     * main.
     *
     * @param args an array of {@link java.lang.String} objects
     */
    public static void main(String[] args) {
        SpringApplication.run(TerraVivaApplication.class, args);
    }

    /**
     * customOpenAPI.
     *
     * @param appVersion a {@link java.lang.String} object
     * @return a {@link io.swagger.v3.oas.models.OpenAPI} object
     */
    @Bean
    public OpenAPI customOpenAPI(@Value("${project.version}") String appVersion) {
        var mapErrorDto = new MapSchema()
                .addPatternProperty("value",
                        new ArraySchema()
                                .items(new StringSchema())
                                .examples(List.of(
                                        "sfsrf",
                                        "aefesfs"
                                )))
                .addProperty("username",
                        new ArraySchema()
                                .items(
                                        new StringSchema()
                                                .example("'username must be not null', 'username must be at least 2 character long'")
                                ))
                .addProperty("password",
                        new ArraySchema()
                                .items(
                                        new StringSchema()
                                                .example("'password must be not null', 'password must contains at leas 1 number'")
                                ));

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
                                                .url("https://www.gnu.org/licenses/quick-guide-gplv3.pdf")))
                .components(
                        new Components()
                                .addSchemas("mapErrorDto", mapErrorDto)
                );
    }
}
