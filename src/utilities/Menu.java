/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.ArrayList;

/**
 *
 * @author quang
 */
public class Menu {

    Input input = new Input();

    /**
     * This function can use for main menu and sub-menu to get choice from User
     * 
     * @param options options of things
     * @return number from 1 to number of max options
     */
    public int getChoice(ArrayList<String> options) {
        System.out.print("Input choice: ");
        int choice = input.inputInt(1, options.size());
        return choice;
    }

    /**
     * This function will print Menu
     * Can be use for many menu
     * 
     * @param options of hotel services
     */
    public void printMenu(ArrayList<String> options) {
        System.out.println("Hotel Management");
        for (int i = 0; i < 30; i++) {
            System.out.printf("-");
        }
        System.out.println("");

        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        for (int i = 0; i < 30; i++) {
            System.out.printf("-");
        }
        System.out.println("");
    }
}
