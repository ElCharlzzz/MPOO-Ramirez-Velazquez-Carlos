package smartvend.dtos;

import smartvend.dominio.Producto;

public record ProductoInventario(Producto producto, double precio, int existencias) {}
