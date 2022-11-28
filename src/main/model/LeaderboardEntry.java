package model;

// Stores a name and a score as an Entry.
public class LeaderboardEntry {

    String name;
    int score;

    // EFFECTS: Creates a leaderboard entry with a given name and score.
    public LeaderboardEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }
}
