package elementosBidimensionales;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import piezas.Caballo;
import piezas.*;

/**
 *
 * @author Arturo y Marta
 */
public class Ajedrez extends JFrame implements Runnable {

    private Ajedrez esto = this;
    private Container panelContenidos;
    private Tablero tablero;
    private final int DIMENSIONES = 8;
    private final static int PIXELES = 640;
    private final int NUM_PIEZAS = 7;
    private final JButton[] botonesPiezas = new JButton[NUM_PIEZAS];
    private final Pieza[] piezas = new Pieza[NUM_PIEZAS];
    private Pieza piezaActual;
    private final ImageIcon imagenAjedrez = new ImageIcon("Ajedrez.png");
    private JPanel panelInterfaces, panelStandBy, panelBotones;

    private final Vector2D posicionInicial = new Vector2D(DIMENSIONES / 2, DIMENSIONES / 2);

    public static void main(String[] args) throws Exception {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("No se ha podido establecer el formato de su plataforma" + e);
        }
        Ajedrez aj = new Ajedrez();
//        Caballo c = new Caballo(new Vector2D(4, 4));
//        c.recorrerTablero(aj.tablero);
//        aj.recorrerTableroMovimientosCorrectos();
    }

    public Ajedrez() {
        setTitle("Ajedrez");
        panelContenidos = getContentPane();

        panelContenidos.setLayout(new CardLayout());
        
        
        panelInterfaces = new JPanel();
        panelInterfaces.setLayout(new CardLayout());
        panelContenidos.add(panelInterfaces, BorderLayout.CENTER);
        panelStandBy = new JPanel();
        panelStandBy.setLayout(new BorderLayout());
        ImageIcon nuevaImagen = redimensionarImagen(imagenAjedrez);
        JLabel etiquetaImagen = new JLabel(nuevaImagen);

        panelStandBy.add(etiquetaImagen, BorderLayout.CENTER);
        panelInterfaces.add(panelStandBy);
        inicializarBotonesYPiezas();
        panelStandBy.setVisible(true);
        
        tablero = new Tablero(DIMENSIONES);
//        tablero.setLayout(new GridLayout(DIMENSIONES, DIMENSIONES));
//        tablero.setVisible(false);
        panelInterfaces.add(tablero);

        setLocationRelativeTo(null);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void inicializarTablero(Pieza p) {
        ((CardLayout) panelContenidos.getLayout()).last(panelContenidos);
        try {
            panelStandBy.setVisible(false);
             tablero.setLayout(new GridLayout(DIMENSIONES, DIMENSIONES));
            tablero.setVisible(true);
            p.recorrerTablero(tablero);
        } catch (Exception ex) {
            System.err.println("Error malo" + ex.getMessage());
        }
    }

    private void inicializarBotonesYPiezas() {

        panelBotones = new JPanel();
        panelBotones.setBackground(Color.black);
        panelBotones.setLayout(new GridLayout(1, 6));

        Pieza[] piezas = new Pieza[NUM_PIEZAS];
        piezas[0] = new Peon(posicionInicial);
        piezas[1] = new Torre(posicionInicial);
        piezas[2] = new Caballo(posicionInicial);
        piezas[3] = new Alfil(posicionInicial);
        piezas[4] = new Reina(posicionInicial);
        piezas[5] = new Rey(posicionInicial);
        piezas[6] = new Peon(posicionInicial); // Especial

        botonesPiezas[0] = new JButton("Peón");
        botonesPiezas[1] = new JButton("Torre");
        botonesPiezas[2] = new JButton("Caballo");
        botonesPiezas[3] = new JButton("Alfil");
        botonesPiezas[4] = new JButton("Reina");
        botonesPiezas[5] = new JButton("Rey");
        botonesPiezas[6] = new JButton("Especial");

        for (int i = 0; i < NUM_PIEZAS; i++) {
            vincularAccion(i, piezas);
            botonesPiezas[i].setBackground(Color.black);
            botonesPiezas[i].setForeground(Color.white);

            panelBotones.add(botonesPiezas[i]);
        }
        panelContenidos.add(panelBotones, java.awt.BorderLayout.PAGE_START);;
    }

    /**
     * Vincula el botón de botonesPiezas con el índice 'i' con un ActionListener
     * que llamará a recorrerTablero pasándole la pieza con el índice 'i' por
     * parámetro.
     *
     * Al intentar deshacerse del método aparte y pasarlo a código dentro del
     * bucle original (la utilización con la que se pensó) salta el error de
     * "local variables referenced from an inner class must be final or
     * effectively final". Por ello la creación del método auxiliar.
     *
     * @param i índice de botonesPiezas y de piezas.
     * @param piezas array de Pieza que corresponde 1 a 1 al array de
     * botonesPiezas, siendo cada Pieza la correspondiente a la que representa
     * presionar el botón del mismo índice.
     */
    private void vincularAccion(int i, Pieza[] piezas) {
        botonesPiezas[i].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piezaActual = piezas[i];
                System.out.println("bbb");
                new Thread(esto).start();
                System.out.println("ccc");
            }
        });
    }

    @Override
    public void run() {
        try {
            ((CardLayout) panelContenidos.getLayout()).last(panelContenidos);
            piezaActual.recorrerTablero(tablero);
        } catch (Exception ex) {
            System.err.println("Error malo" + ex.getMessage());
        }
    }

    private ImageIcon redimensionarImagen(ImageIcon imagen) {

        Image image = imagen.getImage(); // transforma ImageIcon a image
        Image newimg = image.getScaledInstance(920, 920, java.awt.Image.SCALE_DEFAULT);
        imagen = new ImageIcon(newimg);  // transforma  Image a imageIcon
        return imagen;
    }
}
