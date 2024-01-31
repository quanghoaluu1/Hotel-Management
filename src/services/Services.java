/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utilities.Input;
import data.HotelDao;
import data.FileManager;
import utilities.Menu;
import ui.HotelInformation;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author quang
 */
public class Services extends ArrayList<HotelInformation> implements Serviceable {

    Input input = new Input();
    Scanner sc = new Scanner(System.in);
    ArrayList<HotelInformation> hotel = new ArrayList<>();
    String pathFile;
    FileManager fm = new FileManager();
    HotelDao hotelDal = new HotelDao();
    Menu menu = new Menu();

    public Services(String fileName) {
        this.pathFile = fileName;
        try {
            fm.readFromFile(hotel, "hotel.dat");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        if (hotel.isEmpty()) {
            System.out.println("Empty File, Add new hotel");
            addHotel();
        }

    }
/**
 * Print information with format   UI - User Interface
 * @param hotel information of hotel
 */
    public void graphic(HotelInformation hotel) {

        String address = hotel.getHotelAddress();

        Pattern pattern = Pattern.compile("(.{0,30})\\b");
        Matcher matcher = pattern.matcher(address);

        System.out.println("---------------------------------------------------------------------------------------------------------------");

        // In nhóm đầu tiên 
        if (matcher.find()) {
            System.out.printf("|%9s|%17s|%25s|%30s|%11s|%7s star|\n",
                    hotel.getHotelId(),
                    hotel.getHotelName(),
                    hotel.getHotelRoomAvailable(),
                    matcher.group(1), hotel.getHotelPhoneNumber(), hotel.getHotelRating());
        }

        // In các nhóm còn lại
        while (matcher.find()) {
            // Kiểm tra xem có phải nhóm rỗng không
            if (!matcher.group(1).equals("")) {
                System.out.printf("|%9s|%17s|%25s|%30s|%11s|%12s|\n",
                        "", "", "",
                        matcher.group(1), "", "");
            }
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------");

    }

    /**
     * Function will add hotel information to file
     */
    @Override
    public void addHotel() {
        String hotelId;
        String hotelName;
        int hotelRoomAvailable;
        String hotelAddress;
        String hotelPhoneNumber;
        int hotelRating;

        boolean check = true;

        System.out.println("Add new hotel: ");
        while (check) {
            hotelId = input.inputHotelId(hotel);
            hotelName = input.inputHotelName("Input Hotel name: ");
            hotelRoomAvailable = input.inputHotelRoomAvailable("Input Hotel Room Available: ", 1, 1000);
            hotelAddress = input.inputHotelAddress("Input Hotel Address: ");
            hotelPhoneNumber = input.inputHotelPhoneNum("Input Hotel phone number");
            hotelRating = input.inputHotelRating("Input Hotel rating star", 1, 5);
            hotel.add(new HotelInformation(hotelId, hotelName, hotelRoomAvailable, hotelAddress, hotelPhoneNumber, hotelRating));
            System.out.println("Add Success");
            hotelDal.saveToFile(hotel, "hotel.dat", "Save to file success");
            check = input.inputYorN("Do you want to continue add? (Y/N)");

        }

    }

    /**
     * This function check if Hotel exit or not by Hotel_id from User
     */
    @Override
    public void checkExistHotel() {

        boolean check = true;

        while (check) {
            String id = input.inputStringNotEmpty("Input Hotel ID you want to search: ");
            HotelInformation hi = input.searchHotelId(hotel, id);
            if (hi != null) {
                System.out.println("Exist Hotel");
                graphic(hi);
            } else {
                System.out.println("No Hotel Found");
            }
            check = input.inputYorN("Do you want to continue search? (Y/N)");
        }

    }

    /**
     * This function allow User update information of Hotel If new information
     * is blank, then do not update
     */
    @Override
    public void updateHotelInformation() {
        String id = input.inputStringNotEmpty("Input Hotel ID you want to update: ");
        String hotelId;
        String hotelName;
        int hotelRoomAvailable = 0;
        String hotelAddress;
        String hotelPhoneNumber;
        int hotelRating = 0;

        boolean check = true;
        HotelInformation hi = input.searchHotelId(hotel, id);
        do {
            if (hi == null) {
                System.out.println("No Hotel Found!");
                break;
            } else {
                do {
                    graphic(hi);
                    System.out.println("Input ID to update");
                    Pattern pattern = Pattern.compile("H\\d\\d");
                    hotelId = sc.nextLine().toUpperCase();
                    if (hotelId.trim().isEmpty()) {
                        System.out.println("No change");
                        hotelId = hi.getHotelId();

                    } else if (!pattern.matcher(hotelId).matches()) {
                        System.out.println("Wrote format");
                    }
                    hi.setHotelId(hotelId);
                    check = false;

                } while (check);

                do {
                    Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]+");
                    System.out.println("Input name to update");
                    hotelName = sc.nextLine();
                    if (hotelName.trim().isEmpty()) {
                        System.out.println("No change");
                        hotelName = hi.getHotelName();

                    } else if (!pattern.matcher(hotelName).matches()) {
                        System.out.println("Wrote format");
                    }
                    hi.setHotelName(hotelName);
                    break;

                } while (check);

                do {
                    try {
                        System.out.println("Input Room Available to update");
                        String input = sc.nextLine();

                        if (input.trim().isEmpty()) {
                            System.out.println("No change");
                            hotelRoomAvailable = hi.getHotelRoomAvailable();

                        } else {
                            hotelRoomAvailable = Integer.parseInt(input);
                        }

                        hi.setHotelRoomAvailable(hotelRoomAvailable);
                        check = false;

                    } catch (InputMismatchException e) {
                        System.out.println("Input number!");
                        sc.nextLine();
                    }

                } while (check);

                do {
                    Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]+");
                    System.out.println("Input address to update");
                    hotelAddress = sc.nextLine();
                    if (hotelAddress.trim().isEmpty()) {
                        System.out.println("No change");
                        hotelAddress = hi.getHotelAddress();

                    } else if (!pattern.matcher(hotelAddress).matches()) {
                        System.out.println("Wrote format");
                    }
                    hi.setHotelAddress(hotelAddress);
                    check = false;

                } while (check);

                do {
                    Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]+");
                    System.out.println("Input phone number to update");
                    hotelPhoneNumber = sc.nextLine();
                    if (hotelPhoneNumber.trim().isEmpty()) {
                        System.out.println("No change");
                        hotelPhoneNumber = hi.getHotelPhoneNumber();

                    } else if (!pattern.matcher(hotelPhoneNumber).matches()) {
                        System.out.println("Wrote format");
                    }
                    hi.setHotelPhoneNumber(hotelPhoneNumber);
                    check = false;

                } while (check);

                do {
                    try {
                        System.out.println("Input Rating to update");
                        String input = sc.nextLine();

                        if (input.trim().isEmpty()) {
                            System.out.println("No change");
                            hotelRating = hi.getHotelRating();
                        } else {
                            hotelRating = Integer.parseInt(input);
                            hi.setHotelRating(hotelRating);
                            check = false;
                        }

                    } catch (InputMismatchException e) {
                        System.out.println("Input number!");
                        sc.nextLine();
                    }
                } while (check);

                hotel.set(hotel.indexOf(hi), new HotelInformation(hotelId, hotelName, hotelRoomAvailable, hotelAddress, hotelPhoneNumber, hotelRating));
                System.out.println("Update success");
                fm.saveToFile(hotel, "hotel.dat", "Save to file success");
                graphic(hi);
            }

        } while (hi == null);
    }

    /**
     * This function allow User delete Hotel information And ask to sure that
     * User want to delete
     */
    @Override
    public void deleteHotel() {
        String id = input.inputStringNotEmpty("Input Hotel ID you want to delete: ");
        HotelInformation hi = input.searchHotelId(hotel, id);
        do {
            if (hi == null) {
                System.out.println("No Hotel Found!");
            } else {
                System.out.println("The Hotel you want to delete: ");
                graphic(hi);
                System.out.println("Delete this hotel: ? \n Yes(Y) / No(N)");
                String choice;
                choice = sc.nextLine();
                if (choice.equalsIgnoreCase("Y")) {
                    hotel.remove(hi);
                    fm.saveToFile(hotel, "hotel.dat", "Remove successfully!");

                }
            }
        } while (hi == null);
    }

    /**
     * This allow User search Hotel information by ID or Name
     */
    @Override
    public void searchHotel() {
        int choice;
        boolean check;
        ArrayList<String> option = new ArrayList<>();
        ArrayList<HotelInformation> hotelTmp = hotel;
        System.out.println("Select information you want to search:");
        option.add("Search by ID");
        option.add("Search by Name");
        option.add("Search by address");
        do {
            for (int i = 0; i < option.size(); i++) {
                System.out.println((i + 1) + ". " + option.get(i));
            }
            
            choice = menu.getChoice(option);
            check = false;

            switch (choice) {
                case 1:
                    String id = input.inputStringNotEmpty("Input ID you want to search: ");
                    System.out.printf("|%9s|%17s|%25s|%30s|%11s|%10s|\n", "Hotel ID", "Hotel Name", "Hotel Room Available", "Hotel Address", "Hotel Phone", "HotelRating");
                    for (HotelInformation hi : hotelTmp) {
                        if (hi.getHotelId().toLowerCase().contains(id.toLowerCase())) {
                            graphic(hi);
                            check = true;
                        }
                    }
                    if (!check) {
                        System.out.println("Hotel Id not found");
                    }
                    break;
                case 2:
                    String name = input.inputStringNotEmpty("Input name you want to search: ");
                    System.out.printf("|%9s|%17s|%25s|%30s|%11s|%10s|\n", "Hotel ID", "Hotel Name", "Hotel Room Available", "Hotel Address", "Hotel Phone", "HotelRating");
                    for (HotelInformation hi : hotelTmp) {
                        if (hi.getHotelName().toLowerCase().contains(name.toLowerCase())) {
                            graphic(hi);
                            check = true;
                        }
                    }
                    if (!check) {
                        System.out.println("Hotel name  not found");
                    }
                    break;
                case 3:
                    String address =  input.inputStringNotEmpty("Input address you want to search: ");
                    System.out.printf("|%9s|%17s|%25s|%30s|%11s|%10s|\n", "Hotel ID", "Hotel Name", "Hotel Room Available", "Hotel Address", "Hotel Phone", "HotelRating");
                    for(HotelInformation hi: hotelTmp){
                        if(hi.getHotelAddress().toLowerCase().contains(address.toLowerCase())){
                            graphic(hi);
                            check = true;
                        }
                    }
                    if(!check){
                        System.out.println("Address not found");
                    }
            }
            
            check = input.inputYorN("Do you want continue search? (Y/N)");

        } while (check);
    }

    /**
     * This function will display all Hotel information and descending by Name
     */
    public void displayHotel() {

        try {
            fm.readFromFile(hotel, "hotel.dat");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        if (hotel.isEmpty()) {
            System.out.println("No hotel found");
            return;
        }

        hotel.sort((h1, h2) -> h2.getHotelName().compareToIgnoreCase(h1.getHotelName()));
        System.out.printf("|%9s|%17s|%25s|%30s|%11s|%10s|\n", "Hotel ID", "Hotel Name", "Hotel Room Available", "Hotel Address", "Hotel Phone", "HotelRating");
        for (HotelInformation hi : hotel) {
            graphic(hi);
        }

    }

}
