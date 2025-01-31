package poo;

public class App2 {
    public static void main(String[] args) {
        Asignatura a1 = new Asignatura("1234", "Programación 2", 6, 3, false);
        Asignatura a2 = new Asignatura("2134", "Textos Y Discursos básico", 2, 1, true);
        Asignatura a3 = new Asignatura("6754", "Razonamiento Lógico 1", 3, 2, false);

        System.out.println("Asignatura 1: \n" + a1 + "\n");
        System.out.println("Asignatura 2: \n" + a2 + "\n");
        System.out.println("Asignatura 3: \n" + a3 + "\n");
    }
}
