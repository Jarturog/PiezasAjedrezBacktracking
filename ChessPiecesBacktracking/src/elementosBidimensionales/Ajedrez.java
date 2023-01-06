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
public class Ajedrez  {

    private JFrame ventana;
    private JComboBox opcion;
    JPanel panelInterfaces, panelStandBy, panelRCaballo;
    private Tablero tablero;
    private ImageIcon imagenAjedrez = new ImageIcon("ajedrez.png");
    private final int DIMENSIONES = 8;

    public Ajedrez() {
        ventana = new JFrame("Ajedrez");
        ventana.setPreferredSize(new Dimension(920, 920));
        ventana.getContentPane().setLayout(new BorderLayout());
        JMenuBar barraMenu = new JMenuBar();
        ventana.setJMenuBar(barraMenu);
        JMenu menu = new JMenu("Menú");
        JMenuItem recorridoTorre = new JMenuItem("Torre");
        recorridoTorre.addActionListener(new gestorEventos());
        JMenuItem recorridoAlfil = new JMenuItem("Alfil");
        recorridoAlfil.addActionListener(new gestorEventos());
        JMenuItem recorridoCaballo = new JMenuItem("Caballo");
        recorridoCaballo.addActionListener(new gestorEventos());
        JMenuItem recorridoReina = new JMenuItem("Reina");
        recorridoReina.addActionListener(new gestorEventos());
        JMenuItem recorridoRey = new JMenuItem("Rey");
        recorridoRey.addActionListener(new gestorEventos());
        JMenuItem recorridoPeon = new JMenuItem("Peon");
        recorridoPeon.addActionListener(new gestorEventos());
        JMenuItem salir = new JMenuItem("Salir");
        salir.addActionListener(new gestorEventos());
        menu.add(recorridoTorre);
        menu.add(recorridoAlfil);
        menu.add(recorridoCaballo);
        menu.add(recorridoReina);
        menu.add(recorridoRey);
        menu.add(recorridoPeon);
        menu.add(salir);
        barraMenu.add(menu);
        panelInterfaces = new JPanel();
        //asignación administrador de layout CardLayout
        panelInterfaces.setLayout(new CardLayout());

        ventana.getContentPane().add(panelInterfaces, BorderLayout.CENTER);
        panelStandBy = new JPanel();
        //asigna administrador layout
        panelStandBy.setLayout(new BorderLayout());
//        panelStandBy.setBackground(Color.black);

        //redimensiona la imagen 
        ImageIcon nuevaImagen = redimensionarImagen(imagenAjedrez);
        JLabel etiquetaImagen = new JLabel(nuevaImagen);
//        JLabel seleccion = new JLabel();
//        seleccion.setBounds(415, 450, 90, 20);
//        String[] recorridos = {"Torre", "Alfil", "Caballo", "Reina", "Rey", "Peon"};
//        JComboBox<String> seleccionRec = new JComboBox<String>(recorridos);
//        
//        seleccionRec.setEditable(false);
//        seleccion.add(seleccionRec);
//        seleccion.setVisible(true);
        panelStandBy.add(etiquetaImagen, BorderLayout.CENTER);
//        panelStandBy.add(seleccion);
         
       panelInterfaces.add(panelStandBy, "StandBy");
      panelRCaballo = new JPanel();
       panelInterfaces.add(panelRCaballo, "Caballo");
        ventana.setLocationRelativeTo(null);
        ventana.pack();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);

    }

    private class gestorEventos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evento) {

            CardLayout local = (CardLayout) (panelInterfaces.getLayout());
            switch (evento.getActionCommand()) {
                case "StandBy":
                    local.show(panelInterfaces, "StandBy");

//                case "Torre":
//                    break;
//
//                case "Alfil":
//                    break;

                case "Caballo":
                    actualizarPanelRecorridos("Caballo");
                    Caballo c = new Caballo(new Vector2D(4, 4));
                     {
                        try {

                            c.recorrerTablero(tablero);
                        } catch (Exception ex) {
                            Logger.getLogger(Ajedrez.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                     local.show(panelInterfaces, "Caballo");

                    break;
//
//                case "Reina":
//                    break;
//                case "Rey":
//                    break;
//                case "Peon":
//                    break;
                case "Salir":
                    System.exit(0);
                    break;
            }
        }
    }

    private void actualizarPanelRecorridos(String s) {
       
        tablero = new Tablero(DIMENSIONES);
        tablero.setLayout(new GridLayout(DIMENSIONES, DIMENSIONES));
       
//        tablero.setOpaque(true);
       
        panelStandBy.setVisible(false);
        tablero.setVisible(true);
        panelInterfaces.add(tablero, s);
    }

    private ImageIcon redimensionarImagen(ImageIcon imagen) {

        Image image = imagen.getImage(); // transforma ImageIcon a image
        Image newimg = image.getScaledInstance(920, 920, java.awt.Image.SCALE_DEFAULT);
        imagen = new ImageIcon(newimg);  // transforma  Image a imageIcon
        return imagen;
    }

}
