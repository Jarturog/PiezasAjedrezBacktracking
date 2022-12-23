package gráficos;

import javax.swing.JFrame;

/**
 *
 * @author Arturo y Marta
 */
public class VentanaGrafica extends JFrame {

    Tablero tablero;

    public VentanaGrafica() {
        super("Ajedrez");
        tablero = new Tablero(8);
        getContentPane().add(tablero);
        setSize(tablero.getPreferredSize());
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        VentanaGrafica esc = new VentanaGrafica();
        esc.setVisible(true);
    }
}
