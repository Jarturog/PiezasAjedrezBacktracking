package elementosBidimensionales;

import java.awt.Color;
import java.util.LinkedList;
import javax.swing.ImageIcon;



import javax.swing.JLabel;

/**
 *
 * @author Arturo y Marta
 */
public class Casilla extends JLabel {

    private final Color color;
    private boolean ocupada;
    //private ImageIcon imagen;

    public Casilla(Color c) {
        this.color = c;
        setBackground(color);
        setOpaque(true);
        ocupada = false;
    }
    public void setImagen(ImageIcon imagen){
        //this.imagen = imagen;
        setBackground(color);
        setIcon(imagen);
        setOpaque(true);
    }
//    public Color getBackgroundColor(){
//        return color;
//    }

    public boolean estaOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean b) {
        ocupada = b;
    }
//    public void ocuparPosicion(Vector2D pos) throws Exception {
//        if (!casillaLibre(pos)) {
//            throw new Exception("Error ocupando una casilla que no está libre");
//        }
//        t[pos.getX()][pos.getY()].setOcupada(true);
//        casillasVisitadas++;
//    }
//
//    public void desOcuparPosicion(Vector2D pos) throws Exception {
//        if (casillaLibre(pos)) {
//            throw new Exception("Error desocupando una casilla ya libre");
//        }
//        t[pos.getX()][pos.getY()].setOcupada(false);
//        casillasVisitadas--;
//    }
//    
//    public boolean movimientoLegal(Vector2D futuraPosicion) {
//        return !(futuraPosicion.getX() > DIMENSION - 1
//                || futuraPosicion.getY() > DIMENSION - 1
//                || futuraPosicion.getX() < 0 || futuraPosicion.getY() < 0);
//    }
//
//    public boolean casillaLibre(Vector2D pos) throws Exception {
//        if(!movimientoLegal(pos)){
//            throw new Exception("Error comprobando si una casilla inexistente está libre");
//        }
//        return !t[pos.getX()][pos.getY()].estaOcupada();
//    }
//
//    public int getDIMENSION() {
//        return DIMENSION;
//    }
//
//    public int getNumCasillas() {
//        return DIMENSION * DIMENSION;
//    }
//
//    public int getCasillasVisitadas() {
//        return casillasVisitadas;
//    }
//    
//    public void visualizar(LinkedList<Vector2D> movimientosRealizados) {
//        // aquí se visualizará sacando los movimientos
//        System.out.println(movimientosRealizados.getFirst()); // en vez de
//        // println se van colocando y dejando x por donde pasan
//    }
}
