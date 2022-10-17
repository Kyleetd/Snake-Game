package ui;

import controller.ControllerCMD;
import model.Model;
import view.ViewCMD;

public class Main {
    public static void main(String[] args) {

        Model model = new Model();
        ViewCMD view = new ViewCMD();
        ControllerCMD controller = new ControllerCMD(model, view);

        controller.startMainLoop();
    }
}
