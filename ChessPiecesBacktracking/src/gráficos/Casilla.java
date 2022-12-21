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
import java.awt.geom.Rectangle2D;

class Casilla {
    
    private Rectangle2D.Float rec;
    private Color color;
    private Boolean ocupada;

    public Casilla(Rectangle2D.Float r, Color c, Boolean ocupada ) {
        this.rec = r;
        this.color = c;
        this.ocupada = ocupada;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.color);
        g2d.drawRect(0, 0, 150,150);
        g2d.fill(this.rec);
    }

}
