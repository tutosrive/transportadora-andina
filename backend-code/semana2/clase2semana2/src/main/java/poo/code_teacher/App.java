package poo.code_teacher;

public class App {

    public static void main(String[] args) {
        // Definir instancias de tipo Estudiante
        poo.code_teacher.Estudiante e1 = new poo.code_teacher.Estudiante();
        e1.setCodigo("2781");
        e1.setNombre("Luisa Fernanda García");
        e1.setSemestre(2);
        e1.setActivo(true);

        // crear un array de asignaturas
        poo.code_teacher.Asignatura[] asignaturas = new poo.code_teacher.Asignatura[2];
        asignaturas[0] = new poo.code_teacher.Asignatura("A01", "Poo", 5, 3, false);
        asignaturas[1] = new poo.code_teacher.Asignatura("A02", "Cálculo", 3, 2, true);
        // asignar el array a la estudiante
        e1.setAsignaturas(asignaturas);
        System.out.println(e1);

        poo.code_teacher.Estudiante e2 = new poo.code_teacher.Estudiante("6543", "Carlos", 3, true, asignaturas);
        poo.code_teacher.Estudiante e3 = new poo.code_teacher.Estudiante("1253", "Smith", 3, false, asignaturas);
        poo.code_teacher.Estudiante e4 = new poo.code_teacher.Estudiante("7653", "Juan", 3, true, asignaturas);
        System.out.println("Estudiante 2: \n" + e2);
        System.out.println("Estudiante 2: \n" + e3);
        System.out.println("Estudiante 2: \n" + e4);
    }

}