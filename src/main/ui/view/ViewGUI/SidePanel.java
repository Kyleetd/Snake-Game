package ui.view.ViewGUI;

import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// displays side panel with buttons and leaderboard
public class SidePanel extends JPanel {

    ControlPanel controlPanel;
    LeaderboardPanel leaderboardPanel;

    public SidePanel() {
        this.setBackground(Color.green);
        this.setPreferredSize(new Dimension(200, 200));
        this.setLayout(new GridLayout(2, 1));
        this.setFocusable(false);

        controlPanel = new ControlPanel();
        leaderboardPanel = new LeaderboardPanel();

        this.add(controlPanel);
        this.add(leaderboardPanel);
    }
}
