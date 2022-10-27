package ui;

import model.LeaderboardModel;
import model.SnakeModel;
import ui.controller.ControllerCMD;
import ui.view.ViewCMD;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        SnakeModel snakeModel = new SnakeModel();
        LeaderboardModel leaderboardModel = new LeaderboardModel();
        ViewCMD view = new ViewCMD();
        ControllerCMD controller = new ControllerCMD(snakeModel, leaderboardModel, view);

        controller.startMainLoop();
    }
}
