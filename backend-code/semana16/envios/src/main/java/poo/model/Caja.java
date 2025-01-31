package poo.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import poo.helpers.Utils;

public class Caja extends Envio {
    private double ancho;
    private double alto;
    private double largo;

    // Constructor por defecto
    public Caja() {
        // Usar constructor de la clase madre
        super();
    }

    /**
     * Constructor parametrizado
     * 
     * @param remitente      Cliente. Cliente que ha generado el envío
     * @param destinatario   Cliente. Cliente el cual recibirá el envío
     * @param nroGuia        String. Número de guía/rastreo del envío
     * @param fragil         boolean. ¿El contenido es frágil?
     * @param contenido      String. Contenido del envío
     * @param valorDeclarado double. Valor estimado del producto
     * @param peso           double. Peso del envío en kilogramos
     * @param estados        ArrayList<Estado>. Lista de los
     *                       estados del envío
     */
    public Caja(String nroGuia, Cliente remitente, Cliente destinatario, boolean fragil, String contenido,
            double valorDeclarado, double peso, ArrayList<Estado> estados, double ancho, double alto, double largo) {
        // Usar constructor de la clase madre
        super(nroGuia, remitente, destinatario, fragil, contenido, valorDeclarado, peso, estados);
        // Usar setters de la clase caja
        this.setAlto(alto);
        this.setAncho(ancho);
        this.setLargo(largo);
    }

    /**
     * Constructor parametrizado
     * 
     * @param remitente      Cliente. Cliente que ha generado el envío
     * @param destinatario   Cliente. Cliente el cual recibirá el envío
     * @param nroGuia        String. Número de guía/rastreo del envío
     * @param fragil         boolean. ¿El contenido es frágil?
     * @param contenido      String. Contenido del envío
     * @param valorDeclarado double. Valor estimado del producto
     * @param peso           double. Peso del envío en kilogramos
     * @param estados        ArrayList<Estado>. Lista de los
     *                       estados del envío
     */
    public Caja(Cliente remitente, Cliente destinatario, boolean fragil, String contenido,
            double valorDeclarado, double peso, ArrayList<Estado> estados, double ancho, double alto, double largo) {

        super(remitente, destinatario, fragil, contenido, valorDeclarado, peso, estados);
        // Usar setters de la clase caja
        this.setAlto(alto);
        this.setAncho(ancho);
        this.setLargo(largo);
    }

    /**
     * Constructor copia
     * 
     * @param c Caja. Objeto de tipo caja del cual se clonarán los datos
     */
    public Caja(Caja c) {
        super(c);
        this.setAlto(c.alto);
        this.setAncho(c.ancho);
        this.setLargo(c.largo);
    }

    /**
     * Constructor que recibe solo el ID
     * 
     * @param nroGuia
     */
    public Caja(String nroGuia) {
        // Usar constructor con solo ID de la clase Madre
        super(nroGuia);
    }

    /**
     * Generar un envío de tipo Caja a partir de un JSONObject
     * 
     * @param json JSONObject. Objeto JSON con los datos necesarios
     */
    public Caja(JSONObject json) {
        // Usar constructor de la clase madre
        super(json);
        // Usar setters de la clase caja
        this.setAlto(json.getDouble("alto"));
        this.setAncho(json.getDouble("ancho"));
        this.setLargo(json.getDouble("largo"));
    }

    // Accesor alto
    public double getAlto() {
        return this.alto;
    }

    // Mutador alto
    public final void setAlto(double alto) {
        this.alto = alto;
    }

    // Accesor ancho
    public double getAncho() {
        return this.ancho;
    }

    // Mutador ancho
    public final void setAncho(double ancho) {
        this.ancho = ancho;
    }

    // Accesor largo
    public double getLargo() {
        return this.largo;
    }

    // Mutador largo
    public final void setLargo(double largo) {
        this.largo = largo;
    }

    /**
     * Obtener el volúmen con el "Aproximado a un decimal"
     * Las medidas de la Caja deben estar en metros (m)
     */
    public double getVolumen() {
        // Variable de resultado
        double volumen;
        // Se verifica que todas las medidas estén bien
        if (this.alto > 0 && this.ancho > 0 && this.largo > 0) {
            // Fórmula = longitud * ancho * alto
            volumen = this.largo * this.ancho * this.alto;

            // Se aproxima "el primer decimal" (no se aproxima la parte entera)
            volumen = Math.round(volumen * 10.0) / 10.0;
            // Se retorna el volumen
            return volumen;
        }
        // Si las medidas no están bien se lanza excepción con mensaje
        throw new IllegalArgumentException("Alguna de las \"medidas\" es incorrecta.");
    }

