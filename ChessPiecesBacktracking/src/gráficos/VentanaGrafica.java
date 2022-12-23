package gráficos;

import javax.swing.JFrame;
import piezas.Caballo;

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

    public static void main(String[] args) throws Exception {
        VentanaGrafica ventana = new VentanaGrafica();
        ventana.setVisible(true);
        Caballo caballo = new Caballo(new Vector2D(ventana.tablero.getDIMENSION() / 2,
                ventana.tablero.getDIMENSION() / 2));
        caballo.recorrerTablero(ventana.tablero);
    }
}
