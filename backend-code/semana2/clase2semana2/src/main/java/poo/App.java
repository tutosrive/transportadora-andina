package poo;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // Crear instancia de la clase estudiante
        // Estudiante student1 = new Estudiante();

        // // Salida de información
        // // System.out.printf("Id Estudiante: %s\n", student1.id);
        // // System.out.printf("Código Estudiante: %s\n", student1.codigo);
        // // System.out.printf("Nombre Estudiante: %s\n", student1.nombre);
        // // System.out.printf("Semestre Estudiante: %d\n", student1.semestre);
        // // System.out.printf("Estudiante Activo: %s\n", student1.activo);
        // student1.setActivo(false); // "Varvaridad"
        // System.out.println(student1); // Imprimir información acerca del usuario

        System.out.println("\nInstancia 2 de Estudiante: \n");
        Estudiante student1 = new Estudiante();
        student1.setNombre("Santiago");
        student1.setSemestre(2);
        student1.setCodigo("1234");
        student1.setActivo(true);

        System.out.println(student1);

        System.out.println("\nEstudiante 2: \n");
        // Estudiante 2
        Estudiante student2 = new Estudiante("Smith", "55296", 3, false);
        System.out.println(student2);
    }
}
