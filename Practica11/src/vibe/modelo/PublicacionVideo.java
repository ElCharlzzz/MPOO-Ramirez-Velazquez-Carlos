package vibe.modelo;

public class PublicacionVideo extends Publicacion {
    private final int duracionSegundos;
    private int visualizaciones;

    public PublicacionVideo(Long id, String autor, String titulo, int meGusta, 
                            int comentarios, int compartidos, int duracionSegundos, 
                            int visualizaciones) {
        super(id, autor, titulo, meGusta, comentarios, compartidos);
        this.duracionSegundos = duracionSegundos;
        this.visualizaciones = visualizaciones;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public int getVisualizaciones() {
        return visualizaciones;
    }

    public void registrarVisualizacion() {
        this.visualizaciones++;
    }

    @Override
    protected boolean validarContenido() {
        return duracionSegundos > 0 && duracionSegundos <= 600;
    }

    @Override
    protected double calcularRelevancia() {
        return (meGusta * 2) + (comentarios * 4) + (compartidos * 6) + (visualizaciones / 100.0);
    }

    @Override
    protected String getDescripcion() {
    
        return "Video | " + getTitulo() + " | " + duracionSegundos + " s | " + getAutor();
    }
}