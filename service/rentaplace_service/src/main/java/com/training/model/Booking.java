package com.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "booking", schema = "rent_a_place")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int bookingId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pid", nullable = false)
    private Property property;

    @Column(name = "user_id")
    private int userId;

    @Column
    private boolean status;

    @Column
    private Date checkin;

    @Column
    private Date checkout;

    public Booking() {}

    public Booking(int bookingId, Property property, int userId, boolean status, Date checkin, Date checkout) {
        this.bookingId = bookingId;
        this.property = property;
        this.userId = userId;
        this.status = status;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
}
