/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.Serializable;

/**
 *
 * @author quang
 */
public class HotelInformation implements Serializable, Comparable<HotelInformation> {
    private String hotelId;
    private String hotelName;
    private int hotelRoomAvailable;
    private String hotelAddress;
    private String hotelPhoneNumber;
    private int hotelRating;

    public HotelInformation(String hotelId, String hotelName, int hotelRoomAvailable, String hotelAddress,
            String hotelPhoneNumber, int hotelRating) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelRoomAvailable = hotelRoomAvailable;
        this.hotelAddress = hotelAddress;
        this.hotelPhoneNumber = hotelPhoneNumber;
        this.hotelRating = hotelRating;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getHotelRoomAvailable() {
        return hotelRoomAvailable;
    }

    public void setHotelRoomAvailable(int hotelRoomAvailable) {
        this.hotelRoomAvailable = hotelRoomAvailable;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelPhoneNumber() {
        return hotelPhoneNumber;
    }

    public void setHotelPhoneNumber(String hotelPhoneNumber) {
        this.hotelPhoneNumber = hotelPhoneNumber;
    }

    public int getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(int hotelRating) {
        this.hotelRating = hotelRating;
    }

    @Override
    public int compareTo(HotelInformation hi) {
        return this.hotelName.compareTo(hi.hotelName);
    }

    @Override
    public String toString() {
        return String.format("|%9s|%17s|%5d|%20s|%11s|%4d star|\n", hotelId, hotelName, hotelRoomAvailable,
                hotelAddress, hotelPhoneNumber, hotelRating);
    }

}
