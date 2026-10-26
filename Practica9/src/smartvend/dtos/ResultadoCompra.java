package smartvend.dtos;

import smartvend.enums.EstadoCompra;
import smartvend.dominio.Producto;

public record ResultadoCompra(EstadoCompra estado, Producto producto, double cambio) {}
