package poo.model;

import java.util.ArrayList;
import org.json.JSONObject;

/**
 * @author SRM
 * @implNote Esta clase ofrece unaplantilla para crear objetos de tipo
 *           "Computer" con atributos como un "id", "computerDevices" y un
 *           "TypeComputer", atributos necesarias para un objeto de tipo
 *           "Computer"
 */

public class Computer {
    // Atributos de clase
    private String id; // identificador Computer
    public ArrayList<ComputerDevice> computerDevices; // Dispositivos conectados (lista)
    public TypeComputer type; // Tipo de computador

    /**
     * Constructor por defecto Clase Computer
     */
    public Computer() {
        this.computerDevices = new ArrayList<>();
    }

    /**
     * Constructor parametrizado
     * 
     * @param id              String, "identificador" del computador
     * @param computerDevices ArrayList, lista de dispositivos conectados al
     *                        computador
     * @param type            TypeComputer, literal tipo de computador:
     * @see Ejemplo: TypeComputer typePc = TypeComputer.DESKTOP;
     */
    public Computer(String id, ArrayList<ComputerDevice> computerDevices, TypeComputer type) {
        this.setId(id);
        this.setComputerDevices(computerDevices);
        this.setType(type);
    }

    // Set integrated keyboard
    // metodo agregar dispositivo 1 a 1 setter1a1()

    /**
     * Accesor type
     * 
     * @return type Literal de tipo "TypeComputer"
     */
    public TypeComputer getType() {
        return this.type;
    }

    /**
     * Mutador type
     * 
     * @param type Tipo de computador, literal de tipo "TyperComputer"
     */
    public final void setType(TypeComputer type) {
        this.type = type;
    }

    /**
     * Accesor id
     * 
     * @return id "Identificador" del computador
     */
    public String getId() {
        return this.id;
    }

    /**
     * Mutador id
     * 
     * @param id String para establecer el "identificador" del computador
     */
    public final void setId(String id) {
        this.id = id;
    }

    /**
     * Accesor computerDevices
     * 
     * @return computerDevices ArrayList, lista de dispositivos conectados al
     *         computador
     */
    public ArrayList<ComputerDevice> getComputerDevices() {
        return computerDevices;
    }

    /**
     * Mutador computerDevices
     * Asigna la lista de dispositivos recibidos por parámetro al computador
     * 
     * @param computerDevices Lista de dispositivos a agregar al computador:
     *                        ArrayList<ComputerDevice>
     */
    public final void setComputerDevices(ArrayList<ComputerDevice> computerDevices) {
        this.computerDevices = computerDevices;

    }

    // capacidad total libre
    public double getFullFreeCapacity() {
        double freeScpaceFull = 0;
        for (ComputerDevice device : this.computerDevices) {
            if (device instanceof StorageDevice storageDevice) {
                freeScpaceFull += storageDevice.getFreeScpace();
            }
        }

        return freeScpaceFull;
    }

    // capacidad total ocupada
    public double getFullUsedCapacity() {
        double usedScpaceFull = 0;
        for (ComputerDevice device : this.computerDevices) {
            if (device instanceof StorageDevice storageDevice) {
                usedScpaceFull += storageDevice.getUsedCapacityGb();
            }
        }

        return usedScpaceFull;
    }

    // Accesor fullCapacity
    public double getFullCapacity() {
        return getFullFreeCapacity() + getFullUsedCapacity();
    }

    /*
     * Verificar restricciones dadas
     * permitir retornar un listado de computadores que tienen solo un disco de
     * estado sólido y una memoria flash como únicos medios de almacenamiento, al
     * menos un monitor y no tienen mouse.
     */
    public boolean okRestrictions(Computer pc) {
        // recorrer lista de dispositivos
        int ssd = 0, flashMemory = 0, monitor = 0, mouse = 0, hdd = 0;

        for (ComputerDevice device : pc.getComputerDevices()) {
            // Capturar nombre de la clase y manejar según el tipo
            switch (device.getClass().getSimpleName()) {
                case "SolidStateDrive" -> {
                    ssd++;
                }
                case "FlashMemory" -> {
                    flashMemory++;
                }
                case "HardDiskDrive" -> {
                    hdd++;
                }
                case "Monitor" -> {
                    monitor++;
                }
                case "Mouse" -> {
                    mouse++;
                }
            }
        }
        return ssd == 1 && flashMemory == 1 && monitor >= 1 && mouse == 0 && hdd == 0;
    }

    /**
     * @return retornar información formateada sobre la clase
     */
    @Override
    public String toString() {
        return (new JSONObject(this).toString(2));
    }
}