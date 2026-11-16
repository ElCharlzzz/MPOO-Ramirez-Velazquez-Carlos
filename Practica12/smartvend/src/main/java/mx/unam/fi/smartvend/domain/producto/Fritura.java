package mx.unam.fi.smartvend.domain.producto;

import java.time.LocalDate;

public class Fritura extends Producto {
    
    public Fritura(String codigo, String nombre, LocalDate fechaCaducidad) {
        super(codigo, nombre, fechaCaducidad, CategoriaProducto.SNACK);
    }
    
    @Override
    protected String getTipoEmbalaje() { 
        return "Bolsa metalizada"; 
    }
}