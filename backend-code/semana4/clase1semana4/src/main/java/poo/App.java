package poo;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

public class App {

        public static void main(String[] args) {
                // Arreglo de tipo Pelicula (se almacenarán las películas creadas)
                ArrayList<Pelicula> peliculas = new ArrayList<>();

                // película 1
                peliculas.add(new Pelicula("Matrix", Duration.parse("PT4H2M"), LocalDate.parse("2001-12-06"),
                                Genero.CIENCIA_FICCION, 123451.0));

                // película 2
                peliculas.add(new Pelicula("Avengers: Endgame", Duration.parse("PT3H2M"), LocalDate.parse("2019-04-26"),
                                Genero.CIENCIA_FICCION, 2798.0));
                // película 3
                peliculas.add(new Pelicula("The Dark Knight", Duration.parse("PT32H5M"), LocalDate.parse("2008-05-18"),
                                Genero.ACCION, 1005.0));
                // película 4
                peliculas.add(new Pelicula("Inception", Duration.parse("PT2H5M"), LocalDate.parse("2010-05-16"),
                                Genero.SUSPENSE, 836.0));
                // película 5
                peliculas.add(new Pelicula("Titanic", Duration.parse("PT3H25M"), LocalDate.parse("1997-12-19"),
                                Genero.DRAMA,
                                1146.0));

                // Recorrer películas
                for (Pelicula peli : peliculas) {
                        // Imprimir toString() de cada película
                        System.out.println(peli);
                }
        }
}
