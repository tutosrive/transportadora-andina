package poo.code_teacher;

public class Estudiante {
    // atributos
    private String codigo;
    private String nombre;
    private int semestre;
    private boolean activo;
    private Asignatura asignaturas[];

    // constructor por defecto
    public Estudiante() {
        this("0000", "NN", 0, false, new Asignatura[2]);
    }

    // constructor parametrizado
    public Estudiante(String codigo, String nombre, int semestre, boolean activo, Asignatura asignaturas[]) {
        setCodigo(codigo);
        setNombre(nombre);
        setSemestre(semestre);
        setActivo(activo);
        setAsignaturas(asignaturas);
    }

    // accesores: getX y mutadores: setX
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

    public int getSemestre() {
        return semestre;
    }

    public final void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public boolean getActivo() {
        return activo;
    }

    public final void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Asignatura[] getAsignaturas() {
        return asignaturas;
    }

    public final void setAsignaturas(Asignatura[] asignaturas) {
        this.asignaturas = asignaturas;
    }

    private String listarAsignaturas() {
        String aux = "Asignaturas: ";
        for (Asignatura a : asignaturas) {
            if (a != null) {
                aux += a + "\n";
            }
        }
        return aux;
    }

    @Override
    public String toString() {
        String strActivo = activo ? "Si" : "No";
        return "Código: " + codigo +
                "\nNombre: " + nombre +
                "\nSemestre: " + semestre +
                "\nActivo: " + strActivo + "\n" +
                listarAsignaturas();
    }

}