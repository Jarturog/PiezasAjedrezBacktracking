package elementosBidimensionales;

import java.awt.Color;

import javax.swing.JLabel;

/**
 *
 * @author Arturo y Marta
 */
public class Casilla extends JLabel {

    private boolean ocupada;

    public Casilla(Color c) {
        setBackground(c);
        setOpaque(true);
        ocupada = false;
    }

    public boolean estaOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean b) {
        ocupada = b;
    }
}
