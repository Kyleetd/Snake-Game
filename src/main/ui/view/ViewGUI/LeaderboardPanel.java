package ui.view.ViewGUI;

import model.LeaderboardEntry;
import model.LeaderboardModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Displays and updates leaderboard
public class LeaderboardPanel extends JPanel {

    JLabel title;
    JLabel[] leaderboard;

    public LeaderboardPanel() {
        this.setFocusable(false);

        this.setLayout(new GridLayout(11, 1));

        title = new JLabel("Leaderboard");
        leaderboard = new JLabel[10];

        this.add(title);
        initializeLeaderboard();
    }

    // EFFECTS: Creates Leaderboard in LeaderboardPanel
    public void initializeLeaderboard() {
        for (int i = 0; i < leaderboard.length; i++) {
            leaderboard[i] = new JLabel();
            this.add(leaderboard[i]);
        }
    }

    // MODIFIES: This
    // EFFECTS: Adds leaderboard entries to entries panel
    public void updateLeaderboard(List<String> leaderboardEntries) {
        for (int i = 0; i < leaderboardEntries.size(); i++) {
            leaderboard[i].setText(leaderboardEntries.get(i));
        }
    }
}
