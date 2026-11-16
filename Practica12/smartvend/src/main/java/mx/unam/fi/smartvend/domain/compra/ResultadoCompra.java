package mx.unam.fi.smartvend.domain.compra;
import mx.unam.fi.smartvend.domain.producto.Producto;
public record ResultadoCompra(EstadoCompra estado, Producto producto, double cambio) {}