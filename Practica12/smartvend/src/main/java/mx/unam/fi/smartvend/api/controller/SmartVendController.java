package mx.unam.fi.smartvend.api.controller;

import java.util.Optional;
import mx.unam.fi.smartvend.api.dto.*;
import mx.unam.fi.smartvend.application.service.MaquinaExpendedoraService;
import mx.unam.fi.smartvend.domain.compra.ResultadoCompra;
import mx.unam.fi.smartvend.domain.inventario.*;
import mx.unam.fi.smartvend.domain.producto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SmartVendController {

    private final MaquinaExpendedoraService maquinaService;

    @Autowired
    public SmartVendController(MaquinaExpendedoraService maquinaService) {
        this.maquinaService = maquinaService;
    }

    @PostMapping("/productos")
    public ResponseEntity<ResultadoRegistro> registrarProducto(@RequestBody RegistroProductoRequest req) {
        Producto producto;
        if ("BEBIDA".equalsIgnoreCase(req.categoria())) {
            producto = req.nombre().toLowerCase().contains("jugo") 
                       ? new Jugo(req.codigo(), req.nombre(), req.fechaCaducidad())
                       : new Refresco(req.codigo(), req.nombre(), req.fechaCaducidad());
        } else {
            producto = new Fritura(req.codigo(), req.nombre(), req.fechaCaducidad()); // Simplificado
        }
        return ResponseEntity.ok(maquinaService.registrarProducto(producto, req.precio()));
    }

    @GetMapping("/productos/{codigo}")
    public ResponseEntity<ProductoInventario> buscarProducto(@PathVariable String codigo) {
        Optional<ProductoInventario> producto = maquinaService.buscarProducto(codigo);
        return producto.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/productos/{codigo}/existencias")
    public ResponseEntity<ResultadoReabastecimiento> reabastecer(@PathVariable String codigo, @RequestBody ReabastecimientoRequest req) {
        return ResponseEntity.ok(maquinaService.reabastecer(codigo, req.cantidad()));
    }

    @PostMapping("/producto/{codigo}/comprar")
    public ResponseEntity<ResultadoCompra> comprar(@PathVariable String codigo, @RequestBody CompraRequest req) {
        return ResponseEntity.ok(maquinaService.comprar(codigo, req.cantidadRecibida(), req.metodoPago()));
    }

    @GetMapping("/fondos")
    public ResponseEntity<FondosResponse> consultarFondos() {
        double efectivo = maquinaService.getFondosEfectivo();
        double tarjeta = maquinaService.getFondosTarjeta();
        return ResponseEntity.ok(new FondosResponse(efectivo, tarjeta, efectivo + tarjeta));
    }
}