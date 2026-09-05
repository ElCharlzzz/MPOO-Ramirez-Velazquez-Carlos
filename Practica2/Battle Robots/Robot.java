import java.util.UUID;
import java.util.Random;
import java.util.Objects;

public class Robot{

    private static int totalRobots=0;

    private UUID id;
    private String nombre;
    private double peso;
    private double salud=100;
    private Bateria bateria;
    private Propietario propietario;
    private Random random;

    public Robot(String nombre, double peso, int capacidadBateria, String nombreCreador, String carreraCreador){ 
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.peso = peso;
        this.bateria = new Bateria(capacidadBateria); 
        this.propietario = new Propietario(nombreCreador, carreraCreador);
        this.random = new Random();
        totalRobots++;
    }

    public String getId(){
        return this.id.toString();
    }

    public String getNombre(){
        return this.nombre;
    }

    public double getPeso(){
        return this.peso;
    }

    public Bateria getBateria(){
        return this.bateria;
    }

    public Propietario getPropietario(){
        return this.propietario;
    }

    public static int getTotalRobots(){
        return totalRobots;
    }

    public boolean sigueCombatiendo(){
        return this.salud > 0 && this.bateria.getNivelActual() > 0;
    }

    public void recibirDamage(double ataque){
        if(sigueCombatiendo()){
            this.salud-=ataque;
            if(this.salud<0) this.salud=0;
            System.out.println(this.nombre + "recibio" + String.format("%.2f", ataque) + "de daño salud restante" + String.format("%.2f", this.salud));

        }
    }

    public double atacar(){
        if(!sigueCombatiendo()) return 0.0;

        double damageGenerado = 10.0 + random.nextDouble() * 20;

        double consumo = 2 + random.nextDouble() * 6;
        this.bateria.consumir((int) consumo);

        System.out.println(this.nombre + "lanza un ataque" + String.format("%.2f", damageGenerado));
        return damageGenerado;
    }

    public void recargarEnergia(){
        System.out.println(this.nombre + "recargando energia");
        this.bateria=new Bateria(this.bateria.getNivelActual() +15);
    }

    @Override
    public String toString(){
        return "Robot[" + nombre + "| id" + id + 
                "| Salud" + String.format("%.2f", salud) +
                "| Bateria" + String.format("%d", bateria.getNivelActual()) + "%" +
                "| Propietario" +propietario.getNombre() + "]";
    }

    @Override 
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(obj==null || getClass() != obj.getClass()) return false;
        Robot robotObj = (Robot) obj;
        return Objects.equals(this.id, robotObj.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    
}