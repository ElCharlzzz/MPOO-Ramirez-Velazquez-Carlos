package mx.unam.fi.smartvend.domain.producto;

import java.time.LocalDate;

public class Jugo extends Producto {
    
    public Jugo(String codigo, String nombre, LocalDate fechaCaducidad) {
        super(codigo, nombre, fechaCaducidad, CategoriaProducto.BEBIDA);
    }
    
    @Override
    protected String getTipoEmbalaje() { 
        return "Tetrapack"; 
    }
}