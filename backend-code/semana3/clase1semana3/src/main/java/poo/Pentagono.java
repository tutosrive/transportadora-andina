package poo;

/**
 * La clase 'Pentagono', representa una 'Figura' de tipo Pentagono
 * 
 * 'Hereda' de la super clase 'Figura'
 */

public class Pentagono extends Figura {
    /**
     * @param lados Representa un arreglo de parámetros (lados) necesarios para el
     *              cálculo del área, en este caso sólo es necesario un parámetro,
     *              el cual es la 'longitud' del pentágono
     * 
     * @return Retorna el resulatado del 'área' del pentágono
     */
    // Método sobreescrito de la super clase 'Figura'
    // Hallar área del pentágono
    @Override
    public double calcularArea(int... lados) {
        // disciminante raíz cuadrada
        double discriminante = (5 * (5 + 2 * Math.sqrt(5)));
        System.out.println("dis: " + Math.sqrt(discriminante));
        // area = 1/4 * sqrt(5 * ( 5 + 2 * sqrt(5) )) * s^2
        double area = (1.0 / 4.0) * Math.sqrt(discriminante) * (Math.pow(lados[0], 2));
        // Retorno resultado área
        return area;
    }
}
