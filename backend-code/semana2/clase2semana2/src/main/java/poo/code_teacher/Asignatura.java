package poo.code_teacher;

public class Asignatura {
    private String codigo;
    private String nombre;
    private int horasSemana;
    private int creditos;
    private boolean habilitable;

    public Asignatura() {
    }

    public Asignatura(String codigo, String nombre, int horasSemana, int creditos, boolean habilitable) {
        setCodigo(codigo);
        setNombre(nombre);
        setHorasSemana(horasSemana);
        setCreditos(creditos);
        setHabilitable(habilitable);
    }

    public String getCodigo() {
        return codigo;
    }

    public final void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public final void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHorasSemana() {
        return horasSemana;
    }

    public final void setHorasSemana(int horasSemana) {
        this.horasSemana = horasSemana;
    }

    public int getCreditos() {
        return creditos;
    }

    public final void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public boolean getHabilitable() {
        return habilitable;
    }

    public final void setHabilitable(boolean activo) {
        this.habilitable = activo;
    }

    @Override
    public String toString() {
        return String.format("Código: %s\nNombre: %s\nHoraa: %d\nCréditos: %d\nHabilitable: %s",
                codigo,
                nombre,
                horasSemana,
                creditos,
                habilitable ? "Si" : "No");
    }

}
