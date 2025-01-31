# Punto 19

>Con base en el **diagrama de clases** del sistema y también de su implementación, elabore un **listado** de las posibles consultas de información que se pueden llevar a cabo a partir de los **datos** almacenados en los archivos.

| CLIENTES  | MERCANCÍAS             | ENVÍOS              |
| --------- | ---------------------- | ------------------- |
| Dirección | Hora de Ingreso/Salida | Frágil              |
| Ciudad    | Ancho/Alto/Largo       | Contenido           |
|           | Volumen m³             | Tipo de Envío       |
|           | bodega                 | Peso                |
|           |                        | Sobre (certificado) |
|           |                        | Caja (Medidas)      |

# Punto 14

- Qué relación existe entre la línea 35  de la clase App y la línea 31 de la clase Mercancias. 
  
  ```javascript
  // Línea 35 clase App (index.mjs)
  window.editRowButton = () => `<button id="edit-row" class="border-0 bg-transparent" data-bs-toggle="tooltip" title="Editar">${icons.edit}</button>`

  // Línea 31 clase Mercancias (mercancias.mjs)
  // Dentro de Tabulator "columns"
  { formatter: editRowButton, width: 40, hozAlign: 'center', cellClick: Mercancias.#editRowClick },
  ```
  - [x] La relación es que `editRowButton` es una **función anónima** la cual crea un botón con ícono y   `formatter: editRowButton` **referencia** a esta **función** _declarada_ en el _objeto **window**_ y se muestra como un "**Módoulo de formato**" (Muestra una columna con un **formato gráfico**, tal cual lo dice [Tabulator.info](https://tabulator.info/docs/6.3/format#overview))
  
- Realación entre la línea 41  de la clase App y la línea 32 de la clase Mercancias
  
  ```javascript
  // Línea 41 clase App (index.mjs)
  window.deleteButton = `${icons.deleteWhite}<span>Eliminar</span>`

  // Línea 32 clase Mercancias (mercancias.mjs)
  // Dentro de Tabulator "columns"
  { formatter: deleteRowButton, width: 40, hozAlign: 'center', cellClick: Mercancias.#deleteRowClick }
  ```
  - [x] No existe relación entre ambas líneas, porque al igual que la **línea 31** de la clase la clase **Mercancias**, la **línea 32** de la clase **Mercancias** referencia una función anónima _declarada_ en el _Objeto_ **window**, y la **línea 41** de la clase **App** es un ícono!
  
- Relación entre las líneas 34 y 56 de las mismas clases.

    ```javascript
    // línea 34 de la clase App (index.mjs)
    window.addRowButton = `<button id='add-row' class='btn btn-info btn-sm'>${icons.plusSquare}&nbsp;&nbsp;Nuevo registro</button>`

    // línea 56 de la clase Mercancias (mercancias.mjs)
    footerElement: `<div class='container-fluid d-flex justify-content-end p-0'>${addRowButton}</div>`,
    ```

    - [x] La relación existente entre ambas líneas, es que la **línea 56** añade el elemento `button` de la **línea 34** como un elemento del **pie de página** de la tabla, esto para incluir una funcionalidad extra a la tabla (_Añadir registros nuevos_).