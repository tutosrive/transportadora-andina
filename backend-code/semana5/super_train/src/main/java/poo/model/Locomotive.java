package poo.model;

public class Locomotive extends RailVehicle {
    private TypeMotor motorType;

    // COnstructor parametrizado
    public Locomotive() {
        this("Undefined", "Undefined", 0, TypeAcoplator.UNKNOWN, TypeMotor.UNKNOWN);
    }

    // Cosntructor parametrizado
    public Locomotive(String id, String creatorName, int ruedas, TypeAcoplator type, TypeMotor typeMotor) {
        // Constructor clase madre (RailVehicle)
        super(id, creatorName, ruedas, type);
        this.setMotorType(typeMotor);
    }

    // Accesor motorType
    public TypeMotor getMotorType() {
        return this.motorType;
    }

    // Mutador motorType
    public final void setMotorType(TypeMotor motorType) {
        this.motorType = motorType;
    }
}
