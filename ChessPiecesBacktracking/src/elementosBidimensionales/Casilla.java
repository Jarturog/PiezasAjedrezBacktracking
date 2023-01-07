package elementosBidimensionales;

import java.awt.Color;
import java.util.LinkedList;
import javax.swing.ImageIcon;



import javax.swing.JLabel;

/**
 *
 * @author Arturo y Marta
 */
public class Casilla extends JLabel {

    private final Color color;
    private boolean ocupada;

    public Casilla(Color c) {
        this.color = c;
        setBackground(color);
        setOpaque(true);
        ocupada = false;
    }
    public void setImagen(ImageIcon imagen){
        setBackground(color);
        setIcon(imagen);
        setOpaque(true);
    }

    public boolean estaOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean b) {
        ocupada = b;
    }
}
