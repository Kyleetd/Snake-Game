package persistence;

import model.LeaderboardModel;
import model.SnakeModel;

import model.Coordinate;
import model.SnakeModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// DISCLAIMER: I used the class JsonWriterTest from example JsonSerializationDemo on github to build this class.
//             https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonWriterTest {

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
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLeaderboard() {
        try {
            myLeaderboard.clearLeaderboard();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLeaderboard.json");
            writer.open();
            writer.write(myLeaderboard.toJson());
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLeaderboard.json");
            myLeaderboard.loadJson(reader.read());
            assertEquals(0, myLeaderboard.getLeaderBoardForTests().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterSnake() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterSnake.json");
            writer.open();
            writer.write(mySnakeModel.toJson());
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterSnake.json");
            mySnakeModel.loadJson(reader.read());

            assertEquals(1000, mySnakeModel.getScore());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterLeaderboard() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterLeaderboard.json");
            writer.open();
            writer.write(myLeaderboard.toJson());
            writer.close();
            myLeaderboard.clearLeaderboard();

            JsonReader reader = new JsonReader("./data/testWriterLeaderboard.json");
            myLeaderboard.loadJson(reader.read());

            assertEquals(15000, myLeaderboard.getLeaderBoardForTests().get(0).getScore());
            assertEquals(1, myLeaderboard.getLeaderBoardForTests().get(1).getScore());
            assertEquals(2, myLeaderboard.getLeaderBoardForTests().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
