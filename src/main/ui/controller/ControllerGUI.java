package ui.controller;

import model.Event;
import model.EventLog;
import model.LeaderboardModel;
import model.SnakeModel;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.view.viewgui.ViewGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;


import java.util.Timer;
import java.util.TimerTask;

// Handles GUI button and key events.
public class ControllerGUI {

    public static final String SNAKE_FILE_PATH = "./data/snake.json";
    public static final String LEADERBOARD_FILE_PATH = "./data/leaderboard.json";

    public static final int GAME_SPEED = 200;

    SnakeModel snakeModel;
    LeaderboardModel leaderboardModel;
    ViewGUI viewGUI;

    Timer timer;

    char newDirection;

    public ControllerGUI(SnakeModel snakeModel, LeaderboardModel leaderboardModel, ViewGUI viewGUI) throws IOException {
        this.snakeModel = snakeModel;
        this.leaderboardModel = leaderboardModel;
        this.viewGUI = viewGUI;

        newDirection = snakeModel.getSnakeDirection();

        updateGame();
        updateLeaderboard();

        // ADD BUTTON LISTENERS
        viewGUI.getControlPanel().addStartButtonListener(new StartButtonListener());
        viewGUI.getControlPanel().addStopButtonListener(new StopButtonListener());
        viewGUI.getControlPanel().addQuitButtonListener(new QuitButtonListener());
        viewGUI.getControlPanel().addLoadYesButtonListener(new LoadYesButtonListener());
        viewGUI.getControlPanel().addLoadNoButtonListener(new LoadNoButtonListener());
        viewGUI.getControlPanel().addSaveYesButtonListener(new SaveYesButtonListener());
        viewGUI.getControlPanel().addSaveNoButtonListener(new SaveNoButtonListener());
        viewGUI.getControlPanel().addSubmitNameButtonListener(new SubmitNameButtonListener());
        viewGUI.getControlPanel().addSaveToLeaderboardButtonListener(new SaveToLeaderboardButtonListener());
        viewGUI.getControlPanel().addDontSaveToLeaderboardButtonListener(new DontSaveToLeaderboardButtonListener());
        viewGUI.getLeaderboardPanel().addLoadButtonListener(new LoadButtonListener());
        viewGUI.getLeaderboardPanel().addDontLoadButtonListener(new DontLoadButtonListener());

        // ADD KEY BINDINGS
        addKeyBindings();
    }

    public void addKeyBindings() {

        viewGUI.getControlPanel()
                .getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("UP"),"up");
        viewGUI.getControlPanel()
                .getActionMap()
                .put("up", new UpAction());

