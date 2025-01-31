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
}
