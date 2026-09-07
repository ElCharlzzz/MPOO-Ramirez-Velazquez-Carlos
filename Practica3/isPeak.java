import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'longestPeak' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY array as parameter.
     */

    public static int longestPeak(int[] array) {
        if (array == null || array.length <3){
            return 0;
        }
        
        int maxLength = 0;
        int i = 1;
        
        while (i< array.length - 1){
            boolean isPeak = (array[i] - array[i-1] > 1) && (array[i] > array[i+1]);
            
            if (!isPeak){
                i++;
                continue;
            } 
            
            int izquierda = i-1;
            while(izquierda >= 1 && (array[izquierda] - array[izquierda-1] > 1)){
                izquierda--;
            }
            
            int derecha = i+1;
            while(derecha < array.length -1 && array[derecha] > array[derecha+1]){
                derecha++;
            }
            
            int currentLength = derecha - izquierda +1;
            if (currentLength > maxLength){
                maxLength = currentLength;
             }
        
            i=derecha;
            
        }
        return maxLength;
    }

}

public class Solution {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(System.in));

        BufferedWriter bufferedWriter =
                new BufferedWriter(
                        new FileWriter(System.getenv("OUTPUT_PATH"))
                );

        String line = bufferedReader.readLine();

        String[] values = line.trim().split("\\s+");

        int[] array = new int[values.length];

        for (int i = 0; i < values.length; i++) {
            array[i] = Integer.parseInt(values[i]);
        }

        int result = Result.longestPeak(array);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}