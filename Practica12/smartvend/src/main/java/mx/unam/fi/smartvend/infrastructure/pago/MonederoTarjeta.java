package mx.unam.fi.smartvend.infrastructure.pago;

import mx.unam.fi.smartvend.domain.pago.*;

public class MonederoTarjeta implements Monedero {
    private double fondos;

    public MonederoTarjeta(double fondosIniciales) {
        this.fondos = Math.max(0, fondosIniciales);
    }

    @Override
    public ResultadoCobro cobrar(double precio, double cantidadRecibida) {
        // En tarjeta, el cobro es exacto y no genera cambio.
        if (cantidadRecibida < precio) return new ResultadoCobro(EstadoCobro.MONTO_INVALIDO, 0.0);
        fondos += precio; // Solo suma el costo del producto.
        return new ResultadoCobro(EstadoCobro.COBRO_REALIZADO, 0.0); // 0.0 de cambio.
    }

    @Override
    public double consultarFondos() { return fondos; }
}