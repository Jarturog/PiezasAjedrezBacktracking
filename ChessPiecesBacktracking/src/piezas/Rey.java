package piezas;

import tablero.Tablero;
import tablero.Vector2D;

/**
 *
 * @author Arturo y Marta
 */
public class Rey extends Pieza {

    public Rey(Tablero t, int tam) {
        super(t, tam);
    }

    @Override
    public Vector2D[] movimientos() {
        return new Vector2D[]{new Vector2D(1, 0), new Vector2D(1, 1),
            new Vector2D(0, 1), new Vector2D(-1, 1),
            new Vector2D(-1, 0), new Vector2D(-1, -1),
            new Vector2D(0, -1), new Vector2D(1, -1)};
    }

    @Override
    public String imagenPieza() {
        return "rey.png";
    }
}
