package poo.model;

import org.json.JSONObject;

/**
 * @author SRM
 * @see Información: Esta clase <<abstracta>> ofrece una plantilla de herencia
 *      para crear "Objetos" de tipo ComputerDevice. Cuenta con atributos tales
 *      como
 *      "model" y "wireless", atributos básicos que todo dispositvo tiene
 */
public abstract class ComputerDevice {

    protected String model;
    protected boolean wireless;

    /**
     * Constructor parametrizado
     *
     * @param model    String. Ejemplo: K-0001
     * @param wireless boolean. Ejemplo: false
     */
    public ComputerDevice(String model, boolean wireless) {
        this.model = model;
        this.wireless = wireless;
    }

    // Obtener nombre de la clase instanciada
    public String getType() {
        return this.getClass().getSimpleName();
    }

    /**
     * Accesor model
     *
     * @return String. Obtener modelo dispositivo
     */
    public String getModel() {
        return model;
    }

    /**
     * Mutador model
     *
     * @param model String. Ejemplo: K-0001
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Accesor wireless
     *
     * @return boolean. Retorna si un dispositivo es o no inalámbrico
     */
    public boolean getWireless() {
        return this.wireless;
    }

    /**
     * Mutador wireless
     *
     * @param wireless booelan. Ejemplo: false
     */
    public void setWireless(boolean wireless) {
        this.wireless = wireless;
    }

    /**
     * Método para retornar una cadena de texto "si"/"no" según un valor
     * booleano true/false
     *
     * @param attribute
     * @return
     */
    protected String strBooleanAttribute(boolean attribute) {
        return attribute ? "Sí" : "No";
    }

    /*
     * Imprimir información de la clase
     */
    @Override
    public String toString() {
        return (new JSONObject(this).toString(2));
    }
}
