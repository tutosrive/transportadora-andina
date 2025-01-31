package poo;

public class Asignatura {
    // Atributos de la clase
    private String codigo;
    private String nombre;
    private int horaSemana;
    private int creditos;
    private boolean habilitable;

    // Constructor por defecto
    public Asignatura() {
        this("0000", "NN", 4, 3, false);
        System.out.println("Hola desde asignaturas");
    }

    // Constructor parametrizado
    public Asignatura(String codigo, String nombre, int horaSemana, int creditos, boolean habilitable) {
        this.setCodigo(codigo);
        this.setNombre(nombre);
        this.setHoraSemana(horaSemana);
        this.setCreditos(creditos);
        this.setHabilitable(habilitable);
    }

    // Accesor codigo
    public String getCodigo() {
        return this.codigo;
    }

    // Mutador codigo
    public final void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    // Accesor nombre
    public String getNombre() {
        return this.nombre;
    }

    // Mutador nombre
    public final void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Accesor horasSemana
    public int getHoraSemana() {
        return this.horaSemana;
    }

    // Mutador horasSemana
    public final void setHoraSemana(int horaSemana) {
        this.horaSemana = horaSemana;
    }

    // Accesor creditos
    public int getCreditos() {
        return this.creditos;
    }

    // Mutador creditos
    public final void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    // Accesor habilitable
    public boolean getHabilitable() {
        return this.habilitable;
    }

    // Mutador habilitable
    public final void setHabilitable(boolean habilitable) {
        this.habilitable = habilitable;
    }

    // Imprimir información de la clase
    @Override
    public String toString() {
        String habilitableStr = this.habilitable ? "Sí es habilitable" : "No es habilitable";
        String message = String.format("Código: %s\nNombre: %s\nHora Semana: %d\nCréditos: %d\nHabilitable: %s",
                this.codigo, this.nombre, this.horaSemana, this.creditos, habilitableStr);
        return message;
    }

}