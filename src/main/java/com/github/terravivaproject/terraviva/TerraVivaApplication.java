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
                                .items(new StringSchema()))
                .addProperty("field_1",
                        new ArraySchema()
                                .items(
                                        new StringSchema()
                                                .example("'error 1 on field_1', 'error 2 on field_1'")
                                ))
                .addProperty("field_2",
                        new ArraySchema()
                                .items(
                                        new StringSchema()
                                                .example("'error 1 on field_2', 'error 2 on field_2'")
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
