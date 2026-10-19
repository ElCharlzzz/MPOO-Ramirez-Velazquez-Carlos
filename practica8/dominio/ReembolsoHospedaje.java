package practica8.dominio;

import java.time.LocalDate;

public class ReembolsoHospedaje extends Reembolso {

    public ReembolsoHospedaje(String folio, String empleado, double monto, LocalDate fechaGasto, 
                              boolean tieneComprobante, boolean autorizadoGerente) {
        super(folio, empleado, monto, fechaGasto, tieneComprobante, autorizadoGerente);
    }

    public ReembolsoHospedaje(String folio, String empleado, double monto) {
        super(folio, empleado, monto);
    }

    @Override
    public double getLimitePermitido() {
        return 4000.0;
    }
}