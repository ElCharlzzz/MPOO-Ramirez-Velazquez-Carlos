package practica8.validacion;

import practica8.dominio.Reembolso;

public abstract class ValidadorReembolso {
    protected ValidadorReembolso siguiente;

    public void setSiguiente(ValidadorReembolso siguiente) {
        this.siguiente = siguiente;
    }

    public abstract ResultadoValidacion validar(Reembolso reembolso);

    protected ResultadoValidacion pasarAlSiguiente(Reembolso reembolso) {
        if (siguiente != null) {
            return siguiente.validar(reembolso);
        }
        return ResultadoValidacion.APROBADO;
    }
}