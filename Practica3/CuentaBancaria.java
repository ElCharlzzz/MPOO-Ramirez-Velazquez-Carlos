public class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;
    private boolean activa;

    public CuentaBancaria(String numeroCuenta, String titular, double saldo, boolean activa) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
        this.activa = activa;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public boolean retirar(double cantidad) {
        if (activa && cantidad > 0 && saldo >= cantidad) {
            saldo -= cantidad;
            return true;
        }
        return false;
    }

    public boolean depositar(double cantidad) {
        if (activa && cantidad > 0) {
            saldo += cantidad;
            return true;
        }
        return false;
    }
}
