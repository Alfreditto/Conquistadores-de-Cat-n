public class Main {
<<<<<<< HEAD
    public static void main(String[] args) {
        Juego partida = new Juego();
=======
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Buenas, bienvenido a una nueva partida de Conquistadores de Catán");
        Recurso[][] tablero = new Recurso[3][4];
        Jugador Persona = new Jugador(1);
        Jugador Maquina = new Jugador(0);
        crearTablero(tablero);
        asignarCasillas(tablero, Persona, Maquina);
        jugar(tablero, Persona, Maquina);
    }

    private static void jugar(Recurso[][] tablero, Jugador persona, Jugador maquina) {
        while (persona.recursos_totales < 60 && maquina.recursos_totales < 60) {
            recolectarRecursos(tablero, persona, maquina);
        }
    }

    private static void recolectarRecursos(Recurso[][] tablero, Jugador persona, Jugador maquina) {
        int dado = (int) (Math.random() * 6) + 1;
        revisarCasilla(tablero, dado, persona, maquina);
    }

    private static void revisarCasilla(Recurso[][] tablero, int dado, Jugador persona, Jugador maquina) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                aumentarRecursos(tablero, dado, persona, maquina, i, j);
            }
        }
    }

    private static void aumentarRecursos(Recurso[][] tablero, int dado, Jugador persona, Jugador maquina, int i, int j) {
        if (tablero[i][j].valor == dado) {
            if (tablero[i][j].getDueño().getType() == Jugador.Type.Persona) {
                System.out.print("El jugador ha conseguido 1 de ");
                asignarRecursos(tablero, persona, i, j);
            } else {
                asignarRecursos(tablero, maquina, i, j);
            }
        }
    }

    private static void asignarRecursos(Recurso[][] tablero, Jugador jugador, int i, int j) {
        switch (tablero[i][j].getTipo()) {
            case trigo -> {
                System.out.println("trigo");
                jugador.aumentarRecurso(1);
            }
            case carbon -> {
                System.out.println("carbon");
                jugador.aumentarRecurso(2);
            }
            case madera -> {
                System.out.println("madera");
                jugador.aumentarRecurso(3);
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
        int fila = (int) (Math.random() * tablero.length);
        System.out.println("Columna");
        int columna = (int) (Math.random() * tablero[0].length);
        do {
            if (comprobar(tablero, fila, columna)) {
                tablero[fila][columna].setDueño(maquina);
            }
        } while (comprobar(tablero, fila, columna));
    }


    //Le vamos asignando al jugador sus casillas
    private static void asignarCasillasJ(Recurso[][] tablero, Jugador persona) {
        System.out.println("Indica que casilla quieres");
        System.out.println("Fila");
        int fila = sc.nextInt() - 1;
        System.out.println("Columna");
        int columna = sc.nextInt() - 1;
        do {
            if (comprobar(tablero, fila, columna)) {
                tablero[fila][columna].setDueño(persona);
            }
        } while (comprobar(tablero, fila, columna));
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
>>>>>>> 0bf13aaafa6082cff17662900e24bf87b647e347
    }
}
