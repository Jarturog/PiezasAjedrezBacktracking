package piezas;

import gráficos.Vector2D;

/**
 *
 * @author Arturo y Marta
 */
public class Caballo extends Pieza {
    private static int movx[] = {
    public static Vector2D movimientos = {new Vector2D(2, 1), new Vector2D(1, 2),
            new Vector2D(-1, 2), new Vector2D(-2, 1),
            new Vector2D(-2, -1), new Vector2D(-1, -2),
            new Vector2D(1, -2), new Vector2D(2, -1)};
    public Caballo(Vector2D posInicial) {
        super(posInicial);
    }

    @Override
    public static Vector2D[] movimientos() {
        return new Vector2D[]
    }
}
