package poo.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import poo.helpers.Utils;

public class Sobre extends Envio {
    private boolean certificado;

    /** Constructor por defecto de Sobre */
    public Sobre() {
        // Usar constructor de clase madre
        super();
        this.setContenido("Documentos");
    }

    /**
     * Constructor parametrizado, con parámetros "certificado" y "documentos"
     * 
     * @param remitente    Cliente. Cliente que ha generado el envío
     * @param destinatario Cliente. Cliente el cual recibirá el envío
     * @param estados      ArrayList<Estado>. Lista de estados del envío
     * @param certificado  boolean. Es un sobre certificado o no.
     * @param contenido    String. Documentos del Sobre
     */
    public Sobre(String nroGuia, Cliente remitente, Cliente destinatario, String contenido, ArrayList<Estado> estados,
            boolean certificado) {
        // Usar constructor de clase madre
        super(nroGuia, remitente, destinatario, false, contenido, 0, 0, estados);
        this.setCertificado(certificado);
    }

    public Sobre(Cliente remitente, Cliente destinatario, String contenido, ArrayList<Estado> estados,
            boolean certificado) {
        // Usar constructor de clase madre
        super(remitente, destinatario, false, contenido, 0, 0, estados);
        this.setCertificado(certificado);
    }

    /**
     * Constructor copia
     * 
     * @param s Envio. Objeto del cual se clonarán los datos
     */
    public Sobre(Sobre s) {
        super(s.remitente, s.destinatario, false, s.contenido, 0, 0, s.estados);
        this.setCertificado(s.certificado);
    }

    public Sobre(String nroGuia) {
        // Usar constructor con solo nroGuia de la clase Madre
        super(nroGuia);
    }

    /**
     * Generar un envío de tipo Sobre a partir de un JSONObject
     * 
     * @param json JSONObject. Objeto JSON con los datos necesarios
     */
    public Sobre(JSONObject json) {
        // Usar constructor de clase madre
        super(json);
        // Establecer valor de certificado
        this.setCertificado(json.getBoolean("certificado"));
    }

    // Accesor certificado
    public boolean getCertificado() {
        return this.certificado;
    }

    // Mutador certificado
    public final void setCertificado(boolean certificado) {
        this.certificado = certificado;
    }

