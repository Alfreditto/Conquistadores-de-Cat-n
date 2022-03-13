import java.lang.*;

public class Recurso {
    enum Type {trigo, madera, carbon}
    Type tipo;
    Jugador dueño;
    int valor;

    public Recurso() {
        asignarTipo();
        asignarValor();
    }


    public Type getTipo() {
        return tipo;
    }

    public void setTipo(Type tipo) {
        this.tipo = tipo;
    }

    public Jugador getDueño() {
        return dueño;
    }

    public void setDueño(Jugador dueño) {
        this.dueño = dueño;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    private void asignarValor() {
        int valor = (int) (Math.random() * 6) + 1;
        setValor(valor);
    }

    //Se le asigna el tipo del recurso de forma aleatoria
    public void asignarTipo() {
        int tipo = (int) (Math.random() * 3);
        switch (tipo) {
            case 0 -> setTipo(Type.carbon);
            case 1 -> setTipo(Type.madera);
            case 2 -> setTipo(Type.trigo);
        }
    }

    public String mostrarInfo() {
        String jugador = dueño == null ? null : dueño.mostrarInfo();
        return "Recurso{" +
                "tipo=" + tipo +
                ", dueño=" + jugador +
                ", valor=" + valor +
                '}';
    }

}