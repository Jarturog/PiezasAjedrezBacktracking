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
 * Clase principal que es una ventana en la que permite al usuario recorrer un
 * tablero con una pieza elegida.
 *
 * @author Arturo y Marta
 */
public class Ajedrez extends JFrame implements Runnable {

    private final static int DIMENSIONES = 8; // dimensiones tablero
    private final static int PIXELES = 640; // tamaño ventana

    private final int NUM_PIEZAS = 6; // aumentar cuando se añadan piezas
    private boolean recorriendoTablero; // true si lo recorre, false si no
    private Pieza piezaActual; // pieza con la que se va a recorrer el tablero
    private final Ajedrez instancia; // la propia instancia para pasársela al hilo
    private final Container panelContenidos; // panel contenedora de panelBotones y tablero
    private final Tablero tablero; // tablero contenedor de las casillas
    private final JButton[] botonesPiezas; // enlaza los botones a sus respectivas piezas
    private final JPanel panelBotones; // contenedor de botonesPiezas
    private boolean iterativo; // true si se ha elegido iterativo, false si recursivo

    public static void main(String[] args) throws Exception {
        try {
            setLookAndFeel(getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("No se ha podido establecer el formato de su plataforma" + e.getMessage());
        }
        Ajedrez aj = new Ajedrez(); // nuevo ajedrez
//        for (int i = 0; i < DIMENSIONES; i++) {
//            for (int j = 0; j < DIMENSIONES; j++) {
//                System.out.println(i+"\n"+j);
//                aj.piezaActual = new Rey(aj.tablero, PIXELES/DIMENSIONES);
//                aj.piezaActual.setPosicion(new Vector2D(i, j));
//                aj.run();
//            }
//        }
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
        panelBotones.setLayout(new GridLayout(1, NUM_PIEZAS));
        panelContenidos.add(panelBotones, BorderLayout.NORTH);

        botonesPiezas = new JButton[NUM_PIEZAS];
        botonesPiezas[0] = new JButton("Peón");
        botonesPiezas[1] = new JButton("Torre");
        botonesPiezas[2] = new JButton("Caballo");
        botonesPiezas[3] = new JButton("Alfil");
        botonesPiezas[4] = new JButton("Reina");
        botonesPiezas[5] = new JButton("Rey");
        // botonesPiezas[6] = new JButton("---"); para añadir una nueva pieza

        int tamanyoPieza = PIXELES / DIMENSIONES;
        Pieza[] piezas = new Pieza[NUM_PIEZAS];
        piezas[0] = new Peon(tablero, tamanyoPieza);
        piezas[1] = new Torre(tablero, tamanyoPieza);
        piezas[2] = new Caballo(tablero, tamanyoPieza);
        piezas[3] = new Alfil(tablero, tamanyoPieza);
        piezas[4] = new Reina(tablero, tamanyoPieza);
        piezas[5] = new Rey(tablero, tamanyoPieza);
        // piezas[6] = new ---(tablero); para añadir una nueva pieza

        for (int i = 0; i < NUM_PIEZAS; i++) {
            vincularAccion(i, piezas); // se vincula el actionListener con el botón
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
                if (recorriendoTablero) { // no se puede recorrer el tablero dos veces a la vez
                    JOptionPane.showMessageDialog(null, "No se puede recorrer el tablero varias veces a la vez", "Prohibición", HEIGHT);
                    return;
                }
                boolean entradaErronea = true; // se inicializa el booleano que indica si hay que seguir preguntando por una fila/columna correcta
                ImageIcon imagen = new ImageIcon(piezas[i].imagenPieza()); // imagen de la pieza
                // variable input es lo que escribe el usuario
                String input = (String) JOptionPane.showInputDialog(null, "Fila donde comienza (1 - " + DIMENSIONES + "):", null, JOptionPane.INFORMATION_MESSAGE, imagen, null, "");
                int numFila = 0; // la fila
                while (entradaErronea) { // seguirá en el bucle mientras no pueda convertir el String a número
                    if (input == null) { // si se ha cancelado el proceso
                        return; // no sigue
                    }
                    try {
                        numFila = Integer.parseInt(input); // se convierte a número
                        if (numFila < 1 || numFila > DIMENSIONES) {
                            throw new NumberFormatException();
                        } // si no salta la excepción es que ha ido bien
                        numFila--; // se resta 1 para pasarlo a índice (que empieza por 0 y no por 1)
                        entradaErronea = false; // deja de ser errónea
                    } catch (NumberFormatException ex) {
                        input = (String) JOptionPane.showInputDialog(null, "Por favor, escribe un número entre 1 y " + DIMENSIONES + "):", null, JOptionPane.INFORMATION_MESSAGE, imagen, null, "");
                    }
                }
                // se vuelve a repetir el proceso
                entradaErronea = true;
                input = (String) JOptionPane.showInputDialog(null, "Columna donde comienza (1 - " + DIMENSIONES + "):", null, JOptionPane.INFORMATION_MESSAGE, imagen, null, "");
                int numColumna = 0;
                while (entradaErronea) {
                    if (input == null) { // si se ha cancelado el proceso
                        return; // no sigue
                    }
                    try {
                        numColumna = Integer.parseInt(input); // se convierte a número
                        if (numColumna < 1 || numColumna > DIMENSIONES) {
                            throw new NumberFormatException();
                        } // si no salta la excepción es que ha ido bien
                        numColumna--; // se resta 1 para pasarlo a índice (que empieza por 0 y no por 1)
                        entradaErronea = false; // deja de ser errónea
                    } catch (NumberFormatException ex) {
                        input = (String) JOptionPane.showInputDialog(null, "Por favor, escribe un número entre 1 y " + DIMENSIONES + "):", null, JOptionPane.INFORMATION_MESSAGE, imagen, null, "");
                    }
                }
                // se pregunta al usuario el tipo de algoritmo que quiere usar
                iterativo = 0 != JOptionPane.showConfirmDialog(null, "¿Quieres usar la versión recursiva? (en caso contrario se selecciona la iterativa)", "Elige el algoritmo a utilizar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                piezaActual = piezas[i]; // asigna la pieza con la que se recorrerá
                // el tablero a la variable global para que el método run() pueda saber la pieza
                piezaActual.setPosicion(new Vector2D(numFila, numColumna)); // se indica las posiciones iniciales
                recorriendoTablero = true; // se está recorriendo
                try { // el hilo recorre el tablero. Es necesario crear la variable instancia ya que dentro del 
                    new Thread(instancia).start(); // actionPerformed 'this' no hace referencia a la instancia de Ajedrez.
                } catch (IllegalThreadStateException ex) {
                    System.err.println("Error creando hilo para recorrer tablero: " + ex.getMessage());
                    recorriendoTablero = false; // si ha habido un error permite recorrer el tablero.
                }
            }
        });
    }

    /**
     * Ejecución del recorrido del tablero en un hilo aparte ya que si se
     * ejcutara en el hilo principal los gráficos no se actualizarían hasta el
     * final al haberse ejecutado dentro de un actionPerformed(), y por lo tanto
     * solo se verían todas las casillas ocupadas en vez de verse el proceso.
     */
    @Override
    public void run() {
        setTitle("Ajedrez (puede tardar horas calculando)");
        boolean solucion;
        if (iterativo) {
            solucion = piezaActual.recorrerTableroIterativo();
        } else {
            solucion = piezaActual.recorrerTableroRecursivo();
        }
        setTitle("Ajedrez");
        if (solucion) {
            JOptionPane.showMessageDialog(null, "¡Hay solución!\nClica para poder volver a recorrer el tablero.");
        } else {
            JOptionPane.showMessageDialog(null, "No hay solución");
        }
        tablero.limpiar(); // se prepara el tablero para otro recorrido
        recorriendoTablero = false; // a partir de ahora se puede recorrer otra vez
    }
}
