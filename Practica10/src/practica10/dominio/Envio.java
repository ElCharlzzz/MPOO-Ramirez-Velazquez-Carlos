package practica10.dominio;

public abstract class Envio implements ServicioEnvio {
    protected String destinatario;
    protected double pesoKg;
    protected double distanciaKm;

    public Envio(String destinatario, double pesoKg, double distanciaKm) {
        this.destinatario = destinatario;
        this.pesoKg = pesoKg;
        this.distanciaKm = distanciaKm;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }
}