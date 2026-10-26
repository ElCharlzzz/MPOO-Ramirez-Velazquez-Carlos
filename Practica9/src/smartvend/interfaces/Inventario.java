package smartvend.interfaces;

import java.util.Optional;
import smartvend.dominio.Producto;
import smartvend.dtos.ProductoInventario;
import smartvend.enums.*;

public interface Inventario {
    ResultadoRegistro registrarProducto(Producto producto, double precio);
    Optional<ProductoInventario> buscarProducto(String codigo);
    ResultadoReabastecimiento reabastecer(String codigo, int cantidad);
    ResultadoEntrega despachar(String codigo);
}