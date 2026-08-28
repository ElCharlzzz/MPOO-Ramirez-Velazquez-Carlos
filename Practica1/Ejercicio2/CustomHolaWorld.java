//Ramirez Velazquez Carlos
import java.util.Scanner;

public class CustomHolaWorld{
    public static void main(String[] args) {
        Scanner Scanner = new Scanner(System.in);

        System.out.print("Escribe tu nombre:");
        String nombre = Scanner.nextLine();

        System.out.println("Hola, " + nombre);

        Scanner.close();
    }
}