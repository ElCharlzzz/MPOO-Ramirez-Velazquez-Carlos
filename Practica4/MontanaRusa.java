import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'detectarZonaAjuste' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY vagones as parameter.
     */

    public static int[] detectarZonaAjuste(int[] vagones) {

        int n = vagones.length;
        
        if(n<2){
            return new int[]{-1,-1,0};
        }
        
        int max_visto =vagones[0];
        int fin = -1;
        
        for(int i=1; i<n; i++){
            if(vagones[i]<max_visto){
                fin=i;
            }else{
                max_visto =vagones[i];
            }      
        }
        
        if(fin==-1){
            return new int[]{-1,-1,0};
        }
        
        int min_visto =vagones[n-1];
        int inicio = -1;
        
        for(int i=n-2; i>=0; i--){
            if(vagones[i] >min_visto){
                inicio=i;
            }else{
                min_visto =vagones[i];
            }
        }
        
        int longitud =fin-inicio+1;

        return new int[]{inicio, fin, longitud};
    }

}

public class MontanaRusa {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(System.in));

        BufferedWriter bufferedWriter =
                new BufferedWriter(
                        new FileWriter(System.getenv("OUTPUT_PATH"))
                );

        String linea = bufferedReader.readLine();

        String[] datos = linea.trim().split("\\s+");

        int[] vagones = new int[datos.length];

        for (int i = 0; i < datos.length; i++) {
            vagones[i] = Integer.parseInt(datos[i]);
        }

        int[] result = Result.detectarZonaAjuste(vagones);

        for (int i = 0; i < result.length; i++) {

            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}