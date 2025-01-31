package poo;

import java.time.Duration;
import java.time.LocalDate;

/**
 * @author SRM
 *         Esta clase representa una plantilla para crear "Objetos" de películas
 *         Con sus atributos como el nombre, duración, fecha de estreno, género,
 *         y el
 *         recaudo
 */

public class Pelicula {

    // atributos
    private String name; // Nombre película
    private LocalDate fechaEstreno; // Fecha estreno
    private Duration duracion; // Duración película
    private Genero genero; // Género película
    private double recaudo; // Recaudo de la película

    // constructor por defecto
    public Pelicula() {
        // crear una película con el constructor parametrizado
        this("", Duration.ZERO, LocalDate.MIN, Genero.DESCONOCIDO, 0.0);
    }

    /**
     * Constructor parametrizado
     * 
     * @param name         String nombre Película
     * @param duracion     Ejemplo: Duration.parse("PT2H10M")
     * @param fechaEstreno Ejemplo: LocalDate.parse("2005-09-14")
     * @param genero       Ejemplo: Genero.DESCONOCIDO
     * @param recaudo      Valor numérico
     */
    public Pelicula(String name, Duration duracion, LocalDate fechaEstreno, Genero genero, double recaudo) {
        // Establecer atributos pasados por parámetro
        this.setName(name);
        this.setFechaEstreno(fechaEstreno);
        this.setGenero(genero);
        this.setRecaudo(recaudo);
        this.setDuracion(duracion);
    }

    // mutador name
    public final void setName(String name) {
        this.name = name;
    }

    // accesor name
    public String getName() {
        return this.name;
    }

    // mutador duracion
    public final void setDuracion(Duration duracion) {
        this.duracion = duracion;
    }

    // accesor duracion
    public Duration getDuracion() {
        return this.duracion;
    }

    // duracion a str
    public String strDuration(Duration duracion) {
        long hh = duracion.toHours();
        long mm = duracion.toMinutesPart();
        return String.format("%02d:%02d", hh, mm);
    }

    // mutador fechaEstreno
    public final void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    // accesor fechaEstreno
    public LocalDate getFechaEstreno() {
        return this.fechaEstreno;
    }

    // mutador genero
    public final void setGenero(Genero genero) {
        this.genero = genero;
    }

    // accesorgenero
    public Genero getGenero() {
        return this.genero;
    }

    // mutador recaudo
    public final void setRecaudo(double recaudo) {
        this.recaudo = recaudo;
    }

    // accesor recaudo
    public double getRecaudo() {
        return this.recaudo;
    }

    // Imprimir informacipon de clase
    @Override
    public String toString() {
        String info = String.format("%-30s  %-6s  %s  %-18s %13.2f",
                this.name, this.fechaEstreno, this.strDuration(this.duracion), this.genero, this.recaudo);
        return info;
    }
}
