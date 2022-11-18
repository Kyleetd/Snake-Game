package ui.controller;

import model.LeaderboardModel;
import model.SnakeModel;
import ui.view.ViewGUI.SnakePanel;
import ui.view.ViewGUI.ViewGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// Handles GUI button and key events
public class ControllerGUI {

    SnakeModel snakeModel;
    LeaderboardModel leaderboardModel;
    ViewGUI viewGUI;

    public ControllerGUI(SnakeModel snakeModel, LeaderboardModel leaderboardModel, ViewGUI viewGUI) {
        this.snakeModel = snakeModel;
        this.leaderboardModel = leaderboardModel;
        this.viewGUI = viewGUI;

        updateGame();
        updateLeaderboard();

        viewGUI.getControlPanel().addStartButtonListener(new StartButtonListener());
        viewGUI.getControlPanel().addStopButtonListener(new StopButtonListener());
        viewGUI.getControlPanel().addQuitButtonListener(new QuitButtonListener());

        viewGUI.getSnakePanel().requestFocus();
    }

    // MODIFIES: SnakePanel
    // EFFECTS: Updates game with current snake and apple position on the SnakePanel
    private void updateGame() {
        viewGUI.getSnakePanel().updateGrid(snakeModel.getGameState());
    }

    // MODIFIES: LeaderboardPanel
    // EFFECTS: Updates leaderboard with scores
    private void updateLeaderboard() {
        viewGUI.getLeaderboardPanel().updateLeaderboard(leaderboardModel.getLeaderBoard());
    }

    // EFFECTS: starts game when start button is pressed
    class StartButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Start");
        }
    }

    // handles stop button
    class StopButtonListener implements ActionListener {

        // EFFECTS: ends game when stop button is pressed
        public void actionPerformed(ActionEvent e) {
            System.out.println("Stop");
        }
    }

    // handles quit button
    class QuitButtonListener implements ActionListener {

        // EFFECTS: quits application
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
