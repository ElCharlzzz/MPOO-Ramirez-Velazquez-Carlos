package vibe.modelo;

public interface Publicable {
    ResultadoPublicacion publicar();
    Long getId();
    String getAutor();
}