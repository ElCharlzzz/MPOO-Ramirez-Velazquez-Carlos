package smartvend.interfaces;

import smartvend.dtos.ResultadoCobro;

public interface Monedero {
    ResultadoCobro cobrar(double precio, double cantidadRecibida);
    double consultarFondos();
}