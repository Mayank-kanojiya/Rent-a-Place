package com.training.Dto;

import java.sql.Date;

public class BookingDto {
    private int bookingId;
    private int userId;
    private String propertyName;
    private Date checkin;
    private Date checkout;
    private Boolean status;

    public BookingDto() {}

    public BookingDto(int bookingId, int userId, String propertyName, Date checkin, Date checkout, Boolean status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.propertyName = propertyName;
        this.checkin = checkin;
        this.checkout = checkout;
        this.status = status;
    }

    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPropertyName() {
        return propertyName;
    }
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Date getCheckin() {
        return checkin;
    }
    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }
    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
}
