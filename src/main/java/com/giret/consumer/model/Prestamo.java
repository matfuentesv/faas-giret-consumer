package com.giret.consumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PRESTAMO", schema = "ADMIN")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prestamo_seq")
    @SequenceGenerator(name = "prestamo_seq", sequenceName = "ADMIN.prestamo_SEQ", allocationSize = 1)
    @Column(name = "IDPRESTAMO", nullable = false)
    @JsonProperty("idPrestamo")
    private Long idPrestamo;

    @Column(name = "RECURSO_IDRECURSO", nullable = false)
    @JsonProperty("recursoId")
    private Long recursoId;

    @Column(name = "FECHAPRESTAMO", nullable = false, length = 50)
    @JsonProperty("fechaPrestamo")
    private String fechaPrestamo;

    @Column(name = "FECHADEVOLUCION", nullable = false, length = 50)
    @JsonProperty("fechaDevolucion")
    private String fechaDevolucion;

    @Column(name = "SOLICITANTE", nullable = false, length = 50)
    @JsonProperty("solicitante")
    private String solicitante;

    @Column(name = "ESTADO", nullable = false, length = 50)
    @JsonProperty("estado")
    private String estado;


}
