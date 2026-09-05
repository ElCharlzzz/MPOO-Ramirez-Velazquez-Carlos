

public class Bateria{
    private int NivelAutonomia;
    private int NivelActual;

    public Bateria(int NivelAutonomia){
        this.NivelAutonomia=NivelAutonomia;
        this.NivelActual=NivelAutonomia;
    }

    public void consumir(int cantidad){
        this.NivelActual-=cantidad;
        if(this.NivelActual<0) this .NivelActual=0;
    }

    public int getNivelActual(){
        return this.NivelActual;
    }
}