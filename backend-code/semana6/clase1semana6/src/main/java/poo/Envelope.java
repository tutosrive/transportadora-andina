package poo;

public class Envelope implements Enviable {

    // Implementación para método de Enviable
    @Override
    public double getCosto() {
        // Una fórmula cualquiera
        return Math.random() * 56;
    }

    // Implementación para método de Enviable
    @Override
    public String getInfo() {
        // Información cualquiera
        return "Envío de sobre de Pepito Pérez para Juanita Pérez.";
    }
}
