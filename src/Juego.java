import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Juego {

    Scanner sc = new Scanner(System.in);
    Map<Jugador, Jugador> jugadores = new HashMap<>();

    public Juego() {
        System.out.println("Buenas, bienvenido a una nueva partida de Conquistadores de Catán");
        Recurso[][] tablero = new Recurso[3][4];
        Jugador Persona = new Jugador(true);
        Jugador Maquina = new Jugador(false);
        jugadores.put(Persona, Persona);
        jugadores.put(Maquina, Maquina);


        crearTablero(tablero);
        asignarCasillas(tablero, Persona, Maquina);
        sc.nextLine();
        jugar(tablero, Persona, Maquina);


    }

    private void jugar(Recurso[][] tablero, Jugador persona, Jugador maquina) {
        int recursosMaquina;
        int recursosJugador;
        do {
            recolectarRecursos(tablero, persona, maquina);
            recursosMaquina = maquina.getRecursos_totales();
            recursosJugador = persona.getRecursos_totales();
        } while (recursosMaquina < 60 && recursosJugador < 60);

        String ganador = recursosMaquina > recursosJugador ? "Ha ganado la maquina" : "Ha ganado el jugador";
        System.out.println(ganador);
    }

    private void recolectarRecursos(Recurso[][] tablero, Jugador persona, Jugador maquina) {
        System.out.println("Pulsa cualquier tecla");
        String aux = sc.nextLine();
        int dado = (int) (Math.random() * 6) + 1;
        System.out.println("Ha salido " + dado);
        revisarCasilla(tablero, dado, persona, maquina);
    }

    private void revisarCasilla(Recurso[][] tablero, int dado, Jugador persona, Jugador maquina) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j].valor == dado) {
                    switch (tablero[i][j].getTipo()) {
                        case trigo -> {
                            jugadores.get(tablero[i][j].getDueño()).aumentarRecurso(1);
                            System.out.println("Se le ha dado 1 de trigo a " + tablero[i][j].getDueño().mostrarInfo());
                        }
                        case carbon -> {
                            jugadores.get(tablero[i][j].getDueño()).aumentarRecurso(2);
                            System.out.println("Se le ha dado 1 de carbon a " + tablero[i][j].getDueño().mostrarInfo());
                        }
                        case madera -> {
                            jugadores.get(tablero[i][j].getDueño()).aumentarRecurso(3);
                            System.out.println("Se le ha dado 1 de madera a " + tablero[i][j].getDueño().mostrarInfo());
                        }
                        default -> System.out.println("No hay ninguna casilla con el numero " + dado);
                    }
                }
            }
        }
        persona.pintarInventario();
        maquina.pintarInventario();
    }

    //Vamos a asignar las casillas
    private void asignarCasillas(Recurso[][] tablero, Jugador persona, Jugador maquina) {
        for (int i = (tablero.length * tablero[0].length); i > 0; i -= 2) {
            pintarTablero(tablero);
            System.out.println("Quedan " + i + " casillas libres");
            asignarCasillasJ(tablero, persona);
            asignarCasillasM(tablero, maquina);
        }
    }

    //Le vamos asignando mediante un dado las casillas a la Maquina
    private void asignarCasillasM(Recurso[][] tablero, Jugador maquina) {
        boolean seleccionando = true;
        do {
            int fila = (int) (Math.random() * tablero.length);
            int columna = (int) (Math.random() * tablero[0].length);
            if (comprobar(tablero, fila, columna)) {
                tablero[fila][columna].setDueño(maquina);
                seleccionando = !seleccionando;
            }
        } while (seleccionando);
    }


    //Le vamos asignando al jugador sus casillas
    private void asignarCasillasJ(Recurso[][] tablero, Jugador persona) {
        String stringFormat = String.format("Indica que casilla quieres, el tablero son %s filas por %s columnas", tablero.length, tablero[0].length);
        System.out.println(stringFormat);
        boolean seleccionando = true;
        do {
            System.out.println("Fila");
            int fila = sc.nextInt() - 1;
            System.out.println("Columna");
            int columna = sc.nextInt() - 1;
            if (comprobar(tablero, fila, columna)) {
                tablero[fila][columna].setDueño(persona);
                seleccionando = !seleccionando;
            } else {
                System.out.println("Esa casilla ya esta escogida por ti o por la maquina, seleccione otra");
            }
        } while (seleccionando);
    }

    private boolean comprobar(Recurso[][] tablero, int fila, int columna) {
        return tablero[fila][columna].dueño == null;
    }

    //Pintar tablero
    private void pintarTablero(Recurso[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                System.out.printf("%-20s", tablero[i][j].mostrarInfo());
            }
            System.out.print("\n");
        }
    }

    //En el método crear tablero creamos la matriz de este.
    private void crearTablero(Recurso[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j] = new Recurso();
            }
        }
    }

}
