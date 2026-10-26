package smartvend.logica;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import smartvend.dominio.Producto;
import smartvend.dtos.ProductoInventario;
import smartvend.enums.*;
import smartvend.interfaces.Inventario;

public class InventarioImpl implements Inventario {
    private final Map<String, ProductoInventario> almacen = new HashMap<>();

    @Override
    public ResultadoRegistro registrarProducto(Producto producto, double precio) {
        if (producto == null || producto.getCodigo() == null) return ResultadoRegistro.PRODUCTO_INVALIDO;
        if (precio <= 0) return ResultadoRegistro.PRECIO_INVALIDO;
        if (almacen.containsKey(producto.getCodigo())) return ResultadoRegistro.CODIGO_DUPLICADO;

        almacen.put(producto.getCodigo(), new ProductoInventario(producto, precio, 0));
        return ResultadoRegistro.PRODUCTO_REGISTRADO;
    }

    @Override
    public Optional<ProductoInventario> buscarProducto(String codigo) {
        return Optional.ofNullable(almacen.get(codigo));
    }

    @Override
    public ResultadoReabastecimiento reabastecer(String codigo, int cantidad) {
        if (cantidad <= 0) return ResultadoReabastecimiento.CANTIDAD_INVALIDA;
        if (!almacen.containsKey(codigo)) return ResultadoReabastecimiento.PRODUCTO_NO_ENCONTRADO;

        ProductoInventario actual = almacen.get(codigo);
        almacen.put(codigo, new ProductoInventario(actual.producto(), actual.precio(), actual.existencias() + cantidad));
        return ResultadoReabastecimiento.REABASTECIMIENTO_REALIZADO;
    }

    @Override
    public ResultadoEntrega despachar(String codigo) {
        if (!almacen.containsKey(codigo)) return ResultadoEntrega.PRODUCTO_NO_ENCONTRADO;
        
        ProductoInventario actual = almacen.get(codigo);
        if (actual.existencias() <= 0) return ResultadoEntrega.SIN_EXISTENCIAS;

        almacen.put(codigo, new ProductoInventario(actual.producto(), actual.precio(), actual.existencias() - 1));
        return ResultadoEntrega.PRODUCTO_PREPARADO;
    }
}