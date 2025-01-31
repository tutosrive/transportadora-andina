package poo.model;

import java.time.Duration;
import java.time.LocalDateTime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import poo.helpers.Utils;

public class Mercancia implements Costeable, Exportable {
    private String id;
    private LocalDateTime fechaHoraIngreso;
    private LocalDateTime fechaHoraSalida;
    private double alto;
    private double ancho;
    private double largo;
    private String bodega;
    private String contenido;
    private Cliente cliente;

    /**
     * Constructor por defecto
     * Genera una mercancía con ID aleatorio
     */
    public Mercancia() {
        // Generar un ID aleatorio para identificar mercancía
        this.setId(Utils.getRandomKey(8));
        this.setCliente(new Cliente());
    }

    /**
     * Constructor parametrizado
     * 
     * @param fechaHoraIngreso LocalDateTime. Hora de ingreso con formato
     * @param fechaHoraSalida  LocalDateTime. Hora salida con formato
     * @param ancho            dobule. Ancho de la mercancía en metros
     * @param alto             double. Alto de la mercancía en metros
     * @param largo            double. Largo de la mercancía en metros
     * @param bodega           String. Bodega donde está almacenada la mercancía
     * @param contenido        String. Contenido de la mercancí
     */
    public Mercancia(String id, LocalDateTime fechaHoraIngreso, LocalDateTime fechaHoraSalida, double ancho,
            double alto,
            double largo, String bodega, String contenido, Cliente cliente) {
        this.setFechaHoraIngreso(fechaHoraIngreso);
        this.setFechaHoraSalida(fechaHoraSalida);
        this.setAncho(ancho);
        this.setAlto(alto);
        this.setLargo(largo);
        this.setBodega(bodega);
        this.setContenido(contenido);
        this.setCliente(cliente);
        this.setId(id);
    }

    /**
     * Constructor parametrizado
     * 
     * @param fechaHoraIngreso LocalDateTime. Hora de ingreso con formato
     * @param fechaHoraSalida  LocalDateTime. Hora salida con formato
     * @param ancho            dobule. Ancho de la mercancía en metros
     * @param alto             double. Alto de la mercancía en metros
     * @param largo            double. Largo de la mercancía en metros
     * @param bodega           String. Bodega donde está almacenada la mercancía
     * @param contenido        String. Contenido de la mercancí
     */
    public Mercancia(LocalDateTime fechaHoraIngreso, LocalDateTime fechaHoraSalida, double ancho, double alto,
            double largo, String bodega, String contenido, Cliente cliente) {
        this(Utils.getRandomKey(8), fechaHoraIngreso, fechaHoraSalida, ancho, alto, largo, bodega, contenido, cliente);
    }

    /**
     * Constructor copia
     * 
     * @param m Mercancia. Objeto del cual se clonarán los datos
     */
    public Mercancia(Mercancia m) {
        this(Utils.getRandomKey(8), m.fechaHoraIngreso, m.fechaHoraSalida, m.ancho, m.alto, m.largo, m.bodega,
                m.contenido, m.cliente);
    }

    /**
     * Constructor que recibe un JSONObject con los datos requerido
     * 
     * @param json JSONObject. Objeto JSON con los datos necesarios
     */
    public Mercancia(JSONObject json) {
        this(json.getString("id"), LocalDateTime.parse(json.getString("fechaHoraIngreso")),
                LocalDateTime.parse(json.getString("fechaHoraSalida")), json.getDouble("ancho"), json.getDouble("alto"),
                json.getDouble("largo"), json.getString("bodega"), json.getString("contenido"),
                new Cliente(json.getJSONObject("cliente")));
    }

    /**
     * Constructor que recibe solo el ID
     * 
     * @param id String. Identificador único de la mercancía
     */
    public Mercancia(String id) {
        this.id = id;
    }

    // Accesor id
    public String getId() {
        return this.id;
    }

    public final void setId(String id) {
        this.id = id;
    }

    // Accesor fechaHoraIngreso
    public LocalDateTime getFechaHoraIngreso() {
        return this.fechaHoraIngreso;
    }

    // Mutador fechaHoraIngreso
    public final void setFechaHoraIngreso(LocalDateTime fechaHoraIngreso) {
        this.fechaHoraIngreso = fechaHoraIngreso;
    }

    // Accesor fechaHoraSalida
    public LocalDateTime getFechaHoraSalida() {
        return this.fechaHoraSalida;
    }

    // Mutador fechaHoraSalida
    public final void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    // Accesor ancho
    public double getAncho() {
        return this.ancho;
    }

    // Mutador ancho
    public final void setAncho(double ancho) {
        this.ancho = ancho;
    }

    // Accesor alto
    public double getAlto() {
        return this.alto;
    }

    // Mutador alto
    public final void setAlto(double alto) {
        this.alto = alto;
    }

    // Accesor largo
    public double getLargo() {
        return this.largo;
    }

    // Mutador largo
    public final void setLargo(double largo) {
        this.largo = largo;
    }

    // Accesor bodega
    public String getBodega() {
        return this.bodega;
    }

    // Mutador bodega
    public final void setBodega(String bodega) {
        this.bodega = bodega;
    }

    // Accesor contenido
    public String getContenido() {
        return this.contenido;
    }

    // Mutador contenido
    public final void setContenido(String contenido) {
        this.contenido = contenido;
    }

    // Accesor cliente
    public Cliente getCliente() {
        return this.cliente;
    }

