package com.reactivox.springrxjava.model.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.reactivox.springrxjava.model.Raza}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RazaDto implements Serializable {
    private Integer id;
    private String descripción;
}