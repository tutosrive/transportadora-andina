package poo.model;

/**
 * @author SRM
 * @see Información: Esta clase <<abstracta>> ofrece una plantilla de herencia
 *      para crear "Objetos" de tipo ComputerDevice. Cuenta con atributos tales
 *      como "model" y "wireless", atributos básicos que todo dispositvo tiene
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
     * Método para retornar una cadena de texto
     * "si"/"no" según un valor booleano true/false
     * 
     * @param attribute
     * @return
     */
    protected String strBooleanAttribute(boolean attribute) {
        return attribute ? "Sí" : "No";
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
        String isWireless = this.strBooleanAttribute(wireless);
        String info = String.format(
                "Model: %-28s | %n | %-6s | %-10s | %-15s | Wireless: %-25s | %n | %-6s | %-10s | %-15s |",
                this.model, "-----", "-----",
                "-----", isWireless, "-----", "-----",
                "-----");
        return info;
    }
}
