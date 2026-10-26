package smartvend.dominio;

import java.time.LocalDate;
import smartvend.enums.CategoriaProducto;

public class Jugo extends Producto {
    public Jugo(String codigo, String nombre, LocalDate fechaCaducidad) {
        super(codigo, nombre, fechaCaducidad, CategoriaProducto.BEBIDA);
    }
    @Override
    protected String getTipoEmbalaje() { return "Tetrapack"; }
}