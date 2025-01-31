package poo;

/**
 * La clase 'Circulo' representa una 'Figura' de tipo 'Círculo', hereda de la
 * super clase 'Figura'
 */

public class Circulo extends Figura {
    /**
     * @param lados Representa un arreglo de parámetros (lados) necesarios para el
     *              cálculo del área, en este caso sólo es necesario un parámetro,
     *              el cual es el 'radio' del Círculo
     * 
     * @return Retorna el resulatado del 'área' del Círculo
     */

    // Método sobreescrito de la super clase "Figura"
    // Hallar área del círculo
    @Override
    public double calcularArea(int... lados) {
        // area = PI * (r^2)
        double area = Math.PI * (Math.pow(lados[0], 2));
        // Retornar área
        return area;
    }

}
