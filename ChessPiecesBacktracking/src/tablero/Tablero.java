package tablero;

import java.awt.Color;
import javax.swing.JPanel;

/**
 * Tablero de una partida de Ajedrez. Es un JPanel y contiene casillas, en
 * concreto d^2 casillas siendo 'd' las dimensiones que se hayan elegido.
 *
 * @author Arturo y Marta
 */
public class Tablero extends JPanel {

    /**
     * Las dimensiones del tablero. En un tablero de ajdrez típico la variable
     * valdría 8.
     */
    private final int DIMENSIONES;
    /**
     * Cantidad de casillas visitadas en un momento determinado.
     */
    private int casillasVisitadas;
    /**
     * Conjunto de casillas que forman el tablero.
     */
    private final Casilla[] casillas;

    /**
     * Constructor al que se le pasan las dimensiones para crear un tablero de
     * dimensiones x dimensiones casillas intercalando blanco y negro como color
     * de la casilla.
     *
     * @param dimensiones filas y columnas que tendrá el tablero.
     */
    public Tablero(int dimensiones) {
        DIMENSIONES = dimensiones;
        casillas = new Casilla[dimensiones * dimensiones];
        boolean blanco = true;
        for (int i = 0; i < dimensiones; i++) {
            for (int j = 0; j < dimensiones; j++) {
                Vector2D v = new Vector2D(j, i);
                if (blanco) {
                    casillas[getIndexCasilla(v)] = new Casilla(Color.WHITE);
                } else {
                    casillas[getIndexCasilla(v)] = new Casilla(Color.BLACK);
                }
                add(casillas[getIndexCasilla(v)]);
                blanco = !blanco;
            }
            if (DIMENSIONES % 2 == 0) { // el paso extra asegura que no se 
                blanco = !blanco; // dibujen líneas negras y blancas en el 
            }                                    // tablero sino cuadrados
        }
    }

    /**
     * Calcula el índice de la casilla a partir de un vector que representa su
     * posición bidimensional.
     *
     * @param v posición de la casilla en formato vectorial.
     * @return el índice del array de casillas que corresponde a la posición
     * pasada por parámetro.
     */
    private int getIndexCasilla(Vector2D v) {
        return v.getX() + v.getY() * DIMENSIONES;
    }

    /**
     * Consigue la casilla a partir de la posición que debería ocupar en un
     * plano bidimensional.
     *
     * @param posicion Vector2D siendo el primer elemento la fila y el segundo
     * la columna.
     * @return objeto casilla que reside en la posición indicada
     * @throws IndexOutOfBoundsException en el caso de que se intente acceder a
     * una casilla que está fuera del tablero, que no existe.
     */
    public Casilla getCasilla(Vector2D posicion) throws IndexOutOfBoundsException {
        if(!isPosicionDelTablero(posicion)){
            throw new IndexOutOfBoundsException("Intentando acceder a una casilla que está fuera del tablero y que por lo tanto es inexistente. ");
        }
        return casillas[getIndexCasilla(posicion)];
    }

    /**
     * Método con doble propósito, si ocupar vale true se marcará como ocupada
     * la casilla indicada por la posición y el valor total de casillas
     * visitadas aumentará, en caso de que ocupar valga false se interpretará
     * que se quiere liberar una casilla, se desocupa la casilla indicada y
     * disminuye el total de casillas visitadas.
     *
     * @param pos Vector2D que representa el lugar donde está la casilla que se
     * va a ocupar/desocupar
     * @param ocupar booleano que vale true si se quiere ocupar y false si se
     * quiere desocupar una casilla
     */
    public void ocuparPosicion(Vector2D pos, boolean ocupar) {
        int suma = 1; // se inicializa suma a 1
        if (!ocupar) { // en caso de que se vaya a desocupar una casilla se asigna -1
            suma = -1; // para restar en vez de sumar una casilla visitada
        }
        getCasilla(pos).setOcupada(ocupar); //
        casillasVisitadas += suma;
    }

    public boolean isPosicionDelTablero(Vector2D posicion) {
        return !(posicion.getX() > DIMENSIONES - 1
                || posicion.getY() > DIMENSIONES - 1
                || posicion.getX() < 0 || posicion.getY() < 0);
    }

    public boolean isCasillaLibre(Vector2D pos) throws IndexOutOfBoundsException {
        return !getCasilla(pos).estaOcupada();
    }

    public int getDIMENSIONES() {
        return DIMENSIONES;
    }

    public int getNumCasillas() {
        return DIMENSIONES * DIMENSIONES;
    }

    public int getCasillasVisitadas() {
        return casillasVisitadas;
    }

    public void limpiar() {
        for (Casilla casilla : casillas) {
            casilla.setIcon(null);
            casilla.setOcupada(false);
            casillasVisitadas = 0;
        }
    }
}
