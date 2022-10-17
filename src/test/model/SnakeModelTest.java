package model;

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
        mySnakeModel = new SnakeModel(10, 10);
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
        Coordinate offRightSide = new Coordinate(11,5);
        Coordinate offTopSide = new Coordinate(5,-1);
        Coordinate offBottomSide = new Coordinate(5,11);
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
        Coordinate expectedNextRightSnakeHead = new Coordinate(6, 5);
        assertTrue(mySnakeModel.getNextSnakeCoordinate().equals(expectedNextRightSnakeHead));

        mySnakeModel.snakeDirection = 'l';
        Coordinate expectedNextLeftSnakeHead = new Coordinate(4, 5);
        assertTrue(mySnakeModel.getNextSnakeCoordinate().equals(expectedNextLeftSnakeHead));

        mySnakeModel.snakeDirection = 'u';
        Coordinate expectedNextUpSnakeHead = new Coordinate(5, 4);
        assertTrue(mySnakeModel.getNextSnakeCoordinate().equals(expectedNextUpSnakeHead));

        mySnakeModel.snakeDirection = 'd';
        Coordinate expectedNextDownSnakeHead = new Coordinate(5, 6);
        assertTrue(mySnakeModel.getNextSnakeCoordinate().equals(expectedNextDownSnakeHead));
    }

    @Test
    public void isCoordinateInSnakeTest() {
        Coordinate snakeCoordinate = new Coordinate(5,5);
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
    public void createNewSnakeTest() {
        mySnakeModel.createNewSnake();

        // check if snake is the same
        List<Coordinate> snake = new ArrayList<>();
        snake.add(new Coordinate(5,5));
        snake.add(new Coordinate(4,5));
        snake.add(new Coordinate(3,5));

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
        char[][] board = new char[10][10];
        for (char[] row: board) {
            Arrays.fill(row, ' ');
        }

        // place apple
        board[0][0] = 'A';

        // place snake
        board[5][5] = 'S';
        board[5][4] = 'S';
        board[5][3] = 'S';

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
    }
}
