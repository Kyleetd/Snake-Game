package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONPropertyName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for SnakeModel class
public class SnakeModelTest {

    private SnakeModel mySnakeModel;

    @BeforeEach
    public void setUp() {
        mySnakeModel = new SnakeModel();
    }

    @Test
    public void SnakeModelTest() {
        assertEquals(15, mySnakeModel.BOARD_WIDTH);
        assertEquals(15, mySnakeModel.BOARD_HEIGHT);
        Coordinate headCoordinate = new Coordinate(7, 7);
        assertTrue(mySnakeModel.snakeCoordinates.getFirst().equals(headCoordinate));
        assertEquals(0, mySnakeModel.score);
        assertTrue(mySnakeModel.isSnakeAlive);
    }

    @Test
    public void updateGameValidMoveNoApple() {
        Coordinate nextHeadCoordinate = mySnakeModel.getNextSnakeCoordinate();

        mySnakeModel.appleCoordinate = new Coordinate(1, 1);

        mySnakeModel.updateGame();

        Coordinate newTail = mySnakeModel.snakeCoordinates.getLast();
        Coordinate newHead = mySnakeModel.snakeCoordinates.getFirst();

        assertEquals(0, mySnakeModel.score);
        assertTrue(nextHeadCoordinate.equals(newHead));
        assertEquals(7, newTail.getY());
        assertEquals(6, newTail.getX());
    }

    @Test
    public void updateGameHitRightWall() {
        mySnakeModel.updateGame(); // 8
        mySnakeModel.updateGame(); // 9
        mySnakeModel.updateGame(); // 10
        mySnakeModel.updateGame(); // 11
        mySnakeModel.updateGame(); // 12
        mySnakeModel.updateGame(); // 13
        mySnakeModel.updateGame(); // 14
        assertTrue(mySnakeModel.isSnakeAlive);
        mySnakeModel.updateGame();
        assertFalse(mySnakeModel.isSnakeAlive);
    }

    @Test
    public void updateGameEatApple() {
        Coordinate nextHeadCoordinate = mySnakeModel.getNextSnakeCoordinate();
        Coordinate nextTailCoordinate = mySnakeModel.snakeCoordinates.getLast();

        mySnakeModel.appleCoordinate = new Coordinate(8, 7);

        mySnakeModel.updateGame();

        Coordinate newTail = mySnakeModel.snakeCoordinates.getLast();
        Coordinate newHead = mySnakeModel.snakeCoordinates.getFirst();

        assertEquals(1, mySnakeModel.score);
        assertTrue(nextHeadCoordinate.equals(newHead));
        assertTrue(nextTailCoordinate.equals(newTail));
    }

    @Test
    public void updateGameEatSelf() {

        mySnakeModel.appleCoordinate = new Coordinate(8, 7);
        mySnakeModel.updateGame();
        mySnakeModel.appleCoordinate = new Coordinate(9, 7);
        mySnakeModel.updateGame();

        mySnakeModel.snakeDirection = 'u';
        mySnakeModel.updateGame();

        mySnakeModel.snakeDirection = 'l';
        mySnakeModel.updateGame();

        assertFalse(mySnakeModel.isGameOver());

        mySnakeModel.snakeDirection = 'd';
        mySnakeModel.updateGame();

        assertTrue(mySnakeModel.isGameOver());
    }

    @Test
    public void ateAnAppleTest() {
        Coordinate appleCoordinate = new Coordinate(5,5);
        Coordinate notAppleCoordinate = new Coordinate(6,5);
        mySnakeModel.appleCoordinate = appleCoordinate;

        assertTrue(mySnakeModel.ateAnApple(appleCoordinate));
        assertFalse(mySnakeModel.ateAnApple(notAppleCoordinate));
    }

    @Test
    public void isCoordinateOnBoardTest() {
        Coordinate offLeftSide = new Coordinate(-1,5);
        Coordinate offRightSide = new Coordinate(15,5);
        Coordinate offTopSide = new Coordinate(5,-1);
        Coordinate offBottomSide = new Coordinate(5,15);
        Coordinate onBoard = new Coordinate(5,5);

        assertFalse(mySnakeModel.isCoordinateOnBoard(offLeftSide));
        assertFalse(mySnakeModel.isCoordinateOnBoard(offRightSide));
        assertFalse(mySnakeModel.isCoordinateOnBoard(offTopSide));
        assertFalse(mySnakeModel.isCoordinateOnBoard(offBottomSide));
        assertTrue(mySnakeModel.isCoordinateOnBoard(onBoard));
    }

