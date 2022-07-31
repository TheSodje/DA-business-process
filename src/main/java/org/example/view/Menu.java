package org.example.view;

import org.example.repositories.MenuItemsRepository;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner;
    private final MenuItemsRepository menuItemsRepository;

    public Menu(Scanner scanner, MenuItemsRepository menuItemsRepository) {
        this.scanner = scanner;
        this.menuItemsRepository = menuItemsRepository;
    }

    public void startMenu() {
        System.out.println("\n" +
                "          _____                    _____                    _____          \n" +
                "         /\\    \\                  /\\    \\                  /\\    \\         \n" +
                "        /::\\____\\                /::\\    \\                /::\\____\\        \n" +
                "       /:::/    /               /::::\\    \\              /::::|   |        \n" +
                "      /:::/    /               /::::::\\    \\            /:::::|   |        \n" +
                "     /:::/    /               /:::/\\:::\\    \\          /::::::|   |        \n" +
                "    /:::/____/               /:::/__\\:::\\    \\        /:::/|::|   |        \n" +
                "   /::::\\    \\              /::::\\   \\:::\\    \\      /:::/ |::|   |        \n" +
                "  /::::::\\    \\   _____    /::::::\\   \\:::\\    \\    /:::/  |::|___|______  \n" +
                " /:::/\\:::\\    \\ /\\    \\  /:::/\\:::\\   \\:::\\____\\  /:::/   |::::::::\\    \\ \n" +
                "/:::/  \\:::\\    /::\\____\\/:::/  \\:::\\   \\:::|    |/:::/    |:::::::::\\____\\\n" +
                "\\::/    \\:::\\  /:::/    /\\::/   |::::\\  /:::|____|\\::/    / ~~~~~/:::/    /\n" +
                " \\/____/ \\:::\\/:::/    /  \\/____|:::::\\/:::/    /  \\/____/      /:::/    / \n" +
                "          \\::::::/    /         |:::::::::/    /               /:::/    /  \n" +
                "           \\::::/    /          |::|\\::::/    /               /:::/    /   \n" +
                "           /:::/    /           |::| \\::/____/               /:::/    /    \n" +
                "          /:::/    /            |::|  ~|                    /:::/    /     \n" +
                "         /:::/    /             |::|   |                   /:::/    /      \n" +
                "        /:::/    /              \\::|   |                  /:::/    /       \n" +
                "        \\::/    /                \\:|   |                  \\::/    /        \n" +
                "         \\/____/                  \\|___|                   \\/____/         \n" +
                "                                                                           \n");

        System.out.println("Input your firstname and press enter to continue\n");
        String firstname = scanner.nextLine();
        System.out.println("Welcome " + firstname + "\n");
    }

    public void mainMenu() {
        System.out.println("What would you like to do?\n");

        menuItemsRepository.getMainMenuItems().forEach(System.out::println);

        String item = scanner.nextLine();
        switch (item) {
            case "0":
                startMenu();
                break;
            default:
                System.out.println("Invalid item number try again\n");
                mainMenu();
                break;
        }
    }
}
