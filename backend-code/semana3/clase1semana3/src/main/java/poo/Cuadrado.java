package poo;

/**
 * La clase 'Cuadrado', representa una 'Figura' de tipo Cuadrado
 * 
 * 'Hereda' de la super clase 'Figura'
 */
public class Cuadrado extends Figura {
    /**
     * @param lados Representa un arreglo de parámetros (lados) necesarios para el
     *              cálculo del área, en este caso sólo es necesario un parámetro,
     *              un 'lado' del cuadrado del cuadrado
     * 
     * @return Retorna el resulatado del 'área' del cuadrado
     */
    // Método sobreescrito de la super clase 'Figura'
    // Hallar área del cuadrado
    @Override
    public double calcularArea(int... lados) {
        // area = (lado^2)
        double area = Math.pow(lados[0], 2);
        // Retorna el área
        return area;
    }
}
