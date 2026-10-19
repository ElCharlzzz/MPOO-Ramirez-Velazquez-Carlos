package practica8.validacion;

import practica8.dominio.Reembolso;

public class ValidadorLimite extends ValidadorReembolso {

    @Override
    public ResultadoValidacion validar(Reembolso reembolso) {
        if (reembolso.getMonto() > reembolso.getLimitePermitido()) {
            return ResultadoValidacion.EXCEDE_LIMITE;
        }
        return pasarAlSiguiente(reembolso);
    }
}