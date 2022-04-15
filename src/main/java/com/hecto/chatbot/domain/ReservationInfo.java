package com.hecto.chatbot.domain;

import java.util.Date;

public class ReservationInfo {

    String store;

    String name;

    String mobileNumber;

    Date reservationDate;

    int numberPeople;

    public ReservationInfo() {
    }

    public ReservationInfo(String store, String name, String mobileNumber, Date reservationDate, int numberPeople) {
        this.store = store;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.reservationDate = reservationDate;
        this.numberPeople = numberPeople;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(int numberPeople) {
        this.numberPeople = numberPeople;
    }
}
