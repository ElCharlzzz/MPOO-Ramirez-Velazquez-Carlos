package mx.unam.fi.smartvend.application.service;

import java.util.Optional;
import mx.unam.fi.smartvend.domain.compra.*;
import mx.unam.fi.smartvend.domain.inventario.*;
import mx.unam.fi.smartvend.domain.pago.*;
import mx.unam.fi.smartvend.domain.producto.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MaquinaExpendedoraService {

    private final Inventario inventario;
    private final Monedero monederoEfectivo;
    private final Monedero monederoTarjeta;

    @Autowired
    public MaquinaExpendedoraService(Inventario inventario,
                                     @Qualifier("monederoEfectivo") Monedero monederoEfectivo,
                                     @Qualifier("monederoTarjeta") Monedero monederoTarjeta) {
        this.inventario = inventario;
        this.monederoEfectivo = monederoEfectivo;
        this.monederoTarjeta = monederoTarjeta;
    }

    public ResultadoRegistro registrarProducto(Producto producto, double precio) {
        return inventario.registrarProducto(producto, precio);
    }

    public Optional<ProductoInventario> buscarProducto(String codigo) {
        return inventario.buscarProducto(codigo);
    }

    public ResultadoReabastecimiento reabastecer(String codigo, int cantidad) {
        return inventario.reabastecer(codigo, cantidad);
    }

    // El flujo de compra se abstrae para soportar ambos monederos sin duplicar código
    public ResultadoCompra comprar(String codigo, double cantidadRecibida, MetodoPago metodoPago) {
        Optional<ProductoInventario> optProd = inventario.buscarProducto(codigo);
        if (optProd.isEmpty()) return new ResultadoCompra(EstadoCompra.PRODUCTO_NO_ENCONTRADO, null, 0.0);
        
        ProductoInventario pi = optProd.get();
        if (pi.existencias() <= 0) return new ResultadoCompra(EstadoCompra.SIN_EXISTENCIAS, pi.producto(), 0.0);

        Monedero monederoSeleccionado;
        if (metodoPago == MetodoPago.EFECTIVO) {
            monederoSeleccionado = monederoEfectivo;
            // Solo efectivo necesita validar cambio
            if (monederoSeleccionado.consultarFondos() < (cantidadRecibida - pi.precio())) {
                 return new ResultadoCompra(EstadoCompra.SIN_FONDOS_SUFICIENTES, pi.producto(), 0.0);
            }
        } else if (metodoPago == MetodoPago.TARJETA) {
            monederoSeleccionado = monederoTarjeta;
        } else {
            return new ResultadoCompra(EstadoCompra.MONEDERO_NO_SOPORTADO, pi.producto(), 0.0);
        }

        if (cantidadRecibida < pi.precio()) return new ResultadoCompra(EstadoCompra.PAGO_INSUFICIENTE, pi.producto(), 0.0);

        inventario.despachar(pi.producto().getCodigo()); // Invariante protegida
        ResultadoCobro cobro = monederoSeleccionado.cobrar(pi.precio(), cantidadRecibida);
        return new ResultadoCompra(EstadoCompra.VENTA_REALIZADA, pi.producto(), cobro.cambio());
    }

    public double getFondosEfectivo() { return monederoEfectivo.consultarFondos(); }
    public double getFondosTarjeta() { return monederoTarjeta.consultarFondos(); }
}