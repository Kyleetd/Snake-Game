package ui.view.viewgui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Displays and controls buttons on side panel
public class ControlPanel extends JPanel {

    JButton startButton;
    JButton stopButton;
    JButton quitButton;

    JLabel loadLabel;
    JButton loadYesButton;
    JButton loadNoButton;

    JLabel saveLabel;
    JButton saveYesButton;
    JButton saveNoButton;

    JLabel enterNameLabel;
    JTextField textField;
    JButton submitNameButton;

    JLabel submitToLeaderboardLabel;
    JButton saveToLeaderboardButton;
    JButton dontSaveToLeaderboardButton;

    public ControlPanel() {
        this.setLayout(new GridLayout(3, 1));
        this.setBackground(Color.white);

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        quitButton = new JButton("Quit");

        loadLabel = new JLabel("Load Game?", SwingConstants.CENTER);
        loadYesButton = new JButton("Yes");
        loadNoButton = new JButton("No");

        saveLabel = new JLabel("Save Game?", SwingConstants.CENTER);
        saveYesButton = new JButton("Yes");
        saveNoButton = new JButton("No");

        enterNameLabel = new JLabel("Enter Name", SwingConstants.CENTER);
        textField = new JTextField();
        submitNameButton = new JButton("Submit");

        submitToLeaderboardLabel = new JLabel("Save Score to Leaderboard?", SwingConstants.CENTER);
        saveToLeaderboardButton = new JButton("Save");
        dontSaveToLeaderboardButton = new JButton("Don't Save");

        loadLoadMenu();
        disableStopButton();
        disableStartButton();
    }

    public void reloadPanel() {
        this.revalidate();
        this.repaint();
    }

    public void updateButtonsGameStarted() {
        disableStartButton();
        enableStopButton();
        disableQuitButton();
        reloadPanel();
    }

    public void updateButtonsGameStopped() {
        enableStartButton();
        disableStopButton();
        enableQuitButton();
        reloadPanel();
    }

    // EFFECTS: Displays Start, Stop, and Quit buttons
    public void loadMainMenu() {
        this.removeAll();
        this.add(startButton);
        this.add(stopButton);
        this.add(quitButton);
        reloadPanel();
    }

    // EFFECTS: Displays SaveLabel with Yes and No buttons
    public void loadSaveMenu() {
        this.removeAll();
        this.add(saveLabel);
        this.add(saveYesButton);
        this.add(saveNoButton);
        reloadPanel();
    }

    // EFFECTS: Displays LoadLabel with Yes and No buttons
    public void loadLoadMenu() {
        this.removeAll();
        this.add(loadLabel);
        this.add(loadYesButton);
        this.add(loadNoButton);
        reloadPanel();
    }

    // EFFECTS: Displays EnterNameLabel with a text field and Submit button
    public void loadLeaderBoardMenu() {
        this.removeAll();
        this.add(enterNameLabel);
        this.add(textField);
        this.add(submitNameButton);
        reloadPanel();
    }

    // EFFECTS: Displays submitToLeaderboardLabel with save or don't save options
    public void loadSaveOption() {
        this.removeAll();
        this.add(submitToLeaderboardLabel);
        this.add(saveToLeaderboardButton);
        this.add(dontSaveToLeaderboardButton);
        reloadPanel();
    }

    // MODIFIES: this
    // EFFECTS: disables start button
    public void disableStartButton() {
        startButton.setEnabled(false);
        reloadPanel();
    }

    // MODIFIES: this
    // EFFECTS: disables stop button
    public void disableStopButton() {
        stopButton.setEnabled(false);
        reloadPanel();
    }

    // MODIFIES: this
    // EFFECTS: disables quit button
    public void disableQuitButton() {
        quitButton.setEnabled(false);
        reloadPanel();
    }

    // MODIFIES: this
    // EFFECTS: enables start button
    public void enableStartButton() {
        startButton.setEnabled(true);
        reloadPanel();
    }

    // MODIFIES: this
    // EFFECTS: enables stop button
    public void enableStopButton() {
        stopButton.setEnabled(true);
        reloadPanel();
    }

    // MODIFIES: this
    // EFFECTS: enables quit button
    public void enableQuitButton() {
        quitButton.setEnabled(true);
        reloadPanel();
    }

    // EFFECTS: returns user's name from user input
    public String getNameFromTextField() {
        return textField.getText();
    }

    // EFFECTS: registers when Start button is pressed
    public void addStartButtonListener(ActionListener listenForStartButton) {
        startButton.addActionListener(listenForStartButton);
    }

    // EFFECTS: registers when Stop button is pressed
    public void addStopButtonListener(ActionListener listenForStopButton) {
        stopButton.addActionListener(listenForStopButton);
    }

    // EFFECTS: registers when Quit button is pressed
    public void addQuitButtonListener(ActionListener listenForQuitButton) {
        quitButton.addActionListener(listenForQuitButton);
    }

    // EFFECTS: registers when Yes button is pressed
    public void addSaveYesButtonListener(ActionListener listenForSaveYesButton) {
        saveYesButton.addActionListener(listenForSaveYesButton);
    }

    // EFFECTS: registers when No button is pressed
    public void addSaveNoButtonListener(ActionListener listenForSaveNoButton) {
        saveNoButton.addActionListener(listenForSaveNoButton);
    }

    // EFFECTS: registers when Yes button is pressed
    public void addLoadYesButtonListener(ActionListener listenForLoadYesButton) {
        loadYesButton.addActionListener(listenForLoadYesButton);
    }

    // EFFECTS: registers when No button is pressed
    public void addLoadNoButtonListener(ActionListener listenForLoadNoButton) {
        loadNoButton.addActionListener(listenForLoadNoButton);
    }

    // EFFECTS: registers when Submit button is pressed
    public void addSubmitNameButtonListener(ActionListener listenSubmitNameButton) {
        submitNameButton.addActionListener(listenSubmitNameButton);
    }

    // EFFECTS: registers when Save button is pressed
    public void addSaveToLeaderboardButtonListener(ActionListener listenSaveToLeaderboardButton) {
        saveToLeaderboardButton.addActionListener(listenSaveToLeaderboardButton);
    }

    // EFFECTS: registers when Don't Save button is pressed
    public void addDontSaveToLeaderboardButtonListener(ActionListener listenDontSaveToLeaderboardButton) {
        dontSaveToLeaderboardButton.addActionListener(listenDontSaveToLeaderboardButton);
    }
}
