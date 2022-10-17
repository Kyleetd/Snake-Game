package controller;

import model.Model;
import view.ViewCMD;

public class ControllerCMD {

    Model model;
    ViewCMD view;
    Boolean isProgramRunning;
    Boolean isGameRunning;
    Boolean isGameOver;

    // EFFECTS: Constructs a controller.
    //          Sets isProgramRunning to be true and isGameRunning and isGameOver to be false.
    public ControllerCMD(Model model, ViewCMD view) {
        this.model = model;
        this.view = view;
        isProgramRunning = true;
        isGameRunning = false;
        isGameOver = false;
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
            view.printLeaderBoard(model.getLeaderboardModel().getLeaderBoard());
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
        view.printBoard(model.getSnakeModel().getGameState());
        view.printGameInstructions();
        String userInput = view.getUserInput();
        if (userInput.equals("u") || userInput.equals("d") || userInput.equals("r") || userInput.equals("l")) {
            model.getSnakeModel().changeSnakeDirection(userInput.charAt(0));
            model.getSnakeModel().updateGame();
            if (model.getSnakeModel().isGameOver()) {
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
        view.printGameOver(model.getSnakeModel().getScore());
        String userInput = view.getUserInput();

        model.getLeaderboardModel().addEntry(userInput, model.getSnakeModel().getScore());
        view.printLeaderBoard(model.getLeaderboardModel().getLeaderBoard());

        model.resetSnakeModel();

        isGameRunning = false;
        isGameOver = false;
    }
}
