package com.giret.consumer.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resource {

    private Long idRecurso;
    private String modelo;
    private String descripcion;
    private String numeroSerie;
    private String fechaCompra;
    private String fechaVencimientoGarantia;
    private String emailUsuario;
    private String estado;
    private String categoria;



    @Override
    public String toString() {
        return "Resource{" +
                "idRecurso=" + idRecurso +
                ", modelo='" + modelo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", numeroSerie='" + numeroSerie + '\'' +
                ", fechaCompra='" + fechaCompra + '\'' +
                ", fechaVencimientoGarantia='" + fechaVencimientoGarantia + '\'' +
                ", emailUsuario='" + emailUsuario + '\'' +
                ", estado='" + estado + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
