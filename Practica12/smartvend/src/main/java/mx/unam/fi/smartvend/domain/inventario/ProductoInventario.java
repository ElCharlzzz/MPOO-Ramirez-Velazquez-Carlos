package mx.unam.fi.smartvend.domain.inventario;
import mx.unam.fi.smartvend.domain.producto.Producto;
public record ProductoInventario(Producto producto, double precio, int existencias) {}