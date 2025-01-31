package poo.model;

import java.time.LocalDateTime;

import org.json.JSONObject;

import poo.helpers.Utils;

public class Estado implements Exportable {
    private LocalDateTime fecha;
    protected EstadoEnvio estado;

    /**
     * Constructor por defecto
     * Crea un Estado con un LocalDateTime Mínimo, y un EstadoEnvio Indefinido
     */
    public Estado() {
        this(LocalDateTime.MIN, EstadoEnvio.INDEFINIDO);
    }

    /**
     * Constructor parametrizado
     * 
     * @param fecha   LocalDateTime. Registro de fecha
     * @param estados EstadoEnvio. Estado actual
     */
    public Estado(LocalDateTime fecha, EstadoEnvio estado) {
        this.setFecha(fecha);
        this.setEstado(estado);
    }

    /**
     * Constructor copia
     * 
     * @param e Estado. Objeto del cual se clonarán los datos
     */
    public Estado(Estado e) {
        this(e.fecha, e.estado);
    }

    /**
     * Constructor que recibe un JSONObject con la fecha y el estado
     * 
     * @param json
     */
    public Estado(JSONObject json) {
        this(LocalDateTime.parse(json.getString("fechaHora")),
                EstadoEnvio.getEnum(EstadoEnvio.getValue(json.get("estado").toString())));
    }

    // Accesor fecha
    public LocalDateTime getFechaHora() {
        return this.fecha;
    }

    /**
     * Mutador fecha
     * 
     * @param fecha LocalDateTime. Registro de fecha
     */
    public final void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    // Accesor estado
    public EstadoEnvio getEstado() {
        return this.estado;
    }

    // Retornar hashCode() como el "ID"
    public String getId() {
        // hashCode() retorna un entero
        return String.valueOf(super.hashCode());
    }

    /**
     * Mutador estado
     * 
     * @param estados EstadoEnvio. Estado actual
     */
    public final void setEstado(EstadoEnvio estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    // Verificar si un estado es igual a otro (Estado)
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
        // Pertenecen a diferentes clases
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        // Casteo de Objeto a Estado
        Estado e = (Estado) obj;
        // true si son el mismo estado y fecha, false lo contrario
        return this.estado.equals(e.estado) && this.fecha.equals(e.fecha);
    }

    @Override
    public String toString() {
        String encabezado = "\u001B[1m|%s %-20.20s | %-30.30s %s%s|%s%n";
        String encabezadoFormateado = String.format(" %s%-60s %n" + encabezado, Utils.RED,
                "_".repeat(55), Utils.FILLYELLOW, "ESTADO ACTUAL", "FECHA ESTADO", Utils.RESET, Utils.RED,
                Utils.YELLOW);

        String datosFormateados = String.format("|%-5s|%n| %-20.20s | %-30.30s |%n|%-55s|%s", "-".repeat(55),
                this.estado.getValue(), this.fecha, "_".repeat(55), Utils.RESET);
        String info = encabezadoFormateado;
        info += datosFormateados;
        return info;
    }

    // Métodos interfaz "Exportable"
    public String toJSON() {
        return (new JSONObject(this).toString(2));
    }

    @Override
    public JSONObject toJSONObject() {
        return (new JSONObject(this));
    }
}
