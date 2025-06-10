package it.uniroma2.dicii.bd.model.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingFlight {
    String idVolo;
    String CittaPart;
    String CittaArr;
    Date date;
    List<Passenger> passengers = new ArrayList<>();

    public BookingFlight(String idVolo, String CittaPart, String CittaArr, Date date) {
        this.idVolo = idVolo;
        this.CittaPart = CittaPart;
        this.CittaArr = CittaArr;
        this.date = date;
    }

    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }
    
    public String getIdVolo() {
        return idVolo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(idVolo).append(": ").append(CittaPart).append('-').append(CittaArr).append(" (").append(date).append("): \n");
        for(Passenger p: passengers) {
            sb.append('\t').append(p.toString()).append('\n');
        }
        return sb.toString();
    }
}
