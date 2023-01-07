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
import piezas.Caballo;

/**
 *
 * @author Arturo y Marta
 */
public class Ajedrez {

    private JFrame ventana;
    JButton Torre, Alfil, Caballo, Rey, Reina, Peon;
    JPanel panelInterfaces, panelStandBy, panelRecorridos, panelBotones;
    public Tablero tablero;
    private ImageIcon imagenAjedrez = new ImageIcon("ajedrez.png");
    private final int DIMENSIONES = 8;
    Object opcion;

    public Ajedrez() {
        ventana = new JFrame("Ajedrez");
        ventana.setPreferredSize(new Dimension(920, 920));

        Container panelContenidos = ventana.getContentPane();
        panelContenidos.setLayout(new BorderLayout());

        panelBotones = new JPanel();
        panelBotones.setBackground(Color.black);
        panelBotones.setLayout(new GridLayout(1, 6));

        Torre = new JButton("Torre");
        Torre.setBackground(Color.black);
        Torre.setForeground(Color.white);
        Alfil = new JButton("Alfil");
        Alfil.setBackground(Color.black);
        Alfil.setForeground(Color.white);
        Caballo = new JButton("Caballo");
        Caballo.setBackground(Color.black);
        Caballo.setForeground(Color.white);
        Reina = new JButton("Reina");
        Reina.setBackground(Color.black);
        Reina.setForeground(Color.white);
        Rey = new JButton("Rey");
        Rey.setBackground(Color.black);
        Rey.setForeground(Color.white);
        Peon = new JButton("Peon");
        Peon.setBackground(Color.black);
        Peon.setForeground(Color.white);

        Torre.addActionListener(new gestorEventos());
        Alfil.addActionListener(new gestorEventos());
        Caballo.addActionListener(new gestorEventos());
        Reina.addActionListener(new gestorEventos());
        Rey.addActionListener(new gestorEventos());
        Peon.addActionListener(new gestorEventos());
        panelBotones.add(Torre);
        panelBotones.add(Alfil);
        panelBotones.add(Caballo);
        panelBotones.add(Reina);
        panelBotones.add(Rey);
        panelBotones.add(Peon);

        panelContenidos.add(panelBotones, java.awt.BorderLayout.PAGE_START);

      

        JMenuBar barraMenu = new JMenuBar();
        ventana.setJMenuBar(barraMenu);
        JMenu menu = new JMenu("Menú");
//        JMenuItem recorridoTorre = new JMenuItem("Torre");
//        recorridoTorre.addActionListener(new gestorEventos());
//        JMenuItem recorridoAlfil = new JMenuItem("Alfil");
//        recorridoAlfil.addActionListener(new gestorEventos());
//        JMenuItem recorridoCaballo = new JMenuItem("Caballo");
//        recorridoCaballo.addActionListener(new gestorEventos());
//        JMenuItem recorridoReina = new JMenuItem("Reina");
//        recorridoReina.addActionListener(new gestorEventos());
//        JMenuItem recorridoRey = new JMenuItem("Rey");
//        recorridoRey.addActionListener(new gestorEventos());
//        JMenuItem recorridoPeon = new JMenuItem("Peon");
//        recorridoPeon.addActionListener(new gestorEventos());
        JMenuItem salir = new JMenuItem("Salir");
        salir.addActionListener(new gestorEventos());

        JMenuItem recorridos = new JMenuItem("Recorridos");
//        recorridos.addActionListener(new gestorEventos());

//        menu.add(recorridoTorre);
//        menu.add(recorridoAlfil);
//        menu.add(recorridoCaballo);
//        menu.add(recorridoReina);
//        menu.add(recorridoRey);
//        menu.add(recorridoPeon);
//        menu.add((recorridos));
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

    private class gestorEventos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evento) {

            try {
                CardLayout local = (CardLayout) (panelInterfaces.getLayout());
                switch (evento.getActionCommand()) {
                    case "StandBy":
                        local.show(panelInterfaces, "StandBy");
                        break;

                    case "Recorridos":
                        Object[] recorridos = {"Torre", "Alfil", "Caballo", "Reina", "Rey", "Peon"};
                        opcion = JOptionPane.showInputDialog(null, "Selecciona un recorrido", "Titulo", JOptionPane.QUESTION_MESSAGE, imagenAjedrez, recorridos, recorridos[0]);

                        actualizarPanelRecorridos();

//                  actualizarPanelRecorridos(evento.getActionCommand());
//                       
                        local.show(panelInterfaces, "Recorridos");
//                        visualizarRecorridoTablero("Caballo");
//                        Caballo c = new Caballo(new Vector2D(4, 4));
//                        c.recorrerTablero(tablero);
//                        tablero.repaint();
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

    private void recaballo() throws Exception {
        visualizarRecorridoTablero("Caballo");

        Caballo c = new Caballo(new Vector2D(4, 4));
        c.recorrerTablero(tablero);

    }

    private void actualizarPanelRecorridos() throws Exception {

        tablero.setLayout(new GridLayout(DIMENSIONES, DIMENSIONES));

        panelStandBy.setVisible(false);
        tablero.setVisible(true);

        panelInterfaces.add(tablero, "Recorridos");
        recaballo();

    }

    private void actualizarPanelRecorridos2(String s) throws Exception {

        tablero = new Tablero(DIMENSIONES);
        tablero.setLayout(new GridLayout(DIMENSIONES, DIMENSIONES));

//        tablero.setOpaque(true);
        switch (s) {
            case "Torre":
                break;

            case "Alfil":
                break;

            case "Caballo":
//                visualizarRecorridoTablero();
                Caballo c = new Caballo(new Vector2D(4, 4));

                c.recorrerTablero(tablero);

                break;

            case "Reina":
                break;
            case "Rey":
                break;
            case "Peon":
                break;
        }
        panelRecorridos.add(tablero);
        panelRecorridos.repaint();
        panelStandBy.setVisible(false);
        panelRecorridos.setVisible(true);
        panelInterfaces.add(tablero, "Recorridos");
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
