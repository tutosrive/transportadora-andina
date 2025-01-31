package poo.services;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import poo.helpers.Utils;
import poo.model.Cliente;
import poo.model.Envio;
import poo.model.Estado;
import poo.model.EstadoEnvio;

public class EnvioService implements ServiceEnvio {
    private final Class<? extends Envio> subclase;
    private final Service<Cliente> clientes;
    // Lista de envíos
    private List<Envio> envios;
    // Nombre donde se almacenarán los Envíos
    private final String nombreArchivo;

    /** Constructor de EnvioService */
    public EnvioService(Class<? extends Envio> subclase, Service<Cliente> clientes) throws Exception {
        this.subclase = subclase;
        this.clientes = clientes;
        // Dar valor al nombre del archivo
        nombreArchivo = String.format("%s%s.json", Utils.PATH, subclase.getSimpleName());

        // Verificar la existencia del archivo
        if (Utils.fileExists(nombreArchivo)) {
            // Se carga la información del archivo
            load();
        } else {
            // Se inicializa la lista de envíos en vacío
            envios = new ArrayList<>();
        }
    }

    // Buscar envios por su atributo frgilidad

    @Override
    public JSONObject add(String strJson) throws Exception {
        Envio envio = dataToAddOk(strJson);
        if (envios.add(envio)) {
            Utils.writeJSON(envios, nombreArchivo);
        }
        return new JSONObject().put("message", "ok").put("data", envio.toJSONObject());
    }

    @Override
    public JSONObject get(int index) {
        return envios.get(index).toJSONObject();
    }

    @Override
    public JSONObject get(String nroGuia) throws Exception {
        Envio e = getItem(nroGuia);
        if (e == null) {
            throw new NoSuchElementException(String.format(
                    "No se encontró el envío con número guía \"%s\"", nroGuia));
        }
        return e.toJSONObject();
    }

    @Override
    public Envio getItem(String nroGuia) throws Exception {
        try {
            Envio e = subclase.getConstructor(String.class).newInstance(nroGuia);
            int i = envios.indexOf(e);
            return (i > -1) ? envios.get(i) : null;
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException
                | SecurityException | InvocationTargetException e) {
            throw new Exception(String.format(
                    "%s Ha ocurrido un error al tratar de instanciar la subclase %s %s %s: %s %s %s",
                    Utils.RED, Utils.YELLOW, subclase.getSimpleName(),
                    Utils.RED, Utils.YELLOW, e.getMessage(), Utils.RESET));
        }
    }

    @Override
    public JSONObject getAll() {
        try {
            // Arreglo sin datos
            JSONArray data = new JSONArray();

            // Verificar que el archivo exista
            if (Utils.fileExists(nombreArchivo)) {
                data = new JSONArray(Utils.readText(nombreArchivo));
            }

            return new JSONObject().put("message", "ok").put("data", data).put("size", data.length());
        } catch (IOException | JSONException e) {
            Utils.printStackTrace(e);
            return Utils.keyValueToJson("message", "Sin acceso a datos de envíos", "error", e.getMessage());
        }
    }

