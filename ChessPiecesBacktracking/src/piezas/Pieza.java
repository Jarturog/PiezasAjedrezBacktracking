package piezas;

import elementosBidimensionales.Ajedrez;
import elementosBidimensionales.Casilla;
import elementosBidimensionales.Vector2D;
import elementosBidimensionales.Tablero;
import java.awt.Image;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Arturo y Marta
 */
public abstract class Pieza {

    private final static String IMAGEN_CASILLA_VISITADA = "casillaVisitada.png";
    private final static int TAMANYO_PIEZA = Ajedrez.getPixeles() / Ajedrez.getDimensiones();

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
    private Vector2D posicion;

    private final Tablero tablero;

    public Vector2D[] getMovimientos() {
        return movimientos;
    }

    public Pieza(Tablero t) {
        tablero = t;
        Random r = new Random();
        int dim = Ajedrez.getDimensiones();
        posicion = new Vector2D(r.nextInt(dim), r.nextInt(dim));
        movimientos = movimientos();
        imagenPieza = imagenPieza();
    }

    public Pieza(Vector2D posInicial, Tablero t) {
        tablero = t;
        posicion = new Vector2D(posInicial);
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

    public boolean recorrerTableroEnTiempoReal() throws Exception {
        return rRecorrerTableroEnTiempoReal(new LinkedList<Vector2D>(), new Vector2D(0, 0));
    }

    public boolean recorrerTableroSolucion() throws Exception {
        return rRecorrerTableroSolucion(new LinkedList<Vector2D>(), new Vector2D(0, 0));
            
        
    }

    public void visualizarMovimientos(LinkedList<Vector2D> movimientos) throws Exception {
        posicion = movimientos.pollLast();
        while (!movimientos.isEmpty()) {
            dibujar(IMAGEN_CASILLA_VISITADA);
            Thread.sleep(100);
            posicion = movimientos.pollLast();
            dibujar(imagenPieza);
            Thread.sleep(100);
        }
    }

    private boolean rRecorrerTableroSolucion(LinkedList<Vector2D> solucion, Vector2D mov) throws Exception {
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
//                    mover(futuraPosicion, false); // mueve la pieza a la nueva posición
                    solucion.push(new Vector2D(futuraPosicion)); // guarda la posición final en la solución
                    visualizarMovimientos(solucion);
                    return true;
                } else if(rRecorrerTableroSolucion(solucion, movimientos[indMov])){ // en caso contrario sigue recorriendo el tablero
                    return true;
                }
            }
        }
        // si llega aquí es porque no era parte de la solución
        solucion.pop(); // quita el movimiento de la solución
        volverAtras(mov, false); // revierte el movimiento volviendo atrás
        
        return false;
    }

    /**
     * Versión en tiempo real
     *
     * @param t
     * @param solucion
     * @param mov
     * @throws Exception
     */
    private boolean rRecorrerTableroEnTiempoReal(LinkedList<Vector2D> solucion, Vector2D mov) throws Exception {
        System.out.println(tablero.getCasillasVisitadas());
        mover(mov, true); // mueve la pieza a la nueva posición
        Thread.sleep(1000);
        solucion.push(new Vector2D(posicion)); // guarda la posición actual en la solución
        // indMov es el índice que recorre los movimientos de la pieza
        for (int indMov = 0; indMov < movimientos.length; indMov++) {
            // se calcula la hipotética futura posición
            Vector2D futuraPosicion = Vector2D.sumar(posicion, movimientos[indMov]);
            // si la casilla que ocuparía está libre y no se sale del tablero
            if (tablero.isPosicionDelTablero(futuraPosicion) && tablero.isCasillaLibre(futuraPosicion)) {
                // si no ha recorrido todo el tablero
                if (tablero.getCasillasVisitadas() > tablero.getNumCasillas() - 2) {
                    return true;
                } else { // en caso contrario sigue recorriendo el tablero
                    rRecorrerTableroEnTiempoReal(solucion, movimientos[indMov]);
                }
            }
        }
        // si llega aquí es porque no era parte de la solución
        solucion.pop(); // quita el movimiento de la solución
        volverAtras(mov, true); // revierte el movimiento volviendo atrás
        Thread.sleep(1000);
        return false;
    }

    public void iRecorrerTablero(Tablero t) {
        // transformación a iterativo
    }

    public void setPosicion(Vector2D pos) {
        posicion = new Vector2D(pos);
    }
}
