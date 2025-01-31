package poo;

public class Pack implements Enviable {
    // Implementación para método de Enviable
    @Override
    public double getCosto() {
        // fórula cualquiera
        return Math.random() * 23;
    }

    // Implementación para método de Enviable
    @Override
    public String getInfo() {
        // Información cualquiera
        return "Paquete de Juanito Pérez para Juanita Pérez";
    }

}
