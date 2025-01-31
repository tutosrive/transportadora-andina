package poo;

import java.util.ArrayList;

// Importar clases a ejecutar
import poo.model.Computer;
import poo.model.ComputerDevice;
import poo.model.FlashMemory;
import poo.model.HardDiskDrive;
import poo.model.Keyboard;
import poo.model.Language;
import poo.model.Monitor;
import poo.model.Mouse;
import poo.model.SolidStateDrive;
import poo.model.TypeComputer;

public class App {
    public static void main(String[] args) {

        // lista de dispositivos conectados computador 1 y computador 2
        ArrayList<ComputerDevice> devicesPc1 = new ArrayList<>();
        ArrayList<ComputerDevice> devicesPc2 = new ArrayList<>();

        // Computador N°1
        // monitor pc1
        Monitor monitorPc1 = new Monitor("ARH-12", false, 21);
        // monitor 2 pc1
        Monitor monitor2Pc1 = new Monitor("AZR-2", false, 17);
        // teclado pc1
        Keyboard keyboardPc1 = new Keyboard("K-001", true, true, false, Language.ES);
        // mouse pc1
        Mouse mousePc1 = new Mouse("M-001", true, "300", 7);
        // almacenamiento pc1
        SolidStateDrive ssdPc1 = new SolidStateDrive("ssd-0001", false, 123, 20, 545.76, "SATA");
        FlashMemory memory1 = new FlashMemory("USB-arc", false, 23.545, 12, 7.321, "USB-3.0");
        HardDiskDrive hddPC1 = new HardDiskDrive("HDD-W2B3", false, 1230.6564, 70, 456.4343, 3000, 2);

        // añadir dispositivos a la lista de dispositivos computador 1
        devicesPc1.add(monitor2Pc1);
        devicesPc1.add(ssdPc1);
        devicesPc1.add(memory1);
        devicesPc1.add(monitorPc1);
        devicesPc1.add(mousePc1);
        devicesPc1.add(keyboardPc1);
        devicesPc1.add(hddPC1);
        // Objeto computador 1
        // Se crea y añaden sus atributos
        Computer pc1 = new Computer("0001", devicesPc1, TypeComputer.DESKTOP);

        // imprimir información pc1
        System.out.println(pc1);

        // Computadro N°2
        // monitor pc2
        Monitor monitorPc2 = new Monitor("ARH-34", false, 21);
        // monitor 2 pc2
        Monitor monitor2Pc2 = new Monitor("AZR-8", false, 17);
        // teclado pc2
        Keyboard keyboardPc2 = new Keyboard("K-002", true, true, false, Language.EN);
        // mouse pc2
        Mouse mousePc2 = new Mouse("M-002", true, "200", 3);
        // almacenamiento pc2
        SolidStateDrive ssdPc2 = new SolidStateDrive("ssd-0002", false, 753, 67, 5.7, "M.2");
        FlashMemory flashMemoryPc2 = new FlashMemory("F-12B34", false, 12.54, 12, 12.4, "USB-2");
        // añadir dispositivos a la lista de dispositivos
        devicesPc2.add(monitorPc2);
        devicesPc2.add(monitor2Pc2);
        devicesPc2.add(keyboardPc2);
        devicesPc2.add(mousePc2);
        devicesPc2.add(flashMemoryPc2);
        devicesPc2.add(ssdPc2);

        // Objeto computador 2
        // Se crea y añaden sus atributos
        Computer pc2 = new Computer("0002", devicesPc2, TypeComputer.DESKTOP);

        // imprimir información pc2
        System.out.println(pc2);
    }
}
