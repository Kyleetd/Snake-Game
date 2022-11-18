package ui.controller;

import model.LeaderboardModel;
import model.SnakeModel;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.view.ViewCMD;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ControllerCMD {

    public static final String LEADERBOARD_FILE_PATH = "./data/leaderboard.json";
    public static final String SNAKE_FILE_PATH = "./data/snake.json";

    ViewCMD view;
    Boolean isProgramRunning;
    Boolean isGameRunning;
    Boolean isGameOver;
    SnakeModel snakeModel;
    LeaderboardModel leaderboardModel;

    // EFFECTS: Constructs a controller.
    //          Sets isProgramRunning to be true and isGameRunning and isGameOver to be false.
    public ControllerCMD(SnakeModel snakeModel, LeaderboardModel leaderboardModel, ViewCMD view) {
        this.view = view;
        isProgramRunning = true;
        isGameRunning = false;
        isGameOver = false;
        this.snakeModel = snakeModel;
        this.leaderboardModel = leaderboardModel;
    }

    // EFFECTS: Starts infinite game loop and displays correct output depending on program state.
    //          Reads in leaderboard and tries to read in snake if a previous state exists from JSON.
    //          Terminates loop if isProgramRunning is false.
    public void startMainLoop() throws IOException {
        readInLeaderboard(LEADERBOARD_FILE_PATH);
        readInSnake();
        while (isProgramRunning) {
            if (!isGameRunning && !isGameOver) {
                mainMenuState();
            } else if (isGameRunning && !isGameOver) {
                gameRunningState();
            } else if (isGameRunning && isGameOver) {
                gameOverState();
                writeOutLeaderboard(LEADERBOARD_FILE_PATH);
            }
        }
    }

    // MODIFIES: This.
    // EFFECTS: Displays main menu and sets game state according to the user's input.
    private void mainMenuState() throws IOException {
        view.printMainMenu();
        String userInput = view.getUserInput();
        if (userInput.equals("s")) {
            reloadPreviousState();
        } else if (userInput.equals("l")) {
            view.printLeaderBoard(leaderboardModel.getLeaderBoard());
        } else if (userInput.equals("q")) {
            isProgramRunning = false;
        } else {
            view.printInvalidInput();
        }
    }

    // MODIFIES: This.
    // EFFECTS: Prints out current state of board.
    //          Reads in new direction from user.
    //          Every game update, saves new state to JSON if game is not over.
    //          If direction is valid the snake is moved.
    //          Checks if game is over after snake is moved. If it is, isGameOver is set to true.
    private void gameRunningState() throws FileNotFoundException {
        view.printBoard(snakeModel.getGameState());
        view.printGameInstructions();
        String userInput = view.getUserInput();
        if (userInput.equals("u") || userInput.equals("d") || userInput.equals("r") || userInput.equals("l")) {
            snakeModel.changeSnakeDirection(userInput.charAt(0));
            snakeModel.updateGame();
            if (snakeModel.isGameOver()) {
                isGameOver = true;
            } else {
                writeOutSnake();
            }
        } else if (userInput.equals("q")) {
            quitGame();
            isProgramRunning = false;
        } else {
            view.printInvalidInput();
        }
    }

    private void quitGame() throws FileNotFoundException {
        System.out.println("Would you like to save your game? Type 'y' for yes or 'n' for no.");
        String userInput = view.getUserInput();
        if (userInput.equals("y")) {
            writeOutSnake();
        } else if (userInput.equals("n")) {
            resetSnakeModel();
            writeOutSnake();
        } else {
            System.out.println("Invalid input. Type 'y' for yes or 'n' for no.");
        }
    }

    // MODIFIES: This.
    // EFFECTS: Prints game over message and reads in players name from user.
    //          Adds score to leaderboard and prints out the new version of the leaderboard.
    //          Resets the snake model so the game can be played again.
    //          Writes new snake to JSON so the previous state isn't read in when game starts.
    //          Sets isGameRunning and isGameOver to false so program returns to main menu.
    private void gameOverState() throws FileNotFoundException {
        view.printGameOver(snakeModel.getScore());
        String userInput = view.getUserInput();

        leaderboardModel.addEntry(userInput, snakeModel.getScore());
        view.printLeaderBoard(leaderboardModel.getLeaderBoard());

        resetSnakeModel();
        writeOutSnake();

        isGameRunning = false;
        isGameOver = false;
    }

    private void reloadPreviousState() throws IOException {
        System.out.println("Would you like to reload your previous game? Type 'y' for yes or 'n' for no.");
        String userInput = view.getUserInput();
        if (userInput.equals("y")) {
            isGameRunning = true;
            readInSnake();
        } else if (userInput.equals("n")) {
            isGameRunning = true;
        } else {
            System.out.println("Invalid input. Type 'y' for yes or 'n' for no.");
        }
    }

    // MODIFIES: This.
    // EFFECTS: Resets the snake model.
    public void resetSnakeModel() {
        snakeModel = new SnakeModel();
    }

    // EFFECTS: Reads in leaderboard from JSON
    public void readInLeaderboard(String filePath) throws IOException {
        JsonReader reader = new JsonReader(filePath);
        leaderboardModel.loadJson(reader.read());
    }

    // EFFECTS: Stores leaderboard in JSON.
    public void writeOutLeaderboard(String filePath) throws FileNotFoundException {
        JsonWriter writer = new JsonWriter(filePath);
        writer.open();
        writer.write(leaderboardModel.toJson());
        writer.close();
    }

    // EFFECTS: Reads in snake from JSON
    public void readInSnake() throws IOException {
        JsonReader reader = new JsonReader(SNAKE_FILE_PATH);
        snakeModel.loadJson(reader.read());
    }

    // EFFECTS: Stores snake in JSON.
    public void writeOutSnake() throws FileNotFoundException {
        JsonWriter writer = new JsonWriter(SNAKE_FILE_PATH);
        writer.open();
        writer.write(snakeModel.toJson());
        writer.close();
    }
}
