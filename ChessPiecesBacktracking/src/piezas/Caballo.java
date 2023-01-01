package piezas;

import elementosBidimensionales.Vector2D;
import javax.swing.ImageIcon;

/**
 *
 * @author Arturo y Marta
 */
public class Caballo extends Pieza {
    
   private ImageIcon imagen = new ImageIcon("Caballo.png");
    public Caballo(Vector2D posInicial) {
        super(posInicial);
    }
    public ImageIcon getImagen(){
       return imagen; 
    }

    @Override
    public Vector2D[] movimientos() {
        return new Vector2D[]{new Vector2D(2, 1), new Vector2D(1, 2),
            new Vector2D(-1, 2), new Vector2D(-2, 1),
            new Vector2D(-2, -1), new Vector2D(-1, -2),
            new Vector2D(1, -2), new Vector2D(2, -1)};
    }
}
