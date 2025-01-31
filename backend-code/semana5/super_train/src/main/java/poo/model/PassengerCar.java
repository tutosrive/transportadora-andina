package poo.model;

public class PassengerCar extends RailRoadCar {
    private int rows;
    private int columnPerRow;

    // Constructor por defecto
    public PassengerCar() {
        this("Undefined", "Undefined", 0, TypeAcoplator.UNKNOWN, 0, 0, TypeVelocity.UNKNOWN);
    }

    /**
     * Constructor parametrizado
     * 
     * @param rows    int. Cantidad de filas.
     * @param columns int. Cantidad de columnas por fila
     */
    public PassengerCar(String id, String creatorName, int ruedas, TypeAcoplator type, int rows, int columns,
            TypeVelocity typeVelocity) {
        super(id, creatorName, ruedas, type, typeVelocity);
        this.setRows(rows);
        this.setColumnPerRow(columns);
    }

    // Accesor rows
    public int getRows() {
        return this.rows;
    }

    // Mutador rows
    public final void setRows(int rows) {
        this.rows = rows;
    }

    // Accesor columnPerRow
    public int getColumnPerRow() {
        return this.columnPerRow;
    }

    // Obtener capacidad total de pasajeros
    public int getFullPassengerCapacity() {
        // Fila por columna = capacidad total permitida
        return this.rows * this.columnPerRow;
    }

    // Mutador columnPerRow
    public final void setColumnPerRow(int columnPerRow) {
        this.columnPerRow = columnPerRow;
    }
}