    @Override
    public String toString() {
        // Valores String para variable booleana
        String contenidoStr = (super.contenido == null) ? " ---- " : super.contenido;
        String certificadoStr = (this.certificado) ? "Sí" : "No";

        // Cabeceras de la tabla
        String info = String.format(
                " %s%-156s%s %n\u001b[1m|%s%s ENVÍO |TIPO ENVÍO  |COSTO ENVÍO|N° GUÍA    |CONTENIDO      |CERTIFICADO |REMITENTE                     |DESTINATARIO                  |ESTADOS             %s|%n%s|%-156s|%s%n",
                Utils.YELLOW, "_".repeat(156), Utils.RESET, Utils.RED, Utils.FILLBLUE, Utils.RESET, Utils.YELLOW,
                "-".repeat(156), Utils.RESET);

        // Valores desde columna "Tipo Envio" hasta "Peso"
        info += String.format(
                "%s| ----- |%-12.12s|%-11.11s|%-11.11s|%-15.15s|%-12s|", Utils.YELLOW,
                super.getTipo(), this.getCosto() + " COP", super.nroGuia, contenidoStr, certificadoStr);

        // Datos Remitente en formato JSON
        JSONObject dataRe = super.remitente.toJSONObject();
        // Datos Destinatario en formato JSON
        JSONObject dataDes = super.destinatario.toJSONObject();

        // Claves de JSON Remitente
        JSONArray keysRe = dataRe.names();
        // Claves de JSON Destinatario
        JSONArray keysDes = dataDes.names();

        // Objeto JSON con las "siglas/abreviaciones" de cada Clave
        JSONObject siglasKeys = new JSONObject(
                "{\"id\":\"ID:  \", \"nombre\":\"NOM: \", \"direccion\":\"DIR: \",\"ciudad\":\"CIU: \", \"telefono\":\"TEL: \"}");

        // Los objetos que contienen más datos son los Clientes
        // En total son 4 Datos (Se ejecuta 2 veces más para posibles "\n" de los
        // estados)
        for (int i = 0; i < 6; i++) {
            // En lugar de datos primitivos, datos que permitan paso por referencia
            // (Se necesitan) datos que perduren dentro y fuera de llaves internas (try)
            // Se inicializan con este valor, para en caso que se halle un null
            String[] dataReStr = { " ---- " }, dataDesStr = { " ---- " };
            Object[] estado = { " ---- " };

            // Intentar ejecutar el siguiente código
            try {
                // Clave extraída de JSONArray Destiinatario en posición "i"
                String keyDes = keysDes.getString(i);
                // si (!= null) [clave: valor] Destinatario
                dataDesStr[0] = (dataDes.get(keyDes) == null) ? " ---- "
                        : siglasKeys.getString(keyDes) + dataDes.getString(keyDes);

            } catch (JSONException e) {
            }
            try {
                // Clave extraída de JSONArray Remitente en posición "i"
                String keyRe = keysRe.getString(i);
                // si (!= null) [clave: valor] Destinatario
                dataReStr[0] = (dataRe.get(keyRe) == null) ? " ---- "
                        : siglasKeys.getString(keyRe) + dataRe.getString(keyRe);
            } catch (JSONException e) {
            }
            // Intentar ejecutar el siguiente código
            try {
                // Se verifica que i sea menor a la longitud (+1) de los estados
                if (i < super.estados.size() + 1) {
                    // Siendo los estados un ArrayList, contiene dentro Objetos
                    // Los cuales se pasan a formato JSON
                    JSONObject estadosJson = super.estados.get(i).toJSONObject();

                    // Se obtiene la fecha del JSON de Estado en posición (i)
                    String fecha = estadosJson.getString("fecha");
                    // Se obtiene el Estado De Envio del JSON anterior
                    // Se castea a su tipo ORIGINAL, para obtener luego su valor (String del Enum)
                    EstadoEnvio estadoEnvio = (EstadoEnvio) estadosJson.get("estado");

                    // Se da un formato a un String que irá en la salida
                    estado[0] = String.format(
                            "%-20.20s|%n| ----- |%-12.12s|%-11.11s|%-11.11s|%-15.15s|%-12s|%-30.30s|%-30.30s|%-20.20s",
                            fecha,
                            " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", " ---- ",
                            estadoEnvio.getValue());

                }
            } catch (JSONException | IndexOutOfBoundsException e) {
                // System.out.println("Error Estados: " + e);
            }

            /*
             * Se verifica que (i) sea mayor a (0) para que los primeros datos no se
             * "desacomoden" de su lugar, el formato permite "limitar" la longitud máxima de
             * caracteres
             * por columna indicandoselo así: %-10.10s -> Inidica que Strings con length
             * mayor a 10, se "cortarán"
             */
            if (i > 0) {
                info += String.format(
                        "| ----- |%-12.12s|%-11.11s|%-11.11s|%-15.15s|%-12s|%-30.30s|%-30.30s|%-20s|%n",
                        " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", dataReStr[0],
                        dataDesStr[0], estado[0]);
            } else {
                info += String.format(
                        "%-30.30s|%-30.30s|%-20s|%n",
                        dataReStr[0], dataDesStr[0], estado[0]);
            }

        }
        // Finalmente se agrega el pie de tabla
        info += String.format("|%-156s|%s%n%n", "_".repeat(156), Utils.RESET);
        // Se retorna la información formateada
        return info;
    }

    // Métodos "interface" Costeable

    /**
     * Costo total del envío de tipo Sobre
     */
    @Override
    public double getCosto() {
        /*
         * Los sobres a cualquier destino tienen un costo igual al entero más próximo
         * que resulte de dividir un SMLV sobre 1000 y multiplicarlo por 2
         */
        double costo = (1300000 / 1000) * 2;

        // Si es certificado se incrementa el costo en un 10%
        if (this.certificado) {
            costo *= 1.10;
        }
        // Redondear el costo al "entero" más cercano
        return Math.round(costo);
    }

    @Override
    public String toJSON() {
        return (new JSONObject().put("message", "Datos Sobre").put("data", this.toJSONObject())).toString(2);
    }
}
