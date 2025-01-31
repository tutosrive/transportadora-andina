package poo.model;

import org.json.JSONArray;
import org.json.JSONObject;

public enum EstadoEnvio {
    EN_PREPARACION("En preparación"),
    ENVIADO("Enviado"),
    EN_CAMINO("En camino"),
    DEVUELTO("Devuelto"),
    ENTREGADO("Entregado"),
    EXTRAVIADO("Extraviado"),
    REENVIADO("Enviado nuevamente"),
    INDEFINIDO("Indefinido"),
    RECIBIDO("Recibido");

    private final String message;

    // asignar un String a cada Literal
    private EstadoEnvio(String message) {
        this.message = message;
    }

    // Retornar mensage del Literal
    public String getValue() {
        return this.message;
    }

    public static String getValue(String strEnum) {
        return getEnum(EstadoEnvio.valueOf(strEnum).getValue()).getValue();
    }

    // Obtener Literal
    public static EstadoEnvio getEnum(String value) {
        // Valor vacío
        if (value == null) {
            // "Lanzar" una excepción
            throw new IllegalArgumentException();
        }

        // Buscar el valor pasado por parámetro en los valores de los Literales
        for (EstadoEnvio estado : values()) {
            // Si value se encuentra en los valores de los Literales
            if (value.equalsIgnoreCase(estado.getValue())) {
                // Retornar Literal
                return estado;
            }
        }

        throw new IllegalArgumentException("Ha ocurrido un error al tratar de obtener el 'Enum'");
    }

    // Obtener todos los literales y sus respectivos valores (String)
    public static JSONObject getAll() {
        // Arreglo JSON, se almacenarán los datos
        JSONArray arrayLiterales = new JSONArray();
        // Por cada Literal agregar al arreglo un JSONObject{ordinal, key, value}
        for (EstadoEnvio estado : values()) {
            arrayLiterales.put(new JSONObject()
                    .put("ordinal", estado.ordinal())
                    .put("key", estado)
                    .put("value", estado.getValue()));
        }

        // Retornar JSONObject con mensaje y datos
        return new JSONObject().put("message", "ok").put("data", arrayLiterales);
    }
}
