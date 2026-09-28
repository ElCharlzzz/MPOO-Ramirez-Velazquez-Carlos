import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {
    
    

    /*
     * Complete the 'calcularEstancia' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING tipoVehiculo
     *  2. STRING fechaEntrada
     *  3. STRING horaEntrada
     *  4. STRING fechaSalida
     *  5. STRING horaSalida
     */

    public static String calcularEstancia(String tipoVehiculo, String fechaEntrada, String horaEntrada, String fechaSalida, String horaSalida) {
        try {

            enum TipoVehiculo{
                MOTOCICLETA, AUTOMOVIL, CAMIONETA, ELECTRICO;
            }
        
            enum TipoEstancia{
                NORMAL, NOCTURNA, FIN_SEMANA, MIXTA;
            }

            TipoVehiculo vehiculoEnum = TipoVehiculo.valueOf(tipoVehiculo.toUpperCase());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date entrada = sdf.parse(fechaEntrada + " " + horaEntrada);
            Date salida = sdf.parse(fechaSalida + " " + horaSalida);

            Calendar calEntrada = Calendar.getInstance();
            calEntrada.setTime(entrada);

            Calendar calSalida = Calendar.getInstance();
            calSalida.setTime(salida);

            if (calSalida.getTimeInMillis() <= calEntrada.getTimeInMillis()) {
                return "INVALID";
            }

            long diffMillis = calSalida.getTimeInMillis() - calEntrada.getTimeInMillis();
            double diffHoras = (double) diffMillis / (1000 * 60 * 60);
            long horasCobradas = (long) Math.ceil(diffHoras);

            double tarifaPorHora = 0;
            double maxPorBloque = 0;

            switch (vehiculoEnum) {
                case MOTOCICLETA: 
                    tarifaPorHora = 15.00; maxPorBloque = 100.00; break;
                case AUTOMOVIL: 
                    tarifaPorHora = 25.00; maxPorBloque = 180.00; break;
                case CAMIONETA: 
                    tarifaPorHora = 35.00; maxPorBloque = 250.00; break;
                case ELECTRICO: 
                    tarifaPorHora = 20.00; maxPorBloque = 150.00; break;
            }

            double costoBase = 0;
            long horasRestantes = horasCobradas;

            while (horasRestantes > 0) {
                long horasBloque = Math.min(horasRestantes, 24);
                double costoBloque = Math.min(horasBloque * tarifaPorHora, maxPorBloque);
                costoBase += costoBloque;
                horasRestantes -= horasBloque;
            }

            boolean esFinSemana = (calEntrada.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                                   calEntrada.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                                   calSalida.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                                   calSalida.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);

            int horaEnt = calEntrada.get(Calendar.HOUR_OF_DAY);
            int horaSal = calSalida.get(Calendar.HOUR_OF_DAY);
            boolean fechasDiferentes = !fechaEntrada.equals(fechaSalida);
            boolean esNocturna = (horaEnt >= 20 || horaSal < 6 || fechasDiferentes);

            double costoFinal = costoBase;
            if (esFinSemana) costoFinal *= 1.20;
            if (esNocturna) costoFinal *= 1.15;
            
            if (vehiculoEnum == TipoVehiculo.ELECTRICO) costoFinal *= 0.90; 

            TipoEstancia estanciaEnum = TipoEstancia.NORMAL;
            if (esFinSemana && esNocturna) {
                estanciaEnum = TipoEstancia.MIXTA;
            } else if (esFinSemana) {
                estanciaEnum = TipoEstancia.FIN_SEMANA;
            } else if (esNocturna) {
                estanciaEnum = TipoEstancia.NOCTURNA;
            }

            return String.format(Locale.US, "%d %.2f %s", horasCobradas, costoFinal, estanciaEnum.name());

        } catch (IllegalArgumentException e) {
            return "INVALID"; 
        } catch (Exception e) {
            return "INVALID";
        }
    }
        
        
       

}

public class Caseta {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Ingresa el tipo de vehículo (MOTOCICLETA, AUTOMOVIL, CAMIONETA, ELECTRICO): ");
            String tipoVehiculo = scanner.nextLine().trim();

            System.out.print("Ingresa la fecha de entrada (DD/MM/YYYY): ");
            String fechaEntrada = scanner.nextLine().trim();

            System.out.print("Ingresa la hora de entrada (HH:MM): ");
            String horaEntrada = scanner.nextLine().trim();

            System.out.print("Ingresa la fecha de salida (DD/MM/YYYY): ");
            String fechaSalida = scanner.nextLine().trim();

            System.out.print("Ingresa la hora de salida (HH:MM): ");
            String horaSalida = scanner.nextLine().trim();

            String result = Result.calcularEstancia(
                tipoVehiculo, 
                fechaEntrada, 
                horaEntrada, 
                fechaSalida, 
                horaSalida
            );

            System.out.println("\n--- TICKET DE ESTACIONAMIENTO ---");
            System.out.println(result);

        } catch (Exception e) {
            System.out.println("Ocurrió un error al leer los datos.");
        } finally {
            scanner.close();
        }
    }
}
