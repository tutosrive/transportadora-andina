package poo;

/**
 * La clase 'Rombo', representa una 'Figura' de tipo Rombo
 * 
 * 'Hereda' de la super clase 'Figura'
 */
public class Rombo extends Figura {
    /**
     * @param lados Representa un arreglo de parámetros (lados) necesarios para el
     *              cálculo del área, en este caso son necesarios dos (2)
     *              parámetros, la 'Diagonal 1' y la 'Diagonal 2' del rombo
     * 
     * @return Retorna el resulatado del 'área' del rombo
     */
    // Método sobreescrito de la super clase 'Figura'
    // Hallar área del rombo
    @Override
    public double calcularArea(int... lados) {
        // area = (Diagonal1 * Diagonal2) / 2.0
        double area = (lados[0] * lados[1]) / 2.0;
        // Retornar área
        return area;
    }

}
