// Por implementar aún
package poo.model;

import java.util.ArrayList;

import org.json.JSONObject;

public class Bulto extends Envio {
    public Bulto() {
        // Usar constructor por defecto de clase Madre
        super();
    }

    /** */
    public Bulto(Cliente remitente, Cliente destinatario, boolean fragil, String contenido,
            double valorDeclarado, double peso, ArrayList<Estado> estados) {
        // Usar constructor parametrizado de clase Madre
        super(remitente, destinatario, fragil, contenido, valorDeclarado, peso, estados);
    }

    public Bulto(String nroGuia, Cliente remitente, Cliente destinatario, boolean fragil, String contenido,
            double valorDeclarado, double peso, ArrayList<Estado> estados) {
        // Usar constructor parametrizado de clase Madre
        super(nroGuia, remitente, destinatario, fragil, contenido, valorDeclarado, peso, estados);
    }

    public Bulto(Bulto b) {
        super(b);
    }

    /**
     * Constructor que recibe solo el ID
     * 
     * @param nroGuia
     */
    public Bulto(String nroGuia) {
        // Usar constructor con solo ID de la clase Madre
        super(nroGuia);
    }

    public Bulto(JSONObject json) {
        super(json);
    }

    /**
     * Calcular el costo del envío de tipo Bulto
     */
    @Override
    public double getCosto() {
        /*
         * Para determinar el valor del envío de los bultos se cobran mil pesos por cada
         * kilogramo.
         */
        double costo = 1000 * this.peso;
        return costo;
    }

    @Override
    public String toJSON() {
        return (new JSONObject().put("message", "ok").put("data", this.toJSONObject())).toString(2);
    }
}
