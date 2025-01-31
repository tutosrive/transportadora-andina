package poo;

import poo.model.Computer;
import poo.model.FlashMemory;
import poo.model.HardDiskDrive;
import poo.model.Keyboard;
import poo.model.Language;
import poo.model.Monitor;
import poo.model.Mouse;
import poo.model.SolidStateDrive;
import poo.model.TypeComputer;
import java.util.ArrayList;

public class App2 {
    private final static ArrayList<Computer> computers = new ArrayList<>();

    public static void main(String[] args) {
        Computer c1 = new Computer();
        c1.setType(TypeComputer.DESKTOP);
        c1.setId("0001");
        c1.getComputerDevices().add(new Keyboard("Logitech MK345", true, false, false, Language.ES));
        c1.getComputerDevices().add(new Mouse("Genius DX-110", false, "1000", 2));
        c1.getComputerDevices().add(new HardDiskDrive("Western Digital WD10EZEX", false, 300, 6, 700, 7200, 3.5));

        Computer c2 = new Computer();
        c2.setType(TypeComputer.LAPTOP);
        c2.setId("0002");
        c2.getComputerDevices().add(new Monitor("Pantalla FHD, IPS", false, 15.6f));
        c2.getComputerDevices().add(new Keyboard("HP Premium retroiluminado", false, true, true, Language.ES));
        c2.getComputerDevices().add(new SolidStateDrive("Crucial CT1000BX500SSD1", false, 200, 800, 6, "SATA III"));
        c2.getComputerDevices().add(new FlashMemory("ataTraveler G3 DT100G3", false, 10, 20, 10, "USB-A"));

        Computer c3 = new Computer();
        c3.setType(TypeComputer.LAPTOP);
        c3.setId("0003");
        c3.getComputerDevices().add(new Monitor("Viewsonic VX2776-SMH", false, 2.0f));
        c3.getComputerDevices().add(new Keyboard("Logitech MK330", true, false, true, Language.ES));
        c3.getComputerDevices()
                .add(new SolidStateDrive("Samsung 970 Evo Plus", false, 100, 250, 6, "PCIe Gen 3.0 x4, NVMe 1.3"));
        c3.getComputerDevices().add(new FlashMemory("SanDisk Ultra Dual Drive ", false, 7, 16, 10, "USB-C"));

        Computer c4 = new Computer();
        c4.setType(TypeComputer.DESKTOP);
        c4.setId("0004");
        c4.getComputerDevices().add(new Monitor("Dell S3422DW	", false, 34));
        c4.getComputerDevices().add(new Keyboard("Logitech G915 Lightspeed", false, false, false, Language.EN));
        c4.getComputerDevices().add(new SolidStateDrive("Samsung 970 EVO Plus", true, 150, 500, 3, "SATA 6 GB/s"));
        c4.getComputerDevices().add(new FlashMemory("PNY Duo Link OTG", false, 10, 32, 130, "USB-A"));

        Computer c5 = new Computer();
        c5.setType(TypeComputer.LAPTOP);
        c5.setId("0005");
        c5.getComputerDevices().add(new Monitor("LG UltraFine 27UN83A-W", false, 27));
        c5.getComputerDevices().add(new Keyboard("MSI Vigor GK50 Elite", false, false, true, Language.ES));
        c5.getComputerDevices().add(new SolidStateDrive("Samsung 980 PRO", false, 150, 500, 7, "PCIe 4.0"));
        c5.getComputerDevices().add(new FlashMemory("Toshiba TransMemory U365", false, 10, 32, 5, "USB-A"));

        Computer c6 = new Computer();
        c6.setType(TypeComputer.LAPTOP);
        c6.setId("0006");
        c6.getComputerDevices().add(new Monitor("Samsung S32BM702 ", true, 32));
        c6.getComputerDevices().add(new Keyboard("Asus ROG Strix Scope RX", false, false, false, Language.EN));
        c6.getComputerDevices().add(new SolidStateDrive("Samsung 980 Pro", false, 250, 800, 6, "PCIe 4.0 x4"));
        c6.getComputerDevices().add(new FlashMemory("Toshiba TransMemory U365 ", false, 5, 10, 6, "USB-C"));

        Computer c7 = new Computer();
        c7.setType(TypeComputer.DESKTOP);
        c7.setId("0007");
        c7.getComputerDevices().add(new Monitor("widescreen ultra extended graphics array", false, 16));
        c7.getComputerDevices().add(new Keyboard("Asus", true, true, false, Language.ES));
        c7.getComputerDevices().add(new SolidStateDrive("Crucial CT1000BX500SSD1", false, 200, 800, 6, "SATA III"));
        c7.getComputerDevices().add(new FlashMemory("ataTraveler G3 DT100G3", false, 10, 20, 10, "USB-A"));
        c7.getComputerDevices().add(new HardDiskDrive("st500lm012", false, 0, 500, 10, 0, 6));

        Computer c8 = new Computer();
        c8.setType(TypeComputer.LAPTOP);
        c8.setId("0008");
        c8.getComputerDevices().add(new Monitor("Alta definición (HD)", false, 18));
        c8.getComputerDevices().add(new Monitor("2K", false, 14));
        c8.getComputerDevices().add(new Keyboard("HP Premium retroiluminado", true, false, true, Language.ES));
        c8.getComputerDevices().add(new SolidStateDrive("Crucial CT1000BX500SSD1", false, 200, 800, 6, "SATA III"));

        Computer c9 = new Computer();
        c9.setType(TypeComputer.DESKTOP);
        c9.setId("0009");
        c9.getComputerDevices().add(new Keyboard("Green Leaf", false, false, true, Language.ES));
        c9.getComputerDevices().add(new HardDiskDrive("PATA", false, 200, 800, 6, 7, 7200.0));
        c9.getComputerDevices().add(new FlashMemory("ataTraveler G3 DT100G3", false, 10, 20, 10, "USB-A"));

        Computer c10 = new Computer();
        c10.setType(TypeComputer.LAPTOP);
        c10.setId("0010");
        c10.getComputerDevices().add(new Monitor("ViewSonic VA2715-H", false, 15.6f));
        c10.getComputerDevices().add(new Keyboard("Logitech", false, false, true, Language.FRA));
        c10.getComputerDevices().add(new SolidStateDrive("Kingston A400", false, 200, 800, 6, "SATA III"));
        c10.getComputerDevices().add(new FlashMemory("ataTraveler G3 DT100G3", false, 10, 20, 10, "USB-A"));

        Computer c11 = new Computer();
        c11.setType(TypeComputer.DESKTOP);
        c11.setId("0011");
        c11.getComputerDevices().add(new Keyboard("Macally", false, true, true, Language.EN));
        c11.getComputerDevices().add(new HardDiskDrive("SATA", false, 200, 800, 6, 7, 7200.0));
        c11.getComputerDevices().add(new FlashMemory("ataTraveler G3 DT100G3", false, 10, 20, 10, "USB-A"));

        Computer c12 = new Computer();
        c12.setType(TypeComputer.LAPTOP);
        c12.setId("0012");
        c12.getComputerDevices().add(new Monitor("ASUS VZ279HE-W", false, 15.6f));
        c12.getComputerDevices().add(new Keyboard("Eagle Warrior", false, false, true, Language.FRA));
        c12.getComputerDevices().add(new SolidStateDrive("ADATA SU800", false, 200, 800, 6, "SATA III"));

        Computer c13 = new Computer();
        c13.setType(TypeComputer.DESKTOP);
        c13.setId("0013");
        c13.getComputerDevices().add(new Monitor("Samsung FHD, IPS LF22T350", false, 22));
        c13.getComputerDevices().add(new Keyboard("Corsair Rapidfire K70", false, true, false, Language.EN));
        c13.getComputerDevices().add(new Mouse("Logitech Pebble M350", true, "1000", 2));
        c13.getComputerDevices().add(new SolidStateDrive("Western Digital Green", false, 200, 400, 6, "SATA III"));

        Computer c14 = new Computer();
        c14.setType(TypeComputer.DESKTOP);
        c14.setId("0014");
        c14.getComputerDevices().add(new Monitor("HP M24 FHD", false, 23.8f));
        c14.getComputerDevices().add(new Keyboard("Logitech K400", true, false, false, Language.EN));
        c14.getComputerDevices().add(new Mouse("NGS FOG", true, "1000", 2));
        c14.getComputerDevices().add(new HardDiskDrive("Seagate BarraCuda", false, 400, 600, 6, 7200, 2.5));

        Computer c15 = new Computer();
        c15.setType(TypeComputer.LAPTOP);
        c15.setId("0015");
        c15.getComputerDevices().add(new Monitor("LG 32GN650", false, 31.5f));
        c15.getComputerDevices().add(new Keyboard("HP NOTEBOOK", false, false, true, Language.ES));
        c15.getComputerDevices().add(new HardDiskDrive("WD Blue", false, 200, 300, 6, 5400, 3.5));
        c15.getComputerDevices().add(new FlashMemory(" Siemens 6AV2181-8AS20", false, 4, 4, 25, "USB-A"));

        Computer c16 = new Computer();
        c16.setType(TypeComputer.DESKTOP);
        c16.setId("0016");
        c16.getComputerDevices().add(new Monitor("Dell S2421NX", false, 24));
        c16.getComputerDevices().add(new Keyboard("Razer Huntsman V2", false, false, false, Language.ES));
        c16.getComputerDevices().add(new Mouse("Trust Verto", true, "1600", 6));
        c16.getComputerDevices().add(new SolidStateDrive("Samsung 870 QVO", false, 200, 800, 6, "SATA III"));

        Computer c17 = new Computer();
        c17.setType(TypeComputer.LAPTOP);
        c17.setId("0017");
        c17.getComputerDevices().add(new Monitor("ASUS VZ279HE-W", false, 27));
        c17.getComputerDevices().add(new Keyboard("MSI Vigor GK50 ", false, false, true, Language.ES));
        c17.getComputerDevices().add(new SolidStateDrive("Crucial MX500", false, 300, 700, 6, "SATA III"));

        // Añadir los 17 computadores
        computers.add(c1);
        computers.add(c2);
        computers.add(c3);
        computers.add(c4);
        computers.add(c5);
        computers.add(c6);
        computers.add(c7);
        computers.add(c8);
        computers.add(c9);
        computers.add(c10);
        computers.add(c11);
        computers.add(c12);
        computers.add(c13);
        computers.add(c14);
        computers.add(c15);
        computers.add(c16);
        computers.add(c17);

        // Computadores con restricciones ok
        ArrayList<Computer> okComputers = filterComputers(computers);
        // System.out.println(okComputers);
    }

    public static ArrayList<Computer> filterComputers(ArrayList<Computer> computers) {
        ArrayList<Computer> pcs = new ArrayList<>();
        // recorrer lista de computadores
        for (Computer pc : computers) {
            // verificar restricciones por cada pc
            if (pc.okRestrictions(pc)) {
                // si se cumplen las restricciones se agrega el pc a la lista de pcs
                pcs.add(pc);
            }
        }
        // retornar lista de pcs ok
        return pcs;
    }

    // Permitir obtener lista de todos los computadores
    public static ArrayList<Computer> getAllComputers() {
        return computers;
    }
}
