import java.util.Scanner;

public class MainBanco {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        CuentaBancariaService servicio = new CuentaBancariaService();
        int opcion = 0;

        while(opcion != 4){

            System.out.println("\n==============Sistema Bancario==============\n");
            System.out.println("1. Transferir dinero");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Salir");
            System.out.print("\nSeleccione una opción: \n");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch(opcion){
                case 1:
                    System.out.print("Ingrese el número de cuenta de origen: ");
                    String cuentaOrigen = scanner.nextLine();
                    System.out.print("Ingrese el número de cuenta de destino: ");
                    String cuentaDestino = scanner.nextLine();
                    System.out.print("Ingrese la cantidad a transferir: ");
                    double cantidadTransferencia = scanner.nextDouble();
                    if(servicio.transferir(cuentaOrigen, cuentaDestino, cantidadTransferencia)){
                        System.out.println("Transferencia exitosa.");
                    } else {
                        System.out.println("Error en la transferencia.");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el número de cuenta destino: ");
                    String cuentaDeposito = scanner.nextLine();
                    System.out.print("Ingrese la cantidad a depositar: ");
                    double cantidadDeposito = scanner.nextDouble();
                    if(servicio.depositar(cuentaDeposito, cantidadDeposito)){
                        System.out.println("Depósito exitoso.");
                    } else {
                        System.out.println("Error en el depósito.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el número de cuenta origen: ");
                    String cuentaRetiro = scanner.nextLine();
                    System.out.print("Ingrese la cantidad a retirar: ");
                    double cantidadRetiro = scanner.nextDouble();
                    if(servicio.retirar(cuentaRetiro, cantidadRetiro)){
                        System.out.println("Retiro exitoso.");
                    } else {
                        System.out.println("Error en el retiro.");
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
