package vibe.modelo;

public class PublicacionTexto extends Publicacion {
    private final String texto;

    public PublicacionTexto(Long id, String autor, String titulo, int meGusta, 
                            int comentarios, int compartidos, String texto) {
        super(id, autor, titulo, meGusta, comentarios, compartidos);
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    @Override
    protected boolean validarContenido() {
        return texto != null && texto.length() >= 10 && texto.length() <= 280;
    }

    @Override
    protected double calcularRelevancia() {
        return meGusta + (comentarios * 3) + (compartidos * 5);
    }

    @Override
    protected String getDescripcion() {
        return "Texto | " + getTitulo() + " | " + getAutor();
    }
}