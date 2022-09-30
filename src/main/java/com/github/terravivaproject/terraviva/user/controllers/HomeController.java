package com.github.terravivaproject.terraviva.user.controllers;

import dev.dmgiangi.budssecurity.authorizations.annotations.Public;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HomeController class.
 *
 * @author giangi
 * @version $Id: $Id
 */
@RestController
@RequestMapping
@AllArgsConstructor
public class HomeController {
    /**
     * securedEndpoint.
     *
     * @return a {@link java.lang.String} object
     */
    @GetMapping(value = "protected", produces = MediaType.TEXT_HTML_VALUE)
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

    @Public
    @GetMapping(value = "public", produces = MediaType.TEXT_HTML_VALUE)
    public String publicEndpoint() {
        return
                """
                        <html>
                            <header><title>Welcome</title></header>
                            <body>
                                <h1>Welcome to the public endpoint!</h1>
                            </body>
                        </html>""";
    }
}
