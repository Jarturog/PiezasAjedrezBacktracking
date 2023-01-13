package piezas;

import tablero.Tablero;
import tablero.Vector2D;

/**
 *
 * @author Arturo y Marta
 */
public class Caballo extends Pieza {

    public Caballo(Tablero t, int tam) {
        super(t, tam);
    }
    
    @Override
    public Vector2D[] movimientos() {
        return new Vector2D[]{new Vector2D(2, 1), new Vector2D(1, 2),
            new Vector2D(-1, 2), new Vector2D(-2, 1),
            new Vector2D(-2, -1), new Vector2D(-1, -2),
            new Vector2D(1, -2), new Vector2D(2, -1)};
    }

    @Override
    public String imagenPieza() {
        return "caballo.png";
    }
}
