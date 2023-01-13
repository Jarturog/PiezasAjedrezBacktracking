package piezas;

import tablero.Vector2D;
import tablero.Tablero;
import java.util.LinkedList;
import javax.swing.ImageIcon;

/**
 * Clase abstracta que representa una pieza.
 *
 * @author Arturo y Marta
 */
public abstract class Pieza {

    /**
     * String del nombre de la imagen que indica si una casilla ha sido
     * visitada.
     */
    private final static String IMAGEN_CASILLA_VISITADA = "casillaVisitada.png";
    /**
     * El tamaño en píxeles de tanto la altura como de la anchura de las piezas.
     */
    private final int TAMANYO_PIEZA;
    /**
     * El tablero en el que interacciona la pieza.
     */
    private final Tablero tablero;
    /**
     * La posición actual de la pieza, se inicializa con setPosicion().
     */
    private Vector2D posicion;

    /**
     * Equivalente a getMovimientos.
     *
     * @return array de Vector2D que representa los movimientos que puede
     * realizar la pieza.
     */
    public abstract Vector2D[] movimientos();
    /**
     * Conjunto de posibles movimientos que puede realizar la pieza.
     */
    private final Vector2D[] movPosibles;

    /**
     * Equivalente a getImagenPieza.
     *
     * @return String que representa el nombre del archivo imagen de la pieza.
     */
    public abstract String imagenPieza();

    /**
     * Constructor que inicializa una pieza indicando el tablero que reside, los
     * píxeles que ocupa e inicializando el conjunto de movimientos y su
     * respectiva imagen.
     *
     * @param tablero el tablero en el que va a moverse.
     * @param tamanyoPieza el tamaño en píxeles de la altura y anchura de la
     * pieza.
     */
    public Pieza(Tablero tablero, int tamanyoPieza) {
        this.tablero = tablero;
        TAMANYO_PIEZA = tamanyoPieza;
        movPosibles = movimientos(); // se asigna a una variable para no tener
        // que crear nuevos vectores y reservar memoria cada vez que se quiera
        // saber los movimientos
    }

    /**
     * Asigna la posición por la que empezará a recorrer el tablero, no ocupa
     * ninguna casilla del tablero todavía.
     *
     * @param pos Vector2D representando la fila y columna en la que empezará la
     * pieza.
     */
    public void setPosicion(Vector2D pos) {
        posicion = new Vector2D(pos);
    }

    /**
     * Getter del tablero.
     *
     * @return el tablero en el que reside la pieza.
     */
    public Tablero getTablero() {
        return tablero;
    }

    /**
     * Dibuja la imagen pasada por parámetro en la que casilla que ocupa la
     * pieza en el momento. Los casos en los que se usa es para pasar la propia
     * imagen de la pieza, la imagen que representa que una casilla ha sido
     * ocupada y null para quitar el dibujo que ya estuviera en la casilla.
     *
     * @param nombreImagen
     * @throws Exception
     */
    private void dibujar(String nombreImagen) {
        tablero.getCasilla(posicion).setIcon(new ImageIcon(new ImageIcon(nombreImagen).getImage().
                getScaledInstance(TAMANYO_PIEZA, TAMANYO_PIEZA, java.awt.Image.SCALE_DEFAULT)));
    }

    /**
     * Calcula la posición que ocupará la pieza según el movimiento realizado y
     * la ocupa.
     *
     * @param movimiento Vector2D que representa el movimiento realizado.
     */
    public void mover(Vector2D movimiento) {
        posicion = Vector2D.sumar(posicion, movimiento);
        tablero.ocuparPosicion(posicion, true);
    }

    /**
     * Calcula según el movimiento realizado la posición a la que tiene que
     * volver y desocupa la actual.
     *
     * @param movimientoRealizado el movimiento que se va a revertir.
     */
    public void volverAtras(Vector2D movimientoRealizado) {
        tablero.ocuparPosicion(posicion, false);
        posicion = Vector2D.sumar(posicion, Vector2D.multiplicar(movimientoRealizado, -1));
    }

    /**
     * Visualiza las posiciones pasadas por parámetro con la imagen de la pieza
     * y dejando las casillas ocupadas con una X.
     *
     * @param posiciones conjunto de posiciones ordenadas que ha ocupado la
     * pieza.
     */
    public void visualizarPosiciones(LinkedList<Vector2D> posiciones) {
        posicion = posiciones.pollLast();
        dibujar(imagenPieza());
        while (!posiciones.isEmpty()) {
            try {
                Thread.sleep(200); // deja un retardo para que no se instantáneo
            } catch (InterruptedException ex) {
                System.err.println("Error realizando un sleep en el hilo. Mensaje de error: " + ex.getMessage());
            }
            dibujar(IMAGEN_CASILLA_VISITADA);
            posicion = posiciones.pollLast();
            dibujar(imagenPieza());
        }
    }

    /**
     * Recorre el tablero haciendo uso de un algoritmo recursivo.
     *
     * @return true si tiene solución, false en caso contrario.
     */
    public boolean recorrerTableroRecursivo() {
        return rRecorrerTablero(new LinkedList<Vector2D>(), new Vector2D(0, 0)); // inicialización del proceso recursivo
    }

