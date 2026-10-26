package smartvend.dtos;

import java.time.LocalDate;
import smartvend.enums.CategoriaProducto;

public record RegistroProductoDTO(
    String codigo, 
    String nombre, 
    LocalDate fechaCaducidad, 
    CategoriaProducto categoria, 
    double precio
) {}