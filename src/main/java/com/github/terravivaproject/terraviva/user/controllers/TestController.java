package com.github.terravivaproject.terraviva.user.controllers;

import dev.dmgiangi.budssecurity.authorizations.annotations.Public;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Test", description = "Authorization test endpoint")
public class TestController {
    /**
     * securedEndpoint.
     *
     * @return a {@link java.lang.String} object
     */
    @GetMapping("protected")
    @Operation(
            summary = "Secured endpoint",
            description = "test endpoint, NEEDS authentication in order to access",
            security = @SecurityRequirement(name = "basicAuth"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "accessed",
                            content = @Content(
                                    mediaType = MediaType.TEXT_HTML_VALUE))
            })
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

    /**
     * <p>publicEndpoint.</p>
     *
     * @return a {@link java.lang.String} object
     */
    @Public
    @GetMapping("public")
    @Operation(
            summary = "Public endpoint",
            description = "test endpoint, DOES NOT needs authentication in order to access",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "accessed",
                            content = @Content(
                                    mediaType = MediaType.TEXT_HTML_VALUE))
            })
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
