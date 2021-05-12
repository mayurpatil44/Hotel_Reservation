package com.hotelreservation;
import java.util.ArrayList;

public class HotelReservation {
    ArrayList<Hotel> listOfHotels = new ArrayList<Hotel>();

    public void addHotel(Hotel obj) {
        listOfHotels.add(obj);
    }

    public int totalHotels() {
        return listOfHotels.size();
    }

    public static void main(String[] args) {
        System.out.println("Welcome to hotel reservation system!");
    }
}