package chesspiecesbacktracking;

import tablero.Vector2D;
import tablero.Tablero;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.UIManager.getCrossPlatformLookAndFeelClassName;
import static javax.swing.UIManager.setLookAndFeel;
import piezas.*;

/**
 *
 * @author Arturo y Marta
 */
public class Ajedrez extends JFrame implements Runnable {

    private final static int DIMENSIONES = 8;
    private final static int PIXELES = 640;
    
    private final int NUM_PIEZAS = 7;
    private boolean recorriendoTablero;
    private Pieza piezaActual;
    private final Ajedrez instancia;
    private final Container panelContenidos;
    private final Tablero tablero;
    private final JButton[] botonesPiezas;
    private final JPanel panelBotones;

    public static void main(String[] args) throws Exception {
        try {
            setLookAndFeel(getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("No se ha podido establecer el formato de su plataforma" + e.getMessage());
        }
        Ajedrez aj = new Ajedrez();
    }

    public Ajedrez() {
        instancia = this;
        recorriendoTablero = false;
        setTitle("Ajedrez");
        panelContenidos = getContentPane();
        panelContenidos.setLayout(new BorderLayout());

        tablero = new Tablero(DIMENSIONES);
        tablero.setLayout(new GridLayout(DIMENSIONES, DIMENSIONES));
        panelContenidos.add(tablero, BorderLayout.CENTER);

        panelBotones = new JPanel();
        panelBotones.setBackground(Color.black);
        panelBotones.setBackground(Color.black);
        panelBotones.setLayout(new GridLayout(1, NUM_PIEZAS));
        panelContenidos.add(panelBotones, BorderLayout.NORTH);

        botonesPiezas = new JButton[NUM_PIEZAS];
        botonesPiezas[0] = new JButton("Peón");
        botonesPiezas[1] = new JButton("Torre");
        botonesPiezas[2] = new JButton("Caballo");
        botonesPiezas[3] = new JButton("Alfil");
        botonesPiezas[4] = new JButton("Reina");
        botonesPiezas[5] = new JButton("Rey");
        botonesPiezas[6] = new JButton("Especial");

        Pieza[] piezas = new Pieza[NUM_PIEZAS];
        piezas[0] = new Peon(tablero);
        piezas[1] = new Torre(tablero);
        piezas[2] = new Caballo(tablero);
        piezas[3] = new Alfil(tablero);
        piezas[4] = new Reina(tablero);
        piezas[5] = new Rey(tablero);
        piezas[6] = new Campeon(tablero);

        for (int i = 0; i < NUM_PIEZAS; i++) {
            vincularAccion(i, piezas);
            botonesPiezas[i].setBackground(Color.black);
            botonesPiezas[i].setForeground(Color.white);
            panelBotones.add(botonesPiezas[i]);
        }

        setPreferredSize(new Dimension(PIXELES, PIXELES));
        pack();
        setLocationRelativeTo(null);
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
                if(recorriendoTablero){
                    JOptionPane.showMessageDialog(null, "No se puede recorrer el tablero varias veces a la vez","Prohibición", HEIGHT);
                    return;
                }
                boolean entradaErronea = true;
                ImageIcon imagen = new ImageIcon(piezas[i].imagenPieza());
                String fila = (String) JOptionPane.showInputDialog(null, "Fila donde comienza (1 - " + DIMENSIONES + "):", null, JOptionPane.INFORMATION_MESSAGE, imagen, null, "");
                int coordX = 0;
                while (entradaErronea) {
                    if (fila == null) {
                        return;
                    }
                    try {
                        coordX = Integer.parseInt(fila);
                        if (coordX < 1 || coordX > DIMENSIONES) {
                            throw new NumberFormatException();
                        }
                        coordX--;
                        entradaErronea = false;
                    } catch (NumberFormatException ex) {
                        fila = (String) JOptionPane.showInputDialog(null, "Por favor, escribe un número entre 1 y " + DIMENSIONES + "):", null, JOptionPane.INFORMATION_MESSAGE, imagen, null, "");
                    }
                }
                entradaErronea = true;
                String columna = (String) JOptionPane.showInputDialog(null, "Columna donde comienza (1 - " + DIMENSIONES + "):", null, JOptionPane.INFORMATION_MESSAGE, imagen, null, "");
                int coordY = 0;
                while (entradaErronea) {
                    if (columna == null) {
                        return;
                    }
                    try {
                        coordY = Integer.parseInt(columna);
                        if (coordY < 1 || coordY > DIMENSIONES) {
                            throw new NumberFormatException();
                        }
                        coordY--;
                        entradaErronea = false;
                    } catch (NumberFormatException ex) {
                        columna = (String) JOptionPane.showInputDialog(null, "Por favor, escribe un número entre 1 y " + DIMENSIONES + "):", null, JOptionPane.INFORMATION_MESSAGE, imagen, null, "");
                    }
                }
                piezaActual = piezas[i];
                piezaActual.setPosicion(new Vector2D(coordX, coordY));
                recorriendoTablero = true;
                try {
                    new Thread(instancia).start();
                } catch (IllegalThreadStateException ex) {
                    System.err.println("Error creando hilo para recorrer tablero: " + ex.getMessage());
                }
            }
        });
    }

    @Override
    public void run() {
        try {
            if(piezaActual.recorrerTablero()){
                Thread.sleep(5000);
            }else{
                JOptionPane.showMessageDialog(null, "No hay solución");
            }
            tablero.limpiar();
            recorriendoTablero = false;
        } catch (Exception ex) {
            System.err.println("Error inesperado: " + ex.getMessage());
        }
    }

    public static int getPixeles() {
        return PIXELES;
    }

    public static int getDimensiones() {
        return DIMENSIONES;
    }
}