    @Test
    public void getNextSnakeCoordinateTest() {
        mySnakeModel.snakeDirection = 'r';
        Coordinate expectedNextRightSnakeHead = new Coordinate(8, 7);
        assertTrue(mySnakeModel.getNextSnakeCoordinate().equals(expectedNextRightSnakeHead));

        mySnakeModel.snakeDirection = 'l';
        Coordinate expectedNextLeftSnakeHead = new Coordinate(6, 7);
        assertTrue(mySnakeModel.getNextSnakeCoordinate().equals(expectedNextLeftSnakeHead));

        mySnakeModel.snakeDirection = 'u';
        Coordinate expectedNextUpSnakeHead = new Coordinate(7, 6);
        assertTrue(mySnakeModel.getNextSnakeCoordinate().equals(expectedNextUpSnakeHead));

        mySnakeModel.snakeDirection = 'd';
        Coordinate expectedNextDownSnakeHead = new Coordinate(7, 8);
        assertTrue(mySnakeModel.getNextSnakeCoordinate().equals(expectedNextDownSnakeHead));
    }

    @Test
    public void isCoordinateInSnakeTest() {
        Coordinate snakeCoordinate = new Coordinate(7,7);
        Coordinate notSnakeCoordinate = new Coordinate(6,5);

        assertTrue(mySnakeModel.isCoordinateInSnake(snakeCoordinate));
        assertFalse(mySnakeModel.isCoordinateInSnake(notSnakeCoordinate));
    }

    @Test
    public void createNewAppleTest() {
        mySnakeModel.createNewApple();
        Coordinate appleCoordinate = mySnakeModel.appleCoordinate;

        assertTrue(mySnakeModel.isCoordinateOnBoard(appleCoordinate));
        assertFalse(mySnakeModel.isCoordinateInSnake(appleCoordinate));
    }

    @Test
    public void createNewAppleInSnakeTest() {

        mySnakeModel.snakeCoordinates.removeFirst();
        mySnakeModel.snakeCoordinates.removeFirst();
        mySnakeModel.snakeCoordinates.removeFirst();


        char[][] boardState = new char[15][15];

        int currentRow = -1;
        int currentColumn = -1;
        for (char[] row: boardState) {
            currentRow += 1;
            for (char c : row) {
                currentColumn += 1;
                //System.out.println("Here is a pair " + currentColumn + " " + currentRow);
                mySnakeModel.snakeCoordinates.add(new Coordinate(currentColumn, currentRow));
            }
            currentColumn = -1;
        }

        // removes Coordinate(0, 0)
        mySnakeModel.snakeCoordinates.removeFirst();

        mySnakeModel.createNewApple();
        Coordinate appleCoordinate = mySnakeModel.appleCoordinate;

        assertTrue(mySnakeModel.isCoordinateOnBoard(appleCoordinate));
        assertFalse(mySnakeModel.isCoordinateInSnake(appleCoordinate));
        assertTrue(appleCoordinate.equals(new Coordinate(0,0)));
    }

    @Test
    public void createNewSnakeTest() {
        mySnakeModel.createNewSnake();

        // check if snake is the same
        List<Coordinate> snake = new ArrayList<>();
        snake.add(new Coordinate(7,7));
        snake.add(new Coordinate(6,7));
        snake.add(new Coordinate(5,7));

        int idx = 0;
        for(Coordinate c: mySnakeModel.snakeCoordinates) {
            assertTrue(c.equals(snake.get(idx)));
            idx++;
        }

        // check direction
        assertEquals('r', mySnakeModel.snakeDirection);
    }

