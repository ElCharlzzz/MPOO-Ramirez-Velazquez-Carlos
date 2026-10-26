package smartvend.dominio;

import java.time.LocalDate;
import smartvend.enums.CategoriaProducto;

public class Fritura extends Producto {
    public Fritura(String codigo, String nombre, LocalDate fechaCaducidad) {
        super(codigo, nombre, fechaCaducidad, CategoriaProducto.SNACK);
    }
    @Override
    protected String getTipoEmbalaje() { return "Bolsa metalizada"; }
}