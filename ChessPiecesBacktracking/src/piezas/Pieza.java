package piezas;

import gráficos.Vector2D;
import gráficos.Tablero;

/**
 *
 * @author Arturo y Marta
 */
public abstract class Pieza {

    public abstract Vector2D[] movimientos();
    private final Vector2D[] movimientos = movimientos();

    public Pieza(Vector2D posInicial) {
        posicion = posInicial;
    }

    public void mover(Tablero t, Vector2D pos) throws Exception {
        t.ocuparPosicion(pos);
        posicion.sumar(pos);
    }

    public boolean canMove(Tablero t, Vector2D pos, Vector2D mov) {
        Vector2D res = new Vector2D(pos);
        res.sumar(mov);
        return !(res.getX() > t.getDimensiones() - 1 || res.getY() > t.getDimensiones() - 1 || res.getX() < 0 || res.getY() < 0);
    }

    public void recorrerTablero(Tablero t, Vector2D posicion) {
        rRecorrerTablero(new Tablero(8), new int[movimientos().length], 0, posicion);
    }

    private void rRecorrerTablero(Tablero t, int[] s, int indMov, Vector2D posicion) {
        s[i] = -1;
        while (s[i] < s.length - 1) {
            s[i]++;
            if (canMove(t, posicion, movimientos()[indMov])) {
                if (i > s.length - 2) {

                } else {
                    rRecorrerTablero(t, i + 1, s);
                }
            }
        }
        s[i] = -1;
    }

    public static void iRecorrerTablero(Tablero t) {

    }
}
