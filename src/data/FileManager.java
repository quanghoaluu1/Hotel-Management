/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author quang
 */
public class FileManager {

    Scanner sc = new Scanner(System.in);

    /**
     * This function save data of Object to file 
     * 
     * @param <T> generic type
     * @param list list to save
     * @param fileName file name
     * @param txt text you want to print
     * @return true if success , false if fail
     */
    public <T> boolean saveToFile(List<T> list, String fileName, String txt) {

        try {
            File f = new File(fileName);

            ObjectOutputStream oos;
            try (FileOutputStream fos = new FileOutputStream(f)) {
                oos = new ObjectOutputStream(fos);
                for (T hotel : list) {
                    oos.writeObject(hotel);
                }
            }
            oos.close();
            System.out.println(txt);
            return true;

        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * This function read data of Object to file
     * 
     * @param <T>  is generic type
     * @param list of T
     * @param fileName file name
     * @return true if success , false if fail
     */
    public <T> boolean readFromFile(List<T> list, String fileName) {
        list.clear();
        File f = new File(fileName);
        if (!f.exists()) {
            return false;
        }
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            if (f.length() == 0) {
                System.err.println("List Empty");
            }
            boolean check = true;
            while (check) {
                try {
                    @SuppressWarnings("Reverse they")
                    T c = (T) ois.readObject();
                    list.add(c);
                } catch (EOFException e) {
                    break;
                }
            }
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

}
