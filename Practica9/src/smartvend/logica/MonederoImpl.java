package smartvend.logica;

import smartvend.dtos.ResultadoCobro;
import smartvend.enums.EstadoCobro;
import smartvend.interfaces.Monedero;

public class MonederoImpl implements Monedero {
    private double fondos;

    public MonederoImpl(double fondosIniciales) {
        this.fondos = Math.max(0, fondosIniciales);
    }

    @Override
    public ResultadoCobro cobrar(double precio, double cantidadRecibida) {
        if (cantidadRecibida < precio) {
            return new ResultadoCobro(EstadoCobro.MONTO_INVALIDO, 0.0);
        }
        double cambio = cantidadRecibida - precio;
        if (fondos < cambio) {
            return new ResultadoCobro(EstadoCobro.SIN_FONDOS_SUFICIENTES, 0.0);
        }
        fondos += precio; 
        return new ResultadoCobro(EstadoCobro.COBRO_REALIZADO, cambio);
    }

    @Override
    public double consultarFondos() {
        return fondos;
    }
}
