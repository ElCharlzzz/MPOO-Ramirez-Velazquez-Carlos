package vibe.servicio;

import vibe.modelo.EstadoPublicacion;
import vibe.modelo.Publicable;
import vibe.modelo.ResultadoPublicacion;

import java.util.ArrayList;
import java.util.List;

public class Feed {
    private final List<ResultadoPublicacion> publicacionesProcesadas = new ArrayList<>();

    public ResultadoPublicacion procesar(Publicable publicacion) {
        ResultadoPublicacion resultado = publicacion.publicar();
        publicacionesProcesadas.add(resultado);
        return resultado;
    }

    public List<ResultadoPublicacion> procesar(List<Publicable> publicaciones) {
        List<ResultadoPublicacion> resultados = new ArrayList<>();
        for (Publicable p : publicaciones) {
            resultados.add(procesar(p));
        }
        return resultados;
    }

    public List<ResultadoPublicacion> obtenerVirales() {
        List<ResultadoPublicacion> virales = new ArrayList<>();
        for (ResultadoPublicacion resultado : publicacionesProcesadas) {
            if (resultado.getEstado() == EstadoPublicacion.VIRAL) {
                virales.add(resultado);
            }
        }
        return virales;
    }

    public List<ResultadoPublicacion> generarRanking() {
        List<ResultadoPublicacion> ranking = new ArrayList<>(publicacionesProcesadas);
        ranking.sort((r1, r2) -> Double.compare(r2.getRelevancia(), r1.getRelevancia()));
        return ranking;
    }
}