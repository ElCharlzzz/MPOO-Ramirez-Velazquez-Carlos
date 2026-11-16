package mx.unam.fi.smartvend.config;

import mx.unam.fi.smartvend.domain.pago.Monedero;
import mx.unam.fi.smartvend.infrastructure.pago.MonederoEfectivo;
import mx.unam.fi.smartvend.infrastructure.pago.MonederoTarjeta;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmartVendConfig {

    @Bean
    @Qualifier("monederoEfectivo")
    public Monedero monederoEfectivo() {
        return new MonederoEfectivo(500.00); // Fondos iniciales solicitados
    }

    @Bean
    @Qualifier("monederoTarjeta")
    public Monedero monederoTarjeta() {
        return new MonederoTarjeta(250.00); // Fondos iniciales solicitados
    }
}