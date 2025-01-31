package poo.services;

import org.json.JSONObject;

import poo.model.Cliente;
import poo.model.Envio;

public class SobreService extends EnvioService {

    public SobreService(Class<? extends Envio> subclase, Service<Cliente> clientes) throws Exception {
        super(subclase, clientes);
    }

    @Override
    public JSONObject add(String strJson) throws Exception {
        JSONObject json = new JSONObject(strJson)
                .put("peso", 0)
                .put("valorDeclarado", 0)
                .put("fragil", false);

        // Verificar que NO tenga atributo "contenido"
        if (!json.has("contenido")) {
            // De ser así se agregrá al JSON un "contenido" = "Documentos"
            json.put("contenido", "Documentos");
        }

        // Verificar que NO tenga atributo "certificado"
        if (!json.has("certificado")) {
            // De ser así "certificado" se asignará al json en "false"
            json.put("certificado", false);
        }

        JSONObject superReturn = super.add(json.toString());
        return superReturn;
    }
}
