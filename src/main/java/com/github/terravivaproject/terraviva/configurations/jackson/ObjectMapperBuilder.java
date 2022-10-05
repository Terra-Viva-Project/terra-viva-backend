package com.github.terravivaproject.terraviva.configurations.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.format.DateTimeFormatter;

/**
 * class
 *
 * @author Gianluigi De Marco
 * @version x
 * @since 29 09 2022
 */
@Configuration
public class ObjectMapperBuilder {
    private static final String DATE_PATTERN = "dd-MMM-yyy";
    private static final String TIME_PATTERN = "HH:mm:ss";

    /**
     * jackson2ObjectMapperBuilder.
     *
     * @return a {@link org.springframework.http.converter.json.Jackson2ObjectMapperBuilder} object
     */
    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .serializers(
                        new LocalDateTimeSerializer(
                                DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                        new LocalDateSerializer(
                                DateTimeFormatter.ISO_LOCAL_DATE),
                        new LocalTimeSerializer(
                                DateTimeFormatter.ISO_LOCAL_TIME))
                .deserializers(
                        new LocalDateTimeDeserializer(
                                DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                        new LocalDateDeserializer(
                                DateTimeFormatter.ISO_LOCAL_DATE),
                        new LocalTimeDeserializer(
                                DateTimeFormatter.ISO_LOCAL_TIME))
                .serializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
