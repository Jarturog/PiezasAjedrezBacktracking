package tablero;

import java.awt.Color;
import javax.swing.JLabel;

/**
 * Una casilla es un JLabel (etiqueta) que además permite indicar si está
 * ocupada o no por una pieza.
 *
 * @author Arturo y Marta
 */
public class Casilla extends JLabel {

    /**
     * true si está ocupada, false en caso contrario.
     */
    private boolean ocupada;

    /**
     * Constructor que incializa la casilla con el color indicado, no ocupada y
     * la hace visible.
     *
     * @param c el color que tendrá la casilla.
     */
    public Casilla(Color c) {
        setBackground(c);
        setOpaque(true);
        ocupada = false;
    }

    /**
     * Getter del atributo ocupada.
     * @return true si está ocupada, false en caso contrario.
     */
    public boolean estaOcupada() {
        return ocupada;
    }

    /**
     * Setter del atributo ocupada.
     * @param b true si se quiere ocupar, false si se quiere desocupar.
     */
    public void setOcupada(boolean b) {
        ocupada = b;
    }
}
