package piezas;

import gráficos.Vector2D;
import gráficos.Tablero;
import java.util.LinkedList;

/**
 *
 * @author Arturo y Marta
 */
public abstract class Pieza {

    public abstract Vector2D[] movimientos();
    private Vector2D posicion;
    private static Vector2D[] movs;

    public Pieza(Vector2D posInicial) {
        posicion = new Vector2D(posInicial);
    }

    public void mover(Tablero t, Vector2D movimiento) throws Exception {
        posicion = Vector2D.sumar(posicion, movimiento);
        t.ocuparPosicion(movimiento);
    }
    
    public void volverAtras(Tablero t, Vector2D movimientoRealizado) throws Exception{
        t.desOcuparPosicion(posicion);
        posicion = Vector2D.sumar(posicion, Vector2D.multiplicar(movimientoRealizado, -1));
    }

    public boolean movimientoLegal(Tablero t, Vector2D futuraPosicion) {
        return !(futuraPosicion.getX() > t.getDIMENSION() - 1
                || futuraPosicion.getY() > t.getDIMENSION() - 1
                || futuraPosicion.getX() < 0 || futuraPosicion.getY() < 0);
    }

    public void recorrerTablero(Tablero t) throws Exception {
        rRecorrerTablero(t, new LinkedList<Vector2D>(), new Vector2D(0,0));
    }

    private void rRecorrerTablero(Tablero t, LinkedList<Vector2D> solucion, Vector2D mov) throws Exception {
        mover(t, mov); // mueve la pieza a la nueva posición
        solucion.push(new Vector2D(posicion)); // guarda la posición actual en la solución
        // indMov es el índice que recorre los movimientos de la pieza
        for (int indMov = 0; indMov < movs.length; indMov++) {
            // se calcula la hipotética futura posición
            Vector2D futuraPosicion = Vector2D.sumar(posicion, movs[indMov]); 
            // si la casilla que ocuparía está libre y no se sale del tablero
            if (t.casillaLibre(futuraPosicion) && movimientoLegal(t, futuraPosicion)) {
                // si no ha recorrido todo el tablero
                if (t.getCasillasVisitadas() > t.getNumCasillas() - 1) {
                    t.visualizar(solucion);
                    return;
                } else { // en caso contrario sigue recorriendo el tablero
                    rRecorrerTablero(t, solucion, movs[indMov]);
                }
            }
        }
        // si llega aquí es porque no era parte de la solución
        solucion.pop(); // quita el movimiento de la solución
        volverAtras(t, mov); // revierte el movimiento volviendo atrás
    }
    
    public void iRecorrerTablero(Tablero t) {
        // transformación a iterativo
    }
}
