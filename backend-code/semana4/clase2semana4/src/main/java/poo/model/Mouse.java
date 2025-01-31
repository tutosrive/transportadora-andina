package poo.model;

/**
 * @author SRM
 * @see Información: Esta clase, ofrece una plantilla de un dispositivo tipo
 *      "Mouse" y
 *      hereda de la clase madre "ComputerDevice"
 * 
 *      Esta, tiene atributos tales como los "dpi" o "Puntos Por Pulgada"
 *      (sensibilidad/presición del mouse)
 */

public class Mouse extends ComputerDevice {
    private String dpi;
    private int buttons;

    /**
     * Constructor por defecto Clase Mouse
     */
    public Mouse() {
        this("", false, "", 0);
    }

    /**
     * Constructor parametrizado clase Mouse.
     * 
     * @param dpi     String, "Dots Per Inch", Puntos Por Pulgada, cantidad de
     *                presición/sensibilidad del Mouse
     * @param buttons int, cantidad de botones que tiene el Mouse
     */
    public Mouse(String model, boolean wireless, String dpi, int buttons) {
        super(model, wireless);
        this.setDpi(dpi);
        this.setButtons(buttons);
    }

    /**
     * Accesor dpi
     * 
     * @return String, cantidad de presición/sensibilidad Mouse
     */
    public String getDpi() {
        return this.dpi;
    }

    /**
     * Mutador dpi
     * 
     * @param dpi String, cantidad de presición/sensibilidad Mouse
     */
    public final void setDpi(String dpi) {
        this.dpi = dpi;
    }

    /**
     * Accesor buttons
     * 
     * @return int, cantidad de botones que tiene el Mouse
     */
    public int getButtons() {
        return this.buttons;
    }

    /**
     * Mutador de botones
     * 
     * @param buttons int, establecer la cantidad de botones que tiene el computador
     */
    public final void setButtons(int buttons) {
        this.buttons = buttons;
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
        String info = String.format(
                "%s DPI: %-30s | %n | %-6s | %-10s | %-15s | Buttons: %-26d | %n%n | %-6s | %-10s |",
                super.toString(), this.dpi, "-----", "-----", "-----", this.buttons, "-----", "-----");
        return info;
    }
}
