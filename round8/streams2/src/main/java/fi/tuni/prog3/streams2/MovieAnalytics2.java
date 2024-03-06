package fi.tuni.prog3.streams2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * MovieAnalytics2 class provides functionality to analyze movie data.
 * It includes methods for reading movie data from a file, and for
 * generating reports based on various criteria such as director count,
 * average movie duration by genre, and average movie score by genre.
 */
public class MovieAnalytics2 {
    private List<Movie> movies;
    
    /**
     * Constructor initializes an empty movie database.
     */
    public MovieAnalytics2() {
        movies = new ArrayList<>();
    }

    
    /**
     * Reads movie data from the specified file and populates the movie database.
     * Each line in the file represents a movie with details separated by semicolons.
     *
     * @param fileName The path to the file containing movie data.
     */
    public void populateWithData(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            
            // Use streams to read lines and map them to Movie objects
            movies = reader.lines()
                    .map(line -> line.split(";"))
                    .map(parts -> new Movie(parts[0], Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2]), parts[3], Double.parseDouble(parts[4]),
                    parts[5]))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Prints the n directors with the most movies, in descending order of movie count.
     * Directors with the same movie count are ordered by their names.
     *
     * @param n The number of top directors to display.
     */
    public void printCountByDirector(int n) {
        
        // Sort and print the top n directors
        Map<String, Long> directorCounts = movies.stream()
                .collect(Collectors.groupingBy(Movie::getDirector,
                        Collectors.counting()));
        
        // Sort and print the top n directors
        directorCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().
                thenComparing(Map.Entry.comparingByKey())).limit(n)
                .forEach(entry -> System.out.println(entry.getKey() + ": " +
                        entry.getValue() + " movies"));
    }

    public void printAverageDurationByGenre() {
        
        // Group movies by genre and calculate average rating score
        Map<String, Double> averageDurations = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre, 
                        Collectors.averagingInt(Movie::getDuration)));
        
         // Sort and print genres by average duration
        averageDurations.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> System.out.println(entry.getKey() +
                        ": " + String.format("%.2f", entry.getValue())));
    }

    
    /**
     * Prints all movie genres in descending order of the average rating scores
     * of movies in that genre.Genres with the same average rating are ordered 
     * by genre name.
     */
    public void printAverageScoreByGenre() {
    // Group movies by genre and calculate average rating score
    Map<String, Double> averageScores = movies.stream()
            .collect(Collectors.groupingBy(Movie::getGenre, Collectors.
                    averagingDouble(Movie::getScore)));
    
    // Sort genres by average score in descending order and then by genre name 
    //in ascending order. This ensures that genres with the same average score
    //are ordered alphabetically
    averageScores.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed().
            thenComparing(Map.Entry.comparingByKey())).forEach(entry -> System.
            out.println(entry.getKey() + ": " + 
            String.format("%.2f", entry.getValue())));
}
}
