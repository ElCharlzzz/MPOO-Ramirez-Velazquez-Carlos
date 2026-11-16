package vibe.modelo;

import java.time.LocalDateTime;

public abstract class Publicacion implements Publicable {
    private final Long id;
    private final String autor;
    private final String titulo;
    private final LocalDateTime fechaCreacion;
    protected final int meGusta;
    protected final int comentarios;
    protected final int compartidos;

    protected Publicacion(Long id, String autor, String titulo, int meGusta, 
                          int comentarios, int compartidos) {
        this.id = id;
        this.autor = autor;
        this.titulo = titulo;
        this.fechaCreacion = LocalDateTime.now();
        this.meGusta = meGusta;
        this.comentarios = comentarios;
        this.compartidos = compartidos;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    protected abstract boolean validarContenido();
    protected abstract double calcularRelevancia();
    protected abstract String getDescripcion();

    @Override
    public final ResultadoPublicacion publicar() {
        boolean esValido = validarContenido();
        double relevancia = 0.0;
        EstadoPublicacion estado = EstadoPublicacion.RECHAZADA;

        if (esValido) {
            relevancia = calcularRelevancia();
            if (relevancia < 100) {
                estado = EstadoPublicacion.PUBLICADA;
            } else if (relevancia < 300) {
                estado = EstadoPublicacion.DESTACADA;
            } else {
                estado = EstadoPublicacion.VIRAL;
            }
        }

        return new ResultadoPublicacion(
            getId(), 
            getAutor(), 
            getDescripcion(), 
            relevancia, 
            estado, 
            LocalDateTime.now()
        );
    }
}