# transportadora-andina
Caso de estudio Transportadora Andina - Semestre 2 Universidad de Caldas

>[!NOTE]
> Puede probarlo en [API Transportadora Andina](https://transportadora-andina.onrender.com/), aunque tiene almacenamiento efímero.

>[!NOTE]
> Puede probarlo en [API Transportadora Andina - ++](https://comprehensive-harrie-trg-670f482b.koyeb.app/), con almacenamiento CONTINUO.

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
><code> `enpoint = ["cliente", "mercancia","bulto", "sobre", "caja", "paquete"]`</code>
><code> `ID = ID del elemento`</code>

- GET: {{url}}+{endpoint}
- POST: {{url}}+{endpoint}
- DELETE {{url}}/{endpoint}/ID
- PATCH {{url}}/{endpoint}/ID; Y no olvidar los datos que se actualizarán

- _especial_:/envio/estados
