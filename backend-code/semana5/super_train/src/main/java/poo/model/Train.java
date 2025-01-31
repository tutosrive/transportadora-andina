package poo.model;

import java.util.ArrayList;
import org.json.JSONObject;

public class Train {
    private String id;
    private ArrayList<RailVehicle> railVehicles;

    // Constructor por defecto
    public Train() {
        railVehicles = new ArrayList<>();
    }

    /**
     * Constructor parametrizado
     * 
     * @param id          String. Identificador único del Tren
     * @param railVehicle ArrayList<RailVehicle>. Lista de "Vehículos Ferroviarios"
     */
    public Train(String id, ArrayList<RailVehicle> railVehicles) {
        this.setId(id);
        this.setRailVehicle(railVehicles);
    }

    // Accesor id
    public String getId() {
        return this.id;
    }

    // Mutador id
    public final void setId(String id) {
        this.id = id;
    }

    // Accesor railVehicles
    public ArrayList<RailVehicle> getRailVehicle() {
        return this.railVehicles;
    }

    // Mutador railVehicles
    public final void setRailVehicle(ArrayList<RailVehicle> railVehicle) {
        // Antes de agregar la lista de vehículos se verifica las restricciones
        if (checkCapacityRailVehicles(railVehicle)) {
            this.railVehicles = railVehicle;
        } else {
            System.out.println("El tren tiene más capacidad de la permitida.");
        }
    }

    // Verificar máximo de "Vehívulos" ferroviarios acoplados
    public boolean checkCapacityRailVehicles(ArrayList<RailVehicle> railVehicles) {
        int locomotiveCount = 0, railRoadCarCount = 0;

        // Recorrer lista de "vehículos" ferroviario
        for (RailVehicle vehicle : railVehicles) {
            // Obtener casos
            if (vehicle instanceof Locomotive) {
                locomotiveCount++;
            }

            if (vehicle instanceof RailRoadCar) {
                railRoadCarCount++;
            }
        }

        // Retornar true || false => si hay [1 o 2] locomotoras y 1 o más railRoad
        return locomotiveCount > 0 && locomotiveCount < 3 && railRoadCarCount > 0;
    }

    /*
     * Verificar "requerimientos"
     * 
     * trenes eléctricos que tienen solo vagones tipo jaula, al menos un vagón tipo
     * cisterna y no tienen coches de pasajeros
     */
    public boolean okRequeriments(Train train) {
        int wagonsJaulaCont = 0, wagonCisternaCont = 0, passengerCarCount = 0;
        boolean trainElectric = false;

        // Recorrer lista de "Vehículos Ferrorviarios"
        for (RailVehicle vehicle : train.getRailVehicle()) {
            // Caso instancia de Locomotora
            if (vehicle instanceof Locomotive locomotiveCasted) {
                // se verifica si es tren
                if (locomotiveCasted.getMotorType().equals(TypeMotor.ELECTRICO)) {
                    trainElectric = true;
                }
            }
            /*
             * Si es instancia de Wagon, castearlo de
             * RailVehicle a Wagon (para acceder al tipo de carga)
             */
            if (vehicle instanceof Wagon wagonCasted) {
                if (wagonCasted.getLoadType().equals(TypeLoad.JAULA)) {
                    wagonsJaulaCont++;
                } else if (wagonCasted.getLoadType().equals(TypeLoad.CISTERNA)) {
                    wagonCisternaCont++;
                }
            }
            // Vehicle es instancia de PassengerCar?
            if (vehicle instanceof PassengerCar) {
                passengerCarCount++;
            }
        }
        /*
         * Tienen vagones-jaula, vagón-cisterna y no
         * tiene Vehículos ferroviarios de pasajeros
         */
        return trainElectric && wagonsJaulaCont > 0 && wagonCisternaCont > 0 && passengerCarCount == 0;
    }

    // Obtener total de asientos de un tren
    public int getTotalPassengerCapacityTrain() {
        int passengersCapacityCount = 0;
        for (RailVehicle vehicle : railVehicles) {
            if (vehicle instanceof PassengerCar passengerCarCasted) {
                passengersCapacityCount += passengerCarCasted.getFullPassengerCapacity();
            }
        }
        // Retronar total de asientos
        return passengersCapacityCount;

    }

    // información de la clase en formato JSON
    @Override
    public String toString() {
        return (new JSONObject(this).toString(2));
    }
}