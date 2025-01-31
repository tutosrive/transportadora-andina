package poo.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import poo.helpers.Utils;
import poo.model.Cliente;
import poo.model.Mercancia;

public class MercanciaService implements ServiceMercancia {
    private List<Mercancia> mercancias;
    private final String nombreArchivo;
    private final Service<Cliente> clientes;

    public MercanciaService(Service<Cliente> clientes) throws Exception {
        this.clientes = clientes;
        nombreArchivo = Utils.PATH + "Mercancia.json";

        if (Utils.fileExists(nombreArchivo)) {
            load();
        } else {
            mercancias = new ArrayList<>();
        }
    }

    @Override
    public JSONObject add(String strJson) throws Exception {
        Mercancia m = dataToAddOk(strJson);

        if (mercancias.add(m)) {
            Utils.writeJSON(mercancias, nombreArchivo);
        }

        return new JSONObject().put("message", "ok").put("data", m.toJSONObject());
    }

    @Override
    public Mercancia dataToAddOk(String strJson) throws Exception {
        JSONObject json = new JSONObject(strJson);
        JSONObject cliente = clientes.get(json.getString("cliente"));

        // Verificar si NO tiene un ID, o es un ID vacío
        if (!json.has("id") || json.getString("id").isBlank()) {
            json.put("id", Utils.getRandomKey(8));
        }

        // Verificar que el contenido tenga al menos 4 caracteres
        Utils.stringOk("contenido", 4, json);
        // Verificar que el bodega tenga 10 caracteres
        Utils.stringOk("bodega", 10, json);
        // Verificar que el ancho esté en rango [0.1, 2.44]
        Utils.doubleOk("ancho", 0.1, 2.44, json);
        // Verificar que el alto esté en rango [0.1, 2.59]
        Utils.doubleOk("alto", 0.1, 2.59, json);
        // Verificar que el largo esté en rango [0.1, 12.19]
        Utils.doubleOk("largo", 0.1, 12.19, json);

        // Verificar que haya una hora de entrada y una de salida
        // Verificar que la hora de entrada sea menor a la de salida
        if (!json.has("fechaHoraIngreso") || !json.has("fechaHoraSalida")) {
            throw new IllegalArgumentException(String
                    .format("%s La mercancía NO tiene %s Fecha de Entrada %s y/o %s Fecha de Salida %s",
                            Utils.RED, Utils.YELLOW, Utils.RED, Utils.YELLOW, Utils.RESET));
        }

        // Verificar que la fecha de entrada sea menor a la de salida
        if (!LocalDateTime.parse(json.getString("fechaHoraIngreso")).isBefore(
                LocalDateTime.parse(json.getString("fechaHoraSalida")))) {
            throw new IllegalArgumentException(String.format(
                    "%s La %s Hora de Ingreso %s NO es menor a la %s Hora de Salida %s",
                    Utils.RED, Utils.YELLOW, Utils.RED, Utils.YELLOW, Utils.RESET));
        }

        // Reemplazar valor de la clave cliente con un JSONObject de un cliente
        // "completo"
        json.put("cliente", cliente);

        // Si todo está bien en líneas anteriores, crear un Objeto Mercancia a partir
        // del json
        Mercancia m = new Mercancia(json);

        if (mercancias.contains(m)) {
            throw new ArrayStoreException(String.format(
                    "%s La mercancía %s %s %s ya existe %s",
                    Utils.YELLOW, Utils.RED, m.getId(), Utils.YELLOW, Utils.RESET));
        }

        return m;
    }

    @Override
    public JSONObject get(int index) {
        return mercancias.get(index).toJSONObject();
    }

    @Override
    public JSONObject get(String id) throws Exception {
        Mercancia m = getItem(id);
        if (m == null) {
            throw new NoSuchElementException(String.format(
                    "%s No se encontró la mercancía con id %s %s %s",
                    Utils.RED, Utils.YELLOW, id, Utils.RESET));
        }

        return m.toJSONObject();
    }

    @Override
    public Mercancia getItem(String id) throws Exception {
        int index = mercancias.indexOf(new Mercancia(id));
        return index > -1 ? mercancias.get(index) : null;
    }

    @Override
    public JSONObject getAll() {
        try {
            // Un arreglo vacío
            JSONArray data = new JSONArray();
            if (Utils.fileExists(nombreArchivo)) {
                data = new JSONArray(Utils.readText(nombreArchivo));
            }
            return new JSONObject().put("message", "ok").put("data", data).put("size", data.length());
        } catch (IOException | JSONException e) {
            Utils.printStackTrace(e);
            return Utils.keyValueToJson("message", "Sin acceso a datos de mercancías", "error", e.getMessage());
        }
    }

