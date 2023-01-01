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
public class VentanaGrafica extends JFrame {


    private JFrame ventana;
    public JPanel tablero, panelRecorridos;
    private Casilla[] casillas;

    public void inicializacion() {

        ventana = new JFrame("Ajedrez");
        Dimension d = new Dimension();
        d.setSize(920,920);
        ventana.setPreferredSize(d);

        Container panelContenidos = ventana.getContentPane();
        panelContenidos.setLayout(new BorderLayout());

        tablero = new JPanel();
        tablero.setLayout(new GridLayout(8, 8));
        dibujarTablero();
        tablero.setOpaque(true);
        panelContenidos.add(tablero, java.awt.BorderLayout.CENTER);

        panelRecorridos = new JPanel();

        ventana.setLocationRelativeTo(null);
        ventana.pack();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);

    }

    public static void main(String[] args) throws Exception {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("No se ha podido establecer el formato de su plataforma" + e);

        }
        VentanaGrafica v = new VentanaGrafica();
        v.inicializacion();


        v.visualizacionMoviminetos();
    }

    private void dibujarTablero() {
        casillas = new Casilla[8 * 8];
        int c = 0;
        for (int i = 0; i < casillas.length; i++) {
            if ((i != 0) && i % 8 == 0) {
                c = negar(c);
            }
            switch (c) {
                case 0:
                    casillas[i] = new Casilla(Color.WHITE);
                    tablero.add(casillas[i]);
                    c = negar(c);

                    break;

                case 1:
                    casillas[i] = new Casilla(Color.BLACK);
                    tablero.add(casillas[i]);
                    c = negar(c);

                    break;

            }
        }
    }

    private int negar(int c) {
        if (c == 0) {
            c = 1;
        } else {
            c = 0;
        }
        return c;
    }

    private void visualizacionMoviminetos() throws Exception {

//        Caballo caballo = new Caballo(new Vector2D(0)); //es una prueba
       
         ImageIcon imagen = new ImageIcon("Caballo.png");
        ImageIcon nuevaImagen = redimensionarImagen(imagen);
        
        casillas[1].setImagen(nuevaImagen);
//        tablero.add(casillas[0]);
        

        
        
        
        
        
        ventana.setVisible(true);

//        caballo.recorrerTablero(ventana.tablero);
    }
    private void actualizarPantalla(){
        panelRecorridos.removeAll();
        
    }

    private ImageIcon redimensionarImagen(ImageIcon imagen) {

        Image image = imagen.getImage(); // transforma ImageIcon a image
        Image newImg = image.getScaledInstance(115, 115, java.awt.Image.SCALE_DEFAULT);
        imagen = new ImageIcon(newImg);  // transforma  Image a imageIcon
        return imagen;
    }
}
