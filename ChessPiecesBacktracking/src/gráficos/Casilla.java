package gráficos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * 
 * @author Arturo y Marta
 */
class Casilla {
    
    private final int LADO;
    private final Rectangle2D.Float RECTANGULO;
    private Color color;
    private boolean ocupada;
    private final int x;
    private final int y;

    public Casilla(int x, int y, Color c, boolean o, int lado) {
        LADO = lado;
        color = c;
        ocupada = o;
        this.x = x;
        this.y = y;
        RECTANGULO = new Rectangle2D.Float(x * lado, y * lado, lado, lado);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.drawRect(0, 0, 150,150);
        g2d.fill(RECTANGULO);
    }
    
    public boolean estaOcupada(){
        return ocupada;
    }
    
    public void setOcupada(boolean b){
        ocupada = b;
    }

    public int getLado(){
        return LADO;
    }
}
