package vibe.modelo;

import java.time.LocalDateTime;

public class ResultadoPublicacion {
    private final Long idPublicacion;
    private final String autor;
    private final String descripcion;
    private final double relevancia;
    private final EstadoPublicacion estado;
    private final LocalDateTime fechaProcesamiento;

    public ResultadoPublicacion(Long idPublicacion, String autor, String descripcion, 
                                double relevancia, EstadoPublicacion estado, 
                                LocalDateTime fechaProcesamiento) {
        this.idPublicacion = idPublicacion;
        this.autor = autor;
        this.descripcion = descripcion;
        this.relevancia = relevancia;
        this.estado = estado;
        this.fechaProcesamiento = fechaProcesamiento;
    }

    public Long getIdPublicacion() { return idPublicacion; }
    public String getAutor() { return autor; }
    public String getDescripcion() { return descripcion; }
    public double getRelevancia() { return relevancia; }
    public EstadoPublicacion getEstado() { return estado; }
    public LocalDateTime getFechaProcesamiento() { return fechaProcesamiento; }
}