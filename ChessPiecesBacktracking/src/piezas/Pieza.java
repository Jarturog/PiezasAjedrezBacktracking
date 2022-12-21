package piezas;

import gráficos.Vector2D;
import gráficos.Tablero;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Arturo y Marta
 */
public abstract class Pieza {

    public abstract Vector2D[] movimientos();
    public abstract int pos;

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
        return !(res.getX() > t.getDIMENSIO() - 1 || res.getY() > t.getDIMENSIO() - 1 || res.getX() < 0 || res.getY() < 0);
    }

    public void recorrerTablero(Tablero t, Vector2D posicion) {
        rRecorrerTablero(new Tablero(8), new int[movimientos().length], 0, posicion);
    }

    private void rRecorrerTablero(Tablero t, int[] movs, int indMov, Vector2D posicion) {
        movs[indMov] = -1;
        while (movs[indMov] < movs.length - 1) {
            movs[indMov]++;
            if (canMove(t, posicion, movs[indMov])) {
                if (indMov > movs.length - 2) {
                    
                } else {
                    rRecorrerTablero(t, movs, indMov + 1, posicion);
                }
            }
        }
        movs[indMov] = -1;
    }

    public static void iRecorrerTablero(Tablero t) {

    }
    public static ArrayList nShits(Tablero t, Pieza p){
        ArrayList solucions = new ArrayList();
        recursiveNQueens(new int[t.DIMENSION], 0, solucions);
        if(solucions.isEmpty()){
            return null;
        }
        return solucions;
    }
    
    public static Vector2D sigCasilla(Vector2D posi, Vector2D mov){
        posi.sumar(mov);
        return posi;
    }
    
    private static void recursiveNShits(Tablero t, Vector2D []moviments, Vector2D posi, LinkedList<Vector2D> solucion) throws Exception{
        int indMov = 0;
        solucion.push(posi);
        t.ocuparPosicion(posi);
        while(indMov < moviments.length){
            indMov++;
            if(t.casillaLibre(moviments[indMov])){
                if(t.casillasVisitadas > t.getNumCasillas() - 1){ // t.casillasVisitadas+1 > t.length*t.length - 2
                    t.visualizar(solucion);
                    return;
                } else {
                    recursiveNShits(t, moviments, sigCasilla(posi, moviments[indMov]), solucion);
                }
            }
        }
        solucion.pop();
        t.desOcuparPosicion(posi);
        //moviments[indMov] = -1;
    }
}
