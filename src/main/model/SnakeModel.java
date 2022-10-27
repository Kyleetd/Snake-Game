package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class SnakeModel {

    public static final int BOARD_WIDTH = 15;
    public static final int BOARD_HEIGHT = 15;

    char snakeDirection;
    Deque<Coordinate> snakeCoordinates;
    Coordinate appleCoordinate;
    int score;
    boolean isSnakeAlive;


    // EFFECTS: Constructs a snake game and sets score to 0.
    public SnakeModel() {
        createNewSnake();
        createNewApple();
        score = 0;
        isSnakeAlive = true;
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
        char[][] boardState = new char[BOARD_HEIGHT][BOARD_WIDTH];

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
        int middleX = BOARD_WIDTH / 2;
        int middleY = BOARD_HEIGHT / 2;

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
        int x = r.nextInt(BOARD_WIDTH);
        int y = r.nextInt(BOARD_HEIGHT);

        Coordinate newAppleCoordinate = new Coordinate(x, y);

        while (isCoordinateInSnake(newAppleCoordinate)) {
            x = r.nextInt(BOARD_WIDTH);
            y = r.nextInt(BOARD_HEIGHT);
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
        if (0 <= c.getX() && c.getX() < BOARD_WIDTH && 0 <= c.getY() && c.getY() < BOARD_HEIGHT) {
            return true;
        }
        return false;
    }

    // EFFECTS: Returns true if a given coordinate is the same as the current apple's coordinate.
    public boolean ateAnApple(Coordinate c) {
        return appleCoordinate.equals(c);
    }


    // EFFECTS: Saves a snake game to JSON.
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        // add score
        json.put("score", score);

        // add apple
        String appleString = appleCoordinate.getX() + "," + appleCoordinate.getY();
        json.put("apple", appleString);

        // add snake
        JSONArray jsonArray = new JSONArray();
        for (Coordinate coordinate : snakeCoordinates) {
            String snakeCoordinate = coordinate.getX() + "," + coordinate.getY();
            jsonArray.put(snakeCoordinate);
        }
        json.put("snake", jsonArray);

        // add direction
        json.put("direction", String.valueOf(snakeDirection));

        return json;
    }

    // MODIFIES: This.
    // EFFECTS: loads a snake game from JSON.
    public void loadJson(JSONObject json) {
        try {
            // load score
            int score = json.getInt("score");
            this.score = score;

            // load apple
            String appleAsString = json.getString("apple");
            this.appleCoordinate = stringToCoordinate(appleAsString);

            // load snake
            JSONArray snakeAsJsonArray = json.getJSONArray("snake");
            Deque<Coordinate> snakeCoordinates = new LinkedList<Coordinate>();
            for (int i = 0; i < snakeAsJsonArray.length(); i++) {
                String snakeCoordinateAsString = snakeAsJsonArray.getString(i);
                snakeCoordinates.addLast(stringToCoordinate(snakeCoordinateAsString));
            }
            this.snakeCoordinates = snakeCoordinates;

            // load direction
            String snakeDirection = json.getString("direction");
            this.snakeDirection = snakeDirection.charAt(0);

        } catch (Exception e) {
            System.out.println("No valid game state saved.");
        }
    }


    // EFFECTS: Takes a string in form "x,y", splits it at the comma, converts both parts to integers.
    //          Returns both integers as a Coordinate.
    public Coordinate stringToCoordinate(String s) {
        String[] asArray = s.split(",");
        int x = Integer.parseInt(asArray[0]);
        int y = Integer.parseInt(asArray[1]);
        return new Coordinate(x, y);
    }
}
