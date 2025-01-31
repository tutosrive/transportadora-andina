package poo;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Enviable sobre1 = new Envelope();
        Enviable paquete1 = new Pack();

        Enviable[] enviables = { paquete1, sobre1 };

        for (Enviable x : enviables) {
            System.out.printf("Costo %s: %f%n", x.getClass().getSimpleName(), x.getCosto());
            System.out.printf("Información %s: %s%n", x.getClass().getSimpleName(), x.getInfo());
            System.out.println("-".repeat(20));
        }
    }
}
