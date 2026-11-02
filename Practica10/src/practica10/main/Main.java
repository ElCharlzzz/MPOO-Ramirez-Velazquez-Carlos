package practica10.main;

import practica10.dominio.ServicioEnvio;
import practica10.envios.EnvioTerrestre;
import practica10.envios.EnvioAereo;
import practica10.decoradores.SeguroEnvio;
import practica10.decoradores.EntregaExpress;
import practica10.centro.CentroEnvios;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ServicioEnvio terrestre = new EnvioTerrestre("Ana", 10, 100);
        ServicioEnvio aereo = new EnvioAereo("Luis", 5, 200);

        terrestre = new SeguroEnvio(terrestre);
        terrestre = new EntregaExpress(terrestre);

        aereo = new EntregaExpress(aereo);

        List<ServicioEnvio> envios = new ArrayList<>();
        envios.add(terrestre);
        envios.add(aereo);

        CentroEnvios centro = new CentroEnvios();
        List<String> resultados = centro.procesar(envios);

        System.out.println("--- Resultados de LogiPack ---");
        for (String resultado : resultados) {
            System.out.println(resultado);
        }
    }
}