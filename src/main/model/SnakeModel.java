package model;

import java.util.*;

public class SnakeModel {

    // fields
    int boardWidth;
    int boardHeight;
    char snakeDirection;
    Deque<Coordinate> snakeCoordinates;
    Coordinate appleCoordinate;
    int score;
    boolean isSnakeAlive;

    public SnakeModel(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        createNewSnake();
        createNewApple();
        score = 0;
        isSnakeAlive = true;

    }

    public void updateGame() {
        Coordinate newHeadCoordinate = getNextSnakeCoordinate();

        if (!isCoordinateOnBoard(newHeadCoordinate)) {
            isSnakeAlive = false;
            return;
        }

        snakeCoordinates.removeLast();

        if (ateAnApple(newHeadCoordinate)) {
            snakeCoordinates.addFirst(newHeadCoordinate);
            score += 1;
            createNewApple();
            return;
        }

        if (isCoordinateInSnake(newHeadCoordinate)) {
            isSnakeAlive = false;
            return;
        }

        snakeCoordinates.addFirst(newHeadCoordinate);
    }

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

    private void createNewSnake() {
        int middleX = boardWidth / 2;
        int middleY = boardHeight / 2;

        // create a snake
        snakeDirection = 'r';
        snakeCoordinates = new LinkedList<Coordinate>();
        snakeCoordinates.addLast(new Coordinate(middleX, middleY));
        snakeCoordinates.addLast(new Coordinate(middleX - 1, middleY));
        snakeCoordinates.addLast(new Coordinate(middleX - 2, middleY));
    }

    private void createNewApple() {
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

    private boolean isCoordinateInSnake(Coordinate c) {
        for (Coordinate coordinate : snakeCoordinates) {
            if (coordinate.equals(c)) {
                return true;
            }
        }
        return false;
    }

    private Coordinate getNextSnakeCoordinate() {
        Coordinate snakeHead = snakeCoordinates.getFirst();
        Coordinate nextSnakeHead = null;
        if (snakeDirection == 'u') {
            nextSnakeHead = new Coordinate(snakeHead.getX(), snakeHead.getY() + 1);
        } else if (snakeDirection == 'd') {
            nextSnakeHead = new Coordinate(snakeHead.getX(), snakeHead.getY() - 1);
        } else if (snakeDirection == 'r') {
            nextSnakeHead = new Coordinate(snakeHead.getX() + 1, snakeHead.getY());
        } else if (snakeDirection == 'l') {
            nextSnakeHead = new Coordinate(snakeHead.getX() - 1, snakeHead.getY());
        }
        return nextSnakeHead;
    }

    private boolean isCoordinateOnBoard(Coordinate c) {
        if (0 <= c.getX() && c.getX() < boardWidth && 0 <= c.getY() && c.getY() < boardHeight) {
            return true;
        }
        return false;
    }

    private boolean ateAnApple(Coordinate c) {
        if (appleCoordinate.equals(c)) {
            return true;
        }
        return false;
    }
}
