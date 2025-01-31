/**
 * @autor Carlos Cuesta
 */
package poo.helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; // <-------- OJO
import java.util.List;
import java.util.Properties;
import java.util.Random; // <-------- OJO

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.Property;

public class Utils {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[91m";
    public static final String BLACK = "\u001B[30m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String FILLRED = "\u001B[41m";
    public static final String FILLYELLOW = "\u001B[43m";
    public static final String FILLBLUE = "\u001B[43m";
    public static final String UNDERLINE_YELLOW = "\u001B[4m" + YELLOW;

    public static final String PATH = "./data/";
    public static boolean trace = false;

    private Utils() {
    } // lo mismo en Keyboard

    public static void printStackTrace(Exception e) {
        if (Utils.trace) {
            System.out.printf("%s%s%s%s%s%n", Utils.RED, "-".repeat(30), " Reporte de excepciones ", "-".repeat(30),
                    Utils.RESET);
            e.printStackTrace(System.out);
            System.out.printf("%s%s%s%s%s%n", Utils.RED, "-".repeat(30), " Fin del reporte de excepciones ",
                    "-".repeat(30), Utils.RESET);
        }
    }

    /**
     * Genera un string de caracteres alfanuméricos aleatorios de una longitud dada
     * Ver: https://www.baeldung.com/java-random-string
     * 
     * @param stringLength La longitud que se requiere para el string
     * @return Un string de caracteres alfanuméricos aleatorios de una longitud
     */
    public static String getRandomKey(int stringLength) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 90; // letter 'Z'
        Random random = new Random();

        String generatedString = random
                .ints(leftLimit, rightLimit + 1) // definir el rango de caracteres ['0'..'Z']
                .filter(i -> (i <= 57 || i >= 65)) // filtrar entre ['0'..'9'] o entre ['A'..'Z']
                .limit(stringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public static String strDateTime(LocalDateTime dateTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(format);
    }

    public static boolean fileExists(String fileName) {
        Path dirPath = Paths.get(fileName);
        return Files.exists(dirPath) && !Files.isDirectory(dirPath);
    }

    public static boolean pathExists(String path) {
        Path folder = Paths.get(path);
        return Files.exists(folder) && Files.isDirectory(folder);
    }

    public static void createFolderIfNotExist(String folder) throws IOException {
        // si no existe o si existe y no es una carpeta, crear la carpeta
        if (!pathExists(folder)) {
            Path dirPath = Paths.get(folder);
            Files.createDirectories(dirPath);
        }
    }

    public static String getPath(String path) {
        Path parentPath = Paths.get(path).getParent();
        return parentPath == null ? null : parentPath.toString();
    }

    /**
     * Crea la ruta padre indicada en el argumento recibido si no existe
     * 
     * @param filePath Un String que representa una ruta válida
     * @return Una instancia de Path con la ruta original
     * @throws IOException
     */
    public static Path initPath(String filePath) throws IOException {
        String path = getPath(filePath);
        createFolderIfNotExist(path);
        return Paths.get(filePath);
    }

    public static String readText(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        return Files.readString(path, StandardCharsets.UTF_8);
    }

    public static void _writeText(List<?> list, String fileName) throws Exception {
        initPath(fileName);
        try (FileWriter fw = new FileWriter(new File(fileName), StandardCharsets.UTF_8);
                BufferedWriter writer = new BufferedWriter(fw)) {
            for (int i = 0; i < list.size(); i++) {
                writer.append(list.get(i).toString());
                writer.newLine();
            }
        }
    }

    public static void writeText(List<?> list, String fileName) throws Exception {
        // https://www.tabnine.com/code/java/methods/java.nio.file.Files/newBufferedWriter
        Path path = initPath(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (Object o : list) {
                writer.append(o.toString());
                writer.newLine();
            }
        }
    }

    public static void writeText(String content, String fileName) throws IOException {
        Path path = initPath(fileName);
        Files.write(path, content.getBytes(StandardCharsets.UTF_8));
    }

    public static void writeJSON(List<?> list, String fileName) throws IOException {
        JSONArray jsonArray = new JSONArray(list);
        writeText(jsonArray.toString(2), fileName);
    }

    /**
     * Convierte parámetros de una URL en una representación JSON
     * 
     * @param s Algo así como param1=value1&param2=value2...
     * @return Un String JSON con los pares paramX=valueX de s
     * @throws IOException
     */
    public static String paramsToJson(String s) throws IOException {
        s = s.replace("&", "\n");
        StringReader reader = new StringReader(s);
        Properties properties = new Properties();
        properties.load(reader);
        return Property.toJSONObject(properties).toString(2);
    }

    /**
     * Convierte un número par de strings en una representación json {key:value,
     * ...}
     * 
     * @param strings los strings (en número par) que se convierten a json
     * @return Un String JSON con los pares key=value de strings
     */
    public static JSONObject keyValueToJson(String... strings) {
        JSONObject json = new JSONObject();
        for (int i = 0; i < strings.length; i += 2) {
            json.put(strings[i], strings[i + 1]);
        }
        return json;
    }

    public static String keyValueToStrJson(String... strings) {
        return keyValueToJson(strings).toString();
    }

    /**
     * Verifica en cualquier archivo de tipo JSON si un objeto está contenido en uno
     * de los objetos
     * JSON que conforman el array de objetos JSON contenido en el archivo.
     * 
     * @param fileName El nombre del archivo sin extensión, que contiene el array de
     *                 objetos JSON
     * @param key      La clave o atributo que identifica el objeto JSON a buscar
     *                 dentro de cada objeto
     * @param search   El objeto JSON a buscar
     * @return True si se encuentra que search alguno de los objetos del array
     * @throws Exception
     */
    public static boolean exists(String fileName, String key, JSONObject search) throws Exception {
        if (!Utils.fileExists(fileName + ".json")) {
            return false;
        }

        String data = readText(fileName + ".json");
        JSONArray jsonArrayData = new JSONArray(data);

        for (int i = 0; i < jsonArrayData.length(); i++) {
            // obtener el JSONObject del array en la iteración actual
            JSONObject jsonObj = jsonArrayData.getJSONObject(i);

            if (jsonObj.has(key)) {
                // De la instancia actual obtener el objeto JSON que se requiere verificar
                jsonObj = jsonObj.getJSONObject(key);
                // OJO >>> comparar los strings de ambos JSONObject
                if (jsonObj.toString().equals(search.toString())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Verifica en cualquier archivo de tipo JSON si un objeto con una propiedad
     * determinada, está
     * contenido en uno de los objetos JSON que conforman el array de objetos JSON
     * contenido en el archivo.
     * 
     * @param fileName El nombre del archivo sin extensión, que contiene el array de
     *                 objetos JSON
     * @param key      La clave o atributo que identifica el objeto JSON a buscar
     *                 dentro de cada objeto
     * @param search   El objeto JSON a buscar
     * @param property La clave del objeto que se usa para hacer la comparación.
     *                 Ej.: "id"
     * @return True si se encuentra que search alguno de los objetos del array
     * @throws Exception
     */
    public static boolean exists(String fileName, String key, JSONObject search, String property) throws Exception {
        if (!Utils.fileExists(fileName + ".json")) {
            return false;
        }
        String data = readText(fileName + ".json");
        JSONArray jsonArrayData = new JSONArray(data);

        for (int i = 0; i < jsonArrayData.length(); i++) {
            // obtener el JSONObject del array en la iteración actual
            JSONObject jsonObj = jsonArrayData.getJSONObject(i);

            if (jsonObj.has(key)) {
                // De la instancia actual obtener el objeto JSON que se requiere verificar
                jsonObj = jsonObj.getJSONObject(key);
                // OJO >>> utilizar una de las propiedades de los objetos para hacer la
                // comparación
                if (jsonObj.optString(property).equals(search.optString(property))) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Buscar un objeto en un archivo y escribe todos sus registros
     * en un JSONArray
     * 
     * @param filename  Nombre del archivo en el cual se buscará el objeto
     * @param atributte Atributo/Propiedad de la clave que se buscará en el objeto
     *                  deseado en el archivo. Ej: "id"
     * @param property  Propiedad que se validará si es igual al valor de la clave
     *                  encontrada. Ej: Un id = "ASDF34WE"
     * @param arr       JSONArray en el cual se escribirán los registros encontrados
     * @param keys      Clave/s que será el objeto a buscar
     * @throws Exception
     */
    public static void existsInAll(String filename, String atributte, Object property, JSONArray arr,
            String... keys) throws Exception {
        String path = Utils.PATH + filename + ".json";
        if (Utils.fileExists(path)) {
            // Obtener los objetos contenidos en el archivo
            JSONArray data = new JSONArray(Utils.readText(path));

            if (keys.length > 0) {
                // Por cada clave (podría ser "remitene", "destinatario", o solo "cliente")
                for (String key : keys) {
                    // recorrer los datos de cada objeto
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject json = data.getJSONObject(i).getJSONObject(key);
                        if (json.get(atributte).equals(property)) {
                            arr.put(data.getJSONObject(i));
                        }
                    }
                }
            } else {
                for (int i = 0; i < data.length(); i++) {
                    Object json = data.getJSONObject(i);
                    if (((JSONObject) (json)).get(atributte).equals(property)) {
                        arr.put(data.getJSONObject(i));
                    }
                }
            }
        }
    }

    public static String MD5(String s) throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(s.getBytes(), 0, s.length());
        return new BigInteger(1, m.digest()).toString(16);
    }

    // -------------------------------------------------------------------
    // Parte taller semana 9
    public static String stringOk(String key, int lenValue, JSONObject json) {
        // Valor asociado a la clave (key)
        String value = json.getString(key);

        // Si el valor no cumple con la longitud minima lanzar IllegalArgumentException
        if (value.length() < lenValue) {
            throw new IllegalArgumentException(
                    String.format("%s Se esperaban al menos %s %d %s caracteres: %s %s = '%s' %s",
                            Utils.YELLOW, Utils.RED, lenValue, Utils.YELLOW, Utils.RED, key, value, Utils.RESET));
        }

        // Si el value cumple la condición de longitud retornar valor asociado al key
        return value;
    }

    public static double doubleOk(String key, double min, double max, JSONObject json) {
        // Valor asociado a la clave (key)
        double value = json.getDouble(key);

        // Verificar que el valor asociado al "key" esté en el rango [min, max]
        if (value < min || value > max) {
            throw new IllegalArgumentException(
                    String.format(
                            "%s Se esperaba un valor entre %s %.1f y %.1f %s: %s %s = %f %s",
                            Utils.YELLOW, Utils.RED, min, max, Utils.YELLOW, Utils.RED, key, value, Utils.RESET));
        }

        // Retornar valor asociado al "key" si todo está bien
        return value;
    }

    /**
     * Buscar clientes en los archivos de la API
     * 
     * @param id    Identificador único del cliente a buscar
     * @param files Nombres de los archivos en los cuales se buscará el cliente
     * @return JSONObject. JSON con los datos de los registros en los cuales aparece
     *         el cliente
     * @throws Exception
     */
    public static JSONObject searchClients(String id, String... files) throws Exception {
        JSONArray data = new JSONArray();
        for (String filename : files) {
            if ("Caja|Sobre|Paquete|Bulto".contains(filename)) {
                Utils.existsInAll(filename, "id", id, data, "remitente", "destinatario");
            } else if (filename.equals("Mercancia")) {
                Utils.existsInAll(filename, "id", id, data, "cliente");
            }
        }
        return new JSONObject().put("message", "ok").put("data", data).put("size", data.length());
    }
}
