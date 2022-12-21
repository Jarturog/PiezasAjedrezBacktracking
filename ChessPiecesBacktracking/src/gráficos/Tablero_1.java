/*
 * Classe Tauler definida com un panell on es defineix una taula 8x8 de caselles
 *
 * El constructor defineix totes les caselles com a rectangles d'un color
 * determinat inicialitzant-les buides
 *
 * El mètode paintComponent recorr el tauler pintant les caselles
 *
 * El mètode getPreferredSize retorna el tamant del tauler
 */
package gráficos;

/**
 *
 * @author miquelmascaro
 */
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;

public class Tablero_1 extends JPanel {

    private static final int DIMENSION = 8;
    private static final int MAXIMO = 800;
    private static final int LADO = MAXIMO / DIMENSION;
    private static final Color BLANCO = Color.WHITE;
    private static final Color NEGRO = Color.BLACK;
    
    private Casilla t[][];

    public Tablero_1() {
        t = new Casilla[DIMENSION][DIMENSION];
        int y = 0;
        for (int i = 0; i < DIMENSION; i++) {
            int x = 0;
            for (int j = 0; j < DIMENSION; j++) {
                Rectangle2D.Float r =
                        new Rectangle2D.Float(x, y, LADO, LADO);
                Color col;
                if ((i % 2 == 1 && j % 2 == 1) || (i % 2 == 0 && j % 2 == 0)) {
                    col = BLANCO;
                } else {
                    col = NEGRO;
                }
                t[i][j] = new Casilla(r, col, false);
                x += LADO;
            }
            y += LADO;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {                
                t[i][j].paintComponent(g);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MAXIMO, MAXIMO);
    }
}
