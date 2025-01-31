package poo;

public class Estudiante {
    String id;
    String codigo;
    String nombre;
    int semestre;
    boolean activo;

    // Constructor de la clase Estudiante
    // Por definición el constructor tiene el mismo nombre de la clase
    public Estudiante() {
        System.out.println("Hola desde clase Estudiante");
        id = "1109780070"; // Identificación (cc, ti, etc)
        codigo = "55296"; // Código Univertsidad
        nombre = "Santiago"; // Nombre estudiante
        semestre = 2; // Semestre
        activo = true; // Estado activo
    }

    @Override
    public String toString() {
        String res = String.format("ID: %s\nCódigo: %s\nNombre: %s\nSemestre: %d\nActivo: %s", id, codigo, nombre,
                semestre, activo);
        return res;
    }
}