        viewGUI.getControlPanel()
                .getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("DOWN"),"down");
        viewGUI.getControlPanel()
                .getActionMap()
                .put("down", new DownAction());

        viewGUI.getControlPanel()
                .getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("LEFT"),"left");
        viewGUI.getControlPanel()
                .getActionMap()
                .put("left", new LeftAction());

        viewGUI.getControlPanel()
                .getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("RIGHT"),"right");
        viewGUI.getControlPanel()
                .getActionMap()
                .put("right", new RightAction());

    }

    // MODIFIES: SnakePanel.
    // EFFECTS: Updates game with current snake and apple position on the SnakePanel.
    private void updateGame() {
        viewGUI.getSnakePanel().updateGrid(snakeModel.getGameState());
    }

    // MODIFIES: LeaderboardPanel.
    // EFFECTS: Updates Leaderboard Panel with scores
    private void updateLeaderboard() {
        viewGUI.getLeaderboardPanel().updateLeaderboard(leaderboardModel.getLeaderBoard());
    }

    // MODIFIES: SnakeModel.
    // EFFECTS: Reads snakeModel from json file.
    private void readInSnakeModelFromFile() throws IOException {
        JsonReader jsonReader = new JsonReader(SNAKE_FILE_PATH);
        snakeModel.loadJson(jsonReader.read());
    }

    // EFFECTS: Writes snakeModel to json file.
    private void writeOutSnakeModelToFile() {
        JsonWriter jsonWriter = new JsonWriter(SNAKE_FILE_PATH);
        try {
            jsonWriter.open();
            jsonWriter.write(snakeModel.toJson());
            jsonWriter.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    // MODIFIES: LeaderboardModel.
    // EFFECTS: Reads LeaderboardModel from json file.
    private void readInLeaderboardModelFromFile() throws IOException {
        JsonReader jsonReader = new JsonReader(LEADERBOARD_FILE_PATH);
        leaderboardModel.loadJson(jsonReader.read());
    }

    // EFFECTS: Writes leaderboardModel to json file.
    private void writeOutLeaderboardModelToFile() {
        JsonWriter jsonWriterLeaderboard = new JsonWriter(LEADERBOARD_FILE_PATH);
        try {
            jsonWriterLeaderboard.open();
            jsonWriterLeaderboard.write(leaderboardModel.toJson());
            jsonWriterLeaderboard.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    // EFFECTS: Creates a timer that executes game updates at time intervals of GAME_SPEED.
    //          When this timer is running the game is running.
    private void createGameTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    snakeModel.changeSnakeDirection(newDirection);
                    snakeModel.updateGame();
                    updateGame();
                    // stop timer if snake is dead
                    if (snakeModel.isGameOver()) {
                        timer.cancel();
                        viewGUI.getControlPanel().loadSaveOption();
                        viewGUI.getControlPanel().reloadPanel();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, this.GAME_SPEED, this.GAME_SPEED);
    }

    // MODIFIES: SnakeModel, SnakePanel, This.
    // EFFECTS: Resets snake game and updates snake panel
    //          Writes new snake game to JSON so old version is over-written
    public void resetSnakeGame() {
        snakeModel = new SnakeModel();
        writeOutSnakeModelToFile();
        viewGUI.getControlPanel().loadMainMenu();
        viewGUI.getControlPanel().updateButtonsGameStopped();
        updateGame();
    }

    // EFFECTS: Prints EventLog to console.
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n\n");
        }
    }

    private class UpAction extends AbstractAction {

        // MODIFIES: This.
        // EFFECTS: Sets newDirection to up.
        @Override
        public void actionPerformed(ActionEvent e) {
            char currentDirection = snakeModel.getSnakeDirection();
            if (currentDirection != 'd') {
                newDirection = 'u';
            }
        }
    }

    private class DownAction extends AbstractAction {

        // MODIFIES: This.
        // EFFECTS: Sets newDirection to down.
        @Override
        public void actionPerformed(ActionEvent e) {
            char currentDirection = snakeModel.getSnakeDirection();
            if (currentDirection != 'u') {
                newDirection = 'd';
            }
        }
    }

    private class LeftAction extends AbstractAction {

        // MODIFIES: This.
        // EFFECTS: Sets newDirection to left.
        @Override
        public void actionPerformed(ActionEvent e) {
            char currentDirection = snakeModel.getSnakeDirection();
            if (currentDirection != 'r') {
                newDirection = 'l';
            }
        }
    }

    private class RightAction extends AbstractAction {

        // MODIFIES: This.
        // EFFECTS: Sets newDirection to right.
        @Override
        public void actionPerformed(ActionEvent e) {
            char currentDirection = snakeModel.getSnakeDirection();
            if (currentDirection != 'l') {
                newDirection = 'r';
            }
        }
    }

    class StartButtonListener implements ActionListener {

        // EFFECTS: starts game when start button is pressed
        public void actionPerformed(ActionEvent e) {
            viewGUI.getControlPanel().updateButtonsGameStarted();
            viewGUI.requestFocus();
            createGameTimer();
        }
    }

    class StopButtonListener implements ActionListener {

        // EFFECTS: Stops games, disables start button, and enables start button
        public void actionPerformed(ActionEvent e) {
            timer.cancel();
            viewGUI.getControlPanel().updateButtonsGameStopped();
        }
    }

    class QuitButtonListener implements ActionListener {

        // EFFECTS: Prompts user to save game when quit button is pressed
        public void actionPerformed(ActionEvent e) {
            viewGUI.getControlPanel().loadSaveMenu();
        }
    }

    class LoadYesButtonListener implements ActionListener {

        // EFFECTS: Loads snake game from JSON file
        public void actionPerformed(ActionEvent e) {
            try {
                readInSnakeModelFromFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            updateGame();
            newDirection = snakeModel.getSnakeDirection();
            viewGUI.getControlPanel().loadMainMenu();
        }
    }

    class LoadNoButtonListener implements ActionListener {

        // EFFECTS: Loads main menu (start, stop, quit) with start & quit button enabled
        public void actionPerformed(ActionEvent e) {
            viewGUI.getControlPanel().loadMainMenu();
        }
    }

    class SaveYesButtonListener implements ActionListener {

        // EFFECTS: Writes snake game to JSON, quits application
        public void actionPerformed(ActionEvent e) {
            writeOutSnakeModelToFile();
            printLog(EventLog.getInstance());
            System.exit(0);
        }
    }

    class SaveNoButtonListener implements ActionListener {

        // EFFECTS: Quits application
        public void actionPerformed(ActionEvent e) {
            printLog(EventLog.getInstance());
            System.exit(0);
        }
    }

    class SaveToLeaderboardButtonListener implements ActionListener {

        // EFFECTS: Loads leaderboard menu where user can input name
        public void actionPerformed(ActionEvent e) {
            viewGUI.getControlPanel().loadLeaderBoardMenu();
        }
    }

    class DontSaveToLeaderboardButtonListener implements ActionListener {

        // EFFECTS: Resets snake game.
        public void actionPerformed(ActionEvent e) {
            resetSnakeGame();
            viewGUI.getControlPanel().loadMainMenu();
            viewGUI.getControlPanel().disableStopButton();
        }
    }

    class LoadButtonListener implements ActionListener {

        // EFFECTS: Reads leaderboard from json, loads main menu
        public void actionPerformed(ActionEvent e) {
            try {
                readInLeaderboardModelFromFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            viewGUI.getLeaderboardPanel().loadLeaderboard();
            viewGUI.getLeaderboardPanel().updateLeaderboard(leaderboardModel.getLeaderBoard());
            viewGUI.getControlPanel().updateButtonsGameStopped();
        }
    }

    class DontLoadButtonListener implements ActionListener {

        // EFFECTS: Clears Leaderboard, updates Leaderboard Json file, loads Leaderboard Panel, and loads main menu.
        public void actionPerformed(ActionEvent e) {
            leaderboardModel.clearLeaderboard();
            writeOutLeaderboardModelToFile();
            viewGUI.getLeaderboardPanel().loadLeaderboard();

            viewGUI.getControlPanel().updateButtonsGameStopped();
        }
    }

    class SubmitNameButtonListener implements ActionListener {

        // MODIFIES: LeaderboardModel, LeaderboardPanel
        // EFFECTS: Takes name and scores, adds as entry to leaderboard, then writes new leaderboard to JSON
        //          Resets snake game and updates snake panel
        //          Loads main menu (start, stop, quit)
        public void actionPerformed(ActionEvent e) {
            String name = viewGUI.getControlPanel().getNameFromTextField();
            int score = snakeModel.getScore();
            leaderboardModel.addEntry(name, score, false);
            writeOutLeaderboardModelToFile();
            viewGUI.getLeaderboardPanel().updateLeaderboard(leaderboardModel.getLeaderBoard());

            resetSnakeGame();

            viewGUI.getControlPanel().loadMainMenu();
            viewGUI.getControlPanel().disableStopButton();
        }
    }
}
