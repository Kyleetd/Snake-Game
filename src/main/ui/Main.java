package ui;

import model.LeaderboardModel;
import model.SnakeModel;
import ui.controller.ControllerCMD;
import ui.view.ViewCMD;

public class Main {
    public static void main(String[] args) {

        SnakeModel snakeModel = new SnakeModel(10, 10);
        LeaderboardModel leaderboardModel = new LeaderboardModel();
        ViewCMD view = new ViewCMD();
        ControllerCMD controller = new ControllerCMD(snakeModel, leaderboardModel, view);

        controller.startMainLoop();
    }
}
