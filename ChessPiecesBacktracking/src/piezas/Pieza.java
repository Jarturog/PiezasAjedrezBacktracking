package piezas;

import chesspiecesbacktracking.Ajedrez;
import tablero.Vector2D;
import tablero.Tablero;
import java.util.LinkedList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     */
    public Pieza(Tablero tablero) {
        this.tablero = tablero;
        TAMANYO_PIEZA = Ajedrez.getPixeles() / tablero.getDIMENSIONES();
        movPosibles = movimientos(); // se asigna a una variable para no tener
        // que crear nuevos vectores y reservar memoria cada vez que se quiera
        // saver los movimientos
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
        ImageIcon iconoImagen = new ImageIcon(new ImageIcon(nombreImagen).getImage().getScaledInstance(TAMANYO_PIEZA, TAMANYO_PIEZA, java.awt.Image.SCALE_DEFAULT));
        tablero.getCasilla(posicion).setIcon(iconoImagen);
    }

    public void mover(Vector2D movimiento) {
        posicion = Vector2D.sumar(posicion, movimiento);
        tablero.ocuparPosicion(posicion, true);
    }

    public void volverAtras(Vector2D movimientoRealizado) {
        tablero.ocuparPosicion(posicion, false);
        posicion = Vector2D.sumar(posicion, Vector2D.multiplicar(movimientoRealizado, -1));
    }

    public void visualizarMovimientos(LinkedList<Vector2D> movimientos) {
        posicion = movimientos.pollFirst();
        while (!movimientos.isEmpty()) {
            try {
                dibujar(IMAGEN_CASILLA_VISITADA);
                Thread.sleep(500);
                posicion = movimientos.pollFirst();
                dibujar(imagenPieza());
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                System.err.println("Error realizando un sleep en el hilo. Mensaje de error: " + ex.getMessage());
            }
        }
    }

    public boolean recorrerTablero() {
        return rRecorrerTablero(new LinkedList<Vector2D>(), new Vector2D(0, 0));
    }

    /**
     *
     * @param solucion
     * @param mov
     * @return
     * @throws Exception
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
                    solucion.push(futuraPosicion); // guarda la posición final en la solución
                    visualizarMovimientos(solucion);
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

    public boolean iRecorrerTablero() {

        mover(new Vector2D(new Vector2D(0, 0)));
        final Vector2D posicionInicial = new Vector2D(posicion);
        int indMov = 0;
        int movRealizados[] = new int[tablero.getNumCasillas()];
        for (int i = 0; i < movRealizados.length; i++) {
            movRealizados[i] = -1;
        }
        while (indMov > -1) {
            if (movRealizados[indMov] < movPosibles.length - 1) {
                movRealizados[indMov]++;
                Vector2D futuraPosicion = Vector2D.sumar(posicion, movPosibles[movRealizados[indMov]]);
                if (tablero.isPosicionDelTablero(futuraPosicion) && tablero.isCasillaLibre(futuraPosicion)) {
                    if (indMov > movRealizados.length - 3) {
                        mover(movPosibles[movRealizados[indMov]]);
                        LinkedList<Vector2D> solucion = new LinkedList<Vector2D>();
                        Vector2D aux = new Vector2D(posicionInicial);
                        for (int i = 0; i < movRealizados.length - 1; i++) {
                            aux = Vector2D.sumar(aux, movPosibles[movRealizados[i]]);
                            solucion.add(aux);
                        }
                        visualizarMovimientos(solucion);
                        return true;
                    } else if (indMov < movRealizados.length - 1) {
                        mover(movPosibles[movRealizados[indMov]]);
                        indMov++;
                    }
                }
            } else {
                movRealizados[indMov] = -1;
                indMov--;
                try {
                    volverAtras(movPosibles[movRealizados[indMov]]);
                } catch (IndexOutOfBoundsException ex) {
                    if (indMov == -1) {
                        break;
                    }
                }
            }
        }
        return false;
    }
}
