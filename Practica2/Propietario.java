public class Propietario{
    
    private String nombre;
    private String carrera;

    public Propietario(String nombre, String carrera){
        this.nombre=nombre;
        this.carrera=carrera;
    }

    public String getNombre(){
        return nombre;
     }

    public String getCarrera(){
        return carrera;
    }
    
}