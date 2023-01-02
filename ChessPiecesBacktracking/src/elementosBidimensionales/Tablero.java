/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elementosBidimensionales;

import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author jartu
 */
public class Tablero extends JPanel {

    private final int DIMENSION = 8;
    private static final int MAXIMO = 800;
    private Casilla t[][];
    private int casillasVisitadas;
    private Casilla[] casillas;

    public Tablero(int dimensiones) {
        casillas = new Casilla[dimensiones * dimensiones];
        boolean blanco = true;
        for (int i = 0; i < dimensiones; i++) {
            for (int j = 0; j < dimensiones; j++) {
                if (blanco) {
                    casillas[i] = new Casilla(Color.WHITE);
                } else {
                    casillas[i] = new Casilla(Color.BLACK);
                }
                add(casillas[i]);
                blanco = !blanco;
            }
            blanco = !blanco;
        }
    }

    public Tablero(boolean d) {
        casillasVisitadas = 0;
        final int LADO_CASILLA = MAXIMO / DIMENSION;
        t = new Casilla[DIMENSION][DIMENSION];
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                Color col;
                if ((i % 2 == 1 && j % 2 == 1) || (i % 2 == 0 && j % 2 == 0)) {
                    col = Color.WHITE;
                } else {
                    col = Color.BLACK;
                }
                //casillas[i][j] = new Casilla(i,j,col, false,LADO_CASILLA);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                //casillas[i][j].paintComponent(g);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MAXIMO, MAXIMO);
    }

    public void ocuparPosicion(Vector2D pos) throws Exception {
        if (!casillaLibre(pos)) {
            throw new Exception("Error ocupando una casilla que no está libre");
        }
        t[pos.getX()][pos.getY()].setOcupada(true);
        casillasVisitadas++;
    }

    public void desOcuparPosicion(Vector2D pos) throws Exception {
        if (casillaLibre(pos)) {
            throw new Exception("Error desocupando una casilla ya libre");
        }
        t[pos.getX()][pos.getY()].setOcupada(false);
        casillasVisitadas--;
    }
    
    public boolean movimientoLegal(Vector2D futuraPosicion) {
        return !(futuraPosicion.getX() > DIMENSION - 1
                || futuraPosicion.getY() > DIMENSION - 1
                || futuraPosicion.getX() < 0 || futuraPosicion.getY() < 0);
    }

    public boolean casillaLibre(Vector2D pos) throws Exception {
        if(!movimientoLegal(pos)){
            throw new Exception("Error comprobando si una casilla inexistente está libre");
        }
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

