package poo.model;

public class HardDiskDrive extends StorageDevice {
    private int rpm;
    private double inches;

    /**
     * Constructor parametrizado
     * 
     * @param model          String. Ejemplo: K-0001
     * @param wireless       boolean. Ejemplo: false
     * @param usedCapacityGb double. Ejemplo: 154.23 (Corresponde a 154.23 GB)
     * @param speed          int. Ejemplo: 20 (Corresponde a 20MB/s)
     * @param freeScpace     double. Ejemplo: 123.45 (Corresponde a 123.45 GB)
     * @param rpm            int. Ejemplo: 3000 (Indica 3000 Revoluciones/Minuto)
     * @param inches         double. Ejemplo: 2 (Indica 2 Pulgadas)
     */
    public HardDiskDrive(String model, boolean wireless, double usedCapacityGb, int speed, double freeScpace, int rpm,
            double inches) {
        super(model, wireless, usedCapacityGb, speed, freeScpace);
        this.setRpm(rpm);
        this.setInches(inches);
    }

    /**
     * Accesor rpm
     * 
     * @return int. Velocidad de giro
     */
    public int getRpm() {
        return rpm;
    }

    /**
     * Mutador rpm
     * 
     * @param rpm int. Ejemplo: 3000 (Indica 3000 Rotaciones Por Minuto)
     */
    public final void setRpm(int rpm) {
        this.rpm = rpm;
    }

    /**
     * Accesor inches
     * 
     * @return double. Cantidad de pulgadas
     */
    public double getInches() {
        return inches;
    }

    /**
     * Mutador inches
     * 
     * @param inches double. Ejemplo: 2 (Indica 2 Pulgadas)
     */
    public final void setInches(double inches) {
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
        String info = String.format(
                "%s RPM: %-30d | %n | %-6s | %-10s | %-15s | Inches: %-27.1f | %n%n | %-6s | %-10s |",
                super.toString(), this.rpm, "-----", "-----",
                "-----", this.inches, "-----", "-----");
        return info;
    }
}