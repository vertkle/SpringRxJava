package com.reactivox.springrxjava.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link com.reactivox.springrxjava.model.Perro}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PerroDto implements Serializable {
    private Integer id;
    @NotNull
    private String nombrePerro;
    @NotNull
    @Min(value = 0)
    private Integer edad;
    @NotNull
    private RazaDto raza;
}