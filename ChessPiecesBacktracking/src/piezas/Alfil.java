package piezas;

import elementosBidimensionales.Tablero;
import elementosBidimensionales.Vector2D;
import javax.swing.ImageIcon;

/**
 *
 * @author Arturo y Marta
 */
public class Alfil extends Pieza {

    public Alfil(Tablero t) {
        super(t);
    }

    @Override
    public Vector2D[] movimientos() {
        return new Vector2D[]{new Vector2D(1, 1), new Vector2D(-1, 1),
            new Vector2D(-1, 1), new Vector2D(1, -1)};
    }

    @Override
    public String imagenPieza() {
        return "alfil.png";
    }
    
}
