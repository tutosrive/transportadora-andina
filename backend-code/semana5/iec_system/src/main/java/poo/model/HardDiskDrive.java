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
}
