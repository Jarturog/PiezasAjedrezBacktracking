/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gráficos;

/**
 *
 * @author jartu
 */
public class Tablero {
    
    private boolean [][] tableroVisitas;
    
    public Tablero(int d){
        tableroVisitas = new boolean [d][d];
    }
    
    public void ocuparPosicion(Vector2D pos) throws Exception{
        if(!casillaLibre(pos)){
            throw new Exception();
        }
        tableroVisitas[pos.getX()][pos.getY()] = true;
    }
    
    public boolean casillaLibre(Vector2D pos){
        return !tableroVisitas[pos.getX()][pos.getY()];
    }
    
    public int getDimensiones(){
        return tableroVisitas.length;
    }
}
