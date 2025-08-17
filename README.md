# transportadora-andina
Caso de estudio Transportadora Andina - Semestre 2 Universidad de Caldas

---

<div style="display: flex; align-items: center; justify-content: center; margin: 10px 0; gap: 10px; max-height: 48px; height: 48px;">
  <a href="https://github.com/sponsors/tutosrive" target="_blank">
  <img src="https://img.shields.io/badge/Sponsor-💪🤖%20tutosrive-purple?style=for-the-badge&logo=github" alt="Sponsor me on GitHub">
</a>
<a href="https://www.buymeacoffee.com/tutosrive" style="display: inline-flex; align-items: center; text-decoration: none;">
  <img src="https://img.shields.io/badge/☕%20Buy‑me‑a‑coffee-FFDD00?style=for-the-badge&logo=buy-me-a-coffee&logoColor=black" alt="Buy me a coffee"/>
</a>
</div>

---

>[!NOTE]
> **Backend**:
> Puede probarlo en
>KOYEB.COM <br>
> - [API T.A]([https://permanent-brittni-trg-d0f0aa2b.koyeb.app](https://breezy-nedda-trg-cde2bbe6.koyeb.app/),
>
> **Frontend**:
> Puede probarlo en [Frontend T.A](https://srm-ta.onrender.com)
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
