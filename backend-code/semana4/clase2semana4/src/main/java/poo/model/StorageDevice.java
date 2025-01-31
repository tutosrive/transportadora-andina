package poo.model;

public abstract class StorageDevice extends ComputerDevice {
    private double usedCapacityGb;
    private int speed;
    private double freeScpace;

    /**
     * Constructor parametrizado
     * 
     * @param model          String. Ejemplo: K-0001
     * @param wireless       boolean. Ejemplo: false
     * @param usedCapacityGb double. Ejemplo: 154.23 (Corresponde a 154.23 GB)
     * @param speed          int. Ejemplo: 20 (Corresponde a 20MB/s)
     * @param freeScpace     double. Ejemplo: 123.45 (Corresponde a 123.45 GB)
     */
    protected StorageDevice(String model, boolean wireless, double usedCapacityGb, int speed, double freeScpace) {
        super(model, wireless);
        this.setUsedCapacityGb(usedCapacityGb);
        this.setSpeed(speed);
        this.setFreeScpace(freeScpace);
    }

    /**
     * Accesor usedCapacityGb
     * 
     * @return double. Obtener capacidad del disco usada
     */
    public double getUsedCapacityGb() {
        return this.usedCapacityGb;
    }

    /**
     * Mutador usedCapacityGb
     * 
     * @param usedCapacityGb double. Ejemplo: 234.12 (Indica 234.12GB)
     */
    protected final void setUsedCapacityGb(double usedCapacityGb) {
        this.usedCapacityGb = usedCapacityGb;
    }

    /**
     * Accesor speed
     * 
     * @return int. Obtener velocidad de disco/s
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Mutador speed
     * 
     * @param speed int. Ejemplo: 25 (Indica 25MB/s)
     */
    public final void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Accesor freeSpace
     * 
     * @return double. Obtener espacio libre en disco
     */
    public double getFreeScpace() {
        return this.freeScpace;
    }

    /**
     * Mutador freeSpace
     * 
     * @param freeScpace double. Ejemplo: 1234.45 (Corresponde a 1234.45GB)
     */
    public final void setFreeScpace(double freeScpace) {
        this.freeScpace = freeScpace;
    }

    /**
     * Obtener la capacidad total del disco
     * 
     * @return double. Capacidad total del disco duro
     */
    public double getCapacityTotal() {
        return this.freeScpace + this.usedCapacityGb;
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
                "%s Used Capacity: %-20s | %n | %-6s | %-10s | %-15s | Free Capacity: %-20s | %n | %-6s | %-10s | %-15s | Total Capacity: %.3f %-11s | %n | %-6s | %-10s | %-15s | Speed: %-28d | %n | %-6s | %-10s | %-15s |",
                super.toString(),
                this.usedCapacityGb, "-----", "-----",
                "-----", this.freeScpace, "-----", "-----", "-----", this.getCapacityTotal(), "", "-----", "-----",
                "-----", this.speed, "-----", "-----",
                "-----");
        return info;
    }
}
