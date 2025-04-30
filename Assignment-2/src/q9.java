import java.util.*;

//Problem 9: Leaderboard Tracker (TreeSet, Comparator)
//Track player scores using TreeSet<Player>  sorted by score descending.
//•	Update a player's score and maintain correct leaderboard order.

class Player {
    private String name;
    private int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + ": " + score;
    }
}

public class q9 {
    // TreeSet to store players sorted by score in descending order
    private TreeSet<Player> leaderboard;

    public q9() {
        leaderboard = new TreeSet<>(new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Integer.compare(p2.getScore(), p1.getScore()); // Descending order
            }
        });
    }

    // Add or update a player's score
    public void addOrUpdatePlayer(String name, int score) {
        Player newPlayer = new Player(name, score);

        // If player already exists, remove the old one and re-add with updated score
        leaderboard.remove(newPlayer);
        leaderboard.add(newPlayer);
    }

    // Print leaderboard
    public void printLeaderboard() {
        System.out.println("Leaderboard:");
        for (Player player : leaderboard) {
            System.out.println(player);
        }
    }

    public static void main(String[] args) {
        q9 tracker = new q9();

        tracker.addOrUpdatePlayer("Alice", 150);
        tracker.addOrUpdatePlayer("Bob", 200);
        tracker.addOrUpdatePlayer("Charlie", 180);

        // Update Alice's score
        tracker.addOrUpdatePlayer("Alice", 220);

        // Print the leaderboard
        tracker.printLeaderboard();
    }
}

