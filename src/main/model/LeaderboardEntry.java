package model;

public class LeaderboardEntry {

    String name;
    int score;

    // EFFECTS: Creates a leaderboard entry with a given name and score
    public LeaderboardEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
