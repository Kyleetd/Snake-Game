package ui.controller;

import model.LeaderboardModel;
import model.SnakeModel;
import ui.view.ViewCMD;

public class ControllerCMD {

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
    //          Terminates loop if isProgramRunning is false.
    public void startMainLoop() {
        while (isProgramRunning) {
            if (!isGameRunning && !isGameOver) {
                mainMenuState();
            } else if (isGameRunning && !isGameOver) {
                gameRunningState();
            } else if (isGameRunning && isGameOver) {
                gameOverState();
            }
        }
    }

    // MODIFIES: This.
    // EFFECTS: Displays main menu and sets game state according to the user's input.
    private void mainMenuState() {
        view.printMainMenu();
        String userInput = view.getUserInput();
        if (userInput.equals("s")) {
            isGameRunning = true;
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
    //          If direction is valid the snake is moved.
    //          Checks if game is over after snake is moved. If it is, isGameOver is set to true;
    private void gameRunningState() {
        view.printBoard(snakeModel.getGameState());
        view.printGameInstructions();
        String userInput = view.getUserInput();
        if (userInput.equals("u") || userInput.equals("d") || userInput.equals("r") || userInput.equals("l")) {
            snakeModel.changeSnakeDirection(userInput.charAt(0));
            snakeModel.updateGame();
            if (snakeModel.isGameOver()) {
                isGameOver = true;
            }
        } else {
            view.printInvalidInput();
        }
    }

    // MODIFIES: This.
    // EFFECTS: Prints game over message and reads in players name from user.
    //          Adds score to leaderboard and prints out the new version of the leaderboard.
    //          Resets the snake model so the game can be played again.
    //          Sets isGameRunning and isGameOver to false so program returns to main menu.
    private void gameOverState() {
        view.printGameOver(snakeModel.getScore());
        String userInput = view.getUserInput();

        leaderboardModel.addEntry(userInput, snakeModel.getScore());
        view.printLeaderBoard(leaderboardModel.getLeaderBoard());

        resetSnakeModel();

        isGameRunning = false;
        isGameOver = false;
    }

    // MODIFIES: This.
    // EFFECTS: Resets the snake model.
    public void resetSnakeModel() {
        snakeModel = new SnakeModel(10, 10);
    }

    public SnakeModel getSnakeModel() {
        return snakeModel;
    }

    public LeaderboardModel getLeaderboardModel() {
        return leaderboardModel;
    }
}
