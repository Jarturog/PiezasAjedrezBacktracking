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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import piezas.Caballo;
import piezas.*;

/**
 *
 * @author Arturo y Marta
 */
public class Ajedrez extends JFrame implements Runnable {

    private final Ajedrez esto = this;
    private final Container panelContenidos;
    private final Tablero tablero;
    private final static int DIMENSIONES = 8;
    private final static int PIXELES = 640;
    private final int NUM_PIEZAS = 7;
    private final JButton[] botonesPiezas = new JButton[NUM_PIEZAS];
    private Pieza piezaActual;
//    private final ImageIcon imagenAjedrez = new ImageIcon("Ajedrez.png");  NO E NECESARIO
    private final JPanel panelBotones;
    private JOptionPane opcion;

    public static void main(String[] args) throws Exception {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("No se ha podido establecer el formato de su plataforma" + e);
        }
        Ajedrez aj = new Ajedrez();
    }

    public Ajedrez() {
        setTitle("Ajedrez");
        panelContenidos = getContentPane();
        panelContenidos.setLayout(new BorderLayout());

        tablero = new Tablero(DIMENSIONES);
        tablero.setLayout(new GridLayout(DIMENSIONES, DIMENSIONES));
        panelContenidos.add(tablero, BorderLayout.CENTER);

        panelBotones = new JPanel();
        panelBotones.setBackground(Color.black);
        panelBotones.setBackground(Color.black);
        panelBotones.setLayout(new GridLayout(1, 6));

        Pieza[] piezas = new Pieza[NUM_PIEZAS];
        piezas[0] = new Peon();
        piezas[1] = new Torre();
        piezas[2] = new Caballo();
        piezas[3] = new Alfil();
        piezas[4] = new Reina();
        piezas[5] = new Rey();
        piezas[6] = new Peon(); 

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
        panelContenidos.add(panelBotones, BorderLayout.NORTH);

        setPreferredSize(new Dimension(PIXELES, PIXELES));
//        setLocationRelativeTo(null);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
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
                opcion = new JOptionPane();

                ImageIcon imagenPieza = new ImageIcon(piezas[i].imagenPieza());
                String n = (String) opcion.showInputDialog(null, "Posicion inicial: ", null, JOptionPane.INFORMATION_MESSAGE, imagenPieza, null, "");

//              posicionInicial = (AQUI HAY Q CONVERTIR LA ENTRADA A VECTOR 2D);
                piezaActual = piezas[i];
                new Thread(esto).start();
            }
        });
    }

    @Override
    public void run() {
        try {
            piezaActual.recorrerTableroEnTiempoReal(tablero);
        } catch (Exception ex) {
            System.err.println("Error malo" + ex.getMessage());
        }
    }
    
    public static int getPixeles(){
        return PIXELES;
    }
    
    public static int getDimensiones(){
        return DIMENSIONES;
    }

}
