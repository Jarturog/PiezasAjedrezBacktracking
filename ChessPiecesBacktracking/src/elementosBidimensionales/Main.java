/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elementosBidimensionales;

import piezas.Caballo;

/**
 *
 * @author Marta
 */
public class Main {
    public static void main(String[] args) throws Exception {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("No se ha podido establecer el formato de su plataforma" + e);
        }
        Ajedrez aj = new Ajedrez();
//        Caballo c = new Caballo(new Vector2D(4, 4));
//
//        c.recorrerTablero(aj.tablero);

    }
}
