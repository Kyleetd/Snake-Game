package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class EventLogTest {

    LeaderboardModel leaderboardModel;
    String leaderboardFilePath = "./data/testReaderEmptyLeaderboard.json";

    @BeforeEach
    public void setUp() {
        EventLog.getInstance().clear();
        leaderboardModel = new LeaderboardModel();
    }

    @Test
    public void addScoreToLeaderboardEventTest(){
        leaderboardModel.addEntry("K", 3, false);
        Iterator<Event> iterator = EventLog.getInstance().iterator();

        Event first = iterator.next();
        Event second = iterator.next();

        assertEquals(
        "Event log cleared.", first.getDescription());

        assertEquals(
                "INFO: K's score was added to leaderboard.",
                second.getDescription());
    }

    @Test
    public void clearLeaderboardEventTest(){
        leaderboardModel.addEntry("K", 3, false);
        leaderboardModel.clearLeaderboard();
        Iterator<Event> iterator = EventLog.getInstance().iterator();

        Event first = iterator.next();
        Event second = iterator.next();
        Event third = iterator.next();

        assertEquals(
                "Event log cleared.", first.getDescription());

        assertEquals(
                "INFO: K's score was added to leaderboard.",
                second.getDescription());

        assertEquals(
                "INFO: Leaderboard was cleared",
                third.getDescription());

    }

    @Test
    public void loadLeaderboardEventTest() throws IOException {
        JsonReader reader = new JsonReader(leaderboardFilePath);
        leaderboardModel.loadJson(reader.read());

        Iterator<Event> iterator = EventLog.getInstance().iterator();

        Event first = iterator.next();
        Event second = iterator.next();

        assertEquals(
                "Event log cleared.", first.getDescription());

        assertEquals(
                "INFO: Leaderboard was loaded",
                second.getDescription());
    }
}
