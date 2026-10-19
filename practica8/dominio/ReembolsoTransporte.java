package practica8.dominio;

import java.time.LocalDate;

public class ReembolsoTransporte extends Reembolso {

    public ReembolsoTransporte(String folio, String empleado, double monto, LocalDate fechaGasto, 
                               boolean tieneComprobante, boolean autorizadoGerente) {
        super(folio, empleado, monto, fechaGasto, tieneComprobante, autorizadoGerente);
    }

    public ReembolsoTransporte(String folio, String empleado, double monto) {
        super(folio, empleado, monto);
    }

    @Override
    public double getLimitePermitido() {
        return 1500.0;
    }
}