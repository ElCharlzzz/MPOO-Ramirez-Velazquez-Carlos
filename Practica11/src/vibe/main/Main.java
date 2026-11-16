package vibe.main;

import vibe.modelo.*;
import vibe.servicio.Feed;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        
        Locale.setDefault(Locale.US);

        List<Publicable> publicaciones = new ArrayList<>();

        publicaciones.add(new PublicacionTexto(
                1L, "Ana", "Java", 100, 20, 10, "Aprendiendo polimorfismo en Java"
        ));
        
        publicaciones.add(new PublicacionImagen(
                2L, "Luis", "Viaje", 100, 20, 10, "Foto del viaje", FormatoImagen.JPG
        ));
        
        publicaciones.add(new PublicacionVideo(
                3L, "Maria", "Tutorial", 50, 10, 5, 180, 5000
        ));

        Feed feed = new Feed();

        List<ResultadoPublicacion> resultados = feed.procesar(publicaciones);

        for (ResultadoPublicacion res : resultados) {
            System.out.printf("%d|%s|%.2f|%s%n",
                    res.getIdPublicacion(), res.getDescripcion(), 
                    res.getRelevancia(), res.getEstado());
        }

        System.out.println("RANKING");
        List<ResultadoPublicacion> ranking = feed.generarRanking();
        for (ResultadoPublicacion res : ranking) {
            System.out.printf("%d|%.2f|%s%n",
                    res.getIdPublicacion(), res.getRelevancia(), res.getEstado());
        }

        System.out.println("VIRALES");
        List<ResultadoPublicacion> virales = feed.obtenerVirales();
        for (ResultadoPublicacion res : virales) {
            System.out.printf("%d|%s|%.2f%n",
                    res.getIdPublicacion(), res.getDescripcion(), res.getRelevancia());
        }
    }
}