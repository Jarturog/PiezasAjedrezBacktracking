package piezas;

import tablero.Tablero;
import tablero.Vector2D;

/**
 *
 * @author Arturo y Marta
 */
public class Alfil extends Pieza {

    public Alfil(Tablero t, int tam) {
        super(t, tam);
    }

    @Override
    public Vector2D[] movimientos() {
        Vector2D array[] = new Vector2D[4 * super.getTablero().getDIMENSIONES()];
        for (int i = 0, j = 1; i < array.length; j++) {
            array[i++] = new Vector2D(j, j);
            array[i++] = new Vector2D(j, -j);
            array[i++] = new Vector2D(-j, j);
            array[i++] = new Vector2D(-j, -j);
        }
        return array;
    }

    @Override
    public String imagenPieza() {
        return "alfil.png";
    }
}
