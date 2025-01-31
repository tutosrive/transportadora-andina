package poo;

/**
 * La clase 'Paralelograma', representa una 'Figura' de tipo Paralelograma
 * 
 * 'Hereda' de la super clase 'Figura'
 */
public class Paralelograma extends Figura {
    /**
     * @param lados Representa un arreglo de parámetros (lados) necesarios para el
     *              cálculo del área, en este caso son necesarios dos (2)
     *              parámetros, la 'base' y la 'altura' del paralelograma
     * 
     * @return Retorna el resulatado del 'área' del paralelograma
     */
    // Método sobreescrito de la super clase 'Figura'
    // Hallar área del paralelograma
    @Override
    public double calcularArea(int... lados) {
        // area = Base x Altura
        double area = lados[0] * lados[1];
        // Retornar área
        return area;
    }
}
