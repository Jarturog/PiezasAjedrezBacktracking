package elementosBidimensionales;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import piezas.Alfil;
import piezas.Caballo;
import piezas.Peon;
import piezas.Pieza;
import piezas.Reina;
import piezas.Rey;
import piezas.Torre;

/**
 *
 * @author Arturo y Marta
 */
public class Ajedrez {

    /*
    Object[] recorridos = {"Torre", "Alfil", "Caballo", "Reina", "Rey", "Peon"};
                        opcion = JOptionPane.showInputDialog(null, "Selecciona un recorrido", "Titulo", JOptionPane.QUESTION_MESSAGE, imagenAjedrez, recorridos, recorridos[0]);

     */
    private JFrame ventana;

    JPanel panelInterfaces, panelStandBy, panelRecorridos, panelBotones;

    private ImageIcon imagenAjedrez = new ImageIcon("ajedrez.png");

    private Container panelContenidos;
    private Tablero tablero;
    private final int DIMENSIONES = 8;
    private final static int PIXELES = 640;
    private final int NUM_PIEZAS = 7;
    private final JButton[] botonesPiezas = new JButton[NUM_PIEZAS];
    private final Vector2D posicionInicial = new Vector2D(DIMENSIONES / 2, DIMENSIONES / 2);

    public Ajedrez() {
        ventana = new JFrame("Ajedrez");

        panelContenidos = ventana.getContentPane();
        panelContenidos.setLayout(new BorderLayout());
        inicializarBotones();
//        panelBotones = new JPanel();
//        panelBotones.setBackground(Color.black);
//        panelBotones.setLayout(new GridLayout(1, 6));
//        Torre = new JButton("Torre");
//        Torre.setBackground(Color.black);
//        Torre.setForeground(Color.white);
//        Alfil = new JButton("Alfil");
//        Alfil.setBackground(Color.black);
//        Alfil.setForeground(Color.white);
//        Caballo = new JButton("Caballo");
//        Caballo.setBackground(Color.black);
//        Caballo.setForeground(Color.white);
//        Reina = new JButton("Reina");
//        Reina.setBackground(Color.black);
//        Reina.setForeground(Color.white);
//        Rey = new JButton("Rey");
//        Rey.setBackground(Color.black);
//        Rey.setForeground(Color.white);
//        Peon = new JButton("Peon");
//        Peon.setBackground(Color.black);
//        Peon.setForeground(Color.white);
//
//        Torre.addActionListener(new gestorEventos());
//        Alfil.addActionListener(new gestorEventos());
//        Caballo.addActionListener(new gestorEventos());
//        Reina.addActionListener(new gestorEventos());
//        Rey.addActionListener(new gestorEventos());
//        Peon.addActionListener(new gestorEventos());
//        panelBotones.add(Torre);
//        panelBotones.add(Alfil);
//        panelBotones.add(Caballo);
//        panelBotones.add(Reina);
//        panelBotones.add(Rey);
//        panelBotones.add(Peon);
//        panelContenidos.add(panelBotones, java.awt.BorderLayout.PAGE_START);

        JMenuBar barraMenu = new JMenuBar();
        ventana.setJMenuBar(barraMenu);
        JMenu menu = new JMenu("Menú");

        JMenuItem salir = new JMenuItem("Salir");
        salir.addActionListener(new gestorEventos());

        menu.add(salir);
        barraMenu.add(menu);

        panelInterfaces = new JPanel();
        //asignación administrador de layout CardLayout
        panelInterfaces.setLayout(new CardLayout());

        panelContenidos.add(panelInterfaces, BorderLayout.CENTER);
        panelStandBy = new JPanel();
        panelStandBy.setLayout(new BorderLayout());
        ImageIcon nuevaImagen = redimensionarImagen(imagenAjedrez);
        JLabel etiquetaImagen = new JLabel(nuevaImagen);

        panelStandBy.add(etiquetaImagen, BorderLayout.CENTER);
//        panelStandBy.add(seleccion);

        panelInterfaces.add(panelStandBy, "StandBy");
        tablero = new Tablero(DIMENSIONES);
        panelInterfaces.add(tablero, "Recorridos");
        ventana.setLocationRelativeTo(null);
        ventana.pack();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);

    }

    private void inicializarTablero(Pieza p) {
        ((CardLayout) panelContenidos.getLayout()).last(panelContenidos);
        try {
            p.recorrerTablero(tablero);
        } catch (Exception ex) {
            System.err.println("Error malo" + ex.getMessage());
        }
    }

    private void inicializarBotones() {

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
                inicializarTablero(piezas[i]);
            }
        });
    }

    private class gestorEventos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evento) {

            try {
                CardLayout local = (CardLayout) (panelInterfaces.getLayout());
                switch (evento.getActionCommand()) {
                    case "StandBy":
                        local.show(panelInterfaces, "StandBy");
                        break;

                    case "Torre":

                        actualizarPanelRecorridos();
                        local.show(panelInterfaces, "Torre");
                        visualizarRecorridoTablero("Torre");
//                        Caballo c = new Caballo(new Vector2D(4, 4));
//                        c.recorrerTablero(tablero);
//                        tablero.repaint();
                        break;
                    case "Alfil":
                        actualizarPanelRecorridos();
                        visualizarRecorridoTablero("Alfil");
                        local.show(panelInterfaces, "Alfil");
                        break;
                    case "Caballo":
                        actualizarPanelRecorridos();
                        visualizarRecorridoTablero("Caballo");
                        local.show(panelInterfaces, "Caballo");
                        break;
                    case "Reina":
                        actualizarPanelRecorridos();
                        visualizarRecorridoTablero("Reina");
                        local.show(panelInterfaces, "Reina");
                        break;
                    case "Rey":
                        actualizarPanelRecorridos();
                        visualizarRecorridoTablero("Rey");
                        local.show(panelInterfaces, "Rey");
                        break;
                    case "Peon":
                        actualizarPanelRecorridos();
                        visualizarRecorridoTablero("Peon");
                        local.show(panelInterfaces, "Peon");
                        break;

                    case "Salir":
                        System.exit(0);
                        break;
                }
            } catch (Exception ex) {
                Logger.getLogger(Ajedrez.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//    private void recaballo() throws Exception {
//        visualizarRecorridoTablero("Caballo");
//
//        Caballo c = new Caballo(new Vector2D(4, 4));
//        c.recorrerTablero(tablero);
//
//    }
    private void actualizarPanelRecorridos() throws Exception {

        tablero.setLayout(new GridLayout(DIMENSIONES, DIMENSIONES));

        panelStandBy.setVisible(false);
        tablero.setVisible(true);

        panelInterfaces.add(tablero, "Recorridos");
//        recaballo();

    }

    public void visualizarRecorridoTablero(String s) throws Exception {
        ImageIcon imagen = new ImageIcon(s + ".png");
        ImageIcon nuevaImagen = redimensionarImagen(imagen);
    }

    private ImageIcon redimensionarImagen(ImageIcon imagen) {

        Image image = imagen.getImage(); // transforma ImageIcon a image
        Image newimg = image.getScaledInstance(920, 920, java.awt.Image.SCALE_DEFAULT);
        imagen = new ImageIcon(newimg);  // transforma  Image a imageIcon
        return imagen;
    }

}
