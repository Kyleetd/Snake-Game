package persistence;

// DISCLAIMER: I used the class JsonReaderTest from example JsonSerializationDemo on github to build this class.
//             https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

import model.LeaderboardModel;
import model.SnakeModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    private SnakeModel mySnakeModel;
    private LeaderboardModel myLeaderboard;

    @BeforeEach
    public void setUp() {
        mySnakeModel = new SnakeModel();
        mySnakeModel.setScore(1000);

        myLeaderboard = new LeaderboardModel();
        myLeaderboard.addEntry("Kylee", 15000, true);
        myLeaderboard.addEntry("Lex", 1, true);
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            myLeaderboard.loadJson(reader.read());
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLeaderboard() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLeaderboard.json");
        try {
            myLeaderboard.clearLeaderboard();
            myLeaderboard.loadJson(reader.read());
            assertEquals(0, myLeaderboard.getLeaderBoardForTests().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderSnake() {
        JsonReader reader = new JsonReader("./data/testReaderSnake.json");
        try {
            mySnakeModel.loadJson(reader.read());
            assertEquals(1000, mySnakeModel.getScore());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
