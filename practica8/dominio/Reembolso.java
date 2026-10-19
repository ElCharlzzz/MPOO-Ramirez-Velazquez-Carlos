package practica8.dominio;

import java.time.LocalDate;

public abstract class Reembolso {
    protected String folio;
    protected String empleado;
    protected double monto;
    protected LocalDate fechaGasto;
    protected boolean tieneComprobante;
    protected boolean autorizadoGerente;

    public Reembolso(String folio, String empleado, double monto, LocalDate fechaGasto, 
                     boolean tieneComprobante, boolean autorizadoGerente) {
        this.folio = folio;
        this.empleado = empleado;
        this.monto = monto;
        this.fechaGasto = fechaGasto;
        this.tieneComprobante = tieneComprobante;
        this.autorizadoGerente = autorizadoGerente;
    }

    public Reembolso(String folio, String empleado, double monto) {
        this(folio, empleado, monto, LocalDate.now(), true, false);
    }

    public abstract double getLimitePermitido();

    public String getFolio() { return folio; }
    public String getEmpleado() { return empleado; }
    public double getMonto() { return monto; }
    public LocalDate getFechaGasto() { return fechaGasto; }
    public boolean isTieneComprobante() { return tieneComprobante; }
    public boolean isAutorizadoGerente() { return autorizadoGerente; }

    @Override
    public String toString() {
        return String.format("[%s] Folio: %s | Empleado: %s | Monto: $%.2f | Fecha: %s | Comprobante: %s | Auth. Gerente: %s",
                this.getClass().getSimpleName(), folio, empleado, monto, fechaGasto, 
                tieneComprobante ? "Si" : "No", autorizadoGerente ? "Si" : "No");
    }
}