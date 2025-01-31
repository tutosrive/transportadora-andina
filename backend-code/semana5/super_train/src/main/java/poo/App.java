package poo;

import java.util.ArrayList;

import poo.model.Locomotive;
import poo.model.PassengerCar;
import poo.model.Train;
import poo.model.TypeAcoplator;
import poo.model.TypeLoad;
import poo.model.TypeMotor;
import poo.model.TypeVelocity;
import poo.model.Wagon;

public class App {
    public static void main(String[] args) {
        // Arreglo de trenes
        ArrayList<Train> trains = new ArrayList<>();

        // Tren 1
        Train train1 = new Train();
        train1.setId("0001");

        // Añadir condiciones para que cumpla como "Tren Eléctrico"
        train1.getRailVehicle()
                .add(new Locomotive("000", "Santiago", 6, TypeAcoplator.AUTOMATICO, TypeMotor.ELECTRICO));

        train1.getRailVehicle()
                .add(new Wagon("0000", "SRM", 4, TypeAcoplator.AUTOMATICO, TypeLoad.JAULA, 67, TypeVelocity.A));
        train1.getRailVehicle()
                .add(new Wagon("0001", "Undefined", 8, TypeAcoplator.MANUAL, TypeLoad.CISTERNA, 120, TypeVelocity.B));

        train1.getRailVehicle().add(new PassengerCar());

        // Tren 2
        Train train2 = new Train();
        train2.setId("0002");

        // Agregar características para que no cumpla como "Tren Eléctrico"
        train2.getRailVehicle()
                .add(new Locomotive("000", "Santiago", 6, TypeAcoplator.AUTOMATICO, TypeMotor.ELECTRICO));

        train2.getRailVehicle()
                .add(new Wagon("0000", "SRM", 4, TypeAcoplator.AUTOMATICO, TypeLoad.JAULA, 67, TypeVelocity.A));
        train2.getRailVehicle()
                .add(new Wagon("0001", "Undefined", 8, TypeAcoplator.MANUAL, TypeLoad.CISTERNA, 120, TypeVelocity.B));

        train2.getRailVehicle().add(new PassengerCar());

        trains.add(train1);
        trains.add(train2);

        System.out.println(trains + "\n\n");

        filterTrains(trains);
    }

    /**
     * Filtrar trenes eléctricos
     * 
     * @param trains
     *               ArrayList<Train>. Arreglo de trenes.
     */
    public static void filterTrains(ArrayList<Train> trains) {
        // Recorrer arreglo trains
        for (Train train : trains) {
            // Por cada tren, verificar la capacidad de "Vehículos Ferroviarios"
            if (train.checkCapacityRailVehicles(train.getRailVehicle())) {
                // Si lo anterior se cumple, verificar los requerimientos "específicos"
                if (train.okRequeriments(train)) {
                    // Si se cumple, imprimir tren
                    System.out.printf("%nTrenes Eléctricos: ", train);
                } else {
                    // Caso contrario mostrar información relevante
                    System.out.printf("El tren (%s) no cumple los requerimientos para los trenes eléctricos.%n",
                            train.getId());
                }
            } else {
                // Caso contrario, imprimir información relevante
                System.out.println("Los trenes no cumplen con la capacidad permitida de Veículos Ferroviarios");
            }
        }
    }
}
