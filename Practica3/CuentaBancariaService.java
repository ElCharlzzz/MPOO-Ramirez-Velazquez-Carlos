public class CuentaBancariaService {
    
    private CuentaBancaria[] cuentas;

    public CuentaBancariaService(){
        cuentas = new CuentaBancaria[]{
            new CuentaBancaria("123456", "Juan Perez", 1000.0, true),
            new CuentaBancaria("654321", "Maria Lopez", 500.0, true),
            new CuentaBancaria("987654", "Carlos Sanchez", 2000.0, false)
        };
    }

    private CuentaBancaria buscarCuenta(String numeroCuenta) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    public boolean transferir(String cuentaOrigen, String cuentaDestino, double cantidad){

        CuentaBancaria origen = buscarCuenta(cuentaOrigen);
        CuentaBancaria destino = buscarCuenta(cuentaDestino);

        if (origen == null || destino == null) {
            return false;
        }

        if (origen.retirar(cantidad)) {
           if  (destino.depositar(cantidad)){
                return true;
            } else {
                origen.depositar(cantidad);
                return false; 

           }
        }
        return false;
    }

    public boolean depositar(String cuentaDestino, double cantidad){
        CuentaBancaria destino = buscarCuenta(cuentaDestino);

        if (destino == null) {
            return false; 
        }

        return destino.depositar(cantidad); 
    }

    public boolean retirar(String cuentaOrigen, double cantidad){
        CuentaBancaria origen = buscarCuenta(cuentaOrigen);

        if (origen == null) {
            return false; 
        }

        return origen.retirar(cantidad);
    }
}
