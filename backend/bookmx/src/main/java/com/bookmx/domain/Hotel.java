package com.bookmx.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Hotel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(nullable = false, length = 160)
    private String nombre;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ciudad ciudad;

    @Min(1) @Max(5) @Column(nullable = false)
    private int estrellas;

    @Positive @Column(nullable = false)
    private double precioPorNoche;
}
