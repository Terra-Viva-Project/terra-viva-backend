package com.github.terravivaproject.terraviva.configurations.springdoc;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.MapSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springdoc.core.SpringDocUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * class
 *
 * @author Gianluigi De Marco
 * @version x
 * @since 05 10 2022
 */
@Configuration
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class SpringDocConfigurations {
    private Schema mapErrorDtoSchema() {
        return new MapSchema()
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
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${project.version}") String appVersion) {
        SpringDocUtils
                .getConfig()
                .replaceWithSchema(
                        LocalDate.class,
                        new Schema<LocalDate>()
                                .example(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE))
                                .type("string"))
                .replaceWithSchema(
                        LocalDateTime.class,
                        new Schema<LocalDateTime>()
                                .example(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                                .type("string"))
                .replaceWithSchema(
                        LocalTime.class,
                        new Schema<LocalTime>()
                                .example(LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME))
                                .type("string"))
        ;//.replaceWithClass(org.springframework.data.domain.Pageable.class, Pageable.class);

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
                                .addSchemas("mapErrorDto", mapErrorDtoSchema())
                );
    }
}
