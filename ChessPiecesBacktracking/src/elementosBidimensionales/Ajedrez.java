package elementosBidimensionales;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import piezas.Caballo;

/**
 *
 * @author Arturo y Marta
 */
public class Ajedrez {

    private JFrame ventana;
    private Tablero tablero;
    private final int DIMENSIONES = 8;

    public static void main(String[] args) throws Exception {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("No se ha podido establecer el formato de su plataforma" + e);
        }
        Ajedrez aj = new Ajedrez();
        Caballo c = new Caballo(new Vector2D(4, 4));
        c.recorrerTablero(aj.tablero);
//        aj.recorrerTableroMovimientosCorrectos();
    }
    
    public Ajedrez() {
        ventana = new JFrame("Ajedrez");
        ventana.setPreferredSize(new Dimension(920, 920));
        ventana.getContentPane().setLayout(new BorderLayout());

        tablero = new Tablero(DIMENSIONES);
        tablero.setLayout(new GridLayout(DIMENSIONES, DIMENSIONES));
//        tablero.setOpaque(true);
        ventana.getContentPane().add(tablero, java.awt.BorderLayout.CENTER);

        ventana.setLocationRelativeTo(null);
        ventana.pack();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);

    }

//    private void actualizarPantalla() {
//        panelRecorridos.removeAll();
//
//    }

}
