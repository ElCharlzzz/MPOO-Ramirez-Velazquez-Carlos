package smartvend.dominio;

import java.time.LocalDate;
import smartvend.enums.CategoriaProducto;

public class Refresco extends Producto {
    public Refresco(String codigo, String nombre, LocalDate fechaCaducidad) {
        super(codigo, nombre, fechaCaducidad, CategoriaProducto.BEBIDA);
    }
    @Override
    protected String getTipoEmbalaje() { return "Lata o Botella PET"; }
}