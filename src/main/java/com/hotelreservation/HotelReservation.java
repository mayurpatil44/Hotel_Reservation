package com.hotelreservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class HotelReservation {
    ArrayList<Hotel> listOfHotels = new ArrayList<Hotel>();
    Date startDate;
    Date endDate;

    public void addHotel(Hotel obj) {
        listOfHotels.add(obj);
    }

    public int totalHotels() {
        return listOfHotels.size();
    }

    public long getTotalNoOfDays(String startDate1, String endDate1) throws ParseException {
        startDate = new SimpleDateFormat("ddMMMyyyy").parse(startDate1);
        endDate = new SimpleDateFormat("ddMMMyyyy").parse(endDate1);
        long TotalNoOfDays = 1 + (endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60 / 24;
        return TotalNoOfDays;
    }

    public Hotel findCheapestHotel() {
        Hotel cheapestHotel = listOfHotels.stream().min(Comparator.comparing(Hotel::getWeekDayRateRegCus)).orElse(null);
        return cheapestHotel;
    }

    public List<String> findCheapestHotelBasedOnWeekEndAndWeekDaysOffer(String startDate1, String endDate1)
            throws ParseException {
        long totalDays = getTotalNoOfDays(startDate1, endDate1);
        long totalWeekendDays = getTotalWeekendDays();
        long totalWeekDays = totalDays - totalWeekendDays;
        List<Long> hotelRentList = listOfHotels.stream().map(hotel -> {
            return (hotel.getWeekDayRateRegCus() * totalWeekDays + hotel.getWeekEndRateRegCus() * totalWeekendDays);
        }).collect(Collectors.toList());
        long minRent = Collections.min(hotelRentList);
        List<String> cheapHotelList = listOfHotels.stream()
                .filter(hotel -> hotel.getWeekDayRateRegCus() * totalWeekDays
                        + hotel.getWeekEndRateRegCus() * totalWeekendDays == minRent)
                .map(hotel -> hotel.getHotelName()).collect(Collectors.toList());

        return cheapHotelList;
    }

    public long getTotalWeekendDays() throws ParseException {
        long totalWeekendDays = 0;
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        for (; startCalendar.compareTo(endCalendar) <= 0; startCalendar.add(Calendar.DATE, 1)) {
            int dayOfWeek = startCalendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == 0 || dayOfWeek == 6)
                totalWeekendDays++;
        }
        return totalWeekendDays;
    }

    public static void main(String[] args) {

    }

}