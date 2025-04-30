import java.util.*;

//Problem 12: Voting System (Map<String, Integer>)
//Build a voting system that:
//        •	Tallies votes per candidate
//•	Sorts by vote count descending

public class q12 {
    // Map to store candidate name and their vote count
    private Map<String, Integer> voteMap;

    public q12() {
        voteMap = new HashMap<>();
    }

    // Method to cast a vote for a candidate
    public void castVote(String candidateName) {
        // If candidate exists, increase vote count, else start from 1
        if (voteMap.containsKey(candidateName)) {
            int currentVotes = voteMap.get(candidateName);
            voteMap.put(candidateName, currentVotes + 1);
        } else {
            voteMap.put(candidateName, 1);
        }
    }

    // Method to print results sorted by vote count descending
    public void printResults() {
        // Convert Map entries to a List for sorting
        List<Map.Entry<String, Integer>> resultList = new ArrayList<>(voteMap.entrySet());

        // Sort using custom comparator (descending order of vote count)
        Collections.sort(resultList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                return e2.getValue() - e1.getValue(); // descending
            }
        });

        // Print sorted results
        System.out.println("Voting Results:");
        for (Map.Entry<String, Integer> entry : resultList) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " votes");
        }
    }

    // Main method to test the voting system
    public static void main(String[] args) {
        q12 votingSystem = new q12();

        // Simulate votes
        votingSystem.castVote("Alice");
        votingSystem.castVote("Bob");
        votingSystem.castVote("Alice");
        votingSystem.castVote("Charlie");
        votingSystem.castVote("Bob");
        votingSystem.castVote("Alice");

        // Print final result
        votingSystem.printResults();
    }
}
