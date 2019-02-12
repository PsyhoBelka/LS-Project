package ua.rozhkov.project;

import ua.rozhkov.project.views.MainMenu;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        MainMenu mainMenu = new MainMenu();
        mainMenu.show();
    }
}
