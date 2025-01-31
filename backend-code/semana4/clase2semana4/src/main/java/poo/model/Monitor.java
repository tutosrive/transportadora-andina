package poo.model;

public class Monitor extends ComputerDevice {
    private float inches;

    /**
     * Constructor parametrizado, establecer modelo y wireless declase madre
     * 
     * @param model    String. Ejemplo: K-0001
     * @param wireless boolean. Ejemplo: false
     * @param inches   float. Ejemplo: 12.4 (Corresponde a 12.4 Pulgadas)
     */
    public Monitor(String model, boolean wireless, float inches) {
        super(model, wireless);
        this.setInches(inches);
    }

    /**
     * Accesor inches
     * 
     * @return float. Cantidad de inches/pulgadas
     */
    public float getInches() {
        return inches;
    }

    /**
     * Mutador inches
     * 
     * @param inches float. Ejemplo: 17.5 (Corresponde a 17.5 Pulgadas)
     */
    public final void setInches(float inches) {
        this.inches = inches;
    }

    /*
     * Imprimir información de la clase
     * Sé que la forma en la que logré que la salida fuese ordenada no es la
     * adecuada (a primera vista no se entiende que hace en sí pero la salida la
     * consola lo dice todo)
     * pero fue la única forma que imaginé en hacerla
     */
    @Override
    public String toString() {
        String info = String.format("%s Inches: %.1f %-23s| %n%n | %-6s | %-10s |", super.toString(), this.inches,
                "",
                "-----",
                "-----");
        return info;
    }
}
