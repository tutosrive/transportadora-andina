package poo;

public class Estudiante {
    private String codigo;
    private String nombre;
    private int semestre;
    private boolean activo;

    // Constructor de la clase Estudiante
    // Por definición el constructor tiene el mismo nombre de la clase
    public Estudiante() {
        System.out.println("Hola desde clase Estudiante");
    }

    // constructor paarametrizado
    public Estudiante(String nombre, String codigo, int semestre, boolean activo) {
        this.setNombre(nombre);
        this.setCodigo(codigo);
        this.setSemestre(semestre);
        this.setActivo(activo);
    }

    // Accesor de código
    public String getCodigo() {
        return this.codigo;
    }

    // Mutador de código
    public final void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    // Accesor de nombre
    public String getNombre() {
        return this.nombre;
    }

    // Mutador de nombre
    public final void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Accesor de semestre
    public int getSemestre() {
        return this.semestre;
    }

    // Mutador de semestre
    public final void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    // Accesor activo
    public boolean getActivo() {
        return this.activo;
    }

    // Mutador activo
    public final void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        String activoStr = this.activo ? "Si" : "No";
        String res = String.format("Código: %s\nNombre: %s\nSemestre: %d\nActivo: %s", this.codigo, this.nombre,
                this.semestre, activoStr);
        return res;
    }
}
