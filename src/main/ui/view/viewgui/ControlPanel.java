package ui.view.viewgui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Displays and controls buttons on side panel.
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

    // EFFECTS: Resets and reloads ControlPanel.
    public void reloadPanel() {
        this.revalidate();
        this.repaint();
    }

    // MODIFIES: This.
    // EFFECTS: Sets control panel to appropriate state when snake is moving (start button disabled).
    public void updateButtonsGameStarted() {
        disableStartButton();
        enableStopButton();
        disableQuitButton();
        reloadPanel();
    }

    // MODIFIES: This.
    // EFFECTS: Sets control panel to appropriate state when snake is not moving (stop button disabled).
    public void updateButtonsGameStopped() {
        enableStartButton();
        disableStopButton();
        enableQuitButton();
        reloadPanel();
    }

    // MODIFIES: This.
    // EFFECTS: Displays Start, Stop, and Quit buttons
    public void loadMainMenu() {
        this.removeAll();
        this.add(startButton);
        this.add(stopButton);
        this.add(quitButton);
        reloadPanel();
    }

    // MODIFIES: This.
    // EFFECTS: Displays SaveLabel with Yes and No buttons, clears text field.
    public void loadSaveMenu() {
        this.removeAll();
        this.add(saveLabel);
        this.add(saveYesButton);
        this.add(saveNoButton);
        reloadPanel();
    }

    // MODIFIES: This.
    // EFFECTS: Displays LoadLabel with Yes and No buttons
    public void loadLoadMenu() {
        this.removeAll();
        this.add(loadLabel);
        this.add(loadYesButton);
        this.add(loadNoButton);
        reloadPanel();
    }

    // MODIFIES: This.vv
    // EFFECTS: Displays EnterNameLabel with a text field and Submit button
    public void loadLeaderBoardMenu() {
        this.removeAll();
        textField.setText("");
        this.add(enterNameLabel);
        this.add(textField);
        this.add(submitNameButton);
        reloadPanel();
    }

    // MODIFIES: This.
    // EFFECTS: Displays submitToLeaderboardLabel with save or don't save options
    public void loadSaveOption() {
        this.removeAll();
        this.add(submitToLeaderboardLabel);
        this.add(saveToLeaderboardButton);
        this.add(dontSaveToLeaderboardButton);
        reloadPanel();
    }

    // MODIFIES: This.
    // EFFECTS: Disables start button.
    public void disableStartButton() {
        startButton.setEnabled(false);
        reloadPanel();
    }

    // MODIFIES: This.
    // EFFECTS: Disables stop button.
    public void disableStopButton() {
        stopButton.setEnabled(false);
        reloadPanel();
    }

    // MODIFIES: This.
    // EFFECTS: Disables quit button.
    public void disableQuitButton() {
        quitButton.setEnabled(false);
        reloadPanel();
    }

    // MODIFIES: This.
    // EFFECTS: Enables start button.
    public void enableStartButton() {
        startButton.setEnabled(true);
        reloadPanel();
    }

    // MODIFIES: This
    // EFFECTS: Enables stop button.
    public void enableStopButton() {
        stopButton.setEnabled(true);
        reloadPanel();
    }

    // MODIFIES: This.
    // EFFECTS: Enables quit button.
    public void enableQuitButton() {
        quitButton.setEnabled(true);
        reloadPanel();
    }

    // EFFECTS: Returns user's name from user input.
    public String getNameFromTextField() {
        return textField.getText();
    }

    // EFFECTS: Registers when Start button is pressed.
    public void addStartButtonListener(ActionListener listenForStartButton) {
        startButton.addActionListener(listenForStartButton);
    }

    // EFFECTS: Registers when Stop button is pressed.
    public void addStopButtonListener(ActionListener listenForStopButton) {
        stopButton.addActionListener(listenForStopButton);
    }

    // EFFECTS: Registers when Quit button is pressed.
    public void addQuitButtonListener(ActionListener listenForQuitButton) {
        quitButton.addActionListener(listenForQuitButton);
    }

    // EFFECTS: Registers when Yes button is pressed.
    public void addSaveYesButtonListener(ActionListener listenForSaveYesButton) {
        saveYesButton.addActionListener(listenForSaveYesButton);
    }

    // EFFECTS: Registers when No button is pressed.
    public void addSaveNoButtonListener(ActionListener listenForSaveNoButton) {
        saveNoButton.addActionListener(listenForSaveNoButton);
    }

    // EFFECTS: Registers when Yes button is pressed.
    public void addLoadYesButtonListener(ActionListener listenForLoadYesButton) {
        loadYesButton.addActionListener(listenForLoadYesButton);
    }

    // EFFECTS: Registers when No button is pressed.
    public void addLoadNoButtonListener(ActionListener listenForLoadNoButton) {
        loadNoButton.addActionListener(listenForLoadNoButton);
    }

    // EFFECTS: Registers when Submit button is pressed.
    public void addSubmitNameButtonListener(ActionListener listenSubmitNameButton) {
        submitNameButton.addActionListener(listenSubmitNameButton);
    }

    // EFFECTS: Registers when Save button is pressed.
    public void addSaveToLeaderboardButtonListener(ActionListener listenSaveToLeaderboardButton) {
        saveToLeaderboardButton.addActionListener(listenSaveToLeaderboardButton);
    }

    // EFFECTS: Registers when Don't Save button is pressed.
    public void addDontSaveToLeaderboardButtonListener(ActionListener listenDontSaveToLeaderboardButton) {
        dontSaveToLeaderboardButton.addActionListener(listenDontSaveToLeaderboardButton);
    }
}
