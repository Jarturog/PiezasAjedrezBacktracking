    package piezas;

import tablero.Tablero;
import tablero.Vector2D;

/**
 *
 * @author Arturo y Marta
 */
public class Torre extends Pieza {

    public Torre(Tablero t) {
        super(t);
    }
    
    @Override
    public Vector2D[] movimientos() {
        Vector2D array[] = new Vector2D[4 * super.getTablero().getDIMENSIONES()];
        for (int i = 0, j = 1; i < array.length; j++) {
            array[i++] = new Vector2D(j, 0);
            array[i++] = new Vector2D(0, -j);
            array[i++] = new Vector2D(-j, 0);
            array[i++] = new Vector2D(0, j);
        }
        return array;
    }

    @Override
    public String imagenPieza() {
        return "torre.png";
    }
}
