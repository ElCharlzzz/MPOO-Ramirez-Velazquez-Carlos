package practica8.dominio;

import java.time.LocalDate;

public class ReembolsoAlimentos extends Reembolso {

    public ReembolsoAlimentos(String folio, String empleado, double monto, LocalDate fechaGasto, 
                              boolean tieneComprobante, boolean autorizadoGerente) {
        super(folio, empleado, monto, fechaGasto, tieneComprobante, autorizadoGerente);
    }

    public ReembolsoAlimentos(String folio, String empleado, double monto) {
        super(folio, empleado, monto);
    }

    @Override
    public double getLimitePermitido() {
        return 1000.0;
    }
}