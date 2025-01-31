package poo.model;

public class Keyboard extends ComputerDevice {
    private boolean gamer;
    private boolean integrated;
    private Language language;

    /**
     * Constructor parametrizado
     * 
     * @param model      String. Ejemplo: K-0001
     * @param wireless   boolean. Ejemplo: false
     * @param gamer      boolean. Ejemplo: false
     * @param integrated boolean. Ejemplo: true
     * @param language   Language. Ejemplo: Language.ES
     */
    public Keyboard(String model, boolean wireless, boolean gamer, boolean integrated, Language language) {
        super(model, wireless);
        this.setGamer(gamer);
        this.setIntegrated(integrated);
        this.setLanguage(language);
    }

    /**
     * Accesir gamer
     * 
     * @return gamer boolean. Consultar si teclado es gamer
     */
    public boolean getGamer() {
        return this.gamer;
    }

    /**
     * Mutador gamer
     * 
     * @param gamer boolean. Ejemplo: false
     */
    public final void setGamer(boolean gamer) {
        this.gamer = gamer;
    }

    /**
     * Accesor integrated
     * 
     * @return integrated boolean. Consultar si teclado es integrado
     */
    public boolean getIntegrated() {
        return this.integrated;
    }

    /**
     * Mutador integrated
     * 
     * @param integrated boolean. Ejemplo: false
     */
    public final void setIntegrated(boolean integrated) {
        this.integrated = integrated;
    }

    /**
     * Accesor language
     * 
     * @return language Language. Obtener el idioma del teclado
     */
    public Language getLanguage() {
        return this.language;
    }

    /**
     * Mutador language
     * 
     * @param language Language. Ejemlo: Language.ES
     */
    public final void setLanguage(Language language) {
        this.language = language;
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
        String strIntegrated = super.strBooleanAttribute(this.integrated);
        String strGamer = super.strBooleanAttribute(this.gamer);
        String info = String.format(
                "%s Gamer: %-28s | %n | %-6s | %-10s | %-15s | Integrated: %-23s | %n%n | %-6s | %-10s |",
                super.toString(),
                strGamer, "-----", "-----",
                "-----", strIntegrated, "-----", "-----");
        return info;
    }
}
