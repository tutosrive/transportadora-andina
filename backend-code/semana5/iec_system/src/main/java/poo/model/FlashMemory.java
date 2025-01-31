package poo.model;

public class FlashMemory extends StorageDevice
{

    private String type;

    /**
     *
     * @param model String. Ejemplo: K-0001
     * @param wireless boolean. Ejemplo: false
     * @param usedCapacityGb double. Ejemplo: 154.23 (Corresponde a 154.23 GB)
     * @param speed int. Ejemplo: 20 (Corresponde a 20MB/s)
     * @param freeScpace double. Ejemplo: 123.45 (Corresponde a 123.45 GB)
     * @param type String. Ejemplo: USB-3 (Indica un flash-memory USB-3.0)
     */
    public FlashMemory(String model, boolean wireless, double usedCapacityGb, int speed, double freeScpace,
            String type)
    {
        super(model, wireless, usedCapacityGb, speed, freeScpace);
        this.setType(type);
    }

    /**
     * Accesor tipo
     *
     * @return String. Obtener el tipo de flash-memory
     */
    public String getType()
    {
        return type;
    }

    /**
     * Mutador type
     *
     * @param type String. Ejemlo: USB-2 (Indica flash-memory USB-2.0)
     */
    public final void setType(String type)
    {
        this.type = type;
    }
}
