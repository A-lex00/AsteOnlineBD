package it.uniroma2.dicii.bd.model.domain;

import java.util.ArrayList;
import java.util.List;

public class BookingList {
    List<BookingFlight> bookingFlights = new ArrayList<>();

    public void addBookingFlight(BookingFlight bookingFlight) {
        this.bookingFlights.add(bookingFlight);
    }

    public void addPassengerToBookingFlight(Passenger passenger, int volo) {
        bookingFlights.get(volo).addPassenger(passenger);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(BookingFlight bookingFlight : bookingFlights) {
            sb.append(bookingFlight);
        }
        return sb.toString();
    }
}
