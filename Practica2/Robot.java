import java.util.UUID;
import java.util.Random;

public class Robot{

    private UUID id;
    private string nombre;
    private double peso;
    private Bateria bateria;
    private Random random;

    public Robot(String nombre, double peso, int capacidadBateria) {
        this.id = UUID.randomUUID()
        this.nombre = nombre;
        this.peso = peso;
        this.bateria = new Bateria(capacidad); 
        this.random = new Random();
    }

    public String getId(){
        return this.id;
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

    

    
} 