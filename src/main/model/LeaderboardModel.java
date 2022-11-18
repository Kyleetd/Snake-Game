package model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LeaderboardModel {

    List<LeaderboardEntry> leaderboard;
    static int MAX_SIZE = 10;

    // EFFECTS: Instantiates new leaderboard object.
    public LeaderboardModel() {
        leaderboard = new ArrayList<LeaderboardEntry>();
    }

    // MODIFIES: This.
    // EFFECTS: Creates a new leaderboard entry and adds it to leaderboard.
    //          Sorts leaderboard and removes worst score if it contains more than 10 entries.
    public void addEntry(String name, int score) {
        LeaderboardEntry newEntry = new LeaderboardEntry(name, score);
        leaderboard.add(newEntry);

        Collections.sort(leaderboard, Comparator.comparingInt(l -> l.score));
        Collections.reverse(leaderboard);
        if (leaderboard.size() > MAX_SIZE) {
            leaderboard.remove(leaderboard.size() - 1);
        }
    }

    // EFFECTS: Gets a copy of the leaderboard where leaderboard entries are converted to strings.
    public List<String> getLeaderBoard() {
        List<String> lb = new ArrayList<String>();
        for (LeaderboardEntry entry: leaderboard) {
            lb.add(entry.name + ": " + entry.score);
        }

        return lb;
    }

    // EFFECTS: Converts leaderboard to JSON.
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        for (LeaderboardEntry leaderboardEntry : leaderboard) {
            json.put(leaderboardEntry.name, leaderboardEntry.score);
        }

        return json;
    }

    // MODIFIES: This.
    // EFFECTS: Loads leaderboard state from JSON into This.
    public void loadJson(JSONObject json) {
        for (String name: json.keySet()) {
            int score = json.getInt(name);
            addEntry(name, score);
        }
    }

    // EFFECTS: Returns leaderboard (for testing).
    public List<LeaderboardEntry> getLeaderBoardForTests() {
        System.out.println(leaderboard);
        return leaderboard;
    }

    // EFFECTS: Clears leaderboard (for testing).
    public void clearLeaderboard() {
        leaderboard.clear();
    }
}
