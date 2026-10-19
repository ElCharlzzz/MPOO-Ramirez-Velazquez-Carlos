package practica8.validacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import practica8.dominio.Reembolso;

public class ValidadorPeriodo extends ValidadorReembolso {
    private final LocalDate fechaEvaluacion;

    public ValidadorPeriodo() {
        this.fechaEvaluacion = LocalDate.now();
    }

    public ValidadorPeriodo(LocalDate fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    @Override
    public ResultadoValidacion validar(Reembolso reembolso) {
        long diasTranscurridos = ChronoUnit.DAYS.between(reembolso.getFechaGasto(), fechaEvaluacion);
        if (diasTranscurridos > 30) {
            return ResultadoValidacion.FUERA_DE_PERIODO;
        }
        return pasarAlSiguiente(reembolso);
    }
}