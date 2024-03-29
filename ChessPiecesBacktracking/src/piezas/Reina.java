package piezas;

import tablero.Tablero;
import tablero.Vector2D;

/**
 *
 * @author Arturo y Marta
 */
public class Reina extends Pieza {

    public Reina(Tablero t, int tam) {
        super(t, tam);
    }

    @Override
    public Vector2D[] movimientos() {
        Vector2D array[] = new Vector2D[8 * super.getTablero().getDIMENSIONES()];
        for (int i = 0, j = 1; i < array.length; j++) {
            array[i++] = new Vector2D(j, j);
            array[i++] = new Vector2D(j, -j);
            array[i++] = new Vector2D(-j, j);
            array[i++] = new Vector2D(-j, -j);
            array[i++] = new Vector2D(j, 0);
            array[i++] = new Vector2D(0, -j);
            array[i++] = new Vector2D(-j, 0);
            array[i++] = new Vector2D(0, j);
        }
        return array;
    }

    @Override
    public String imagenPieza() {
        return "reina.png";
    }
}
