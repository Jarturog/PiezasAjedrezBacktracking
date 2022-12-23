package piezas;

import gráficos.Vector2D;

/**
 *
 * @author Arturo y Marta
 */
public class Torre extends Pieza {
    
    public Torre(Vector2D posInicial) {
        super(posInicial);
    }
    
    @Override
    public Vector2D[] movimientos() {
        return new Vector2D[]{new Vector2D(1, 0), new Vector2D(0, 1),
            new Vector2D(-1, 0), new Vector2D(0, -1)};
    }
}
