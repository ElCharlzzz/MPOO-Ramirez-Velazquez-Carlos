package smartvend.interfaces;

import java.util.Optional;
import smartvend.dtos.*;
import smartvend.enums.*;

public interface MaquinaDespachadora {
    ResultadoRegistro registrarProducto(RegistroProductoDTO producto);
    Optional<ProductoInventario> buscarProducto(String codigo);
    ResultadoReabastecimiento reabastecer(ReabastecimientoDTO solicitud);
    ResultadoCompra comprar(CompraDTO solicitud);
    double consultarFondos();
}
