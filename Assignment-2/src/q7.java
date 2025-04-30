import java.util.*;
//Problem 7: Movie Rating Aggregator (Map, List)
//Users rate movies between 1-5.
//        •	Store movie name -> list of ratings.
//•	Compute and display average rating per movie sorted descending.

public class q7 {
    private Map<String, List<Integer>> movieRatings;

    public q7() {
        movieRatings = new HashMap<>();
    }

    // Add rating for a movie
    public void addRating(String movie, int rating) {
        if (rating < 1 || rating > 5) {
            System.out.println("Invalid rating. Must be between 1 and 5.");
            return;
        }

        // If movie doesn't exist, create new list
        if (!movieRatings.containsKey(movie)) {
            movieRatings.put(movie, new ArrayList<>());
        }

        // Add rating to the movie
        movieRatings.get(movie).add(rating);
    }

    // Display average rating per movie sorted in descending order
    public void displayAverageRatings() {
        // Create a list to hold movie name and average rating
        List<MovieAverage> averages = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> entry : movieRatings.entrySet()) {
            String movie = entry.getKey();
            List<Integer> ratings = entry.getValue();

            // Calculate average
            int sum = 0;
            for (int r : ratings) {
                sum += r;
            }
            double average = (double) sum / ratings.size();

            averages.add(new MovieAverage(movie, average));
        }

        // Sort by average descending
        Collections.sort(averages, new Comparator<MovieAverage>() {
            @Override
            public int compare(MovieAverage m1, MovieAverage m2) {
                return Double.compare(m2.average, m1.average);  // descending
            }
        });

        // Print results
        System.out.println("Average ratings per movie:");
        for (MovieAverage m : averages) {
            System.out.println(m.movie + ": " + String.format("%.2f", m.average));
        }
    }

    // Helper class to store movie and its average
    static class MovieAverage {
        String movie;
        double average;

        MovieAverage(String movie, double average) {
            this.movie = movie;
            this.average = average;
        }
    }

    // Test the functionality
    public static void main(String[] args) {
        q7 aggregator = new q7();

        aggregator.addRating("Inception", 5);
        aggregator.addRating("Inception", 4);
        aggregator.addRating("Inception", 5);

        aggregator.addRating("Interstellar", 5);
        aggregator.addRating("Interstellar", 5);

        aggregator.addRating("The Matrix", 4);
        aggregator.addRating("The Matrix", 4);
        aggregator.addRating("The Matrix", 4);

        aggregator.displayAverageRatings();
    }
}
