package poo.model;

import java.util.ArrayList;

import org.json.JSONObject;

import poo.helpers.Utils;

public class Paquete extends Envio {
    public Paquete() {
        // Usar constructor clase madre
        super();
    }

    /**
     * Constructor parametrizado con nroGuia específico
     * 
     * @param nroGuia        String. Número de guía/rastreo del envío
     * @param remitente      Cliente. Cliente que ha generado el envío
     * @param destinatario   Cliente. Cliente el cual recibirá el envío
     * @param nroGuia        String. Número de guía/rastreo del envío
     * @param fragil         boolean. ¿El contenido es frágil?
     * @param contenido      String. Contenido del envío
     * @param valorDeclarado double. Valor estimado del producto
     * @param peso           double. Peso del envío en gramos
     */
    public Paquete(String nroGuia, Cliente remitente, Cliente destinatario, boolean fragil, String contenido,
            double valorDeclarado,
            double peso, ArrayList<Estado> estados) {
        super(nroGuia, remitente, destinatario, fragil, contenido, valorDeclarado, peso, estados);
        this.paqueteOk();
    }

    protected final void paqueteOk() {
        if (this.peso == 0 && !this.fragil && this.valorDeclarado == 0) {
            throw new VerifyError(String.format(
                    "%sEste %sEnvío%s%s debe registrarse como un %sSobre%s",
                    Utils.RED, Utils.UNDERLINE_YELLOW, Utils.RESET, Utils.RED, Utils.UNDERLINE_YELLOW, Utils.RESET));
        }
    }

    /**
     * Constructor parametrizado sin nroGuia
     * 
     * @param remitente      Cliente. Cliente que ha generado el envío
     * @param destinatario   Cliente. Cliente el cual recibirá el envío
     * @param fragil         boolean. ¿El contenido es frágil?
     * @param contenido      String. Contenido del envío
     * @param valorDeclarado double. Valor estimado del producto
     * @param peso           double. Peso del envío en gramos
     */
    public Paquete(Cliente remitente, Cliente destinatario, boolean fragil, String contenido, double valorDeclarado,
            double peso, ArrayList<Estado> estados) {
        super(remitente, destinatario, fragil, contenido, valorDeclarado, peso, estados);
    }

    /**
     * Constructor copia
     * 
     * @param e Envio. Objeto Envio del cual se clonarán los datos
     */
    public Paquete(Paquete e) {
        // Usar constructor copia de la clase madre
        super(e);
    }

    /**
     * Constructor que recibe solo el ID
     * 
     * @param nroGuia
     */
    public Paquete(String nroGuia) {
        // Usar constructor con solo ID de la clase Madre
        super(nroGuia);
    }

    /**
     * Crear envío (paquete) a partir de JSON
     * 
     * @param json JSONObject. Contiene los datos necesarios
     */
    public Paquete(JSONObject json) {
        super(json);
    }

    // No hay tostring

    // Métodos "interface" Costeable
    // String información de clase en formato JSON
    @Override
    public String toJSON() {
        return (new JSONObject().put("message", "ok").put("data", this.toJSONObject())).toString(2);
    }

    /**
     * Calcular el costo del envío de tipo Paquete
     */
    @Override
    public double getCosto() {
        // El valor de envío de los paquetes es 1000 * (gramos / 10)
        return 30 * this.peso;
    }
}
