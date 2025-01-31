package poo.services;

import org.json.JSONObject;

import poo.model.Mercancia;

// Interfaz que hereda de la interfaz principla Service
public interface ServiceMercancia extends Service<Mercancia> {
    /**
     * Buscar mercancías por sus ciudad
     * 
     * @param city Ciudad a buscar
     * @return JSON con las mercancías que coinciden
     */
    public JSONObject searchByCity(String city) throws Exception;
}
