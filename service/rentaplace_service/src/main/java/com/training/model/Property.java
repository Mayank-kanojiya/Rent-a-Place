package com.training.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "property", schema = "rent_a_place")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    @Column
    private String name;

    @Column
    private String location;

    @Column
    private String type;

    @Column
    private String features;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column
    private String phone;

    @Column(name = "owner_id")
    private int owner_id;

    // A property can have many bookings
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Booking> bookings;

    @Column
    private int price;

    @Lob
    private byte[] image;

    @Lob
    private byte[] image1;

    @Lob
    private byte[] image2;

    @Lob
    private byte[] image3;

    public Property() {}

    public Property(int pid, String name, String location, String type, String features, String description,
                    String phone, int owner_id, List<Booking> bookings, int price, byte[] image, byte[] image1,
                    byte[] image2, byte[] image3) {
        this.pid = pid;
        this.name = name;
        this.location = location;
        this.type = type;
        this.features = features;
        this.description = description;
        this.phone = phone;
        this.owner_id = owner_id;
        this.bookings = bookings;
        this.price = price;
        this.image = image;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
    }

    // Getters and setters for all fields

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage1() {
        return image1;
    }

    public void setImage1(byte[] image1) {
        this.image1 = image1;
    }

    public byte[] getImage2() {
        return image2;
    }

    public void setImage2(byte[] image2) {
        this.image2 = image2;
    }

    public byte[] getImage3() {
        return image3;
    }

    public void setImage3(byte[] image3) {
        this.image3 = image3;
    }
}
