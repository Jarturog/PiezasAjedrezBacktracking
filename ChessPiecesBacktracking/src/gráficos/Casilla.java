/*
 * Classe casella
 *
 * El constructor defineix una casella com un rectangle d'un color i si està
 * ocupada o no
 *
 * El mètode paintComponent pinta el rectangle de la casella
 */

package gráficos;

/**
 *
 * @author miquelmascaro
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D.Float;

class Casilla {
    
    private Float rec;
    private Color color;
    private boolean ocupada;

    public Casilla(Float r, Color c, boolean ocupada ) {
        this.rec = r;
        this.color = c;
        this.ocupada = ocupada;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.drawRect(0, 0, 150,150);
        g2d.fill(rec);
    }
    
    public boolean estaOcupada(){
        return ocupada;
    }
    
    public void setOcupada(boolean b){
        ocupada = b;
    }

}
