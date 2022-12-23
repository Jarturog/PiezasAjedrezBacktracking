package gráficos;

import java.util.LinkedList;
//import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D.Float;
import javax.swing.JPanel;

public class Tablero extends JPanel {

    private final int DIMENSION;
    private static final int MAXIMO = 800;
    private final int LADO;
    private static final Color BLANCO = Color.WHITE;
    private static final Color NEGRO = Color.BLACK;

    private Casilla t[][];
    private int casillasVisitadas;

    public Tablero(int d) {
        casillasVisitadas = 0;
        DIMENSION = d;
        LADO = MAXIMO / DIMENSION;
        t = new Casilla[DIMENSION][DIMENSION];
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                Color col;
                if ((i % 2 == 1 && j % 2 == 1) || (i % 2 == 0 && j % 2 == 0)) {
                    col = BLANCO;
                } else {
                    col = NEGRO;
                }
                t[i][j] = new Casilla(new Float(j * LADO, i * LADO, LADO, LADO),
                        col, false);
            }
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

    public void ocuparPosicion(Vector2D pos) throws Exception {
        if (!casillaLibre(pos)) {
            throw new Exception();
        }
        t[pos.getX()][pos.getY()].setOcupada(true);
    }

    public void desOcuparPosicion(Vector2D pos) throws Exception {
        if (casillaLibre(pos)) {
            throw new Exception();
        }
        t[pos.getX()][pos.getY()].setOcupada(false);
    }

    public boolean casillaLibre(Vector2D pos) {
        return !t[pos.getX()][pos.getY()].estaOcupada();
    }

    public int getDIMENSION() {
        return DIMENSION;
    }

    public int getNumCasillas() {
        return DIMENSION * DIMENSION;
    }

    public int getCasillasVisitadas() {
        return casillasVisitadas;
    }
    
    public void visualizar(LinkedList<Vector2D> movimientosRealizados) {
        // aquí se visualizará sacando los movimientos
        System.out.println(movimientosRealizados.getFirst()); // en vez de
        // println se van colocando y dejando x por donde pasan
    }
}
