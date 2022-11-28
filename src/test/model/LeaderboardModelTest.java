package model;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LeaderboardModelTest {

    LeaderboardModel leaderboard;

    @BeforeEach
    public void setUp() {
        leaderboard = new LeaderboardModel();
    }

    @Test
    public void addEntryTest() {
        leaderboard.addEntry("Test", 1, true);
        assertEquals(1, leaderboard.leaderboard.size());

        leaderboard.addEntry("Test", 2, true);
        assertEquals(2, leaderboard.leaderboard.size());

        leaderboard.addEntry("Test", 3, true);
        assertEquals(3, leaderboard.leaderboard.size());

        leaderboard.addEntry("Test", 4, true);
        assertEquals(4, leaderboard.leaderboard.size());

        leaderboard.addEntry("Test", 5, true);
        assertEquals(5, leaderboard.leaderboard.size());

        leaderboard.addEntry("Test", 6, true);
        assertEquals(6, leaderboard.leaderboard.size());

        leaderboard.addEntry("Test", 7, true);
        assertEquals(7, leaderboard.leaderboard.size());

        leaderboard.addEntry("Test", 8, true);
        assertEquals(8, leaderboard.leaderboard.size());

        leaderboard.addEntry("Test", 9, true);
        assertEquals(9, leaderboard.leaderboard.size());

        leaderboard.addEntry("Test", 10, true);
        assertEquals(10, leaderboard.leaderboard.size());

        leaderboard.addEntry("Test", 11, true);
        assertEquals(10, leaderboard.leaderboard.size());

        int score = 11;
        for(LeaderboardEntry entry: leaderboard.leaderboard) {
            assertEquals(score, entry.score);
            score--;
        }
    }

    @Test
    public void getLeaderboardTest() {
        leaderboard.addEntry("Test1", 1, true);
        leaderboard.addEntry("Test2", 2, true);

        List<String> lb = leaderboard.getLeaderBoard();
        assertEquals(lb.get(0), "Test2: 2");
        assertEquals(lb.get(1), "Test1: 1");
    }

    @Test
    public void toJsonTest() throws IOException {
        leaderboard.addEntry("Test1", 1, true);
        leaderboard.addEntry("Test2", 2, true);

        JSONObject leaderboardJson = leaderboard.toJson();

        assertEquals(1, leaderboardJson.getInt("Test1"));
        assertEquals(2, leaderboardJson.getInt("Test2"));
    }

    @Test
    public void loadJsonTest() {
        JSONObject leaderboardJson = new JSONObject();
        leaderboardJson.put("Test1", 18);
        leaderboardJson.put("Test2", 10);
        leaderboardJson.put("Test3", 1);

        leaderboard.loadJson(leaderboardJson);

        assertEquals(18, leaderboard.leaderboard.get(0).score);
        assertEquals(10, leaderboard.leaderboard.get(1).score);
        assertEquals(1, leaderboard.leaderboard.get(2).score);
        assertEquals(3, leaderboard.leaderboard.size());
    }
}
