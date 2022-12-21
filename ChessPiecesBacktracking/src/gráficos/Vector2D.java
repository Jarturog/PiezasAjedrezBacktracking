/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gráficos;

/**
 *
 * @author jartu
 */
public class Vector2D {
    private int x,y;

    public Vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public Vector2D(Vector2D v){
        x = v.x;
        y = v.y;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void sumar(Vector2D v){
        x += v.x;
        y += v.y;
    }
}
