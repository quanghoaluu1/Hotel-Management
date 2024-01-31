/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import ui.HotelInformation;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author quang
 */
public class Input {
    Scanner sc = new Scanner(System.in);
     /**
     * This function allow User enter an integer between min number and max number
     * @param min min number 
     * @param max max number
     * @return  number
     */
    public int inputInt( int min, int max) {
        
        boolean check = true;
        int input;
        while (check) {
            try {
                input = sc.nextInt();
                if (input < min || input > max) {
                    System.out.println("This number must be " + min + " to " + max);
                    check = true;
                } else {
                    return input;
                }
            } catch (NumberFormatException e) {
                System.err.println("This must be number");
                check = true;
            }
        }
        return 0;
    }

    
    /**
     * allow user input non-blank string
     * @param txt a text you want to print out
     * @return string
     */
    public String inputStringNotEmpty(String txt){
        String input ="";
        do{
            System.out.println(txt);
            input = sc.nextLine();
        }while(input.trim().isEmpty());
        return input;
    }
    
    /**
     * This function get Yes or Others from User to continue or not
     * @param txt a text you want to print out
     * @return  true if Y, false if others
     */
    public boolean inputYorN(String txt ){
        String choice;
        System.out.println(txt); 
            choice = sc.nextLine();
       return choice.equalsIgnoreCase("Y");
    }

  /**
   *  Search hotel matches Id
     * @param arr list of HotelInformation to search through
     * @param id  Hotel Id you want to search
     * @return hotel information
   */
    
    public HotelInformation searchHotelId(ArrayList<HotelInformation> arr, String id){
        for(HotelInformation hi: arr){
            if(id.equalsIgnoreCase(hi.getHotelId())){
                return hi;
            }
        }
        return null;
    }
    
    /**
     * Input Allow user input write ID format
     * @param arr list of HotelInformation to search through
     * @return  hotel id
     */
    public String inputHotelId(ArrayList<HotelInformation> arr){
        String id="";
        Pattern pattern = Pattern.compile("H\\d\\d");
        do{
            System.out.println("Enter ID: ");
            id = sc.nextLine().toUpperCase();
            if(searchHotelId(arr, id)!=null){
                System.out.println("Duplicated, try again");
            }else if(!pattern.matcher(id).matches()){
                System.out.println("Wrong format, try again");
            }else if(id.trim().isEmpty()){
                System.out.println("Hotel ID can't empty");
            }else{
                return id.toUpperCase();
            }
        }while(true);
    }
    /**
     *  Input Allow user input write name format
     * @param txt a text you want to print out
     * @return hotel name
     */
    
    public String inputHotelName(String txt){
        String name ;
        System.out.println(txt);
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]+");
        do{
            name = sc.nextLine();
            if(name.isEmpty()){
                System.out.println("Name can't empty");
            }else if(!pattern.matcher(name).matches()){
                System.out.println("Input corret name format");
            }else{
                return name;
            }
        }while(true);
    }
    
    /**
     *  Input Allow user input hotel room available
     * @param txt a text you want to print out
     * @param minRoom min room in hotel must be bigger than 0
     * @param maxRoom max room in hotel
     * @return hotel room
     */
    public int inputHotelRoomAvailable(String txt, int minRoom, int maxRoom){
        int hotelRoomAvailable = 0;
        System.out.println(txt);
        
            do{
                try{
                    
                    hotelRoomAvailable = inputInt( 0, 1000);
                    if(hotelRoomAvailable <= 0 ){
                        System.out.println("Input number >0");
                    }else{
                        return hotelRoomAvailable;
                    }
                }catch(InputMismatchException e){
                    System.out.println("Input Number!");
                    sc.nextLine();
                }
            }while(hotelRoomAvailable<minRoom || hotelRoomAvailable>maxRoom);
        
        
        return 0;
    }
    /**
     * Input Allow user input hotel address
     * @param txt a text you want to print out
     * @return hotel address
     */
    
    public String inputHotelAddress(String txt){
        String address;
        System.out.println(txt);
        do{
            address = sc.nextLine();
            if(address.isEmpty()){
                System.out.println("Address can't empty");
            }
        }while(address.trim().isEmpty());
        return address;
    }
    
    /**
     * Input Allow user input right phone number format
     * @param txt a text you want to print out
     * @return hotel phone number
     */
    public String inputHotelPhoneNum(String txt){
        String phoneNum;
        System.out.println(txt);
        Pattern pattern = Pattern.compile("0\\d{9,10}");
        do{
            phoneNum = sc.next();
            if(phoneNum.isEmpty()){
                System.out.println("Phone number can't empty");
            }if(!pattern.matcher(phoneNum).matches()){
                System.out.println("Input correct phone number format");
            }else{
                return phoneNum;
            }
        }while(!pattern.matcher(phoneNum).matches());
        return null;
        
    }
    /**
     * Input Allow user input hotel rating star
     * @param txt a text you want to print out
     * @param minRate min hotel rating must be bigger than 0
     * @param maxRate max hotel rating 
     * @return hotel rating
     */
    
    public int inputHotelRating(String txt, int minRate, int maxRate){
        int hotelRating;
        System.out.println(txt);
        try{
            do{
                hotelRating = inputInt( 0, 7);
                if(hotelRating <=0){
                    System.out.println("Input number >0");
                }else{
                    return hotelRating;
                }
            }while(hotelRating<minRate || hotelRating>maxRate);
        }catch(InputMismatchException e){
            System.out.println("Input Number!");
        }
        return 0;
    }

  
}
    

