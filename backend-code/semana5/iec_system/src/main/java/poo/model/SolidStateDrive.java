package poo.model;

public class SolidStateDrive extends StorageDevice {

    private String interfaceType;

    /**
     * Constructor parametrizado
     *
     * @param model          String. Ejemplo: K-0001
     * @param wireless       boolean. Ejemplo: false
     * @param usedCapacityGb double. Ejemplo: 154.23 (Corresponde a 154.23 GB)
     * @param speed          int. Ejemplo: 20 (Corresponde a 20MB/s)
     * @param freeScpace     double. Ejemplo: 123.45 (Corresponde a 123.45 GB)
     * @param interfaceType  String. Ejemplo: SATA-6 (Corresponde a SATA-6GB/s)
     */
    public SolidStateDrive(String model, boolean wireless, double usedCapacityGb, int speed, double freeScpace,
            String interfaceType) {
        super(model, wireless, usedCapacityGb, speed, freeScpace);
        this.setInterfaceType(interfaceType);
    }

    /**
     *
     * Accesor interfaceType
     *
     * @return String. Tipo de interfaz SSD
     */
    public String getInterfaceType() {
        return interfaceType;
    }

    /**
     * Mutador interfaceType
     *
     * @param interfaceType Establecer tipo de interfaz SSD
     */
    public final void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }
}