    @Override
    public final List<Mercancia> load() throws Exception {
        mercancias = new ArrayList<>();
        JSONArray jsonArr = new JSONArray(Utils.readText(nombreArchivo));
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            mercancias.add(new Mercancia(jsonObj));
        }
        return mercancias;
    }

    @Override
    public JSONObject update(String id, String strJson) throws Exception {
        JSONObject json = new JSONObject(strJson);
        // Buscar mercancía que se actualizará
        Mercancia mercancia = getItem(id);
        int index = mercancias.indexOf(mercancia);

        if (mercancia == null) {
            throw new NullPointerException(String.format(
                    "%s No se encontró la mercancía %s %s %s",
                    Utils.RED, Utils.YELLOW, id, Utils.RESET));
        }

        // "Buscar" por ID el cliente y obtener sus datos en formato JSON
        JSONObject clienteUpdate = clientes.get(json.getString("cliente"));

        // Actualizar cliente del json con datos completos
        json.put("cliente", clienteUpdate);

        // Crear un JSONObject con las propiedades del strJson
        JSONObject auxiliar = mercancia.toJSONObject();
        JSONArray propiedades = json.names();
        // Recorrer arreglo de las propiedades
        for (int i = 0; i < propiedades.length(); i++) {
            // Asignar a auxiliar los valores de las propiedades
            String propiedad = propiedades.getString(i);
            Object valor = json.get(propiedad);
            // Put permite tanto "poner" cuando no exixte la propiedad, pero sobreescribir
            // el valor cuando la propiedad existe
            auxiliar.put(propiedad, valor);
        }

        // Actualizar los datos de la mercancía con los almacenados en "auxiliar"
        mercancia = new Mercancia(auxiliar);
        // Usar setter del ID (Cada constructor de mercancía genera un ID aleatorio)
        // Es necesario, porque quedaría con ID distinto al de entrada
        mercancia.setId(id);

        // Actualizar también mercancía en la lista de mercancias
        mercancias.set(index, mercancia);
        // Actualizar el archivo de Mercancias.json
        Utils.writeJSON(mercancias, nombreArchivo);
        // Retornar la mercancía con los datos actualizados y mensaje de éxito ("ok")
        return new JSONObject().put("message", "ok").put("data", mercancia.toJSONObject());
    }

    public void updateCliente(JSONObject json) throws Exception {
        String idCliente = json.getString("cliente");
        JSONObject jsonCliente = clientes.get(idCliente);
        json.put("cliente", jsonCliente);
    }

    @Override
    public JSONObject remove(String id) throws Exception {
        JSONObject mercanciaJson = get(id);

        Mercancia m = new Mercancia(mercanciaJson);

        mercancias.remove(m);
        Utils.writeJSON(mercancias, nombreArchivo);

        return new JSONObject().put("message", "ok").put("data", mercanciaJson);
    }

    @Override
    public Class<Mercancia> getDataType() {
        return Mercancia.class;
    }

    @Override
    public Mercancia getUpdated(JSONObject newData, Mercancia current) throws Exception {
        JSONObject updated = current.toJSONObject();

        if (newData.has("cliente")) {
            try {
                updateCliente(newData.getJSONObject("cliente"));
            } catch (Exception e) {
                throw new IllegalArgumentException(String.format(
                        "%s Error al determinar el %s cliente %s propietario de la mercancía %s",
                        Utils.RED, Utils.YELLOW, Utils.RED, Utils.RESET));
            }
        }

        if (newData.has("contenido")) {
            updated.put("contenido", Utils.stringOk("contenido", 4, newData));
        }
        if (newData.has("ancho")) {
            updated.put("ancho", Utils.doubleOk("ancho", 0.1, 2.44, newData));
        }
        if (newData.has("alto")) {
            updated.put("alto", Utils.doubleOk("alto", 0.1, 2.59, newData));
        }
        if (newData.has("largo")) {
            updated.put("largo", Utils.doubleOk("largo", 0.1, 12.19, newData));
        }

        // Verificar si existe la clave "fechaHoraIngreso"
        if (newData.has("fechaHoraIngreso")) {
            // Verificar si existe la clave "fechaHoraSalida"
            if (newData.has("fechaHoraSalida")) {
                // Verificar si "fechaHoraIngreso" NO es menor a "fechaHoraSalida"
                if (!LocalDateTime.parse(newData.getString("fechaHoraIngreso"))
                        .isBefore(LocalDateTime.parse(newData.getString("fechaHoraSalida")))) {
                    // "Lanzar" excepción
                    throw new IllegalArgumentException(String.format(
                            "%s La %s Hora de Ingreso %s NO es menor a la %s Hora de Salida %s",
                            Utils.RED, Utils.YELLOW, Utils.RED, Utils.YELLOW, Utils.RESET));
                }
                // Si no se han generado "excepciones", se agrega la hora de salida
                updated.put("fechaHoraIngreso", newData.getString("fechaHoraIngreso"));
            }
            // Si no se han generado "excepciones", se agrega la hora de inicio
            updated.put("fechaHoraIngreso", newData.getString("fechaHoraIngreso"));
        }

        // Crear nuevo Objeto de Mercancia con datos actualizados
        Mercancia m = new Mercancia(updated);

        return m;
    }

    @Override
    public JSONObject size() {
        return new JSONObject().put("message", "ok").put("size", this.mercancias.size());
    }

    @Override
    public JSONObject searchByCity(String city) throws Exception {
        JSONArray mercanciasEncontradas = new JSONArray();
        int cont = 0;
        // Verificar que ciudad no sea un valor "vacío" o que por lo menos tenga 4
        // caracteres
        if (city.length() < 4 || city.isBlank()) {
            throw new IllegalArgumentException("La ciudad debe ser válida (Al menos 4 caracteres)");
        }

        // Recorrer la lista de mercancías
        for (Mercancia m : mercancias) {
            JSONObject client = m.getCliente().toJSONObject();
            String ciudad = client.getString("ciudad");

            // validar que la ciudad buscada y la obtenida sean iguales
            if (ciudad.equals(city.toUpperCase())) {
                // Agregar una clave:valor (ciuadad{cont}:mercancia)
                mercanciasEncontradas.put(m.toJSONObject());
                cont++;
            }
        }

        // Finalmente retornar las mercancías encontradas
        return new JSONObject().put("message", "ok").put("data", mercanciasEncontradas).put("size", cont);
    }

}
