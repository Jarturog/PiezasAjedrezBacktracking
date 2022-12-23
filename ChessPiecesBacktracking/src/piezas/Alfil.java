package piezas;

import elementosBidimensionales.Vector2D;

/**
 *
 * @author Arturo y Marta
 */
public class Alfil extends Pieza {
    
    public Alfil(Vector2D posInicial) {
        super(posInicial);
    }

    @Override
    public Vector2D[] movimientos() {
        return new Vector2D[]{new Vector2D(1, 1), new Vector2D(-1, 1),
            new Vector2D(-1, 1), new Vector2D(1, -1)};
    }
    
}
