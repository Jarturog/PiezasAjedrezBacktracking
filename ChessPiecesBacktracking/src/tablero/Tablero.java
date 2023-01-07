package tablero;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Arturo y Marta
 */
public class Tablero extends JPanel {

    private final int DIMENSIONES;
    private int casillasVisitadas;
    private final Casilla[] casillas;

    public Tablero(int dimensiones) {
        DIMENSIONES = dimensiones;
        casillas = new Casilla[dimensiones * dimensiones];
        boolean blanco = true;
        for (int i = 0; i < dimensiones; i++) {
            for (int j = 0; j < dimensiones; j++) {
                Vector2D v = new Vector2D(j, i);
                if (blanco) {
                    casillas[getIndexCasilla(v)] = new Casilla(Color.WHITE);
                } else {
                    casillas[getIndexCasilla(v)] = new Casilla(Color.BLACK);
                }
                add(casillas[getIndexCasilla(v)]);
                blanco = !blanco;
            }
            if (DIMENSIONES % 2 == 0) {
                blanco = !blanco;
            }
        }
    }

    private int getIndexCasilla(Vector2D v) {
        return v.getX() + v.getY() * DIMENSIONES;
    }

    public Casilla getCasilla(Vector2D posicion) {
        return casillas[getIndexCasilla(posicion)];
    }

    public void ocuparPosicion(Vector2D pos, boolean ocupar) throws Exception {
        if (!isCasillaLibre(pos) == ocupar) {
            String s;
            if(ocupar){
                s = "Error ocupando una casilla que ya estaba ocupada";
            }else{
                s = "Error desocupando una casilla que ya estaba libre";
            }
            throw new Exception(s);
        }
        casillas[getIndexCasilla(pos)].setOcupada(ocupar);
        if (ocupar) {
            casillasVisitadas++;
        } else {
            casillasVisitadas--;
        }
    }

    public boolean isPosicionDelTablero(Vector2D futuraPosicion) {
        return !(futuraPosicion.getX() > DIMENSIONES - 1
                || futuraPosicion.getY() > DIMENSIONES - 1
                || futuraPosicion.getX() < 0 || futuraPosicion.getY() < 0);
    }

    public boolean isCasillaLibre(Vector2D pos) throws Exception {
        if (!isPosicionDelTablero(pos)) {
            throw new Exception("Error comprobando si una casilla inexistente está libre");
        }
        return !casillas[getIndexCasilla(pos)].estaOcupada();
    }

    public int getDIMENSIONES() {
        return DIMENSIONES;
    }

    public int getNumCasillas() {
        return DIMENSIONES * DIMENSIONES;
    }

    public int getCasillasVisitadas() {
        return casillasVisitadas;
    }

    public void limpiar(){
        for (int i = 0; i < casillas.length; i++) {
            casillas[i].setIcon(null);
            casillas[i].setOcupada(false);
            casillasVisitadas = 0;
        }
    }
}