    /**
     * Algoritmo recursivo que recorre el tablero. Se ha de inicializar con un
     * LinkedList vacío y un Vector2D de ceros.
     *
     * @param solucion LinkedList donde se acumulan las posiciones que se han
     * ocupado. Al final contiene todas las que definen el recorrido.
     * @param mov movimiento realizado.
     * @return true si tiene solución, false en caso contrario.
     */
    private boolean rRecorrerTablero(LinkedList<Vector2D> solucion, Vector2D mov) {
        mover(mov); // mueve la pieza a la nueva posición
        solucion.push(new Vector2D(posicion)); // guarda la posición actual en la solución
        // indMov es el índice que recorre los movimientos de la pieza
        for (int indMov = 0; indMov < movPosibles.length; indMov++) {
            // se calcula la hipotética futura posición
            Vector2D futuraPosicion = Vector2D.sumar(posicion, movPosibles[indMov]);
            // si la casilla que ocuparía está libre y no se sale del tablero
            if (tablero.isPosicionDelTablero(futuraPosicion) && tablero.isCasillaLibre(futuraPosicion)) {
                // si ha recorrido todo el tablero    
                if (tablero.getCasillasVisitadas() > tablero.getNumCasillas() - 2) {
                    // -2 en vez de -1 porque la posición que ocupará futuraPosicion
                    // no se ha indicado todaavía en el número total de casillas, y
                    // si se deja con -1 hará otra llamada recursiva en la que al estar
                    // todas las casillas ocupadas no entrará en el caso base porque no pasará isValid()
                    solucion.push(futuraPosicion); // guarda la posición final en la solución
                    visualizarPosiciones(solucion);
                    return true;
                } else if (rRecorrerTablero(solucion, movPosibles[indMov])) { // en caso contrario sigue recorriendo el tablero
                    return true;
                }
            }
        }
        // si llega aquí es porque no era parte de la solución
        solucion.pop(); // quita el movimiento de la solución
        volverAtras(mov); // revierte el movimiento volviendo atrás
        return false;
    }

    /**
     * Algoritmo recursivo que recorre el tablero con un algoritmo iterativo.
     *
     * @return true si tiene solución, false en caso contrario.
     */
    public boolean recorrerTableroIterativo() {
        mover(new Vector2D(0, 0)); // mueve la pieza a la posición que ya ocupa
        // se asigna la constante de posición inicial para la reconstrucción de las posiciones ocupadas en la solución
        final Vector2D posicionInicial = new Vector2D(posicion);
        int indMov = 0; // índice que indica en qué profundidad del árbol 
        // (abstrayendo el algoritmo en un árbol) se está en un momento dado
        int movRealizados[] = new int[tablero.getNumCasillas()];
        // todo el conjunto de movimientos que se van a realizar para recorrer
        // por completo el árbol. movRealizados[indMov] sirve como índice para
        // la cantidad de movimientos posibles que puede realizar una pieza
        for (int i = 0; i < movRealizados.length; i++) {
            movRealizados[i] = -1; // se inicializan a -1
        }
        while (indMov > -1) { // mientras haya solución y no se haya recorrido el árbol
            if (movRealizados[indMov] < movPosibles.length - 1) { // si todavía queda un movimiento para probar en la pieza
                movRealizados[indMov]++; // siguiente movimiento
                // cálculo de la posición que ocuparía si se elige el movimiento
                Vector2D futuraPosicion = Vector2D.sumar(posicion, movPosibles[movRealizados[indMov]]);
                // se comprueba si el movimiento es válido
                if (tablero.isPosicionDelTablero(futuraPosicion) && tablero.isCasillaLibre(futuraPosicion)) {
                    // se comprueba si ha encontrado solución (además de recorrer todo el árbol)
                    if (indMov > movRealizados.length - 3) {
                        // -3 en vez de -2 porque la posición que ocupará futuraPosicion
                        // no se ha indicado todaavía en el número total de casillas, y
                        // si se deja con -2 hará otra llamada recursiva en la que al estar
                        // todas las casillas ocupadas no entrará en el caso base porque no pasará isValid()
                        // y -2 y no -1 por el mismo motivo por el que se comprueba lo siguiente de la manera en la que lo hace
                        // en líneas anteriores: (movRealizados[indMov] < movPosibles.length - 1)
                        mover(movPosibles[movRealizados[indMov]]); // mueve por última vez la pieza
                        // se crea un LinkedList en el que se almacenarán las posiciones que ha ocupado la pieza
                        LinkedList<Vector2D> solucion = new LinkedList<Vector2D>();
                        // aux recorrerá todo el tablero desde la posición inicial hasta la última visitdada
                        Vector2D aux = new Vector2D(posicionInicial);
                        for (int i = 0; i < movRealizados.length - 1; i++) { // mientras queden movimientos
                            aux = Vector2D.sumar(aux, movPosibles[movRealizados[i]]); // se calcula la siguiente posición
                            solucion.addFirst(aux); // y se añade
                        }
                        solucion.addLast(posicionInicial); // finalmente se añade la posición inicial
                        visualizarPosiciones(solucion); // y se visualizan
                        return true; // hay solución
                    } // si no ha encontrado solución mueve la pieza
                    mover(movPosibles[movRealizados[indMov]]);
                    indMov++; // y busca el siguiente movimiento a realizar
                }
            } else { // si ya se han probado todos los movimientos
                // se asigna a -1 indicando que no se ha encontrado una casilla que ocupar
                movRealizados[indMov] = -1; // teniendo en cuenta las casillas ocupadas anteriormente
                indMov--; // vuelve atrás un nodo para comprobar el resto de posibles casillas
                try {
                    // se vuelve atrás en cuanto a posición y desocupa la casilla que había ocupado
                    volverAtras(movPosibles[movRealizados[indMov]]);
                } catch (IndexOutOfBoundsException ex) { // en caso de que salte una excepción de fuera de rango
                    if (indMov == -1) { // y indMov valga -1 es porque se ha recorrido el tablero entero y no se ha encontrado solución
                        break; // sale del bucle y devolverá falso
                    }
                }
            }
        }
        return false; // no hay solución
    }
}
