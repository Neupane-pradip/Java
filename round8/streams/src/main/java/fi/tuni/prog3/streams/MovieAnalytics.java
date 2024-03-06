
package fi.tuni.prog3.streams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class MovieAnalytics {
    private List<Movie> movies;

    public MovieAnalytics() {
        movies = new ArrayList<>();
    }

    public static Consumer<Movie> showInfo() {
        return movie -> System.out.println(movie.getTitle() + " (By " + 
               movie.getDirector() + ", " + movie.getReleaseYear() + ")");
    }

    public void populateWithData(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader
        (fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                movies.add(new Movie(parts[0], Integer.parseInt(parts[1]), 
                Integer.parseInt(parts[2]), parts[3], Double.parseDouble(parts[4]),
                parts[5]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stream<Movie> moviesAfter(int year) {
        return movies.stream()
                .filter(movie -> movie.getReleaseYear() >= year)
                .sorted((m1, m2) -> m1.getReleaseYear() == m2.getReleaseYear() ?
                m1.getTitle().compareTo(m2.getTitle()) : m1.getReleaseYear() - 
                 m2.getReleaseYear());
    }

    public Stream<Movie> moviesBefore(int year) {
        return movies.stream()
                .filter(movie -> movie.getReleaseYear() <= year)
                .sorted((m1, m2) -> m1.getReleaseYear() == m2.getReleaseYear() ?
                        m1.getTitle().compareTo(m2.getTitle()) : m1.getReleaseYear()
                        - m2.getReleaseYear());
    }

    public Stream<Movie> moviesBetween(int yearA, int yearB) {
        return movies.stream()
                .filter(movie -> movie.getReleaseYear() >= yearA && movie.getReleaseYear()
                        <= yearB)
                .sorted((m1, m2) -> m1.getReleaseYear() == m2.getReleaseYear() ?
                        m1.getTitle().compareTo(m2.getTitle()) : m1.getReleaseYear()
                        - m2.getReleaseYear());
    }

    public Stream<Movie> moviesByDirector(String director) {
        return movies.stream()
                .filter(movie -> movie.getDirector().equals(director))
                .sorted((m1, m2) -> m1.getReleaseYear() == m2.getReleaseYear() ?
                        m1.getTitle().compareTo(m2.getTitle()) : m1.getReleaseYear()
                        - m2.getReleaseYear());
    }
}
