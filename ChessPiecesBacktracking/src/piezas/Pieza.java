package piezas;

import elementosBidimensionales.Ajedrez;
import elementosBidimensionales.Casilla;
import elementosBidimensionales.Vector2D;
import elementosBidimensionales.Tablero;
import java.awt.Image;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Arturo y Marta
 */
public abstract class Pieza {

    private final static String IMAGEN_CASILLA_VISITADA = "casillaVisitada.png";
    private final static int TAMANYO_PIEZA = Ajedrez.getPixeles() / Ajedrez.getDimensiones();

    public abstract Vector2D[] movimientos();
    private final Vector2D[] movimientos;

    public abstract String imagenPieza();
    private final String imagenPieza;
    private Vector2D posicion;

    public Vector2D[] getMovimientos() {
        return movimientos;
    }

    public Pieza() {
        Random r = new Random();
        int dim = Ajedrez.getDimensiones();
        posicion = new Vector2D(r.nextInt(dim), r.nextInt(dim));
        movimientos = movimientos();
        imagenPieza = imagenPieza();
    }

    public Pieza(Vector2D posInicial) {
        posicion = new Vector2D(posInicial);
        movimientos = movimientos();
        imagenPieza = imagenPieza();
    }

    private void dibujar(Tablero tablero, String nombreImagen) throws Exception {
        ImageIcon iconoImagen = new ImageIcon(new ImageIcon(nombreImagen).getImage().getScaledInstance(TAMANYO_PIEZA, TAMANYO_PIEZA, java.awt.Image.SCALE_DEFAULT));
        tablero.getCasilla(posicion).setIcon(iconoImagen);
    }

    public void mover(Tablero t, Vector2D movimiento, boolean dibujarPieza) throws Exception {
        if(dibujarPieza){
            dibujar(t, IMAGEN_CASILLA_VISITADA);
        }
        posicion = Vector2D.sumar(posicion, movimiento);
        t.ocuparPosicion(posicion, true);
        if(dibujarPieza){
            dibujar(t, imagenPieza);
        }
    }

    public void volverAtras(Tablero t, Vector2D movimientoRealizado, boolean dibujarPieza) throws Exception {
        if(dibujarPieza){
            dibujar(t, null);
        }
        t.ocuparPosicion(posicion, false);
        posicion = Vector2D.sumar(posicion, Vector2D.multiplicar(movimientoRealizado, -1));
        if(dibujarPieza){
            dibujar(t, imagenPieza);
        }
    }

    public void recorrerTableroEnTiempoReal(Tablero t) throws Exception {
        rRecorrerTableroEnTiempoReal(t, new LinkedList<Vector2D>(), new Vector2D(0, 0));
    }
    
    public void recorrerTableroSolucion(Tablero t) throws Exception {
        rRecorrerTableroSolucion(t, new LinkedList<Vector2D>(), new Vector2D(0, 0));
    }

    private void rRecorrerTableroSolucion(Tablero t, LinkedList<Vector2D> solucion, Vector2D mov) throws Exception {
        mover(t, mov, false); // mueve la pieza a la nueva posición
        solucion.push(new Vector2D(posicion)); // guarda la posición actual en la solución
        // indMov es el índice que recorre los movimientos de la pieza
        for (int indMov = 0; indMov < movimientos.length; indMov++) {
            // se calcula la hipotética futura posición
            Vector2D futuraPosicion = Vector2D.sumar(posicion, movimientos[indMov]);
            // si la casilla que ocuparía está libre y no se sale del tablero
            if (t.isPosicionDelTablero(futuraPosicion) && t.isCasillaLibre(futuraPosicion)) {
                // si no ha recorrido todo el tablero
                if (t.getCasillasVisitadas() > t.getNumCasillas() - 1) {
                    t.visualizarMovimientos(solucion, this);
                    return;
                } else { // en caso contrario sigue recorriendo el tablero
                    rRecorrerTableroSolucion(t, solucion, movimientos[indMov]);
                }
            }
        }
        // si llega aquí es porque no era parte de la solución
        solucion.pop(); // quita el movimiento de la solución
        volverAtras(t, mov, false); // revierte el movimiento volviendo atrás
    }

    /**
     * Versión en tiempo real
     *
     * @param t
     * @param solucion
     * @param mov
     * @throws Exception
     */
    private void rRecorrerTableroEnTiempoReal(Tablero t, LinkedList<Vector2D> solucion, Vector2D mov) throws Exception {
        System.out.println(t.getCasillasVisitadas());
        mover(t, mov, true); // mueve la pieza a la nueva posición
        Thread.sleep(1000);
        solucion.push(new Vector2D(posicion)); // guarda la posición actual en la solución
        // indMov es el índice que recorre los movimientos de la pieza
        for (int indMov = 0; indMov < movimientos.length; indMov++) {
            // se calcula la hipotética futura posición
            Vector2D futuraPosicion = Vector2D.sumar(posicion, movimientos[indMov]);
            // si la casilla que ocuparía está libre y no se sale del tablero
            if (t.isPosicionDelTablero(futuraPosicion) && t.isCasillaLibre(futuraPosicion)) {
                // si no ha recorrido todo el tablero
                if (t.getCasillasVisitadas() > t.getNumCasillas() - 1) {
                    return;
                } else { // en caso contrario sigue recorriendo el tablero
                    rRecorrerTableroEnTiempoReal(t, solucion, movimientos[indMov]);
                }
            }
        }
        // si llega aquí es porque no era parte de la solución
        solucion.pop(); // quita el movimiento de la solución
        volverAtras(t, mov, true); // revierte el movimiento volviendo atrás
        Thread.sleep(1000);
    }

    public void iRecorrerTablero(Tablero t) {
        // transformación a iterativo
    }
}
