/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import ui.HotelInformation;
import java.util.List;
/**
 *
 * @author quang
 */
public class HotelDao {
    FileManager fm = new FileManager();
    
    /**
     * This function read data of Hotel to file
     * @param list list of hotel information
     * @param fileName file name
     * @return true if read success
     */
    public boolean readFromFile(List<HotelInformation> list, String fileName){
        return fm.readFromFile(list, fileName);
    }
    
    /**
     * This function save data of Hotel to file
     * @param list list of hotel information
     * @param fileName file name
     * @param txt text you want to print
     * @return true if save success
     */
    public boolean saveToFile(List<HotelInformation> list, String fileName, String txt){
        return fm.saveToFile(list, fileName,  txt  );
    }
}
