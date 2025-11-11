package com.bookmx.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Reservacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Hotel hotel;

    @NotBlank @Column(nullable = false, length = 160)
    private String nombreHuesped;

    @Email @NotBlank @Column(nullable = false, length = 160)
    private String email;

    @Positive @Column(nullable = false)
    private int numPersonas;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;
}
