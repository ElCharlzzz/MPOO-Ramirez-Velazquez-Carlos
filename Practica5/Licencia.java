import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {
    
    

    /*
     * Complete the 'evaluarLicencia' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING fechaActual
     *  2. STRING fechaVencimiento
     *  3. STRING tipoLicencia
     *  4. INTEGER renovacionesPrevias
     */

    public static String evaluarLicencia(String fechaActual, String fechaVencimiento, String tipoLicencia, int renovacionesPrevias) {
        try {

            enum TipoLicencia {
                BASICA, PROFESIONAL, EMPRESARIAL, TEMPORAL
            }

            enum EstadoLicencia {
                VIGENTE, PROXIMA_A_VENCER, VENCIDA, BLOQUEADA
            }

            TipoLicencia licenciaEnum = TipoLicencia.valueOf(tipoLicencia.toUpperCase());
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateActual = sdf.parse(fechaActual);
            Date dateVencimiento = sdf.parse(fechaVencimiento);

            long diffMillis = dateVencimiento.getTime() - dateActual.getTime();
            long diasRest = diffMillis / (1000L * 60 * 60 * 24);

            EstadoLicencia estado;
            if (diasRest > 30) {
                estado = EstadoLicencia.VIGENTE;
            } else if (diasRest >= 0) {
                estado = EstadoLicencia.PROXIMA_A_VENCER;
            } else if (diasRest >= -90) {
                estado = EstadoLicencia.VENCIDA;
            } else {
                estado = EstadoLicencia.BLOQUEADA;
            }

            double costoBase = 0.0;
            int aniosSuma = 0;
            int mesesSuma = 0;

            switch (licenciaEnum) {
                case BASICA: 
                    costoBase = 1000.00; aniosSuma = 1; break;
                case PROFESIONAL: 
                    costoBase = 1500.00; aniosSuma = 2; break;
                case EMPRESARIAL: 
                    costoBase = 2500.00; aniosSuma = 3; break;
                case TEMPORAL: 
                    costoBase = 600.00; mesesSuma = 6; break;
            }

            double costoFinal = costoBase;
            String nuevaFechaStr = "";

            if (estado == EstadoLicencia.BLOQUEADA) {
                costoFinal = 0.00;
                nuevaFechaStr = "NO_DISPONIBLE";
            } else {
                Calendar cal = Calendar.getInstance();

                if (estado == EstadoLicencia.VIGENTE) {
                    costoFinal *= 0.90; // -10%
                    cal.setTime(dateVencimiento);
                } else if (estado == EstadoLicencia.PROXIMA_A_VENCER) {
                    cal.setTime(dateVencimiento);
                } else if (estado == EstadoLicencia.VENCIDA) {
                    costoFinal *= 1.20; // +20%
                    cal.setTime(dateActual);
                }

                if (renovacionesPrevias > 3) {
                    costoFinal *= 0.95; // -5%
                }

                cal.add(Calendar.YEAR, aniosSuma);
                cal.add(Calendar.MONTH, mesesSuma);
                nuevaFechaStr = sdf.format(cal.getTime());
            }

            return String.format(Locale.US, "%s %d %.2f %s", estado.name(), diasRest, costoFinal, nuevaFechaStr);

        } catch (IllegalArgumentException e) {
            return "INVALID";
        } catch (Exception e) {
            return "ERROR";
        }
    }

}



public class Licencia {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Ingresa la fecha actual (DD/MM/YYYY): ");
            String fechaActual = scanner.nextLine().trim();

            System.out.print("Ingresa la fecha de vencimiento (DD/MM/YYYY): ");
            String fechaVencimiento = scanner.nextLine().trim();

            System.out.print("Ingresa el tipo de licencia (BASICA, PROFESIONAL, EMPRESARIAL, TEMPORAL): ");
            String tipoLicencia = scanner.nextLine().trim();

            System.out.print("Ingresa el número de renovaciones previas: ");
            int renovacionesPrevias = Integer.parseInt(scanner.nextLine().trim());

            String result = Result.evaluarLicencia(
                    fechaActual,
                    fechaVencimiento,
                    tipoLicencia,
                    renovacionesPrevias
            );

            System.out.println("\n--- RESULTADO DE LA EVALUACIÓN ---");
            System.out.println(result);

        } catch (NumberFormatException e) {
            System.out.println("\nError: Debes ingresar un número entero válido para las renovaciones.");
        } finally {
            scanner.close();
        }
    }
}
