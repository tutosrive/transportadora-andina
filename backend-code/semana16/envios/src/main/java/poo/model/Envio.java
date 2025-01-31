package poo.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import poo.helpers.Utils;

public abstract class Envio implements Costeable, Exportable {
    // Datos de envío
    protected String nroGuia;
    protected boolean fragil;
    protected String contenido;
    protected double valorDeclarado;
    protected double peso;
    protected Cliente remitente;
    protected Cliente destinatario;
    protected ArrayList<Estado> estados;

    /**
     * Constructor por defecto
     * Crea un Envio con un ID aleatorio, "clientes" con solo ID
     * y una lista de estados vacía
     */
    protected Envio() {
        this.nroGuia = Utils.getRandomKey(8);
        this.setRemitente(new Cliente());
        this.setDestinatario(new Cliente());
        this.estados = new ArrayList<>();
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
     * @param estados        ArrayList<Estado>. Lista de los estados del envío
     */
    protected Envio(String nroGuia, Cliente remitente, Cliente destinatario, boolean fragil, String contenido,
            double valorDeclarado, double peso, ArrayList<Estado> estados) {
        // Antes de usar los setters, verificar algunas restricciones

        /*
         * En fragil, valorDeclarado y peso, se evita el uso de los setters, porque en
         * caso de ser un "Sobre" se lanzarán Excepciones
         */

        this.fragil = fragil;
        this.valorDeclarado = valorDeclarado;
        this.peso = peso;

        this.setRemitente(remitente);
        this.setDestinatario(destinatario);
        this.setNroGuia(nroGuia);
        this.setContenido(contenido);
        this.setEstados(estados);
    }

    /**
     * Constructor parametrizado sin nroGuia
     * 
     * @param remitente      Cliente. Cliente que ha generado el envío
     * @param destinatario   Cliente. Cliente el cual recibirá el envío
     * @param fragil         boolean. ¿El contenido es frágil?
     * @param contenido      String. Contenido del envío
     * @param valorDeclarado double. Valor estimado del producto
     * @param peso           double. Peso del envío en kilogramos
     * @param estados        ArrayList<Estado>. Lista de los
     *                       estados del envío
     */
    protected Envio(Cliente remitente, Cliente destinatario, boolean fragil, String contenido,
            double valorDeclarado, double peso, ArrayList<Estado> estados) {
        this(Utils.getRandomKey(8), remitente, destinatario, fragil, contenido, valorDeclarado, peso, estados);
    }

    /**
     * Constructor copia
     * 
     * @param e Envio. Objeto Envio del cual se clonarán los datos
     */
    protected Envio(Envio e) {
        // Uso constructor de parametrizado
        this(e.nroGuia, e.remitente, e.destinatario, e.fragil, e.contenido, e.valorDeclarado, e.peso, e.estados);
    }

    /**
     * Constructor que recibe solo el Número Guía
     * 
     * @param nroGuia String. Cadena de caracteres correspondientes al número guía
     */
    protected Envio(String nroGuia) {
        // Generar un envío con datos básicos vacíos
        // this();
        this.setNroGuia(nroGuia);
    }

    /**
     * Generar un envío a partir de un JSONObject
     * 
     * @param json JSONObject. Objeto JSON con los datos necesarios
     */
    protected Envio(JSONObject json) {
        // Usar el constructor parametrizado
        this(
                json.getString("nroGuia"),
                new Cliente(json.getJSONObject("remitente")), // Crear cliente a partir de un JSONObject
                new Cliente(json.getJSONObject("destinatario")), // Crear cliente a partir de un JSONObject
                json.getBoolean("fragil"),
                json.getString("contenido"),
                json.getDouble("valorDeclarado"),
                json.getDouble("peso"),
                new ArrayList<>());

        JSONArray listaEstados = json.getJSONArray("estados");
        for (int i = 0; i < listaEstados.length(); i++) {
            this.estados.add(new Estado(listaEstados.getJSONObject(i)));
        }
    }

    // Accesor remitente
    public Cliente getRemitente() {
        return this.remitente;
    }

    // Mutador remitente
    public final void setRemitente(Cliente remitente) {
        this.remitente = remitente;
    }

    // Accesor destinatario
    public Cliente getDestinatario() {
        return this.destinatario;
    }

    // Mutador destinatario
    public final void setDestinatario(Cliente destinatario) {
        this.destinatario = destinatario;
    }

    // Accesor fragil
    public boolean getFragil() {
        return this.fragil;
    }

    // Mutador fragil
    public final void setFragil(boolean fragil) {
        /*
         * Para un sobre no puede especificarse frágil como verdadero, en caso de
         * requerir un tratamiento de cuidado, debe enviarse como un paquete
         */
        if (getTipo().equals("Sobre")) {
            throw new IllegalArgumentException(String.format(
                    "%sSi el %sSobre%s%s necesita cuidados especiales (%sfrágil%s%s) se debe clasificar como un %sPaquete%s%n",
                    Utils.RED, Utils.UNDERLINE_YELLOW, Utils.RESET, Utils.RED, Utils.UNDERLINE_YELLOW,
                    Utils.RESET,
                    Utils.RED, Utils.GREEN, Utils.RESET));
        }

        this.fragil = fragil;
    }

    // Accesor contenido
    public String getContenido() {
        return this.contenido;
    }

    // Mutador contenido
    public final void setContenido(String contenido) {
        this.contenido = contenido;
    }

    // Accesor valorDeclarado
    public double getValorDeclarado() {
        return this.valorDeclarado;
    }

    // Mutador valorDeclarado
    public final void setValorDeclarado(double valorDeclarado) {
        // Verificar que el Tipo Envio sea diferente de Sobre
        // Porque su valor (sobre) siempre es 0
        if (getTipo().equals("Sobre")) {
            throw new IllegalArgumentException(String.format(
                    "%sUn %sSobre%s%s no tiene un %svalor declarado%s%n",
                    Utils.RED, Utils.UNDERLINE_YELLOW, Utils.RESET, Utils.RED, Utils.UNDERLINE_YELLOW,
                    Utils.RESET, Utils.RESET));
        }

        this.valorDeclarado = valorDeclarado;
    }

    // Accesor peso
    public double getPeso() {
        return this.peso;
    }

    // Mutador peso
    public final void setPeso(double peso) {
        /*
         * El peso no se tiene en cuenta, se supone 0.0, si no fuera así, sería un
         * paquete. Se hace esta verificación para filtrar el setPeso cuando se usa el
         * constructor del JSON
         */
        if (this.getTipo().equals("Sobre")) {
            throw new IllegalArgumentException(String.format(
                    "%sSi el %speso%s%s en Kg de un %sSobre%s%s es mayor a cero (0), entonces debe clasificarse como un %sPaquete%s",
                    Utils.RED, Utils.UNDERLINE_YELLOW, Utils.RESET, Utils.RED, Utils.UNDERLINE_YELLOW,
                    Utils.RESET,
                    Utils.RED, Utils.GREEN,
                    Utils.RESET));
        }

        this.peso = peso;
    }

    // Retornar nroGuia como un "ID" -> Supongo que se usará para metamorfismo :)
    public String getId() {
        return this.nroGuia;
    }

    // Accesor nroGuia
    public String getNroGuia() {
        return this.nroGuia;
    }

    /*
     * Mutador nroGuia para constructor copia
     * es "protected" porque solo debe usarse dentro de
     * la jerarquía de herencia
     */
    public final void setNroGuia(String nroGuia) {
        this.nroGuia = nroGuia;
    }

    // Accesor estados
    public ArrayList<Estado> getEstados() {
        return this.estados;
    }

    /**
     * Mutador estados
     * 
     * @param estados ArrayList<Estado>. Estado actual del envío
     */
    public final void setEstados(ArrayList<Estado> states) {
        this.estados = states;

    }

    /**
     * @return Nombre de la clase intanciada
     */
    public String getTipo() {
        // this, hace referencia al "objeto mismo" instanciado
        return this.getClass().getSimpleName();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /** Verificar si un envio es igual a otro */
    @Override
    public boolean equals(Object obj) {
        // Si son el mismo objeto
        if (this == obj) {
            return true;
        }

        // Si obj es objeto vacío
        if (obj == null) {
            return false;
        }

        // Si provienen de distintas clases
        if (this.getClass() != obj.getClass()) {
            return false;
        }

        // Casteo de Object a Envio
        Envio e = (Envio) obj;
        // Retorna true || false según .equals()
        return this.nroGuia.equals(e.nroGuia);
    }

    @Override
    public String toString() {
        // Valores String para variable booleana
        String fragilStr = (this.fragil) ? "Sí" : "No";
        String contenidoStr = (this.contenido == null) ? " ---- " : this.contenido;

        // Cabeceras de la tabla
        String info = String.format(
                " %s%-203s%s %n\u001b[1m|%s%s ENVÍO |TIPO ENVÍO  |COSTO ENVÍO              |N° GUÍA    |FRÁGIL|CONTENIDO      |$ VALOR     |  PESO  |REMITENTE                     |DESTINATARIO                  |ESTADO         |FECHA ESTADO         %s|%n%s|%-203s|%s%n",
                Utils.YELLOW, "_".repeat(203), Utils.RESET, Utils.RED, Utils.FILLBLUE, Utils.RESET, Utils.YELLOW,
                "-".repeat(203),
                Utils.RESET);

        // Valores desde columna "Tipo Envio" hasta "Peso"
        info += String.format(
                "%s| ----- |%-12.12s|%-25.25s|%-11.11s|%-6.6s|%-15.15s|%-12.12s|%-8.8s|",
                Utils.YELLOW, this.getTipo(), this.getCosto() + " COP", this.nroGuia, fragilStr,
                contenidoStr,
                this.valorDeclarado, this.peso + " Kg");

        // Datos Remitente en formato JSON
        JSONObject dataRe = this.remitente.toJSONObject();
        // Datos Destinatario en formato JSON
        JSONObject dataDes = this.destinatario.toJSONObject();

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
        int len = Math.max(keysRe.length(), keysDes.length());
        len = Math.max(len, this.estados.size());

        for (int i = 0; i < len; i++) {
            // En lugar de datos primitivos, datos que permitan paso por referencia
            // (Se necesitan) datos que perduren dentro y fuera de llaves internas (try)
            // Se inicializan con este valor, para en caso que se halle un null
            String[] dataReStr = { " ---- " }, dataDesStr = { " ---- " };
            Object[] estado = { " ----          | -----" };

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
                    String fecha = estadosJson.getString("fecha");
                    // Se obtiene el Estado De Envio del JSON anterior
                    // Se castea a su tipo ORIGINAL, para obtener luego su valor (String del Enum)
                    EstadoEnvio estadoEnvio = (EstadoEnvio) estadosJson.get("estado");

                    // Se da un formato a un String que irá en la salida
                    estado[0] = String.format(
                            "%-15.15s|%-22.22s|%n",
                            estadoEnvio.getValue(), fecha);

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
                        "| ----- |%-12.12s|%-25.25s|%-11.11s|%-6.6s|%-15.15s|%-12.12s|%-8.8s|%-30.28s|%-30.28s|%-37.37s|%n",
                        " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", dataReStr[0],
                        dataDesStr[0], estado[0]);
            } else {
                info += String.format(
                        "%-30.30s|%-30.30s|%-37.37s|%n",
                        dataReStr[0], dataDesStr[0], estado[0]);
            }

        }
        // Finalmente se agrega el pie de tabla
        info += String.format("|%-203s|%s%n%n", "_".repeat(203), Utils.RESET);
        // Se retorna la información formateada
        return info;
    }

    @Override
    public String toJSON() {
        return (new JSONObject(this).toString(2));
    }

    // Método "interface" Exportable
    @Override
    public JSONObject toJSONObject() {
        return (new JSONObject(this));
    }
}