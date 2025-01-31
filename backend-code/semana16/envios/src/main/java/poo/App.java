package poo;

import java.util.Locale;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.javalin.Javalin;
import io.javalin.http.Context;
import poo.helpers.Controller;
import poo.helpers.Utils;
import poo.model.Bulto;
import poo.model.Caja;
import poo.model.Cliente;
import poo.model.Envio;
import poo.model.EstadoEnvio;
import poo.model.Mercancia;
import poo.model.Paquete;
import poo.model.Sobre;
import poo.services.ClienteService;
import poo.services.EnvioService;
import poo.services.MercanciaService;
import poo.services.Service;
import poo.services.SobreService;

public final class App {
        private static final Logger LOG = LoggerFactory.getLogger(App.class);

        public static void main(String[] args) throws Exception {
                int port = 7070;
                String message = String.format(
                                "%sIniciando la API Rest de Envios y bodegaje. Use Ctrl+C para detener la ejecución%s",
                                Utils.CYAN,
                                Utils.RESET);
                LOG.info(message);

                Utils.trace = true; // no deshabilite la traza de errores hasta terminar completamente la aplicación

                int length = args.length;
                if (length > 0) {
                        Utils.trace = Boolean.parseBoolean(args[0]);
                        if (length >= 2) {
                                port = Integer.parseInt(args[1]);
                        }
                }

                if (Utils.trace) {
                        // ver para tiempo de desarrollo: ./.vscode/launch.json
                        LOG.info(String.format("%sHabilitada la traza de errores%s", Utils.YELLOW, Utils.RESET));
                } else {
                        LOG.info(String.format(
                                        "%sEnvíe un argumento true|false para habilitar|deshabilitar la traza de errores%s",
                                        Utils.YELLOW, Utils.RESET));
                }

                // esencial para estandarizar el formato monetario con separador de punto
                // decimal, no con coma
                Locale.setDefault(Locale.of("es_CO"));

                Service<Cliente> clienteService = new ClienteService();
                Service<Mercancia> mercanciaService = new MercanciaService(clienteService);
                Service<Envio> paqueteService = new EnvioService(Paquete.class, clienteService);
                Service<Envio> cajaService = new EnvioService(Caja.class, clienteService);
                Service<Envio> bultoService = new EnvioService(Bulto.class, clienteService);
                Service<Envio> sobreService = new SobreService(Sobre.class, clienteService);
                // Se crea aquí una instancia de EnvioService para acceder a métodos
                EnvioService serviceEnvio = new EnvioService(Paquete.class, clienteService);

                Javalin.create(config -> {
                        config.http.defaultContentType = "application/json";
                        config.bundledPlugins.enableCors(cors -> cors.addRule(it -> it.anyHost()));
                        config.router.apiBuilder(() -> {
                                new Controller<>(clienteService).info();
                                new Controller<>(mercanciaService).info();
                                new Controller<>(paqueteService).info();
                                new Controller<>(cajaService).info();
                                new Controller<>(bultoService).info();
                                new Controller<>(sobreService).info();
                        });
                })
                                // Bienvenida del localhost:7070
                                .get("", ctx -> ctx.result(new JSONObject()
                                                .put("data", "Bienvenido a la API T.A. By SRM").put("message",
                                                                "ok")
                                                .toString()))
                                .get("/envio/estados", ctx -> ctx.json(EstadoEnvio.getAll().toString()))
                                // Buscar por fragilidad (envios)
                                .get("/envios/search/fragilidad/{param}", ctx -> {
                                        boolean fragil = Boolean.parseBoolean(ctx.pathParam("param"));
                                        ctx.json(serviceEnvio.searchByFragile(fragil, "Caja", "Bulto",
                                                        "Paquete", "Sobre").toString());
                                })
                                // Consulta de jacobo
                                .get("/cliente/id/{param}/conteo", ctx -> {
                                        String id = ctx.pathParam("param");
                                        ctx.result(countTotalRegister(id).toString());
                                })
                                .afterMatched(ctx -> updateClients(ctx))
                                .start(port)
                                .exception(Exception.class, (e, ctx) -> {
                                        Utils.printStackTrace(e);
                                        String error = Utils.keyValueToStrJson("message", e.getMessage(), "request",
                                                        ctx.fullUrl());
                                        ctx.json(error).status(400);
                                });

                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                        LOG.info(String.format("%sEl servidor Jetty de Javalin ha sido detenido%s%n", Utils.RED,
                                        Utils.RESET));
                }));
        }

        private static JSONObject countTotalRegister(String id) throws Exception {
                return Utils.searchClients(id, "Caja", "Sobre", "Bulto", "Paquete", "Mercancia");
        }

        // Actualizar clientes
        private static void updateClients(@NotNull Context ctx) throws Exception {
                // si se hace una solicitud de actualizar un cliente...
                if (ctx.path().contains("cliente") && ctx.method().toString().equals("PATCH")) {

                        // obtener la instancia de cliente actualizada
                        JSONObject cliente = new JSONObject(ctx.result()).getJSONObject("data");

                        writeUpdate("Sobre", cliente, "id", "remitente", "destinatario");
                        writeUpdate("Caja", cliente, "id", "remitente", "destinatario");
                        writeUpdate("Paquete", cliente, "id", "remitente", "destinatario");
                        writeUpdate("Bulto", cliente, "id", "remitente", "destinatario");

                        // Escribir la lista con la Mercancia actualizada
                        writeUpdate("Mercancia", cliente, "id", "cliente");
                }
        }

        /**
         * Actualizar objetos dentro de un archivo.json
         * 
         * @param filename  String. Nombre del archivo. Ej: "Mercancia"
         * @param obj       JSONObject. Objeto con los nuevos datos.
         * @param propertie String. Propiedad que se actualizará. Ej: "id"
         * @param keys      String... Puede ingresar cuantas propiedades guste, cada una
         *                  será actualizada, siempre y cuando exista!
         *                  Ej: "remitente", "destinatario"
         * @throws Exception
         */
        private static void writeUpdate(String filename, JSONObject obj, String propertie, String... keys)
                        throws Exception {
                // Por cada clave (podría ser "remitene", "destinatario", o solo "cliente")
                for (String key : keys) {
                        // Verificar si la clave "actual" existe en el JSON "obj"
                        if (Utils.exists(Utils.PATH + filename, key, obj, propertie)) {
                                // Obtener los objetos contenidos en el archivo
                                JSONArray data = new JSONArray(Utils.readText(Utils.PATH + filename + ".json"));

                                // recorrer los datos de cada objeto
                                for (int i = 0; i < data.length(); i++) {
                                        // Ya que se sabe que existe el "key", se obtiene directamente del objeto
                                        JSONObject json = data.getJSONObject(i).getJSONObject(key);
                                        // Validar que el ID sea igual al obj que se quiere actualizar
                                        if (json.getString(propertie).equals(obj.getString(propertie))) {
                                                // Modificar objeto dentro del arreglo
                                                data.getJSONObject(i).put(key, obj);
                                        }

                                }
                                // Escribir la lista con el/los objeto/s actualizado/s
                                Utils.writeJSON(data.toList(), Utils.PATH + filename + ".json");
                        }
                }
        }

}