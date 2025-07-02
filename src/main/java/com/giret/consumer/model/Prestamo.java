package com.giret.consumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;




public class Prestamo {


    private Long idPrestamo;
    private Long recursoId;
    private String fechaPrestamo;
    private String fechaDevolucion;

    private String solicitante;
    private String estado;

}
