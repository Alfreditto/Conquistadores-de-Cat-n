public class Jugador {
    enum Type {Persona, Maquina}
    private Type type;
    private int almacenM;
    private int almacenC;
    private int almacenT;
    private int recursos_totales;

    public int getAlmacenC() {
        return almacenC;
    }

    public void setAlmacenC(int almacenC) {
        this.almacenC = almacenC;
    }

    public int getAlmacenT() {
        return almacenT;
    }

    public void setAlmacenT(int almacenT) {
        this.almacenT = almacenT;
    }
    public int getAlmacenM() {
        return almacenM;
    }

    public void setAlmacenM(int almacenM) {
        this.almacenM = almacenM;
    }

    public int getRecursos_totales() {
        contarMateriales();
        return recursos_totales;
    }

    public void setRecursos_totales(int recursos_totales) {
        this.recursos_totales = recursos_totales;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Jugador(boolean esJugador) {
        asignartipo(esJugador);
    }
    public void aumentarRecurso(int aux){
        switch (aux) {
            case 1 -> almacenT++;
            case 2 -> almacenC++;
            case 3 -> almacenM++;
        }
    }
    public void contarMateriales(){
        setRecursos_totales((almacenM + almacenC + almacenT));
    }
    public void asignartipo(boolean esJugador) {
        if (esJugador) {
            setType(Type.Persona);
        } else {
            setType(Type.Maquina);
        }
    }

    public String mostrarInfo() {
        return String.valueOf(this.type);
    }
}
