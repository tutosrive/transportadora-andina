package poo;

/**
 * La clase 'Trapecio' representa una 'Figura' de tipo 'Trapecio'.
 * 
 * Hereda de la super clase 'Figura'
 */
public class Trapecio extends Figura {
    /**
     * @param lados Representa un arreglo de enteros, con las medidas necesarias
     *              para el cálculo de su área, en este caso se requieren (3)
     *              parámetros [baseMenor, baseMayor, altura]
     * 
     * @return Retorna el resultado del área calculada
     */

    // Método sobreescrito de la super clase Figura
    // Hallar área del trapecio
    @Override
    public double calcularArea(int... lados) {
        // area = ((baseMenor + baseMayor) * altura) / 2.0
        double area = ((lados[0] + lados[1]) * lados[2]) / 2.0;
        // Retorna el área
        return area;
    }
}
