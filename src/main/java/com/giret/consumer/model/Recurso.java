package com.giret.consumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "RECURSO", schema = "ADMIN")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recurso_seq")
    @SequenceGenerator(name = "recurso_seq", sequenceName = "ADMIN.recurso_SEQ", allocationSize = 1)
    @Column(name = "IDRECURSO", nullable = false)
    @JsonProperty("idRecurso")
    private Long idRecurso;

    @Column(name = "MODELO", nullable = false, length = 50)
    @JsonProperty("modelo")
    private String modelo;

    @Column(name = "DESCRIPCION", nullable = false, length = 50)
    @JsonProperty("descripcion")
    private String descripcion;

    @Column(name = "NUMEROSERIE", nullable = false, length = 100)
    @JsonProperty("numeroSerie")
    private String numeroSerie;

    @Column(name = "FECHACOMPRA", nullable = false, length = 50)
    @JsonProperty("fechaCompra")
    private String fechaCompra;

    @Column(name = "FECHAVENCIMIENTOGARANTIA", length = 50)
    @JsonProperty("fechaVencimientoGarantia")
    private String fechaVencimientoGarantia;

    @Column(name = "EMAILUSUARIO", nullable = false, length = 50)
    @JsonProperty("emailUsuario")
    private String emailUsuario;

    @Column(name = "ESTADO", nullable = false, length = 50)
    @JsonProperty("estado")
    private String estado;

    @Column(name = "CATEGORIA", nullable = false, length = 50)
    @JsonProperty("categoria")
    private String categoria;

}
