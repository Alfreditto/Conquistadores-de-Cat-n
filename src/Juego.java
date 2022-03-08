import java.util.Scanner;

public class Juego {

    static Scanner sc = new Scanner(System.in);

    public Juego(){
        System.out.println("Buenas, bienvenido a una nueva partida de Conquistadores de Catán");
        Recurso[][] tablero = new Recurso[3][4];
        Jugador Persona = new Jugador(true);
        Jugador Maquina = new Jugador(false);


        crearTablero(tablero);
        asignarCasillas(tablero, Persona, Maquina);
        jugar(tablero, Persona, Maquina);


    }

    private static void jugar(Recurso[][] tablero, Jugador persona, Jugador maquina) {
        while (persona.recursos_totales < 60 && maquina.recursos_totales < 60){
            recolectarRecursos(tablero, persona, maquina);
        }
    }

    private static void recolectarRecursos(Recurso[][] tablero, Jugador persona, Jugador maquina) {
        int dado = (int) (Math.random()*6)+1;
        revisarCasilla(tablero, dado, persona, maquina);
    }

    private static void revisarCasilla(Recurso[][] tablero, int dado, Jugador persona, Jugador maquina) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if(tablero[i][j].valor == dado) {
                    if (tablero[i][j].getDueño().getType() == Jugador.Type.Persona){
                        switch(tablero[i][j].getTipo()){
                            case trigo-> persona.aumentarRecurso(1);
                            case carbon -> persona.aumentarRecurso(2);
                            case madera-> persona.aumentarRecurso(3);
                        }
                    }
                }
            }
        }
    }

    //Vamos a asignar las casillas
    private static void asignarCasillas(Recurso[][] tablero, Jugador persona, Jugador maquina) {
        for (int i = 12; i >= 0; i--) {
            pintarTablero(tablero);
            System.out.println("Quedan " + i + " casillas libres");
            asignarCasillasJ(tablero, persona);
            asignarCasillasM(tablero, maquina);
        }
    }

    //Le vamos asignando mediante un dado las casillas a la Maquina
    private static void asignarCasillasM(Recurso[][] tablero, Jugador maquina) {
            boolean seleccionando = true;
            do {
                int fila = (int) (Math.random()* tablero.length);
                int columna = (int) (Math.random()* tablero[0].length);
                if (comprobar(tablero, fila, columna)) {
                    tablero[fila][columna].setDueño(maquina);
                    seleccionando = !seleccionando;
                }
            } while (seleccionando);
    }


    //Le vamos asignando al jugador sus casillas
    private static void asignarCasillasJ(Recurso[][] tablero, Jugador persona) {
        String stringFormat = String.format("Indica que casilla quieres, el tablero son %s filas por %s columnas", tablero.length, tablero[0].length);
        System.out.println(stringFormat);
        boolean seleccionando = true;
        do {
            System.out.println("Fila");
            int fila = sc.nextInt()-1;
            System.out.println("Columna");
            int columna = sc.nextInt()-1;
            if (comprobar(tablero, fila, columna)) {
                tablero[fila][columna].setDueño(persona);
                seleccionando = !seleccionando;
            } else {
                System.out.println("Esa casilla ya esta escogida por ti o por la maquina, seleccione otra");
            }
        } while (seleccionando);
    }

    private static boolean comprobar(Recurso[][] tablero, int fila, int columna) {
        return tablero[fila][columna].dueño == null;
    }
    
    //Pintar tablero
    private static void pintarTablero(Recurso[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                System.out.println(tablero[i][j]);
            }
        }
    }

    //En el método crear tablero creamos la matriz de este.
    private static void crearTablero(Recurso[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j] = new Recurso();
            }
        }
    }
    
}
