package practica10.envios;

import practica10.dominio.Envio;

public class EnvioAereo extends Envio {
    public EnvioAereo(String destinatario, double pesoKg, double distanciaKm) {
        super(destinatario, pesoKg, distanciaKm);
    }

    @Override
    public double calcularCosto() {
        return (distanciaKm * 1.20) + (pesoKg * 5);
    }

    @Override
    public String getDescripcion() {
        return "Envio aereo";
    }
}