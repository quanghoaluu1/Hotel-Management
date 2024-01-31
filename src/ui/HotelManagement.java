/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import utilities.Menu;
import java.util.ArrayList;
import services.Services;

/**
 *
 * @author quang
 */
public class HotelManagement {
    public static void main(String[] args) throws ClassNotFoundException {
        ArrayList<String> options = new ArrayList<>();
        Menu menu = new Menu();
        Services func = new Services("hotel.dat");
        int choice;

        options.add("Add new hotel");
        options.add("Check exists hotel");
        options.add("Update hotel information");
        options.add("Delete hotel");
        options.add("Search hotel");
        options.add("Display hotel list");
        options.add("Quit");

        do {
            menu.printMenu(options);
            choice = menu.getChoice(options);
            switch (choice) {
                case 1:
                    func.addHotel();
                    break;
                case 2:
                    func.checkExistHotel();
                    break;
                case 3:
                    func.updateHotelInformation();
                    break;
                case 4:
                    func.deleteHotel();
                    break;
                case 5:
                    func.searchHotel();
                    break;
                case 6:
                    func.displayHotel();
                    break;
                default:
                    System.out.println("Bye!");
            }

        } while (choice >= 1 && choice <= 6);

    }

}
