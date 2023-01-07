package piezas;

import elementosBidimensionales.Vector2D;

/**
 *
 * @author Arturo y Marta
 */
public class Torre extends Pieza {
    
    @Override
    public Vector2D[] movimientos() {
        return new Vector2D[]{new Vector2D(1, 0), new Vector2D(0, 1),
            new Vector2D(-1, 0), new Vector2D(0, -1)};
    }

    @Override
    public String imagenPieza() {
        return "torre.png";
    }
}
