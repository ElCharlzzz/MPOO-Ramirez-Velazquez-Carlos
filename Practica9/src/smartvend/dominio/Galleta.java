package smartvend.dominio;

import java.time.LocalDate;
import smartvend.enums.CategoriaProducto;

public class Galleta extends Producto {
    public Galleta(String codigo, String nombre, LocalDate fechaCaducidad) {
        super(codigo, nombre, fechaCaducidad, CategoriaProducto.SNACK);
    }
    @Override
    protected String getTipoEmbalaje() { return "Envoltura plástica"; }
}