    @Test
    public void getGameStateTest() {
        // place apple so I know where it is
        mySnakeModel.appleCoordinate = new Coordinate(0, 0);
        mySnakeModel.createNewSnake();

        // create board
        char[][] board = new char[15][15];
        for (char[] row: board) {
            Arrays.fill(row, ' ');
        }

        // place apple
        board[0][0] = 'A';

        // place snake
        board[7][7] = 'S';
        board[7][6] = 'S';
        board[7][5] = 'S';

        char[][] boardState = mySnakeModel.getGameState();

        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[0].length; c++) {
                assertEquals(board[r][c], boardState[r][c]);
            }
        }
    }

    @Test
    public void changeSnakeDirectionTest() {
        // turn into self
        mySnakeModel.snakeDirection = 'r';
        mySnakeModel.changeSnakeDirection('l');
        assertEquals('r', mySnakeModel.snakeDirection);

        mySnakeModel.snakeDirection = 'l';
        mySnakeModel.changeSnakeDirection('r');
        assertEquals('l', mySnakeModel.snakeDirection);

        mySnakeModel.snakeDirection = 'u';
        mySnakeModel.changeSnakeDirection('d');
        assertEquals('u', mySnakeModel.snakeDirection);

        mySnakeModel.snakeDirection = 'd';
        mySnakeModel.changeSnakeDirection('u');
        assertEquals('d', mySnakeModel.snakeDirection);

        // right change to up or down
        mySnakeModel.snakeDirection = 'r';
        mySnakeModel.changeSnakeDirection('u');
        assertEquals('u', mySnakeModel.snakeDirection);

        mySnakeModel.snakeDirection = 'r';
        mySnakeModel.changeSnakeDirection('d');
        assertEquals('d', mySnakeModel.snakeDirection);

        // up change to left or right
        mySnakeModel.snakeDirection = 'u';
        mySnakeModel.changeSnakeDirection('l');
        assertEquals('l', mySnakeModel.snakeDirection);

        mySnakeModel.snakeDirection = 'u';
        mySnakeModel.changeSnakeDirection('r');
        assertEquals('r', mySnakeModel.snakeDirection);

        // left change to up or down
        mySnakeModel.snakeDirection = 'l';
        mySnakeModel.changeSnakeDirection('u');
        assertEquals('u', mySnakeModel.snakeDirection);

        mySnakeModel.snakeDirection = 'l';
        mySnakeModel.changeSnakeDirection('d');
        assertEquals('d', mySnakeModel.snakeDirection);

        // down change to left or right
        mySnakeModel.snakeDirection = 'd';
        mySnakeModel.changeSnakeDirection('l');
        assertEquals('l', mySnakeModel.snakeDirection);

        mySnakeModel.snakeDirection = 'd';
        mySnakeModel.changeSnakeDirection('r');
        assertEquals('r', mySnakeModel.snakeDirection);

        // invalid direction
        mySnakeModel.snakeDirection = 'r';
        mySnakeModel.changeSnakeDirection('k');
        assertEquals('r', mySnakeModel.snakeDirection);
    }

    @Test
    public void getScoreTest() {
        assertEquals(mySnakeModel.getScore(), mySnakeModel.score);
    }

    @Test
    public void toJsonTest() {
        JSONObject snakeJson = mySnakeModel.toJson();

        assertEquals(0, snakeJson.getInt("score"));

        String appleString = mySnakeModel.appleCoordinate.getX() + "," + mySnakeModel.appleCoordinate.getY();
        assertTrue(snakeJson.getString("apple").equals(appleString));

        assertTrue("r".equals(snakeJson.getString("direction")));

        String coordinateOne = "7,7";
        String coordinateTwo = "6,7";
        String coordinateThree = "5,7";

        assertTrue(coordinateOne.equals(snakeJson.getJSONArray("snake").get(0)));
        assertTrue(coordinateTwo.equals(snakeJson.getJSONArray("snake").get(1)));
        assertTrue(coordinateThree.equals(snakeJson.getJSONArray("snake").get(2)));
    }

    @Test
    public void loadJsonTest() {
        JSONObject snakeJson = new JSONObject();

        snakeJson.put("score", 100);
        snakeJson.put("apple", "1,1");

        JSONArray snakeJsonArray = new JSONArray();
        snakeJsonArray.put("9,5");
        snakeJsonArray.put("9,4");
        snakeJsonArray.put("9,3");
        snakeJsonArray.put("9,2");
        snakeJson.put("snake", snakeJsonArray);

        snakeJson.put("direction", "u");

        mySnakeModel.loadJson(snakeJson);

        assertEquals(100, mySnakeModel.score);

        assertTrue(mySnakeModel.appleCoordinate.equals(new Coordinate(1, 1)));

        assertTrue(mySnakeModel.snakeCoordinates.getFirst().equals(new Coordinate(9, 5)));
        assertTrue(mySnakeModel.snakeCoordinates.getLast().equals(new Coordinate(9, 2)));
        assertEquals(4, mySnakeModel.snakeCoordinates.size());

        char snakeDir = mySnakeModel.snakeDirection;
        String snakeDirAsString = Character.toString(snakeDir);
        assertTrue(snakeDirAsString.equals("u"));
    }

    @Test
    public void loadJsonEmptyJSonTest() {
        JSONObject snakeJson = new JSONObject();
        mySnakeModel.loadJson(snakeJson);

        assertEquals(0, mySnakeModel.score);

        char snakeDir = mySnakeModel.snakeDirection;
        String snakeDirAsString = Character.toString(snakeDir);
        assertTrue(snakeDirAsString.equals("r"));

        assertTrue(mySnakeModel.snakeCoordinates.getFirst().equals(new Coordinate(7, 7)));
        assertTrue(mySnakeModel.snakeCoordinates.getLast().equals(new Coordinate(5, 7)));
        assertEquals(3, mySnakeModel.snakeCoordinates.size());
    }

    @Test
    public void stringToCoordinateTest() {
        String coordinateAsString = "1,11";

        Coordinate coordinate = mySnakeModel.stringToCoordinate(coordinateAsString);

        assertTrue(coordinate.equals(new Coordinate(1, 11)));
    }
}
