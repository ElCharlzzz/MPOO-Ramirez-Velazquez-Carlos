package smartvend.dtos;

import smartvend.enums.EstadoCobro;

public record ResultadoCobro(EstadoCobro estado, double cambio) {}
