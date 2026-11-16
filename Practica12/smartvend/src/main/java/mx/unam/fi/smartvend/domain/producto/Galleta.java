package mx.unam.fi.smartvend.domain.producto;

import java.time.LocalDate;

public class Galleta extends Producto {
    
    public Galleta(String codigo, String nombre, LocalDate fechaCaducidad) {
        super(codigo, nombre, fechaCaducidad, CategoriaProducto.SNACK);
    }
    
    @Override
    protected String getTipoEmbalaje() { 
        return "Envoltura plástica"; 
    }
}