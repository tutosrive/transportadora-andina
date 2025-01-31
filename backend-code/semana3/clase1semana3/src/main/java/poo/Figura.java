package poo;

/**
 * La clase "Figura" representa una "plantilla" de una figura cualquiera con un
 * único atributo (color de la figura).
 * 
 * Proporciona métodos como "calcularArea(int ...lados)" el cual es un método
 * abstracto y es la base para que las subclases calculen su área respectiva
 */

public abstract class Figura {
    // Atributo protegido "color" (color de figura)
    protected String color;

    /**
     * Accesor color
     * 
     * @return Retorna el 'color' de la figura
     */
    public String getColor() {
        return color;
    }

    /**
     * Mutador color
     * 
     * @param color Color de la figura
     */
    public void setColor(String color) {
        // Sobreescribe/inicializa el atributo color
        this.color = color;
    }

    /*
     * Método abstracto (no se puede "instanciar"/"llamar") para calcular el área,
     * debe implemenarse en las subclases
     */
    public abstract double calcularArea(int... lados);
}
