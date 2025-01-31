package poo;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // Crear instancia de la clase estudiante
        Estudiante student1 = new Estudiante();

        student1.activo = false; // "Varvaridad"
        System.out.println(student1); // Imprimir información acerca del usuario

        // Salida de información
        System.out.println("\nInstancia 2 de Estudiante: \n");

        // Nueva instancia/objeto Estudainte()
        Estudiante student2 = new Estudiante();
        student2.id = "3456"; // ID estudiante
        student2.codigo = "12345"; // Código estudiante
        student2.nombre = "Carlos"; // Nombre estudiante
        student2.semestre = 3; // Semestre estudiante
        student2.activo = false; // Estudiante activo?

        // Imprimir atributos de estudiante 2 (student2)
        System.out.println(student2);
    }
}
