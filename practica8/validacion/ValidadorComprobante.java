package practica8.validacion;

import practica8.dominio.Reembolso;

public class ValidadorComprobante extends ValidadorReembolso {

    @Override
    public ResultadoValidacion validar(Reembolso reembolso) {
        if (!reembolso.isTieneComprobante()) {
            return ResultadoValidacion.SIN_COMPROBANTE;
        }
        return pasarAlSiguiente(reembolso);
    }
}