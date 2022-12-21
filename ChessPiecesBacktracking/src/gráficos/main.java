/*
 * Programa que defineix un tauler d'escacs dins un marc
 *
 */
package gráficos;
/**
 *
 * @author miquelmascaro
 */
import javax.swing.*;

public class main extends JFrame {

    Tablero tauler;

    public main() {
        super("Escacs");
        tauler = new Tablero();
        this.getContentPane().add(tauler);
        this.setSize(tauler.getPreferredSize());
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        main esc = new main();
        esc.setVisible(true);
    }

}

