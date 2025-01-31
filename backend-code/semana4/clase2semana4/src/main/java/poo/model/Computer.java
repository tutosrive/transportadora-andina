package poo.model;

import java.util.ArrayList;

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
        this("", new ArrayList<>(), TypeComputer.UNKNOWN);
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
        if (type == TypeComputer.LAPTOP) {

        }
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
        // recorrer lista buscar teclado
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

    /**
     * Recorrer la lista de dispositivos conectados y listarlos fila por fila
     * 
     * @return devices Listado de dispositivos conectados (se linstan línea a línea)
     */
    public String strDevices() {
        String devices = "";

        for (ComputerDevice device : this.computerDevices) {
            devices += String.format("%-15s | %-19s ",
                    device.getClass().getSimpleName(), device.toString());
        }
        devices += String.format("%-15s | %-35s |", "-----", "-----");
        return devices;
    }

    /**
     * @return retornar información formateada sobre la clase
     */
    @Override
    public String toString() {
        String info = String.format(
                "  %s %n | ID     | Type PC    | Devices         | Especificaciones                    | %n%n | %-6s | %-10s | %s %n%n",
                "_____________________________________________________________________________",
                this.id, this.type,
                this.strDevices());
        return info;
    }
}