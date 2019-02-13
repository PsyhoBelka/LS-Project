package ua.rozhkov.project;

import ua.rozhkov.project.views.MainMenu;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
//        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        MainMenu mainMenu = new MainMenu();
        mainMenu.show();
    }
}
