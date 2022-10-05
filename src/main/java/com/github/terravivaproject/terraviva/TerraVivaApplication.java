package com.github.terravivaproject.terraviva;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * TerraVivaApplication class.
 *
 * @author giangi
 * @version $Id: $Id
 */
@SpringBootApplication
@EnableAsync
public class TerraVivaApplication {

    /**
     * main.
     *
     * @param args an array of {@link java.lang.String} objects
     */
    public static void main(String[] args) {
        SpringApplication.run(TerraVivaApplication.class, args);
    }
}
