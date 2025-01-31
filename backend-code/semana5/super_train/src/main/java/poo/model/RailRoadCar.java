package poo.model;

public abstract class RailRoadCar extends RailVehicle {
    private TypeVelocity velocityType;

    // Constructor por defecto
    public RailRoadCar() {
        this("Undefined", "Undefined", 0, TypeAcoplator.UNKNOWN, TypeVelocity.UNKNOWN);
    }

    // Cosntructor parametrizado
    public RailRoadCar(String id, String creatorName, int ruedas, TypeAcoplator type, TypeVelocity typeVelocity) {
        // Constructor clase madre (RailVehicle)
        super(id, creatorName, ruedas, type);
        this.setVelocityType(velocityType);
    }

    // Accesor velocityType
    public TypeVelocity getVelocityType() {
        return this.velocityType;
    }

    // Mutador velocityType
    public final void setVelocityType(TypeVelocity velocityType) {
        this.velocityType = velocityType;
    }

}
