# transportadora-andina
Caso de estudio Transportadora Andina - Semestre 2 Universidad de Caldas

>[!NOTE]
> **Backend**:
> Puede probarlo en
> - [API Transportadora Andina](https://transportadora-andina.onrender.com/), en RENDER.com <br>
> - [API T.A](https://alternative-marianne-pbr-e260b950.koyeb.app), en KOYEB.com <br>
>
> **Frontend**:
> Puede probarlo en [Frontend T.A](https://srm-ta.onrender.com/)
## endPoints disponibles:
- cliente
- bulto
- mercancia
- caja
- sobre

Cada uno de los anteriores además tiene:

- {endpoint}/conteo

# Verbos/Acciones disponibles:

>[!NOTE]
><code> `{{url}} = https://comprehensive-harrie-trg-670f482b.koyeb.app/`</code>
><code> `enpoint = "cliente" || "mercancia" || "bulto" || "sobre" || "caja" || "paquete"`</code>
><code> `ID = ID del elemento`</code>

- GET: {{url}}+{endpoint}
- POST: {{url}}+{endpoint}
- DELETE {{url}}/{endpoint}/ID
- PATCH {{url}}/{endpoint}/ID; Y no olvidar los datos que se actualizarán

- _especial_:/envio/estados