    @Override
    public final List<Envio> load() throws Exception {
        envios = new ArrayList<>();
        JSONArray jsonArr = new JSONArray(Utils.readText(nombreArchivo));
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            // Verificar estados de cada Envío (antes de cargar)
            verificarEstados(jsonObj.getJSONArray("estados"));
            envios.add(subclase.getConstructor(JSONObject.class).newInstance(jsonObj));
        }
        return envios;
    }

    @Override
    public JSONObject update(String nroGuia, String strJson) throws Exception {
        JSONObject newData = new JSONObject(strJson);

        Envio envio = subclase.cast(getItem(nroGuia));

        if (envio == null) {
            throw new NullPointerException(String.format(
                    "%s No se encontró el %s envío %s %s",
                    Utils.RED, Utils.YELLOW, nroGuia, Utils.RESET));
        }

        // Buscar posición de envío en lista y actualizar
        int index = envios.indexOf(envio);
        // Capturar excepcion de estados

        envio = getUpdated(newData, envio);

        envios.set(index, envio);

        // Actualizar archivo de envios
        Utils.writeJSON(envios, nombreArchivo);

        return new JSONObject().put("message", "ok").put("data", envio.toJSONObject());
    }

    @Override
    public JSONObject remove(String nroGuia) throws Exception {
        JSONObject envioJson = get(nroGuia);

        Envio e = subclase.getConstructor(JSONObject.class).newInstance(envioJson);

        envios.remove(e);
        Utils.writeJSON(envios, nombreArchivo);

        return new JSONObject().put("message", "ok").put("data", envioJson);
    }

    @Override
    public Envio dataToAddOk(String strJson) throws Exception {
        JSONObject json = new JSONObject(strJson);

        // Verificar si NO tiene un nroGuia o es un nroGuia vacío
        if (!json.has("nroGuia") || json.getString("nroGuia").isBlank()) {
            json.put("nroGuia", Utils.getRandomKey(8));
        }

        if (!json.has("estados")) {
            Estado estadoInicial = new Estado(LocalDateTime.now().withNano(0), EstadoEnvio.RECIBIDO);
            JSONArray estados = new JSONArray()
                    .put(new JSONObject()
                            .put("estado", estadoInicial.getEstado().toString())
                            .put("fechaHora", estadoInicial.getFechaHora().toString()));
            json.put("estados", estados);
        }

        verificarEstados(json.getJSONArray("estados"));

        if (!json.has("fragil")) {
            json.put("fragil", false);
        }

        // Agregar clientes
        updateCliente(json, "remitente");
        updateCliente(json, "destinatario");

        // Validaciones de caracteres y double
        Utils.stringOk("contenido", 3, json);
        Utils.doubleOk("peso", 0.0, 1000000.0, json);
        Utils.doubleOk("valorDeclarado", 0.0, 100000000.0, json);

        // Alberga todas las subclases de envío mediante "refelction"
        Envio envio = subclase.getConstructor(JSONObject.class).newInstance(json);

        if (!(json.has("remitente") || json.has("destinatario"))) {
            throw new IllegalArgumentException(String.format(
                    "%s Verifique que el %s envío %s tenga los atributos %s \"remitente\" %s y %s \"destinatario\" %s",
                    Utils.RED, Utils.YELLOW, Utils.RED, Utils.YELLOW, Utils.RED, Utils.YELLOW, Utils.RESET));
        }

        // Instancias de Cliente para facilitar las verificaciones
        Cliente remitente = new Cliente(json.getJSONObject("remitente"));
        Cliente destinatario = new Cliente(json.getJSONObject("destinatario"));

        // Verificar si remitente === destinatario
        if (remitente.equals(destinatario)) {
            throw new IllegalArgumentException(String.format(
                    "%s Se espera un %s destinatario %s diferente al %s remitente %s: %s id = %s %s",
                    Utils.RED, Utils.YELLOW, Utils.RED, Utils.YELLOW, Utils.RED, Utils.YELLOW, remitente.getId(),
                    Utils.RESET));
        }

        if (envios.contains(envio)) {
            throw new ArrayStoreException(String.format(
                    "%s La instancia de %s %s %s con %s guía %s %s, ya existe %s",
                    Utils.RED, Utils.YELLOW, subclase.getSimpleName(), Utils.RED, Utils.YELLOW, envio.getNroGuia(),
                    Utils.RED, Utils.RESET));
        }

        return envio;
    }

    private void updateCliente(JSONObject json, String cliente) throws Exception {
        // Obtener ID del remitente/destinatario
        String id = json.getString(cliente);
        // Buscar el remitente/destinatario por id
        JSONObject clienteJson = clientes.get(id);
        // Actualizar datos remitente/destinatario en el json
        json.put(cliente, clienteJson);
    }

    @Override
    public Envio getUpdated(JSONObject newData, Envio current) throws Exception {
        EstadoEnvio estado = current.getEstados().getLast().getEstado();

        // No se pueden actualizar los estados de un envío con estado "Entregado"
        if ("Entregado".equals(estado.getValue())) {
            throw new IllegalStateException(
                    String.format("El %s \"%s\" fue entregado. No se permite la actualización de estados.",
                            current.getTipo(), current.getNroGuia()));
        }

        // Intentar actualizar los datos
        try {
            updateCliente(newData, "remitente");
            updateCliente(newData, "destinatario");
        } catch (JSONException e) {
            System.out.printf(
                    "%sNo se encontró un %s %s %s para actualizar%s%n",
                    Utils.YELLOW, Utils.RED, e.getMessage().subSequence(12, 21), Utils.YELLOW, Utils.RESET);
        }

        // Datos actualizados en formato JSON
        JSONObject updated = current.toJSONObject();

        if (newData.has("estados")) {
            updated.put("estados", verifyStates(newData));
        }

        // Verificar si newData tiene clave "remitente"
        if (newData.has("remitente")) {
            // De ser así, actualizar "remitente" pero antes se verifica su valor
            updated.put("remitente", newData.getJSONObject("remitente"));
        }

        // Verificar si newData tiene clave "destinatario"
        if (newData.has("destinatario")) {
            // De ser así, actualizar "destinatario" pero antes se verifica su valor
            updated.put("destinatario", newData.getJSONObject("destinatario"));
        }

        // Verificar si newData tiene clave "peso"
        if (newData.has("peso")) {
            // De ser así, actualizar "peso" pero antes se verifica su valor
            updated.put("peso", Utils.doubleOk("peso", 0.0, 1000000.0, newData));
        }

        // Verificar si newData tiene clave "fragil"
        if (newData.has("fragil")) {
            // De ser así, actualizar "fragil"
            updated.put("fragil", newData.getBoolean("fragil"));
        }

        // Verificar si newData tiene clave "contenido"
        if (newData.has("contenido")) {
            // De ser así, actualizar "contenido" pero antes se verifica su valor
            updated.put("contenido", Utils.stringOk("contenido", 3, newData));
        }

        // Verificar si newData tiene clave "valorDeclarado"
        if (newData.has("valorDeclarado")) {
            // De ser así, actualizar "valorDeclarado" pero antes se verifica su valor
            updated.put("valorDeclarado", Utils.doubleOk("valorDeclarado", 0.0, 10000000, newData));
        }

        // Verificar si newData tiene clave "certificado"
        if (newData.has("certificado")) {
            // De ser así, actualizar "certificado" pero antes se verifica su valor
            updated.put("certificado", newData.getBoolean("certificado"));
        }

        // Verificar si newData tiene clave "alto"
        if (newData.has("alto")) {
            // De ser así, actualizar "alto" pero antes se verifica su valor
            updated.put("alto", Utils.doubleOk("alto", 0.1, 2.59, newData));
        }

        // Verificar si newData tiene clave "ancho"
        if (newData.has("ancho")) {
            // De ser así, actualizar "ancho" pero antes se verifica su valor
            updated.put("ancho", Utils.doubleOk("ancho", 0.1, 2.44, newData));
        }

        // Verificar si newData tiene clave "largo"
        if (newData.has("largo")) {
            // De ser así, actualizar "largo" pero antes se verifica su valor
            updated.put("largo", Utils.doubleOk("largo", 0.1, 12.19, newData));
        }

        // Nueva instancia de subclase de "Envio" con datos actualizados
        Envio envioUpdated = subclase.getConstructor(JSONObject.class).newInstance(updated);

        // Se retorna la el "Envio" actualizado
        return envioUpdated;
    }

    /**
     * Verificar estados antes de actualizar
     * 
     * @param json JSONObject. Objeto que contiene la
     *             "key"="estados"=<strong>JSONArray</strong>
     * @return JSONArray. Arreglo con los datos de los estados actualizados
     * @throws Exception
     */
    private JSONArray verifyStates(JSONObject json) throws Exception {
        JSONArray estadosJSONArr = new JSONArray();
        JSONArray estadosToAdd = json.getJSONArray("estados");
        // Recorrer cada objeto:estado
        for (int i = 0; i < estadosToAdd.length(); i++) {
            JSONObject state = estadosToAdd.getJSONObject(i);
            // "Poner" en el JSONArray cada estado
            estadosJSONArr
                    .put(new JSONObject()
                            .put("estado", state.getString("estado"))
                            .put("fechaHora", state.getString("fechaHora")));
        }
        // Verificar el arreglo de estados
        verificarEstados(estadosJSONArr);
        // Si no hay Excepciones se retornará el arreglo de estados actualizados
        return estadosJSONArr;
    }

    /**
     * Validar posiciones y fechas de los estados
     * 
     * <ol>
     * <li><strong>RECIBIDO</strong>: El envío ha llegado a las "instalaciones" de
     * la empresa.</li>
     * <li><strong>EN_PREPARACION</strong>: Inicio del proceso.</li>
     * <li><strong>ENVIADO</strong>: El envío ha salido del origen.</li>
     * <li><strong>EN_CAMINO</strong>: El paquete está en tránsito.</li>
     * <ul>
     * <p>
     * Puede uno de estos estados
     * </p>
     * <li><strong>DEVUELTO</strong>: Si no fue posible la entrega y regresa al
     * origen.</li>
     * <li><strong>REENVIADO</strong>: En caso de ser enviado nuevamente.</li>
     * <li><strong>EXTRAVIADO</strong>: El envío se perdió en el proceso.</li>
     * <li><strong>INDEFINIDO</strong>: Situación desconocida o no clasificada.</li>
     * <li><strong>ENTREGADO</strong>: El paquete ha llegado a su destino.</li>
     * </ul>
     * </ol>
     * 
     * @param states JSONArray. Arreglo JSON de estados
     */
    public final void verificarEstados(JSONArray states) {
        if (states != null) {
            // Lista de fechas (de los estados)
            ArrayList<LocalDateTime> datesStr = new ArrayList<>();
            // Cragar todas las fechas en "dateStr"
            states.forEach((Object e) -> {
                JSONObject state = (JSONObject) e;
                datesStr.add((LocalDateTime.parse(state.getString("fechaHora"))));
            });

            // Validar las fechas
            for (int i = 0; i < datesStr.size() - 1; i++) {
                LocalDateTime fechaBefore = datesStr.get(i);
                for (int j = 0; j < 10; j++) {
                    LocalDateTime fechaAfter = datesStr.get(i + 1);
                    if (!fechaAfter.isAfter(fechaBefore)) {
                        throw new IllegalArgumentException(
                                "La fecha del nuevo estado no puede ser menor a la del anterior estado");
                    }
                }
            }

            // Validar posiciones de los estados
            for (int i = 0; i < states.length(); i++) {
                JSONObject jsonStates = states.getJSONObject(i);
                String state = EstadoEnvio.getValue(jsonStates.getString("estado"));

                if (i == 0 && !state.equals("Recibido")) {
                    throw new IllegalArgumentException("El primer estado debe ser \"Recibido\"");
                } else if (i == 1 && !state.equals("En preparación")) {
                    throw new IllegalArgumentException("El segundo estado debe ser \"En preparación\"");
                } else if (i == 2 && !state.equals("Enviado")) {
                    throw new IllegalArgumentException("El tercer estado debe ser \"Enviado\"");
                } else if (i == 3 && !state.equals("En camino")) {
                    throw new IllegalArgumentException("El cuarto estado debe ser \"En camino\"");
                } else if (i > 3 && !"Devuelto|Extraviado|Indefinido|Enviado nuevamente|Entregado".contains((state))) {
                    throw new IllegalArgumentException("El cuarto estado no es correcto");
                }
            }
            /*
             * No tuve en cuenta la verificación de los estados repetidos, porque el filtro
             * anterior verifica cada posición y no permite estados repetidos
             */
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<Envio> getDataType() {
        // Retornar el Tipo de Subclase, Casteado a Envio
        return (Class<Envio>) subclase;
    }

    @Override
    public JSONObject size() {
        return new JSONObject().put("message", "ok").put("size", this.envios.size());
    }

    @Override
    public JSONObject searchByFragile(boolean fragil, String... fileNames) throws Exception {
        JSONArray enviosFragiles = new JSONArray();
        for (String file : fileNames) {
            Utils.existsInAll(file, "fragil", fragil, enviosFragiles);
        }
        return new JSONObject().put("message", "ok").put("data", enviosFragiles).put("size", enviosFragiles.length());
    }
}
