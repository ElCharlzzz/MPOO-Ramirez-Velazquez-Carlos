package mx.unam.fi.smartvend.api.dto;
import java.time.LocalDate;
public record RegistroProductoRequest(String tipo, String codigo, String nombre, LocalDate fechaCaducidad, String categoria, double precio) {}