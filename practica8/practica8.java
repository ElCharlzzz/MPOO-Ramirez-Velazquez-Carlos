package practica8;

import java.time.LocalDate;
import practica8.dominio.Reembolso;
import practica8.factory.RegistroAlimentos;
import practica8.factory.RegistroHospedaje;
import practica8.factory.RegistroReembolso;
import practica8.factory.RegistroTransporte;
import practica8.validacion.ResultadoValidacion;
import practica8.validacion.ValidadorComprobante;
import practica8.validacion.ValidadorGerencia;
import practica8.validacion.ValidadorLimite;
import practica8.validacion.ValidadorPeriodo;
import practica8.validacion.ValidadorReembolso;

public class practica8 {

    public static void main(String[] args) {
        
        RegistroReembolso fabAlimentos = new RegistroAlimentos();
        RegistroReembolso fabTransporte = new RegistroTransporte();
        RegistroReembolso fabHospedaje = new RegistroHospedaje();

        LocalDate hoy = LocalDate.now();
        ValidadorReembolso valComprobante = new ValidadorComprobante();
        ValidadorReembolso valPeriodo = new ValidadorPeriodo(hoy);
        ValidadorReembolso valLimite = new ValidadorLimite();
        ValidadorReembolso valGerencia = new ValidadorGerencia();

        valComprobante.setSiguiente(valPeriodo);
        valPeriodo.setSiguiente(valLimite);
        valLimite.setSiguiente(valGerencia);

        Reembolso[] solicitudes = {
            
            fabHospedaje.crearReembolso("R-001", "Ana Torres", 3500.0, hoy.minusDays(8), true, true),

            fabTransporte.crearReembolso("R-002", "Carlos Ruiz", 800.0, hoy.minusDays(5), false, false),

            fabAlimentos.crearReembolso("R-003", "Laura Diaz", 600.0, hoy.minusDays(40), true, false),

            fabAlimentos.crearReembolso("R-004", "Pedro Gomez", 1250.0, hoy.minusDays(3), true, true),

            fabHospedaje.crearReembolso("R-005", "Sofia Vargas", 2800.0, hoy.minusDays(10), true, false),

            fabAlimentos.crearReembolso("R-006", "Miguel Angel", 450.0)
        };

        System.out.println("=== SISTEMA CORPORATIVO DE REEMBOLSOS NOVACORP ===\n");
        for (Reembolso solicitud : solicitudes) {
            ResultadoValidacion resultado = valComprobante.validar(solicitud);
            System.out.println(solicitud);
            System.out.println("-> Limite de categoria: $" + solicitud.getLimitePermitido());
            System.out.println("-> RESULTADO DE VALIDACIoN: " + resultado);
            System.out.println("---------------------------------------------------------------------------------------");
        }
    }
}