package poo.model;

import org.json.JSONObject;

public abstract class RailVehicle {
    private String id;
    private String creatorName;
    private int ruedasCant;
    private TypeAcoplator acoplatorType;

    // Accesor por defecto
    protected RailVehicle() {
        this("Undefined", "Udefined", 0, TypeAcoplator.UNKNOWN);
    }

    // Constructor parametrizado
    protected RailVehicle(String id, String creatorName, int ruedas, TypeAcoplator type) {
        this.setId(id);
        this.setCreatorName(creatorName);
        this.setRuedasCant(ruedas);
        this.setAcoplatorType(type);
    }

    // Accesor id
    public String getId() {
        return this.id;
    }

    // Mutador id
    public final void setId(String id) {
        this.id = id;
    }

    // Accesor creatorName
    public String getCreatorName() {
        return this.creatorName;
    }

    // Mutador creatorName
    public final void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    // Accesor ruedasCant
    public int getRuedasCant() {
        return this.ruedasCant;
    }

    // Mutador ruedasCant
    public final void setRuedasCant(int ruedasCant) {
        this.ruedasCant = ruedasCant;
    }

    // Accesor acoplatorType
    public TypeAcoplator getAcoplatorType() {
        return this.acoplatorType;
    }

    // Mutador acoplatorType
    public final void setAcoplatorType(TypeAcoplator acoplatorType) {
        this.acoplatorType = acoplatorType;
    }

    // Obtener nombre de clase instanciada
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    // Información de la clase en formato JSON
    @Override
    public String toString() {
        return (new JSONObject(this).toString(2));
    }
}
