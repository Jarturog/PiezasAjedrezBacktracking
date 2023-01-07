package piezas;

import elementosBidimensionales.Casilla;
import elementosBidimensionales.Vector2D;
import elementosBidimensionales.Tablero;
import java.awt.Image;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Arturo y Marta
 */
public abstract class Pieza {

    private final static String CASILLA_VISITADA = "casillaVisitada.png";

    public abstract Vector2D[] movimientos();
    private final Vector2D[] movimientos;

    public abstract String imagenPieza();
    private final String imagenPieza;
    private Vector2D posicion;

    public Vector2D[] getMovimientos() {
        return movimientos;
    }

    public Pieza(Vector2D posInicial) {
        posicion = new Vector2D(posInicial);
        movimientos = movimientos();
        imagenPieza = imagenPieza();
    }

    private ImageIcon redimensionarImagen(ImageIcon imagen) {

        Image image = imagen.getImage(); // transforma ImageIcon a image
        Image newimg = image.getScaledInstance(115, 115, java.awt.Image.SCALE_DEFAULT);
        imagen = new ImageIcon(newimg);  // transforma  Image a imageIcon
        return imagen;
    }

    private void dibujar(Tablero tablero, String nombreImagen) throws Exception {
        ImageIcon iconoImagen = new ImageIcon(new ImageIcon(nombreImagen).getImage().getScaledInstance(115, 115, java.awt.Image.SCALE_DEFAULT));
        Casilla c = tablero.getCasilla(posicion);
        c.setImagen(iconoImagen);
//         ImageIcon iconoImagen = new ImageIcon(nombreImagen);
//        redimensionarImagen(iconoImagen);
    }

    public void moverGrafico(Tablero t, Vector2D movimiento) throws Exception {
        dibujar(t, CASILLA_VISITADA);
        posicion = Vector2D.sumar(posicion, movimiento);
        t.ocuparPosicion(posicion);
        dibujar(t, imagenPieza);
    }

    public void moverLogico(Tablero t, Vector2D movimiento) throws Exception {
        posicion = Vector2D.sumar(posicion, movimiento);
        t.ocuparPosicion(posicion);
    }

    public void volverAtrasGrafico(Tablero t, Vector2D movimientoRealizado) throws Exception {
        dibujar(t, null);
        t.desOcuparPosicion(posicion);
        posicion = Vector2D.sumar(posicion, Vector2D.multiplicar(movimientoRealizado, -1));
        dibujar(t, imagenPieza);
    }

    public void volverAtrasLogico(Tablero t, Vector2D movimientoRealizado) throws Exception {
        t.desOcuparPosicion(posicion);
        posicion = Vector2D.sumar(posicion, Vector2D.multiplicar(movimientoRealizado, -1));
    }

    public void recorrerTablero(Tablero t) throws Exception {
        rRecorrerTablero(t, new LinkedList<Vector2D>(), new Vector2D(0, 0));
    }

    private void rRecorrerTableroSolucion(Tablero t, LinkedList<Vector2D> solucion, Vector2D mov, int i) throws Exception {
        moverLogico(t, mov); // mueve la pieza a la nueva posición
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
                    rRecorrerTableroSolucion(t, solucion, movimientos[indMov], i + 1);
                }
            }
        }
        // si llega aquí es porque no era parte de la solución
        solucion.pop(); // quita el movimiento de la solución
        volverAtrasLogico(t, mov); // revierte el movimiento volviendo atrás
    }

    /**
     * Versión en tiempo real
     *
     * @param t
     * @param solucion
     * @param mov
     * @throws Exception
     */
    private void rRecorrerTablero(Tablero t, LinkedList<Vector2D> solucion, Vector2D mov) throws Exception {
        System.out.println(t.getCasillasVisitadas());
        moverGrafico(t, mov); // mueve la pieza a la nueva posición
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
                    //                t.visualizar(solucion);
                    return;
                } else { // en caso contrario sigue recorriendo el tablero
                    rRecorrerTablero(t, solucion, movimientos[indMov]);
                }
            }
        }
        // si llega aquí es porque no era parte de la solución
        solucion.pop(); // quita el movimiento de la solución
        volverAtrasGrafico(t, mov); // revierte el movimiento volviendo atrás
        Thread.sleep(1000);
    }

    public void iRecorrerTablero(Tablero t) {
        // transformación a iterativo
    }
}
