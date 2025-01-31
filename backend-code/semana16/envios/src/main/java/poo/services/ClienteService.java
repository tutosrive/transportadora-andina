package poo.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import poo.helpers.Utils;
import poo.model.Cliente;

public class ClienteService implements Service<Cliente> {
    private List<Cliente> list;
    private final String fileName; // Nombre de archivo JSON

    public ClienteService() throws Exception {
        fileName = Utils.PATH + "Cliente.json";

        // Verificar si file ya existe
        if (Utils.fileExists(fileName)) {
            load(); // Cargar archivo
        } else {
            list = new ArrayList<>(); // ArrayList Vacío
        }
    }

    @Override
    public JSONObject add(String strJson) throws Exception {
        Cliente c = dataToAddOk(strJson);
        if (list.add(c)) {
            Utils.writeJSON(list, fileName);
        }
        return new JSONObject().put("message", "ok").put("data", c.toJSONObject());
    }

    // dataToAddOk(String strJson) del taller semana 9
    @Override
    public Cliente dataToAddOk(String strJson) {
        JSONObject json = new JSONObject(strJson);

        if (!json.has("id") || json.getString("id").isBlank()) {
            json.put("id", Utils.getRandomKey(5));
        }
        // Verificar que el ID tenga al menos cinco caracteres
        Utils.stringOk("id", 5, json);
        // Verificar que el nombre tenga al menos un caracter
        Utils.stringOk("nombre", 1, json);
        // Verificar que la dirección tenga 10 caracteres
        Utils.stringOk("direccion", 10, json);
        // Verificar que el teléfono tenga 10 caracteres
        Utils.stringOk("telefono", 10, json);
        // Verificar que la ciudad tenga al menos 4 caracteres
        Utils.stringOk("ciudad", 4, json);

        // Si todo está bien en líneas anteriores, crear un Objeto cliente con el json
        Cliente c = new Cliente(json);
        // Verificar si cliente ya existe
        if (list.contains(c)) {
            throw new ArrayStoreException(String.format(
                    "%s El cliente %s %s - %s %s ya existe %s",
                    Utils.YELLOW, Utils.RED, c.getId(), c.getNombre(), Utils.YELLOW, Utils.RESET));
        }
        return c;
    }

    @Override
    public JSONObject get(int index) {
        return list.get(index).toJSONObject();
    }

    // Refactorizado
    @Override
    public JSONObject get(String id) throws Exception {
        Cliente c = getItem(id);
        if (c == null) {
            throw new NoSuchElementException(String.format(
                    "%s No se encontró el cliente con id %s %s %s",
                    Utils.RED, Utils.YELLOW, id, Utils.RESET));
        }

        return c.toJSONObject();
    }

    @Override
    public Cliente getItem(String id) throws Exception {
        int i = list.indexOf(new Cliente(id));
        return i > -1 ? list.get(i) : null;
    }

    @Override
    public JSONObject getAll() {
        try {
            JSONArray data = new JSONArray();

            // Validar si existe un archivo con clientes
            if (Utils.fileExists(fileName)) {
                data = new JSONArray(Utils.readText(fileName));
            }

            return new JSONObject().put("message", "ok").put("data", data).put("size", data.length());
        } catch (IOException | JSONException e) {
            Utils.printStackTrace(e);
            return Utils.keyValueToJson("message", "Sin acceso a datos de clientes", "error", e.getMessage());
        }
    }

    @Override
    public final List<Cliente> load() throws Exception {
        list = new ArrayList<>();
        JSONArray jsonArr = new JSONArray(Utils.readText(fileName));
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            list.add(new Cliente(jsonObj));
        }
        return list;
    }

    @Override
    public JSONObject update(String id, String strJson) throws Exception {
        JSONObject json = new JSONObject(strJson);
        // buscar el cliente que se debe actualizar
        Cliente cliente = getItem(id);
        int i = list.indexOf(cliente);

        if (cliente == null) {
            throw new NoSuchElementException(String.format(
                    "%s No se encontró el cliente con id %s %s %s",
                    Utils.RED, Utils.YELLOW, id, Utils.RESET));
        }

        // crear un objeto JSON con las propiedades del objeto a actualizar
        JSONObject aux = cliente.toJSONObject();
        JSONArray propiedades = json.names();
        for (int k = 0; k < propiedades.length(); k++) {
            // asignar a aux los nuevos valores de las propiedades dadas
            String propiedad = propiedades.getString(k);
            Object valor = json.get(propiedad);
            aux.put(propiedad, valor);
        }

        // utilizar aux para actualizar el cliente
        cliente = new Cliente(aux);
        list.set(i, cliente);
        // actualizar el archivo de clientes
        Utils.writeJSON(list, fileName);

        // devolver el cliente con los cambios realizados
        return new JSONObject().put("message", "ok").put("data", cliente.toJSONObject());
    }

    @Override
    public Cliente getUpdated(JSONObject newData, Cliente current) throws Exception {
        JSONObject updated = current.toJSONObject();

        if (newData.has("nombre")) {
            updated.put("nombre", Utils.stringOk("nombre", 1, newData));
        }
        if (newData.has("direccion")) {
            updated.put("direccion", Utils.stringOk("direccion", 10, newData));
        }
        if (newData.has("telefono")) {
            updated.put("telefono", Utils.stringOk("telefono", 10, newData));
        }
        if (newData.has("ciudad")) {
            updated.put("ciudad", Utils.stringOk("ciudad", 4, newData));
        }

        Cliente c = new Cliente(updated);

        return c;
    }

    @Override
    public JSONObject remove(String id) throws Exception {
        JSONObject cliente = get(id);

        Cliente c = new Cliente(cliente);
        exists(cliente);

        list.remove(c);
        Utils.writeJSON(list, fileName);

        return new JSONObject().put("message", "ok").put("data", cliente);
    }

    private void exists(JSONObject cliente) throws Exception {
        String name = cliente.getString("nombre");

        if (Utils.exists(Utils.PATH + "Mercancia", "cliente", cliente)) {
            throw new Exception(String.format(
                    "No se puede eliminar a \"%s\", porque tiene mercancías registradas", name));
        }

        exists("Paquete", cliente);
        exists("Sobre", cliente);
        exists("Caja", cliente);
        exists("Bulto", cliente);
    }

    private void exists(String fileName, JSONObject cliente) throws Exception {
        if (Utils.exists(Utils.PATH + fileName, "remitente", cliente) || Utils.exists(Utils.PATH + fileName,
                "destinatario", cliente)) {
            throw new Exception(String.format(
                    "No se puede eliminar a \"%s\", porque tiene \"%ss\" registrados",
                    cliente.getString("nombre"), fileName));
        }
    }

    @Override
    public Class<Cliente> getDataType() {
        return Cliente.class;
    }

    @Override
    public JSONObject size() {
        return new JSONObject().put("message", "ok").put("size", this.list.size());
    }
}
