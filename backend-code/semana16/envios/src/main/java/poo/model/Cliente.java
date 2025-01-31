package poo.model;

import org.json.JSONObject;

import poo.helpers.Utils;

public class Cliente implements Exportable {
    // Datos de cliente
    private String id;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String telefono;

    /**
     * Constructor por defecto
     * Genera un Cliente con ID aleatorio
     */
    public Cliente() {
        this(Utils.getRandomKey(5));
    }

    /**
     * Constructor parametrizado
     * 
     * @param id        String. Identificador único de cliente
     * @param nombre    String. Nombre cliente
     * @param direccion String. Ubicación exacta de residencia del cliente
     * @param ciudad    String. Ciudad de residencia
     * @param telefono  String. Teléfono (Número de contacto)
     */
    public Cliente(String id, String nombre, String direccion, String ciudad, String telefono) {
        this.setId(id);
        this.setNombre(nombre);
        this.setDireccion(direccion);
        this.setCiudad(ciudad);
        this.setTelefono(telefono);
    }

    /**
     * Constructor copia
     * Copia de los datos para una nueva instancia
     * 
     * @param c Cliente. Objeto Cliente del cual se clonarán sus datos
     */
    public Cliente(Cliente c) {
        this(c.id, c.nombre, c.direccion, c.ciudad, c.telefono);
    }

    /**
     * Constructor para un cliente inicialmente con solo ID
     * 
     * @param id String. Identificador único de cliente
     */
    public Cliente(String id) {
        this.id = id;
    }

    /**
     * Cliente con id generado aleatoriamente
     * 
     * @param nombre    String. Nombre cliente
     * @param ciudad    String. Ciudad de residencia
     * @param telefono  String. Teléfono (Número de contacto)
     * @param direccion String. Ubicación exacta de residencia del cliente
     */
    public Cliente(String nombre, String ciudad, String telefono, String direccion) {
        this(Utils.getRandomKey(5), nombre, direccion, ciudad, telefono);
    }

    /**
     * Crear cliente a partir de un JSONObject
     * 
     * @param json JSONObject. Objeto JSON Con los datos requeridos
     */
    public Cliente(JSONObject json) {
        this(json.getString("id"), json.getString("nombre"), json.getString("direccion"), json.getString("ciudad"),
                json.getString("telefono"));
    }

    // Accesor id
    public String getId() {
        return this.id;
    }

    // Mutador id
    public final void setId(String id) {
        this.id = id;
    }

    // Accesor nombre
    public String getNombre() {
        return this.nombre;
    }

    // Mutador nombre
    public final void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Accesor direccion
    public String getDireccion() {
        return this.direccion;
    }

    // Mutador direccion
    public final void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Accesor ciudad
    public String getCiudad() {
        return this.ciudad;
    }

    // Mutador ciudad
    public final void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    // Accesor telefono
    public String getTelefono() {
        return this.telefono;
    }

    // Mutador telefono
    public final void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    // Verificar si un cliente es igual a otro
    @Override
    public boolean equals(Object obj) {
        // Son el mismo objeto
        if (this == obj) {
            return true;
        }
        // Un objeto vacío
        if (obj == null) {
            return false;
        }
        // Provienen de diferentes clases
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        // Casteo a Cliente
        Cliente c = (Cliente) obj;
        // true si ambos id son iguales
        return this.id.equals(c.id);
    }

    /**
     * String id;
     * String nombre;
     * String direccion;
     * String ciudad;
     * String telefono;
     */
    @Override
    public String toString() {
        // Encabezado de la tabla
        String encabezadoTabla = "\u001B[1m|%s%s %-10.10s | %-6.6s| %-30.30s | %-30.30s | %-20.20s | %-20.20s %s|%s%n";
        // Títulos de encabezado
        String formatoEncabezado = String.format(" %s%-132s%n" + encabezadoTabla,
                Utils.YELLOW, "_".repeat(132), Utils.RED, Utils.FILLYELLOW, "CLIENTE", "ID",
                "NOMBRE", "DIRECCIÓN", "CIUDAD", "TELÉFONO", Utils.RESET, Utils.YELLOW);
        // Datos de las columnas (listadas en el encabezado)
        String datosColumnas = String.format(
                "|%-132s|%n| %-10.10s | %-6.6s| %-30.30s | %-30.30s | %-20.20s | %-20.20s |%n|%-132s|%s%n",
                "-".repeat(132), " ---- ",
                this.id, this.nombre, this.direccion, this.ciudad, this.telefono, "_".repeat(132), Utils.RESET);
        // Agregar encabezado formateado al String que se retornará
        String info = formatoEncabezado;
        // Agregar datos ed columnas formateados al String que se retornará
        info += datosColumnas;
        // Retornar información
        return info;
    }

    // Método de "interface" Expoertable
    @Override
    public JSONObject toJSONObject() {
        return (new JSONObject(this));
    }
}
