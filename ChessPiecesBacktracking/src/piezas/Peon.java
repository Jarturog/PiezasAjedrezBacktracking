package piezas;

import tablero.Tablero;
import tablero.Vector2D;

/**
 *
 * @author Arturo y Marta
 */
public class Peon extends Pieza {

    public Peon(Tablero t, int tam) {
        super(t, tam);
    }
    
    @Override
    public Vector2D[] movimientos() {
        return new Vector2D[]{new Vector2D(0, 1), new Vector2D(0, -1), // avanzar color 1 y 2
            new Vector2D(1, 1), new Vector2D(-1, 1), // matar en diagonal color 1
            new Vector2D(1, -1), new Vector2D(-1, -1), // matar en diagonal color 2
            new Vector2D(0, 2), new Vector2D(0, -2)}; // primer movimiento
    }

    @Override
    public String imagenPieza() {
        return "peon.png";
    }
}
