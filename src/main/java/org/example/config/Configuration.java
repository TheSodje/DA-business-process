package org.example.config;

import org.example.repositories.MenuItemsRepository;
import org.example.view.Menu;

import java.util.Scanner;

public class Configuration {

    private final Scanner scanner = new Scanner(System.in);

    public MenuItemsRepository menuItemsRepository() {
        return new MenuItemsRepository();
    }

    public Menu menu() {
        return new Menu(scanner, menuItemsRepository());
    }
}
