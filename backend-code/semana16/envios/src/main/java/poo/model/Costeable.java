package poo.model;

public interface Costeable {
    /**
     * Calcular el costo del envío "según su tipo"
     * 
     * @return Valor de tipo double con el costo total del envío
     */
    public double getCosto();

    /**
     * Genera un String con formato JSON con la información de la clase
     * 
     * @return Cadena de texto con toda la información de la clase instanciada. En
     *         formato JSON.
     */
    public String toJSON();
}
