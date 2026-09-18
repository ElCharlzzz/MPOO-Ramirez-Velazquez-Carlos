import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'cifrarMensaje' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING mensaje
     *  2. STRING alfabetoInterior
     *  3. INTEGER posicionInicial
     *  4. INTEGER intervaloRotacion
     */

    public static String cifrarMensaje(String mensaje, String alfabetoInterior, int posicionInicial, int intervaloRotacion) {
    
        StringBuilder mensajeCifrado = new StringBuilder();
    
        int desplazamiento = posicionInicial %26;
        int letrasProcesadas = 0;
    
        for(int i=0; i<mensaje.length(); i++){
            char caracter =mensaje.charAt(i);
        
            if(caracter == ' '){
                mensajeCifrado.append(' ');
                continue;
            }
            
            int indiceOriginal = caracter - 'A';
            
            int indiceDesplazado =(indiceOriginal -desplazamiento) %26;
            
            if(indiceDesplazado<0){
                indiceDesplazado +=26;
            }
            
            char letraCifrada = alfabetoInterior.charAt(indiceDesplazado);
            mensajeCifrado.append(letraCifrada);
            
            letrasProcesadas++;
            if(letrasProcesadas == intervaloRotacion){
                desplazamiento =(desplazamiento+1)%26;
                letrasProcesadas=0;
            }
        
        }
        return mensajeCifrado.toString();

    }

}

public class RuedaMensajero {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String mensaje = bufferedReader.readLine();

        String alfabetoInterior = bufferedReader.readLine();

        int posicionInicial = Integer.parseInt(bufferedReader.readLine().trim());

        int intervaloRotacion = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.cifrarMensaje(mensaje, alfabetoInterior, posicionInicial, intervaloRotacion);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