    // Mutador cliente
    public final void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Obtener ciudad de la mercancía, extraída desde el cliente
    public String getCiudad() {
        return this.cliente.getCiudad();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // Son el mismo objeto
        if (this == obj) {
            return true;
        }
        // Objeto vacío
        if (obj == null) {
            return false;
        }
        // Provienen de diferente clase
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        // Casteo de Object a Mercancia
        Mercancia mercancia = (Mercancia) obj;
        // true si id iguales, false contrario
        return this.id.equals(mercancia.id);
    }

    // Obtener el volúmen con el "Aproximado a un decimal"
    public double getVolumen() {
        // Variable de resultado
        double volumen;
        // Se verifica que todas las medidas estén bien
        if (this.alto > 0 && this.ancho > 0 && this.largo > 0) {
            // Fórmula = longitud * ancho * alto
            volumen = getLargo() * getAncho() * getAlto();

            // Se aproxima a tres decimales (no se aproxima la parte entera)
            volumen = (Math.round(volumen * 1000.0) / 1000.0);
            // Se retorna el volumen
            return Math.max(volumen, 0.125);
        }
        // Si las medidas no están bien se lanza excepción con mensaje
        throw new NumberFormatException("Alguna de las \"medidas\" son incorrectas.");
    }

    /**
     * Obtener la cantidad de días que la Mercancía estuvo almacenada
     * 
     * @return long. Días almacenados
     */
    public long getDiasAlmacenado() {
        // Almacena la diferencia de días entre una fecha y otra
        long dias = Duration.between(this.fechaHoraIngreso, this.fechaHoraSalida).toDays();
        return Math.max(dias, 1l);
    }

    @Override
    public String toString() {
        String encabezado = String.format(
                "%s %-192s %n\u001b[1m%s|%s %-9s | %-8s | %-25.25s | %-20.20s | %-20.20s | %-6s | %-6s | %-6s | %-15.15s | %-20.20s | %-25.25s %s%s|%s%n|%-192s|%n",
                Utils.YELLOW, "_".repeat(192), Utils.RED, Utils.FILLYELLOW, "MERCANCÍA", "ID", "COSTO BODEGAJE",
                "FECHA INGRESO",
                "FECHA SALIDA",
                "ALTO", "ANCHO", "LARGO", "BODEGA", "CONTENIDO", "CLIENTE", Utils.RESET, Utils.RED, Utils.YELLOW,
                "-".repeat(192));

        String fechaIngreso = " ---- ", fechaSalida = " ---- ", bodegaStr = " ---- ", contenidoStr = " ---- ";

        if (this.fechaHoraIngreso != null) {
            fechaIngreso = this.fechaHoraIngreso.toString();
        }

        if (this.fechaHoraSalida != null) {
            fechaSalida = this.fechaHoraSalida.toString();
        }

        if (this.bodega != null) {
            bodegaStr = this.bodega;
        }

        if (this.contenido != null) {
            contenidoStr = this.contenido;
        }

        String datos = String.format(
                "| %-9s | %-6s | %-25.25s | %-20.20s | %-20.20s | %-6s | %-6s | %-6s | %-15.15s | %-20.20s |",
                " ---- ", this.id, this.getCosto() + " COP", fechaIngreso, fechaSalida, this.alto, this.ancho,
                this.largo, bodegaStr,
                contenidoStr);
        JSONObject clienteDatos = cliente.toJSONObject();
        JSONArray clavesCliente = clienteDatos.names();

        // Objeto JSON con las "siglas/abreviaciones" de cada Clave
        JSONObject siglasKeys = new JSONObject(
                "{\"id\":\"ID:  \", \"nombre\":\"NOM: \", \"direccion\":\"DIR: \",\"ciudad\":\"CIU: \", \"telefono\":\"TEL: \"}");

        for (int i = 0; i < clavesCliente.length(); i++) {
            String[] arrCliente = { " ---- " };
            // Intentar acceder a una llave de cliente
            try {
                String keyCliente = clavesCliente.getString(i);
                arrCliente[0] = (clienteDatos.get(keyCliente) == null) ? " ---- "
                        : siglasKeys.getString(keyCliente) + clienteDatos.getString(keyCliente);
            } catch (JSONException e) {
                // System.out.println("Error en toString() Mercanci: " + e.getMessage());
            }

            if (i > 0) {
                datos += String.format(
                        "| %-9s | %-6s | %-20.20s | %-20.20s | %-6s | %-6s | %-6s | %-15.15s | %-20.20s | %-25.25s |%n",
                        " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", " ---- ", " ---- ",
                        arrCliente[0]);
            } else {
                datos += String.format(" %-25.25s |%n", arrCliente[0]);
            }
        }
        String info = encabezado;
        info += datos;
        info += String.format("|%-192s|%s", "_".repeat(192), Utils.RESET);
        return info;
    }

    // Métodos "interface" Costeable
    /**
     * Obtener costo de servicio de Almacenaje (Mercancía)
     */
    @Override
    public double getCosto() {
        // Calcular costo con fórmula (m3 * 10000)
        double costo = (getVolumen() * 10000);
        try {
            // Verificar que los días sean mayor a cero (0)
            if (getDiasAlmacenado() > 0) {
                // Si ha pasado más de un día, se multiplica el costo "actual" por días
                // almacenado
                costo *= getDiasAlmacenado();
                // Retornar costo redondeado al entero más cercano
                return Math.round(costo);
            }
        } catch (NullPointerException e) {
            System.out.println("Ocurrió un error obteniendo el costo: " + e.getMessage());
        }
        // caso que días almacenados menores o iguales a cero (0) se cobrará un día
        // completo
        return costo;
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
