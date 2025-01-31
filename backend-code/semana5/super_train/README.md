1. Clase a ejecutar con los requerimientos pedidos

   > App.java

2. Clase con **7** trenes con diferentes condiciones, en la cual se imprime el tren cúmplase o no la restricción, esto con el fin de ver la información completa.
   > APP2.java

---

# Restricciones

1. Un _tren_ se compone de _1_ o _2_ **locomotoras**.
2. _Mínimo_ un **vehículo remolcado**.

> checkCapacityRailVehicles(): boolean

---

# Tomadas del documento [Taller Super Train](https://docs.google.com/document/d/1XR2PDiE9sG0h5eiBgT_mmHEm3Bek3INKc79_74UuwlE/edit)

- Implementar un método que **obtenga el total** de **asientos de un tren**.
- Implementar un método que _retorne_ el **listado de trenes eléctricos** que tienen **solo vagones tipo jaula**, **al menos** un **vagón tipo cisterna** y **NO** tienen **coches de pasajeros**.

---

# Posibles Funciones

1.  Un método que permita _verificar la cantidad_ de vehículos ferroviarios en la clase principal "**Train**", antes de agregar un vehículo ferroviario se debe "verificar" la capacidad permitida por tren:

          checkCapacityRailVehicles(): boolean => "El tren tiene más capacidad de la permitida" || "Vehículo Agregado"

2.  Un método que permita _ver la capacidad total de pasajeros_ en un vehículo ferroviario de tipo "PassengerCar":

            getFullPassengerCapacity(): int

3.  Un método que permita _ver la capacidad total de carga vehículo_ ferroviario de tipo "wagons":

            getLoadCapacity(): double

> By Santiago Rivera Marin (55296)
