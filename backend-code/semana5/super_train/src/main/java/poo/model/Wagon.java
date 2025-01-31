package poo.model;

public class Wagon extends RailRoadCar {
    private TypeLoad loadType;
    private double loadCapacity;

    // Constructor por defecto
    public Wagon() {
        this("Undefined", "Undefined", 0, TypeAcoplator.UNKNOWN, TypeLoad.UNKNOWN, 0.0,
                TypeVelocity.UNKNOWN);
    }

    // Constructor parametrizado
    public Wagon(String id, String creatorName, int ruedas, TypeAcoplator typeAcoplator, TypeLoad loadType,
            double loadCapacity, TypeVelocity typeVelocity) {
        // Constructor clase madre (RailRoadCar)
        super(id, creatorName, ruedas, typeAcoplator, typeVelocity);
        this.setLoadType(loadType);
        this.setLoadCapacity(loadCapacity);
    }

    // Accesor loadType
    public TypeLoad getLoadType() {
        return this.loadType;
    }

    // Mutador loadType
    public final void setLoadType(TypeLoad loadType) {
        this.loadType = loadType;
    }

    // Accesor loadCapacity
    public double getLoadCapacity() {
        return this.loadCapacity;
    }

    // Mutador loadCapacity
    public final void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
}
