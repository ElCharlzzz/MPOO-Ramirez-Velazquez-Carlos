package practica8.factory;

import java.time.LocalDate;
import practica8.dominio.Reembolso;
import practica8.dominio.ReembolsoHospedaje;

public class RegistroHospedaje extends RegistroReembolso {

    @Override
    public Reembolso crearReembolso(String folio, String empleado, double monto, 
                                    LocalDate fechaGasto, boolean tieneComprobante, boolean autorizadoGerente) {
        return new ReembolsoHospedaje(folio, empleado, monto, fechaGasto, tieneComprobante, autorizadoGerente);
    }

    @Override
    public Reembolso crearReembolso(String folio, String empleado, double monto) {
        return new ReembolsoHospedaje(folio, empleado, monto);
    }
}