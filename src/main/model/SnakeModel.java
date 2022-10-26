package model;

import java.util.*;

public class SnakeModel {

    int boardWidth;
    int boardHeight;
    char snakeDirection;
    Deque<Coordinate> snakeCoordinates;
    Coordinate appleCoordinate;
    int score;
    boolean isSnakeAlive;


    // EFFECTS: Constructs a snake game and sets score to 0.
    public SnakeModel(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        createNewSnake();
        createNewApple();
        score = 0;
        isSnakeAlive = true;

        char[][] boardState = new char[10][10];

        int currentRow = -1;
        int currentColumn = -1;
        for (char[] row: boardState) {
            currentRow += 1;
            for (char c : row) {
                currentColumn += 1;
                System.out.println("Here is a pair " + currentColumn + " " + currentRow);
            }
            currentColumn = -1;
        }
    }

    // REQUIRES: isSnakeAlive is true.
    // MODIFIES: This.
    // EFFECTS: Updates the game state by moving the snake forward by one space in current direction.
    //          If moving the snake causes it to move off board, sets isSnakeAlive to false.
    //          If moving the snake causes it to eat an apple, increment score and generate new apple.
    //          If moving the snake causes it to eat itself, sets isSnakeAlive to false.
    public void updateGame() {
        Coordinate newHeadCoordinate = getNextSnakeCoordinate();

        if (!isCoordinateOnBoard(newHeadCoordinate)) {
            isSnakeAlive = false;
            return;
        }

        if (ateAnApple(newHeadCoordinate)) {
            snakeCoordinates.addFirst(newHeadCoordinate);
            score += 1;
            createNewApple();
            return;
        }

        snakeCoordinates.removeLast();

        if (isCoordinateInSnake(newHeadCoordinate)) {
            isSnakeAlive = false;
            return;
        }

        snakeCoordinates.addFirst(newHeadCoordinate);
    }

    // MODIFIES: This.
    // EFFECTS: Updates the snake's direction to match the given direction.
    //          If the current direction is not perpendicular to the given direction, the direction doesn't change.
    //          If the given direction is invalid, the direction doesn't change.
    public void changeSnakeDirection(char direction) {
        if (snakeDirection == 'u' && direction == 'd') {
            return;
        }
        if (snakeDirection == 'd' && direction == 'u') {
            return;
        }
        if (snakeDirection == 'r' && direction == 'l') {
            return;
        }
        if (snakeDirection == 'l' && direction == 'r') {
            return;
        }
        if (direction != 'u' && direction != 'd' && direction != 'l' && direction != 'r') {
            return;
        }
        snakeDirection = direction;
    }

    // EFFECTS: Generates and returns a 2D char array representing the board's current state.
    //          Places snake and current apple on board.
    public char[][] getGameState() {
        char[][] boardState = new char[boardHeight][boardWidth];

        for (char[] row: boardState) {
            Arrays.fill(row, ' ');
        }

        for (Coordinate coordinate : snakeCoordinates) {
            boardState[coordinate.getY()][coordinate.getX()] = 'S';
        }

        boardState[appleCoordinate.getY()][appleCoordinate.getX()] = 'A';

        return boardState;
    }

    // EFFECTS: Returns true if the snake is dead.
    public boolean isGameOver() {
        return !isSnakeAlive;
    }

    // EFFECTS: Returns the current score.
    public int getScore() {
        return score;
    }

    // MODIFIES: This.
    // EFFECTS: Creates a new snake and places the head on the center of the board moving right.
    public void createNewSnake() {
        int middleX = (boardWidth - 1) / 2;
        int middleY = (boardHeight - 1) / 2;

        // create a snake
        snakeDirection = 'r';
        snakeCoordinates = new LinkedList<Coordinate>();
        snakeCoordinates.addLast(new Coordinate(middleX, middleY));
        snakeCoordinates.addLast(new Coordinate(middleX - 1, middleY));
        snakeCoordinates.addLast(new Coordinate(middleX - 2, middleY));
    }

    // MODIFIES: This.
    // EFFECTS: Generates a new random apple coordinate on the board that isn't inside the snake.
    public void createNewApple() {
        Random r = new Random();
        int x = r.nextInt(boardWidth);
        int y = r.nextInt(boardHeight);

        Coordinate newAppleCoordinate = new Coordinate(x, y);

        while (isCoordinateInSnake(newAppleCoordinate)) {
            x = r.nextInt(boardWidth);
            y = r.nextInt(boardHeight);
            newAppleCoordinate = new Coordinate(x, y);
        }
        appleCoordinate = newAppleCoordinate;
    }

    // EFFECTS: Returns true if a given coordinate overlaps with any part of the snake.
    public boolean isCoordinateInSnake(Coordinate c) {
        for (Coordinate coordinate : snakeCoordinates) {
            if (coordinate.equals(c)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: Generates and returns new snake head based on current snake head and direction.
    public Coordinate getNextSnakeCoordinate() {
        Coordinate snakeHead = snakeCoordinates.getFirst();
        Coordinate nextSnakeHead = null;
        if (snakeDirection == 'u') {
            nextSnakeHead = new Coordinate(snakeHead.getX(), snakeHead.getY() - 1);
        } else if (snakeDirection == 'd') {
            nextSnakeHead = new Coordinate(snakeHead.getX(), snakeHead.getY() + 1);
        } else if (snakeDirection == 'r') {
            nextSnakeHead = new Coordinate(snakeHead.getX() + 1, snakeHead.getY());
        } else if (snakeDirection == 'l') {
            nextSnakeHead = new Coordinate(snakeHead.getX() - 1, snakeHead.getY());
        }
        return nextSnakeHead;
    }

    // EFFECTS: Returns true if a given coordinate is on the board.
    public boolean isCoordinateOnBoard(Coordinate c) {
        if (0 <= c.getX() && c.getX() < boardWidth && 0 <= c.getY() && c.getY() < boardHeight) {
            return true;
        }
        return false;
    }

    // EFFECTS: Returns true if a given coordinate is the same as the current apple's coordinate.
    public boolean ateAnApple(Coordinate c) {
        return appleCoordinate.equals(c);
    }

//    public void toJson() {
//
//    }
//
//    public void loadJson(String str) {
//
//    }
}
