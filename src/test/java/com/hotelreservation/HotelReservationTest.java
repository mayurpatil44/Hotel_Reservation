package com.hotelreservation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HotelReservationTest {
    @Test
    public void WhenHostelsAreAddedReturnSize() {
        Hotel hotel1 = new Hotel("Lakewood", 3, 90, 110, 80, 80);
        Hotel hotel2 = new Hotel("Bridgewood", 4, 60, 160, 50, 110);
        Hotel hotel3 = new Hotel("Ridgewood", 5, 150, 220, 40, 100);

        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.addHotel(hotel1);
        hotelReservation.addHotel(hotel2);
        hotelReservation.addHotel(hotel3);
        int result = hotelReservation.totalHotels();
        assertEquals(3, result);
    }
}