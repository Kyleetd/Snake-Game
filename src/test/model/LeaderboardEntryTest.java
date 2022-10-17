package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LeaderboardEntryTest {

    LeaderboardEntry leaderboardEntry;

    @BeforeEach
    public void setUp() {
        leaderboardEntry = new LeaderboardEntry("Test", 6);
    }

    @Test
    public void LeaderboardEntryTest() {
        assertEquals("Test", leaderboardEntry.getName());
        assertEquals(6, leaderboardEntry.getScore());
    }
}
