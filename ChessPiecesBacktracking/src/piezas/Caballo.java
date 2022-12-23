package piezas;

import gráficos.Vector2D;

/**
 *
 * @author Arturo y Marta
 */
public class Caballo extends Pieza {

    public Caballo(Vector2D posInicial) {
        super(posInicial);
    }

    @Override
    public Vector2D[] movimientos() {
        return new Vector2D[]{new Vector2D(2, 1), new Vector2D(1, 2),
            new Vector2D(-1, 2), new Vector2D(-2, 1),
            new Vector2D(-2, -1), new Vector2D(-1, -2),
            new Vector2D(1, -2), new Vector2D(2, -1)};
    }
}
