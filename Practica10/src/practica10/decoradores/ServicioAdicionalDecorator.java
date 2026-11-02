package practica10.decoradores;

import practica10.dominio.ServicioEnvio;

public abstract class ServicioAdicionalDecorator implements ServicioEnvio {
    protected ServicioEnvio envio;

    public ServicioAdicionalDecorator(ServicioEnvio envio) {
        this.envio = envio;
    }

    @Override
    public double calcularCosto() {
        return envio.calcularCosto();
    }

    @Override
    public String getDescripcion() {
        return envio.getDescripcion();
    }
}