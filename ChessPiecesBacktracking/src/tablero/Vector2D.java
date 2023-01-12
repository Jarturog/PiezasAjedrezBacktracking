package tablero;

/**
 * Clase que representa un vector de dos elementos. Implementa operaciones
 * básicas y se utiliza para estimar posiciones en un plano bidimensional, como
 * por ejemplo es el caso de dibujar una pieza en una casilla.
 *
 * @author Arturo y Marta
 */
public class Vector2D {

    /**
     * Elementos constituyentes del vector.
     */
    private final int x, y;

    /**
     * Constructor que crea un vector del siguiente estilo: (x, y)
     *
     * @param x primer x;
     * @param y segundo y;
     */
    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor que crea una nueva instancia copia del vector pasado por
     * parámetro.
     *
     * @param v mencionado vector.
     */
    public Vector2D(Vector2D v) {
        x = v.x;
        y = v.y;
    }

    /**
     * Getter del elemento x;
     *
     * @return elemento x;
     */
    public int getX() {
        return x;
    }

    /**
     * Getter del elemento y;
     *
     * @return elemento y;
     */
    public int getY() {
        return y;
    }

    /**
     * Suma dos vectores y crea uno nuevo.
     *
     * @param v1 primer vector a sumar.
     * @param v2 segundo vector a sumar.
     * @return nueva instancia resultande de sumar v1 y v2.
     */
    public static Vector2D sumar(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.x + v2.x, v1.y + v2.y);
    }

    /**
     * Crea una nueva instancia de Vector2D a partir de la multiplicación de un
     * vector y un escalar.
     *
     * @param v vector a multiplicar.
     * @param m escalar.
     * @return nueva instancia de v * m.
     */
    public static Vector2D multiplicar(Vector2D v, int m) {
        return new Vector2D(v.x * m, v.y * m);
    }
}
