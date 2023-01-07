package piezas;

import elementosBidimensionales.Tablero;
import elementosBidimensionales.Vector2D;

/**
 *
 * @author Arturo y Marta
 */
public class Campeon extends Pieza {

    public Campeon(Tablero t) {
        super(t);
    }

    @Override
    public Vector2D[] movimientos() {
        return new Vector2D[]{new Vector2D(2, 2), new Vector2D(2, -2),
            new Vector2D(-2, 2), new Vector2D(-2, -2),
            new Vector2D(2, 0), new Vector2D(-2, 0),
            new Vector2D(0, 2), new Vector2D(0, -2),
            new Vector2D(1, 0), new Vector2D(-1, 0),
            new Vector2D(0, 1), new Vector2D(0, -1)};
    }

    @Override
    public String imagenPieza() {
        return "campeon.png";
    }

}
