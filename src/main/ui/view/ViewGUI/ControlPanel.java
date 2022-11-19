package ui.view.ViewGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// displays and controls buttons on side panel
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

    public ControlPanel() {
        this.setLayout(new GridLayout(3, 1));
        this.setFocusable(false);

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        quitButton = new JButton("Quit");

        loadLabel = new JLabel("Load Game?");
        loadYesButton = new JButton("Yes");
        loadNoButton = new JButton("No");

        saveLabel = new JLabel("Save Game?");
        saveYesButton = new JButton("Yes");
        saveNoButton = new JButton("No");

        enterNameLabel = new JLabel("Enter Name");
        textField = new JTextField();
        submitNameButton = new JButton("Submit");

        loadLoadMenu();

        disableStopButton();
    }

    public void destroyTextField() {

    }

    public void loadMainMenu() {
        this.removeAll();
        this.add(startButton);
        this.add(stopButton);
        this.add(quitButton);
    }

    public void loadSaveMenu() {
        this.removeAll();
        this.add(saveLabel);
        this.add(saveYesButton);
        this.add(saveNoButton);
    }

    public void loadLoadMenu() {
        this.removeAll();
        this.add(loadLabel);
        this.add(loadYesButton);
        this.add(loadNoButton);
    }

    public void loadLeaderBoardMenu() {
        this.removeAll();
        this.add(enterNameLabel);
        this.add(textField);
        this.add(submitNameButton);
    }

    // MODIFIES: this
    // EFFECTS: disables start button
    public void disableStartButton() {
        startButton.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: disables stop button
    public void disableStopButton() {
        stopButton.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: disables quit button
    public void disableQuitButton() {
        quitButton.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: enables start button
    public void enableStartButton() {
        startButton.setEnabled(true);
    }

    // MODIFIES: this
    // EFFECTS: enables stop button
    public void enableStopButton() {
        stopButton.setEnabled(true);
    }

    // MODIFIES: this
    // EFFECTS: enables quit button
    public void enableQuitButton() {
        quitButton.setEnabled(true);
    }

    public String getNameFromTextField() {
        return textField.getText();
    }

    // EFFECTS: registers when start button pressed
    public void addStartButtonListener(ActionListener listenForStartButton) {
        startButton.addActionListener(listenForStartButton);
    }

    // EFFECTS: registers when stop button pressed
    public void addStopButtonListener(ActionListener listenForStopButton) {
        stopButton.addActionListener(listenForStopButton);
    }

    // EFFECTS: registers when quit button pressed
    public void addQuitButtonListener(ActionListener listenForQuitButton) {
        quitButton.addActionListener(listenForQuitButton);
    }

    public void addSaveYesButtonListener(ActionListener listenForSaveYesButton) {
        saveYesButton.addActionListener(listenForSaveYesButton);
    }

    public void addSaveNoButtonListener(ActionListener listenForSaveNoButton) {
        saveNoButton.addActionListener(listenForSaveNoButton);
    }

    public void addLoadYesButtonListener(ActionListener listenForLoadYesButton) {
        loadYesButton.addActionListener(listenForLoadYesButton);
    }

    public void addLoadNoButtonListener(ActionListener listenForLoadNoButton) {
        loadNoButton.addActionListener(listenForLoadNoButton);
    }

    public void addSubmitNameButtonListener(ActionListener listenSubmitNameButton) {
        submitNameButton.addActionListener(listenSubmitNameButton);
    }
}
