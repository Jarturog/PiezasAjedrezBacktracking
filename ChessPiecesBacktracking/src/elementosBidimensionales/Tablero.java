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
    private int casillasVisitadas;
    private Casilla[] casillas;

    public Tablero(int dimensiones) {
        casillas = new Casilla[dimensiones * dimensiones];
        boolean blanco = true;
        for (int i = 0; i < dimensiones; i++) {
            for (int j = 0; j < dimensiones; j++) {
                if (blanco) {
                    casillas[getIndexCasilla(new Vector2D(i, j))] = new Casilla(Color.WHITE);
                } else {
                    casillas[getIndexCasilla(new Vector2D(i, j))] = new Casilla(Color.BLACK);
                }
                add(casillas[getIndexCasilla(new Vector2D(i, j))]);
                blanco = !blanco;
            }
            blanco = !blanco;
        }
    }

//    @Override
//    public void paintComponent(Graphics g) {
//        for (int i = 0; i < DIMENSION; i++) {
//            for (int j = 0; j < DIMENSION; j++) {
//                casillas[i][j].paintComponent(g);
//            }
//        }
//    }
    private int getIndexCasilla(Vector2D v) {
        return v.getX() + v.getY() * DIMENSION;
    }
    
    public Casilla getCasilla(Vector2D posicion){
        return casillas[getIndexCasilla(posicion)];
    }

    public void ocuparPosicion(Vector2D pos) throws Exception {
        if (!casillaLibre(pos)) {
            throw new Exception("Error ocupando una casilla que no está libre");
        }
        casillas[getIndexCasilla(pos)].setOcupada(true);
        casillasVisitadas++;
    }

    public void desOcuparPosicion(Vector2D pos) throws Exception {
        if (casillaLibre(pos)) {
            throw new Exception("Error desocupando una casilla ya libre");
        }
        casillas[getIndexCasilla(pos)].setOcupada(false);
        casillasVisitadas--;
    }

    public boolean movimientoLegal(Vector2D futuraPosicion) {
        return !(futuraPosicion.getX() > DIMENSION - 1
                || futuraPosicion.getY() > DIMENSION - 1
                || futuraPosicion.getX() < 0 || futuraPosicion.getY() < 0);
    }

    public boolean casillaLibre(Vector2D pos) throws Exception {
        if (!movimientoLegal(pos)) {
            throw new Exception("Error comprobando si una casilla inexistente está libre");
        }
        return !casillas[getIndexCasilla(pos)].estaOcupada();
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
}
