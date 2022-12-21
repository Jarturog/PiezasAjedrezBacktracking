package piezas;

import gráficos.Vector2D;

/**
 *
 * @author Arturo y Marta
 */
public class Peon extends Pieza {
    
    //private static int[][] movimientos;
    movimientos = new Vector2D[4];
    movimientos[0] = new Vector2D(1,2);
        
    public Peon(Vector2D posInicial) {
        super(posInicial);
        
    }
}
