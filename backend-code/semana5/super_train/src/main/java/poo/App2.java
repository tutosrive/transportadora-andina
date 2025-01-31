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

public class App2 {
    public static void main(String[] args) {
        // Arreglo de trenes
        ArrayList<Train> trains = new ArrayList<>();

        // Tren 1
        Train train1 = new Train();
        train1.setId("TR-001");

        // Añadir condiciones para que cumpla como "Tren Eléctrico"
        train1.getRailVehicle()
                .add(new Locomotive("LO-001", "Siemens", 8, TypeAcoplator.AUTOMATICO, TypeMotor.ELECTRICO));
        train1.getRailVehicle()
                .add(new Wagon("WG-001", "Mitsubishi", 5, TypeAcoplator.AUTOMATICO, TypeLoad.JAULA, 80,
                        TypeVelocity.B));
        train1.getRailVehicle()
                .add(new Wagon("WG-002", "Siemens", 8, TypeAcoplator.MANUAL, TypeLoad.CISTERNA, 120, TypeVelocity.B));

        // Tren 2
        Train train2 = new Train();
        train2.setId("TR-002");

        // No cumple como "Tren Eléctrico"
        train2.getRailVehicle()
                .add(new Locomotive("LO-002", "General Electric", 6, TypeAcoplator.AUTOMATICO, TypeMotor.DIESEL));
        train2.getRailVehicle()
                .add(new Wagon("WG-002", "Bombardier", 6, TypeAcoplator.MANUAL, TypeLoad.CISTERNA, 120,
                        TypeVelocity.D));
        train2.getRailVehicle()
                .add(new PassengerCar("PC-002", "CAF", 60, TypeAcoplator.MANUAL, 23, 5, TypeVelocity.B));

        // Tren 3
        Train train3 = new Train();
        train3.setId("TR-003");

        train3.getRailVehicle()
                .add(new Locomotive("LO-003", "Hitachi", 10, TypeAcoplator.AUTOMATICO, TypeMotor.ELECTRICO));
        train3.getRailVehicle()
                .add(new Wagon("WG-003", "Talgo", 5, TypeAcoplator.AUTOMATICO, TypeLoad.ABIERTO, 75, TypeVelocity.A));
        train3.getRailVehicle()
                .add(new PassengerCar("PC-003", "Alstom", 45, TypeAcoplator.AUTOMATICO, 15, 4, TypeVelocity.B));

        // Tren 4
        Train train4 = new Train();
        train4.setId("TR-004");

        train4.getRailVehicle()
                .add(new Locomotive("LO-004", "Kawasaki", 7, TypeAcoplator.AUTOMATICO, TypeMotor.ELECTRICO));
        train4.getRailVehicle()
                .add(new Wagon("WG-004", "Bombardier", 4, TypeAcoplator.MANUAL, TypeLoad.JAULA, 90, TypeVelocity.B));
        train4.getRailVehicle()
                .add(new PassengerCar("PC-004", "Hyundai Rotem", 55, TypeAcoplator.AUTOMATICO, 17, 4, TypeVelocity.A));

        // Tren 5
        Train train5 = new Train();
        train5.setId("TR-005");

        train5.getRailVehicle()
                .add(new Locomotive("LO-005", "Siemens", 6, TypeAcoplator.AUTOMATICO, TypeMotor.DIESEL));
        train5.getRailVehicle()
                .add(new Wagon("WG-005", "CAF", 6, TypeAcoplator.AUTOMATICO, TypeLoad.CISTERNA, 100, TypeVelocity.D));
        train5.getRailVehicle()
                .add(new PassengerCar("PC-005", "Bombardier", 48, TypeAcoplator.AUTOMATICO, 10, 4, TypeVelocity.B));

        // Tren 6
        Train train6 = new Train();
        train6.setId("TR-006");

        train6.getRailVehicle()
                .add(new Locomotive("LO-006", "Hitachi", 9, TypeAcoplator.AUTOMATICO, TypeMotor.ELECTRICO));
        train6.getRailVehicle()
                .add(new Wagon("WG-006", "Mitsubishi", 6, TypeAcoplator.MANUAL, TypeLoad.ABIERTO, 110, TypeVelocity.B));
        train6.getRailVehicle()
                .add(new PassengerCar("PC-006", "Alstom", 50, TypeAcoplator.AUTOMATICO, 12, 4, TypeVelocity.A));

        // Tren 7
        Train train7 = new Train();
        train7.setId("TR-007");

        train7.getRailVehicle()
                .add(new Locomotive("LO-007", "Hyundai Rotem", 8, TypeAcoplator.AUTOMATICO, TypeMotor.ELECTRICO));
        train7.getRailVehicle()
                .add(new Wagon("WG-007", "Talgo", 4, TypeAcoplator.AUTOMATICO, TypeLoad.JAULA, 95,
                        TypeVelocity.B));
        train7.getRailVehicle()
                .add(new PassengerCar("PC-007", "CAF", 58, TypeAcoplator.MANUAL, 14, 4, TypeVelocity.A));
        train7.getRailVehicle()
                .add(new PassengerCar("PC-008", "FAC", 58, TypeAcoplator.MANUAL, 14, 4, TypeVelocity.A));
        train7.getRailVehicle()
                .add(new PassengerCar("PC-009", "Alstom", 58, TypeAcoplator.MANUAL, 14, 4, TypeVelocity.A));
        train7.getRailVehicle()
                .add(new PassengerCar("PC-010", "Hitachi", 58, TypeAcoplator.MANUAL, 14, 4, TypeVelocity.A));

        // Añadir los trenes al arreglo
        trains.add(train1);
        trains.add(train2);
        trains.add(train3);
        trains.add(train4);
        trains.add(train5);
        trains.add(train6);
        trains.add(train7);

        filterTrains(trains);
        System.out.println("-".repeat(20));
        // Mostrar los trenes
        System.out.println(trains + "\n\n");
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
                    System.out.printf("%nTrenes Eléctricos: %s%n", train);
                } else {
                    // Caso contrario mostrar información relevante
                    System.out.printf("El tren (%s) no cumple los requerimientos para los trenes eléctricos.%n",
                            train.getId());
                }
            } else {
                // Caso contrario, imprimir información relevante
                System.out.println("Los trenes no cumplen con la capacidad permitida de Vehículos Ferroviarios");
            }
        }
    }
}
