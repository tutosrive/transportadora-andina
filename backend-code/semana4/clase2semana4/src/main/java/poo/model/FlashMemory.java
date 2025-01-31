package poo.model;

public class FlashMemory extends StorageDevice {
    private String type;

    /**
     * 
     * @param model          String. Ejemplo: K-0001
     * @param wireless       boolean. Ejemplo: false
     * @param usedCapacityGb double. Ejemplo: 154.23 (Corresponde a 154.23 GB)
     * @param speed          int. Ejemplo: 20 (Corresponde a 20MB/s)
     * @param freeScpace     double. Ejemplo: 123.45 (Corresponde a 123.45 GB)
     * @param type           String. Ejemplo: USB-3 (Indica un flash-memory USB-3.0)
     */
    public FlashMemory(String model, boolean wireless, double usedCapacityGb, int speed, double freeScpace,
            String type) {
        super(model, wireless, usedCapacityGb, speed, freeScpace);
        this.setType(type);
    }

    /**
     * Accesor tipo
     * 
     * @return String. Obtener el tipo de flash-memory
     */
    public String getType() {
        return type;
    }

    /**
     * Mutador type
     * 
     * @param type String. Ejemlo: USB-2 (Indica flash-memory USB-2.0)
     */
    public final void setType(String type) {
        this.type = type;
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
        String info = String.format("%s Type: %-29s | %n%n | %-6s | %-10s |", super.toString(), this.type, "-----",
                "-----");
        return info;
    }
}
