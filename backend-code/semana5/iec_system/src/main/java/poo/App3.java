package poo;

import java.util.ArrayList;
import poo.model.Computer;
import poo.model.ComputerDevice;

public class App3 {
    // Obtener lista de todos los computadores
    private final static ArrayList<Computer> pcs = App2.getAllComputers();

    public static void main(String[] args) {
        // Ejecutar App2, para que se agreguen los computadores a la lista
        App2.main(args);

        // imprimir información

        // parte 1
        getFullFreeCapacityIECComputers();
        getAmountAllHdd();
        getAllMonitor();
    }

    // Obtener la capacidad libre en todos los computadores
    public static void getFullFreeCapacityIECComputers() {
        double freeCapacityAll = 0;

        // Recorrer cada computador obtener la capacidad libre de cada uno y sumarla
        for (Computer pc : pcs) {
            // sumar progresivamente cada "capacidad libre"
            freeCapacityAll += pc.getFullFreeCapacity();
        }
        // Imprimir información
        System.out.printf("1. Capacidad libre de todos los Computadores: %.2fGB %n", freeCapacityAll);
    }

    // Contar hdd de todos los computadores
    public static void getAmountAllHdd() {
        int hdd = 0;
        // Recorrer cada computador
        for (Computer pc : pcs) {
            // Por cada computador se verifica cada dispositivo
            for (ComputerDevice device : pc.getComputerDevices()) {
                // Verificar si el nombre de la clase corresponde con el tipo requerido
                if (device.getClass().getSimpleName().equals("HardDiskDrive")) {
                    hdd++;
                }
            }
        }

        // Imprimir información
        System.out.printf(
                "2. Se necesitan comprar (%d) \"Solid State Drive\"(SSD) para reemplazar 'Todos' los \"Hard Disk Drive\" (HDD).%n",
                hdd);
    }

    // Listar monitores existentes
    public static void getAllMonitor() {
        int monitors = 0;
        String res = "3. Lista de Monitores: \n";
        // Recorrer cada computador
        for (Computer pc : pcs) {
            // Por cada computador se verifica cada dispositivo
            for (ComputerDevice device : pc.getComputerDevices()) {
                // Verificar si el nombre de la clase corresponde con el tipo requerido
                if (device.getClass().getSimpleName().equals("HardDiskDrive")) {
                    monitors++;
                    // Se crea un String formateado con la información
                    res += String.format("%nMonitor N° %d %n %s", monitors, device);
                }
            }
        }
        // Imprimir información
        System.out.println(res);
    }
}
