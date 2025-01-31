package poo;

/**
 * La clase 'Triángulo', representa una 'Figura' de tipo Triángulo
 * 
 * 'Hereda' de la super clase 'Figura'
 */
public class Triangulo extends Figura {
    /**
     * @param lados Representa un arreglo de parámetros (lados) necesarios para el
     *              cálculo del área, en este caso son necesarios dos (2)
     *              parámetros, la 'base' y la 'altura' del triángulo
     * 
     * @return Retorna el resulatado del 'área' del triángulo
     */
    // Método sobreescrito de la super clase 'Figura'
    // Hallar área del triángulo
    @Override
    public double calcularArea(int... lados) {
        // area = (base * altura) / 2.0
        double area = (lados[0] * lados[1]) / 2.0;
        // Retornar área
        return area;
    }

}
