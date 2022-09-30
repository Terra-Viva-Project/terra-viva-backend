package com.github.terravivaproject.terraviva.social.entities.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * PostRto class.
 *
 * @author giangi
 * @version $Id: $Id
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class PostRto {

    private Collection<String> tags;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 1000)
    private String message;
}
