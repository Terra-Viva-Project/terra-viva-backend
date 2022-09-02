package com.github.terravivaproject.terraviva.user.controllers;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
public class HomeController {
    public static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String securedEndpoint() {
        return
            """
                <html>
                    <header><title>Welcome</title></header>
                    <body>
                        <h1>Welcome to the secure endpoint!</h1>
                    </body>
                </html>""";
    }
}
