package poo;

/**
 * La clase 'Rectangulo', representa una 'Figura' de tipo Rectángulo
 * 
 * 'Hereda' de la super clase 'Figura'
 */
public class Reactangulo extends Figura {
    /**
     * @param lados Representa un arreglo de parámetros (lados) necesarios para el
     *              cálculo del área, en este caso son necesarios dos (2)
     *              parámetros, la 'base' y la 'altura' del rectángulo
     * 
     * @return Retorna el resulatado del 'área' del rectángulo
     */
    // Método sobreescrito de la super clase 'Figura'
    // Hallar área del rectángulo
    @Override
    public double calcularArea(int... lados) {
        // lados[0] = base = a
        // lados[1] = altura = b
        // area = a * b
        double area = lados[0] * lados[1];
        // Retorna el área
        return area;
    }

}
