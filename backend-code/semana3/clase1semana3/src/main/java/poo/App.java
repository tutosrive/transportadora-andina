package poo;

public class App {
    public static void main(String[] args) {
        System.out.println("Inicio del programa: \n");

        // Ejemplo de como usar Figura() sin errores
        Figura f1 = new Cuadrado(); // la Figura no se puede instanciar, pero si sus subclases

        // Figura f1 = new Figura(); // Nos da error, no se puede crear instancias

        // Objeto Cuadrado
        Cuadrado c1 = new Cuadrado();
        // Área Cuadrado
        double areaSquare = c1.calcularArea(4);

        // Objeto Rectángulo
        Reactangulo r1 = new Reactangulo();
        // Área Rectángulo
        double areaRectangle = r1.calcularArea(3, 2);

        // Objeto Paralelograma
        Paralelograma paralel1 = new Paralelograma();
        // Área Paralelograma
        double areaParalelograma = paralel1.calcularArea(6, 3);

        // Objeto Rombo
        Rombo rombo1 = new Rombo();
        // Área Rombo
        double areaRombo = rombo1.calcularArea(7, 5);

        // Objeto Triángulo Rectángulo
        Triangulo tri1 = new Triangulo();
        // Área Triángulo Rectángulo
        double areaTriangulo = tri1.calcularArea(10, 5);

        // Objeto Trapecio
        Trapecio trap1 = new Trapecio();
        // Área Trapecio
        double areaTrapecio = trap1.calcularArea(3, 5, 2);

        // Objeto Pentágono
        Pentagono pen1 = new Pentagono();
        // Área Pentágono
        double areaPentagono = pen1.calcularArea(15);

        // Objeto Círculo
        Circulo cir1 = new Circulo();
        // Área Círculo
        double areaCirculo = cir1.calcularArea(5);

        // Salida de información (Áreas Figuras)
        System.out.println("Área Del Cuadrado: " + areaSquare);
        System.out.println("Área Del Rectángulo: " + areaRectangle);
        System.out.println("Área Paralelograma: " + areaParalelograma);
        System.out.println("Área Rombo: " + areaRombo);
        System.out.println("Área Triángulo: " + areaTriangulo);
        System.out.println("Área Trapecio: " + areaTrapecio);
        System.out.println("Área Pentágono: " + areaPentagono);
        System.out.println("Área Círculo: " + areaCirculo);

    }
}