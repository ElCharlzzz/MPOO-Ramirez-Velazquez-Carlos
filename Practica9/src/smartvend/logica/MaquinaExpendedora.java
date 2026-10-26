package smartvend.logica;

import java.util.Optional;
import smartvend.dominio.*;
import smartvend.dtos.*;
import smartvend.enums.*;
import smartvend.interfaces.*;

public class MaquinaExpendedora implements MaquinaDespachadora {
    private final Inventario inventario;
    private final Monedero monedero;

    private MaquinaExpendedora(Builder builder) {
        this.inventario = builder.inventario;
        this.monedero = builder.monedero;
    }

    @Override
    public ResultadoRegistro registrarProducto(RegistroProductoDTO dto) {
        Producto producto;
        String nombreLower = dto.nombre() != null ? dto.nombre().toLowerCase() : "";
        
        if (dto.categoria() == CategoriaProducto.BEBIDA) {
            if (nombreLower.contains("jugo")) {
                producto = new Jugo(dto.codigo(), dto.nombre(), dto.fechaCaducidad());
            } else {
                producto = new Refresco(dto.codigo(), dto.nombre(), dto.fechaCaducidad());
            }
        } else {
            if (nombreLower.contains("galleta") || nombreLower.contains("oreo")) {
                producto = new Galleta(dto.codigo(), dto.nombre(), dto.fechaCaducidad());
            } else {
                producto = new Fritura(dto.codigo(), dto.nombre(), dto.fechaCaducidad());
            }
        }
        
        return inventario.registrarProducto(producto, dto.precio());
    }

    @Override
    public Optional<ProductoInventario> buscarProducto(String codigo) {
        return inventario.buscarProducto(codigo);
    }

    @Override
    public ResultadoReabastecimiento reabastecer(ReabastecimientoDTO solicitud) {
        return inventario.reabastecer(solicitud.codigo(), solicitud.cantidad());
    }

    @Override
    public ResultadoCompra comprar(CompraDTO solicitud) {
        Optional<ProductoInventario> optProd = inventario.buscarProducto(solicitud.codigoProducto());
        
        if (optProd.isEmpty()) {
            return new ResultadoCompra(EstadoCompra.PRODUCTO_NO_ENCONTRADO, null, 0.0);
        }
        
        ProductoInventario pi = optProd.get();
        if (pi.existencias() <= 0) {
            return new ResultadoCompra(EstadoCompra.SIN_EXISTENCIAS, pi.producto(), 0.0);
        }

        double precio = pi.precio();
        if (solicitud.cantidadRecibida() < precio) {
            return new ResultadoCompra(EstadoCompra.PAGO_INSUFICIENTE, pi.producto(), 0.0);
        }
        
        double cambioEsperado = solicitud.cantidadRecibida() - precio;
        if (monedero.consultarFondos() < cambioEsperado) {
            return new ResultadoCompra(EstadoCompra.SIN_FONDOS_SUFICIENTES, pi.producto(), 0.0);
        }

        inventario.despachar(pi.producto().getCodigo());
        ResultadoCobro cobro = monedero.cobrar(precio, solicitud.cantidadRecibida());
        
        return new ResultadoCompra(EstadoCompra.VENTA_REALIZADA, pi.producto(), cobro.cambio());
    }

    @Override
    public double consultarFondos() {
        return monedero.consultarFondos();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Inventario inventario;
        private Monedero monedero;

        public Builder inventario(Inventario inventario) {
            this.inventario = inventario;
            return this;
        }

        public Builder monedero(Monedero monedero) {
            this.monedero = monedero;
            return this;
        }

        public MaquinaExpendedora build() {
            if (this.inventario == null || this.monedero == null) {
                throw new IllegalStateException("Faltan dependencias");
            }
            return new MaquinaExpendedora(this);
        }
    }
}