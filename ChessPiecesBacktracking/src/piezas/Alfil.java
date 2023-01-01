package piezas;

import elementosBidimensionales.Vector2D;
import javax.swing.ImageIcon;

/**
 *
 * @author Arturo y Marta
 */
public class Alfil extends Pieza {
    private ImageIcon imagen = new ImageIcon("Alfil.png");
 
    public Alfil(Vector2D posInicial) {
        super(posInicial);
    }
      public ImageIcon getImagen(){
       return imagen; 
    }

    @Override
    public Vector2D[] movimientos() {
        return new Vector2D[]{new Vector2D(1, 1), new Vector2D(-1, 1),
            new Vector2D(-1, 1), new Vector2D(1, -1)};
    }
    
}