    @Override
    public String toString() {
        // Valores String para variable booleana
        String fragilStr = (super.fragil) ? "Sí" : "No";
        String contenidoStr = (super.contenido == null) ? " ---- " : super.contenido;

        // Cabeceras de la tabla
        String info = String.format(
                " %s%-218s%s %n\u001b[1m|%s%s ENVÍO |TIPO ENVÍO  |COSTO ENVÍO              |N° GUÍA    |Volumen m³|FRÁGIL|CONTENIDO      |$ VALOR     |  PESO  |ANCHO |ALTO  |LARGO |REMITENTE                     |DESTINATARIO                  |ESTADOS             %s|%n%s|%-218s|%s%n",
                Utils.YELLOW, "_".repeat(218), Utils.RESET, Utils.RED, Utils.FILLBLUE, Utils.RESET, Utils.YELLOW,
                "-".repeat(218),
                Utils.RESET);

        // Valores desde columna "Tipo Envio" hasta "Peso"
        info += String.format(
                "%s| ----- |%-12.12s|%-25.25s|%-11.11s|%-10.10s|%-6.6s|%-15.15s|%-12.12s|%-8.8s|%-6s|%-6s|%-6s|",
                Utils.YELLOW, super.getTipo(), this.getCosto() + " COP", super.nroGuia, this.getVolumen(), fragilStr,
                contenidoStr, super.valorDeclarado + " COP", super.peso + " Kg", this.ancho + " m", this.alto + " m",
                this.largo + " m");

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
                if (i < this.estados.size() + 1) {
                    // Siendo los estados un ArrayList, contiene dentro Objetos
                    // Los cuales se pasan a formato JSON
                    JSONObject estadosJson = this.estados.get(i).toJSONObject();

                    // Se obtiene la fecha del JSON de Estado en posición (i)
                    String fecha = estadosJson.getString("fechaHora");
                    // Se obtiene el Estado De Envio del JSON anterior
                    // Se castea a su tipo ORIGINAL, para obtener luego su valor (String del Enum)
                    EstadoEnvio estadoEnvio = (EstadoEnvio) estadosJson.get("estado");

                    // Se da un formato a un String que irá en la salida
                    estado[0] = String.format(
                            "%-20.20s|%n| ----- |%-12.12s|%-25.25s|%-11.11s|%-10.10s|%-6.6s|%-15.15s|%-12.12s|%-8.8s|%-6s|%-6s|%-6s|%-30.30s|%-30.30s|%-20.20s",
                            fecha, " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", " ---- ",
                            " ---- ", " ---- ", " ---- ",
                            " ---- ", " ---- ", estadoEnvio.getValue());

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
                        "| ----- |%-12.12s|%-25.25s|%-11.11s|%-10.10s|%-6.6s|%-15.15s|%-12.12s|%-8.8s|%-6s|%-6s|%-6s|%-30.30s|%-30.30s|%-20s|%n",
                        " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", " ---- ",
                        " ---- ", " ---- ",
                        dataReStr[0],
                        dataDesStr[0], estado[0]);
            } else {
                info += String.format(
                        "%-30.30s|%-30.30s|%-20s|%n",
                        dataReStr[0], dataDesStr[0], estado[0]);
            }

        }
        // Finalmente se agrega el pie de tabla
        info += String.format("|%-218s|%s%n%n", "_".repeat(218), Utils.RESET);
        // Se retorna la información formateada
        return info;
    }

    // Información de la clase en formato JSON
    @Override
    public JSONObject toJSONObject() {
        return (new JSONObject(this));
    }

    // Métodos "interface" Costeable
    @Override
    public String toJSON() {
        return (new JSONObject().put("message", "Datos Caja").put("data", this.toJSONObject())).toString(2);
    }

    /**
     * Calcular el costo del envío de tipo Caja
     */
    @Override
    public double getCosto() {
        double costo;
        // Si el volumen es menor o igual a 0, lanzar una excepción
        if (this.getVolumen() <= 0) {
            throw new IllegalArgumentException("Alguna/s medidas son erróneas, verifique las medidas ingresadas.");
        }
        double valorPeso = 500 * super.peso;
        // Costo tomado de "tabla de costo Caja" del caso de estudio actual
        if (this.getVolumen() <= 0.5) {
            costo = 10000;
        } else if (this.getVolumen() <= 1) {
            costo = 12000;
        } else if (this.getVolumen() <= 3) {
            costo = 15000;
        } else if (this.getVolumen() <= 6) {
            costo = 25000;
        } else if (this.getVolumen() <= 10) {
            costo = 30000;
        } else {
            costo = 10000 * (this.getVolumen() / 10);
        }
        return costo + valorPeso;
    }
}
