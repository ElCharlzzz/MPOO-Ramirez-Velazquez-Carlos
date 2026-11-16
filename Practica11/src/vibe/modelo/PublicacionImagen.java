package vibe.modelo;

public class PublicacionImagen extends Publicacion {
    private final String descripcionImagen;
    private final FormatoImagen formato;

    public PublicacionImagen(Long id, String autor, String titulo, int meGusta, 
                             int comentarios, int compartidos, String descripcionImagen, 
                             FormatoImagen formato) {
        super(id, autor, titulo, meGusta, comentarios, compartidos);
        this.descripcionImagen = descripcionImagen;
        this.formato = formato;
    }

    public String getDescripcionImagen() {
        return descripcionImagen;
    }

    public FormatoImagen getFormato() {
        return formato;
    }

    @Override
    protected boolean validarContenido() {
        return descripcionImagen != null && !descripcionImagen.trim().isEmpty() && formato != null;
    }

    @Override
    protected double calcularRelevancia() {
        return (meGusta * 2) + (comentarios * 3) + (compartidos * 5);
    }

    @Override
    protected String getDescripcion() {
        return "Imagen | " + getTitulo() + " | " + formato.name() + " | " + getAutor();
    }
}