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
> {{url}} = https://comprehensive-harrie-trg-670f482b.koyeb.app/
>[!NOTE]
> enpoint = ["cliente", "mercancia","bulto", "sobre", "caja", "paquete"]
>[!NOTE]
> ID = ID del elemento

- GET: {{url}}+{endpoint}
- POST: {{url}}+{endpoint}
- DELETE {{url}}/{endpoint}/ID
- PATCH {{url}}/cliente/ID; Y no olvidar los datos que se actualizarán

- _especial_:/envio/estados
