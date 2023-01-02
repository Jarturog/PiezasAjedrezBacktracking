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
    private JPanel panelRecorridos;

    private final int DIMENSIONES = 8;

    public static void main(String[] args) throws Exception {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("No se ha podido establecer el formato de su plataforma" + e);
        }
        Ajedrez aj = new Ajedrez();
        aj.visualizarRecorridoTablero();
//        aj.visualizarRecorridoTableroMovimientosCorrectos();
    }
    
    public Ajedrez() {
        ventana = new JFrame("Ajedrez");
        ventana.setPreferredSize(new Dimension(640, 480));
        ventana.getContentPane().setLayout(new BorderLayout());

        tablero = new Tablero(DIMENSIONES);
        tablero.setLayout(new GridLayout(DIMENSIONES, DIMENSIONES));
        tablero.setOpaque(true);
        ventana.getContentPane().add(tablero, java.awt.BorderLayout.CENTER);

        panelRecorridos = new JPanel();

        ventana.setLocationRelativeTo(null);
        ventana.pack();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);

    }
    
    private void visualizarRecorridoTablero() throws Exception {

//        Caballo caballo = new Caballo(new Vector2D(0)); //es una prueba
        ImageIcon imagen = new ImageIcon("Caballo.png");
        ImageIcon nuevaImagen = redimensionarImagen(imagen);

//        casillas[1].setImagen(nuevaImagen);
//        tablero.add(casillas[0]);

        ventana.setVisible(true);

//        caballo.recorrerTablero(ventana.tablero);
    }

//    private void actualizarPantalla() {
//        panelRecorridos.removeAll();
//
//    }

    private ImageIcon redimensionarImagen(ImageIcon imagen) {

        Image image = imagen.getImage(); // transforma ImageIcon a image
        Image newImg = image.getScaledInstance(115, 115, java.awt.Image.SCALE_DEFAULT);
        imagen = new ImageIcon(newImg);  // transforma  Image a imageIcon
        return imagen;
    }
}
