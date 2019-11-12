package com.teamnaqq.androidbaberbooking.Model;

import java.util.List;

public class UserBookingLoadEvent {

    private boolean success;
    private String message;
    private List<BookingInformation> bookingInformations;

    public UserBookingLoadEvent(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public UserBookingLoadEvent(boolean success, List<BookingInformation> bookingInformations) {
        this.success = success;
        this.bookingInformations = bookingInformations;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BookingInformation> getBookingInformations() {
        return bookingInformations;
    }

    public void setBookingInformations(List<BookingInformation> bookingInformations) {
        this.bookingInformations = bookingInformations;
    }
}
