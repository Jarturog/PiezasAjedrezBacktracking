package piezas;

import chesspiecesbacktracking.Ajedrez;
import tablero.Vector2D;
import tablero.Tablero;
import java.util.LinkedList;
import javax.swing.ImageIcon;

/**
 *
 * @author Arturo y Marta
 */
public abstract class Pieza {

    private final static String IMAGEN_CASILLA_VISITADA = "casillaVisitada.png";
    private final int TAMANYO_PIEZA;
    private final Tablero tablero;
    private Vector2D posicion;
    
    /**
     * Equivalente a getMovimientos.
     *
     * @return array de Vector2D que representa los movimientos que puede
     * realizar la pieza
     */
    public abstract Vector2D[] movimientos();
    private final Vector2D[] movimientos;

    /**
     * Equivalente a getImagenPieza.
     *
     * @return String que representa el nombre del archivo imagen de la pieza.
     */
    public abstract String imagenPieza();
    private final String imagenPieza;
    
    public Vector2D[] getMovimientos() {
        return movimientos;
    }

    public Pieza(Tablero t) {
        tablero = t;
        TAMANYO_PIEZA = Ajedrez.getPixeles() / tablero.getDIMENSIONES();
        movimientos = movimientos();
        imagenPieza = imagenPieza();
    }

    private void dibujar(String nombreImagen) throws Exception {
        ImageIcon iconoImagen = new ImageIcon(new ImageIcon(nombreImagen).getImage().getScaledInstance(TAMANYO_PIEZA, TAMANYO_PIEZA, java.awt.Image.SCALE_DEFAULT));
        tablero.getCasilla(posicion).setIcon(iconoImagen);
    }

    public void mover(Vector2D movimiento, boolean dibujarPieza) throws Exception {
        if (dibujarPieza) {
            dibujar(IMAGEN_CASILLA_VISITADA);
        }
        posicion = Vector2D.sumar(posicion, movimiento);
        tablero.ocuparPosicion(posicion, true);
        if (dibujarPieza) {
            dibujar(imagenPieza);
        }
    }

    public void volverAtras(Vector2D movimientoRealizado, boolean dibujarPieza) throws Exception {
        if (dibujarPieza) {
            dibujar(null);
        }
        tablero.ocuparPosicion(posicion, false);
        posicion = Vector2D.sumar(posicion, Vector2D.multiplicar(movimientoRealizado, -1));
        if (dibujarPieza) {
            dibujar(imagenPieza);
        }
    }

    public boolean recorrerTablero() throws Exception {
        return rRecorrerTablero(new LinkedList<Vector2D>(), new Vector2D(0, 0));
    }

    public void visualizarMovimientos(LinkedList<Vector2D> movimientos) throws Exception {
        posicion = movimientos.pollLast();
        while (!movimientos.isEmpty()) {
            dibujar(IMAGEN_CASILLA_VISITADA);
            Thread.sleep(500);
            posicion = movimientos.pollLast();
            dibujar(imagenPieza);
            Thread.sleep(500);
        }
    }

    private boolean rRecorrerTablero(LinkedList<Vector2D> solucion, Vector2D mov) throws Exception {
        mover(mov, false); // mueve la pieza a la nueva posición
        solucion.push(new Vector2D(posicion)); // guarda la posición actual en la solución
        // indMov es el índice que recorre los movimientos de la pieza
        for (int indMov = 0; indMov < movimientos.length; indMov++) {
            // se calcula la hipotética futura posición
            Vector2D futuraPosicion = Vector2D.sumar(posicion, movimientos[indMov]);
            // si la casilla que ocuparía está libre y no se sale del tablero
            if (tablero.isPosicionDelTablero(futuraPosicion) && tablero.isCasillaLibre(futuraPosicion)) {
                // si no ha recorrido todo el tablero
                if (tablero.getCasillasVisitadas() > tablero.getNumCasillas() - 2) {
                    solucion.push(new Vector2D(futuraPosicion)); // guarda la posición final en la solución
                    visualizarMovimientos(solucion);
                    return true;
                } else if (rRecorrerTablero(solucion, movimientos[indMov])) { // en caso contrario sigue recorriendo el tablero
                    return true;
                }
            }
        }
        // si llega aquí es porque no era parte de la solución
        solucion.pop(); // quita el movimiento de la solución
        volverAtras(mov, false); // revierte el movimiento volviendo atrás
        return false;
    }

    public boolean iRecorrerTablero() {
        // transformación a iterativo
        return false;
    }

    public void setPosicion(Vector2D pos) {
        posicion = new Vector2D(pos);
    }

    public Tablero getTablero() {
        return tablero;
    }
}
