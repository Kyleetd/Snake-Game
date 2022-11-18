package ui.view.ViewGUI;

import model.LeaderboardEntry;
import model.LeaderboardModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// displays side panel with buttons and leaderboard
public class LeaderboardPanel extends JPanel {

    JPanel title;
    JPanel entries;

    public LeaderboardPanel() {
        //this.setLayout(new GridLayout(2,1));
        this.setBackground(Color.lightGray);
        this.setFocusable(false);

        title = new JPanel();
        entries = new JPanel(new GridLayout(10, 1, 0, 0));


        this.add(title, BorderLayout.NORTH);
        title.setPreferredSize(new Dimension(200, 40));

        this.add(entries, BorderLayout.CENTER);
        entries.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        entries.setPreferredSize(new Dimension(200, 350));

        //initializeRows();
        displayLeaderboardTitle();
    }

    public void initializeRows() {
        for (int i = 0; i < 10; i++) {
            JPanel lbEntry = new JPanel();
            lbEntry.setPreferredSize(new Dimension(0, 100));
            lbEntry.setBorder(BorderFactory.createLineBorder(Color.black));
            entries.add(lbEntry);
        }
    }

    // EFFECTS: Displays leaderboard title
    public void displayLeaderboardTitle() {
        JLabel titleText = new JLabel("Top 10 Scores");
        titleText.setFont(new Font("Verdana",1,20));
        titleText.setForeground(Color.BLACK);
        titleText.setVerticalAlignment(JLabel.TOP);
        titleText.setHorizontalAlignment(JLabel.CENTER);
        title.add(titleText, "push, align center");
    }

    // EFFECTS: Adds leaderboard entries to entries panel
    public void updateLeaderboard(List<String> leaderboardEntries) {
        for (String entry : leaderboardEntries) {

            JPanel lbEntry = new JPanel();
            lbEntry.setPreferredSize(new Dimension(0, 100));
            lbEntry.setBorder(BorderFactory.createLineBorder(Color.black));

            JLabel label = new JLabel(entry);
            label.setFont(new Font("Verdana",1,10));
            label.setForeground(Color.BLACK);
            label.setVerticalAlignment(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);

            lbEntry.add(label, "push, align center");

            entries.add(lbEntry);
        }
    }
}
