# transportadora-andina
Caso de estudio Transportadora Andina - Semestre 2 Universidad de Caldas

---

<div style="display: flex; align-items: center; justify-content: center; margin: 10px 0; gap: 10px; max-height: 48px; height: 48px;">
  <a href="https://github.com/sponsors/tutosrive" target="_blank">
  <img src="https://img.shields.io/badge/Sponsor-%F0%9F%92%96%20tutosrive-orange?style=for-the-badge&logo=github" alt="Sponsor me on GitHub">
</a>
  <a href="https://www.buymeacoffee.com/tutosrive">
    <img 
      src="https://img.buymeacoffee.com/button-api/?text=Buy me a coffee&emoji=☕&slug=tutosrive&button_colour=FFDD00&font_colour=000000&font_family=Cookie&outline_colour=000000&coffee_colour=ffffff" 
      style="height: 48px; width: auto; object-fit: contain; border-radius: 6px;" 
      alt="Buy me a coffee button">
  </a>
</div>

---

>[!NOTE]
> **Backend**:
> Puede probarlo en
>KOYEB.COM <br>
> - [API T.A](https://permanent-brittni-trg-d0f0aa2b.koyeb.app),
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
