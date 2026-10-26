package smartvend;

import java.time.LocalDate;
import smartvend.dtos.*;
import smartvend.enums.*;
import smartvend.interfaces.*;
import smartvend.logica.*;

public class Main {
    public static void main(String[] args) {
        Inventario inventario = new InventarioImpl();
        Monedero monedero = new MonederoImpl(100.0);
        
        MaquinaDespachadora maquina = MaquinaExpendedora.builder()
                .inventario(inventario)
                .monedero(monedero)
                .build();
        
        System.out.println("--- INICIANDO SMARTVEND ---");
        System.out.println("Fondos iniciales: $" + maquina.consultarFondos());

        RegistroProductoDTO nuevoProducto = new RegistroProductoDTO(
                "A01", "Coca Cola", LocalDate.of(2027, 6, 30), CategoriaProducto.BEBIDA, 22.50
        );
        System.out.println("\nRegistrando producto: " + maquina.registrarProducto(nuevoProducto));

        ReabastecimientoDTO reabastecimiento = new ReabastecimientoDTO("A01", 1);
        System.out.println("Reabasteciendo: " + maquina.reabastecer(reabastecimiento));

        System.out.println("\n--- PRUEBA: COMPRA CORRECTA ---");
        CompraDTO compraExitosa = new CompraDTO("A01", 50.00); 
        ResultadoCompra resultadoExitoso = maquina.comprar(compraExitosa);
        System.out.println("Estado: " + resultadoExitoso.estado());
        System.out.println("Producto entregado: " + resultadoExitoso.producto().getNombre());
        System.out.println("Cambio devuelto: $" + resultadoExitoso.cambio());
        System.out.println("Fondos actuales máquina: $" + maquina.consultarFondos());

        System.out.println("\n--- PRUEBA: COMPRA RECHAZADA ---");
        CompraDTO compraRechazada = new CompraDTO("A01", 50.00);
        ResultadoCompra resultadoRechazado = maquina.comprar(compraRechazada);
        System.out.println("Estado: " + resultadoRechazado.estado());
        System.out.println("Fondos actuales máquina: $" + maquina.consultarFondos());
    }
}