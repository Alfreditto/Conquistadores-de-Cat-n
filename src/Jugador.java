public class Jugador {
    private Type type;
    private int almacenM;
    private int almacenC;
    private int almacenT;
    private int recursos_totales;

    public Jugador(boolean esJugador) {
        asignartipo(esJugador);
    }

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

    public void aumentarRecurso(int aux) {
        switch (aux) {
            case 1:
                if (almacenT < 20) {
                    almacenT++;
                } else {
                    System.out.println("El almacen de trigo esta lleno!");
                }
                break;
            case 2:
                if (almacenC < 20) {
                    almacenC++;
                } else {
                    System.out.println("El almacen de carbon esta lleno!");
                }
                break;
            case 3:
                if (almacenM < 20) {
                    almacenM++;
                } else {
                    System.out.println("El almacen de Madera esta lleno!");
                }
                break;
        }
    }

    public void contarMateriales() {
        setRecursos_totales((almacenM + almacenC + almacenT));
    }

    public void asignartipo(boolean esJugador) {
        if (esJugador) {
            setType(Type.Persona);
        } else {
            setType(Type.Maquina);
        }
    }

    public void pintarInventario() {
        System.out.println(mostrarInfo() + " M: " + almacenM + " C " + almacenC + " T " + almacenT);
    }

    public String mostrarInfo() {
        return String.valueOf(this.type);
    }

    enum Type {Persona, Maquina}
}
