package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

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
        leaderboard.addEntry("Test", 1);
        assertEquals(1, leaderboard.leaderboard.size());

        leaderboard.addEntry("Test", 2);
        assertEquals(2, leaderboard.leaderboard.size());

        leaderboard.addEntry("Test", 3);
        assertEquals(3, leaderboard.leaderboard.size());

        leaderboard.addEntry("Test", 4);
        assertEquals(4, leaderboard.leaderboard.size());

        leaderboard.addEntry("Test", 5);
        assertEquals(5, leaderboard.leaderboard.size());

        leaderboard.addEntry("Test", 6);
        assertEquals(6, leaderboard.leaderboard.size());

        leaderboard.addEntry("Test", 7);
        assertEquals(7, leaderboard.leaderboard.size());

        leaderboard.addEntry("Test", 8);
        assertEquals(8, leaderboard.leaderboard.size());

        leaderboard.addEntry("Test", 9);
        assertEquals(9, leaderboard.leaderboard.size());

        leaderboard.addEntry("Test", 10);
        assertEquals(10, leaderboard.leaderboard.size());

        leaderboard.addEntry("Test", 11);
        assertEquals(10, leaderboard.leaderboard.size());

        int score = 11;
        for(LeaderboardEntry entry: leaderboard.leaderboard) {
            assertEquals(score, entry.score);
            score--;
        }
    }

    @Test
    public void getLeaderboardTest() {
        leaderboard.addEntry("Test1", 1);
        leaderboard.addEntry("Test2", 2);

        List<String> lb = leaderboard.getLeaderBoard();
        assertEquals(lb.get(0), "Test2: 2");
        assertEquals(lb.get(1), "Test1: 1");
    }
}
