package practica8.validacion;

import practica8.dominio.Reembolso;

public class ValidadorGerencia extends ValidadorReembolso {

    @Override
    public ResultadoValidacion validar(Reembolso reembolso) {
        if (reembolso.getMonto() > 2000.0 && !reembolso.isAutorizadoGerente()) {
            return ResultadoValidacion.REQUIERE_AUTORIZACION;
        }
        return pasarAlSiguiente(reembolso);
    }
}