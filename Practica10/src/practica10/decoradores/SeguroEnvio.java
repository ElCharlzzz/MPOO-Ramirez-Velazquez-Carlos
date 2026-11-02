package practica10.decoradores;

import practica10.dominio.ServicioEnvio;

public class SeguroEnvio extends ServicioAdicionalDecorator {
    public SeguroEnvio(ServicioEnvio envio) {
        super(envio);
    }

    @Override
    public double calcularCosto() {
        double costoAnterior = super.calcularCosto();
        return costoAnterior + (costoAnterior * 0.10);
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Seguro";
    }
}