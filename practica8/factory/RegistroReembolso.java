package practica8.factory;

import java.time.LocalDate;
import practica8.dominio.Reembolso;

public abstract class RegistroReembolso {

    public abstract Reembolso crearReembolso(String folio, String empleado, double monto, 
                                             LocalDate fechaGasto, boolean tieneComprobante, 
                                             boolean autorizadoGerente);

    public abstract Reembolso crearReembolso(String folio, String empleado, double monto);
}