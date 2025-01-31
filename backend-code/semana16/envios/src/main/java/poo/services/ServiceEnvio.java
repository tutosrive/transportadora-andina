package poo.services;

import org.json.JSONObject;

import poo.model.Envio;

public interface ServiceEnvio extends Service<Envio> {
    /**
     * Permite encontrar y obtener los Envíos por su "fragilidad"
     * 
     * @param fragile Boolean. true || false, será el estado de "fragilidad" que se
     *                buscará
     * @param files   String... Nombre/s del || los archivo/s en los cuales se
     *                buscará
     * @return JSONObject. Datos encontrados en formato: {"data": [JSON's], "size":
     *         length, "message": "ok"}
     * @throws Exception
     */
    public JSONObject searchByFragile(boolean fragile, String... files) throws Exception;
}
