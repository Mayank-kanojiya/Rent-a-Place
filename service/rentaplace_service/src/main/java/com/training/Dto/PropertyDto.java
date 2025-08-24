package com.training.Dto;

import org.springframework.web.multipart.MultipartFile;

public class PropertyDto {
    private int pid;
    private String name;
    private String location;
    private String features;
    private String type;
    private String description;
    private String phone;
    private int owner_id;
    private String ownername;
    private int price;
    
    // Compressed image data to be returned to frontend
    private byte[] returnedImage;
    private byte[] returnedImage1;
    private byte[] returnedImage2;
    private byte[] returnedImage3;

    // Multipart files for uploading images
    private MultipartFile image;
    private MultipartFile image1;
    private MultipartFile image2;
    private MultipartFile image3;

    public PropertyDto() {
    }

    // Full constructor, if needed
    public PropertyDto(int pid, String name, String location, String features, String type, String description,
                       String phone, int owner_id, String ownername, int price, byte[] returnedImage,
                       byte[] returnedImage1, byte[] returnedImage2, byte[] returnedImage3,
                       MultipartFile image, MultipartFile image1, MultipartFile image2, MultipartFile image3) {
        this.pid = pid;
        this.name = name;
        this.location = location;
        this.features = features;
        this.type = type;
        this.description = description;
        this.phone = phone;
        this.owner_id = owner_id;
        this.ownername = ownername;
        this.price = price;
        this.returnedImage = returnedImage;
        this.returnedImage1 = returnedImage1;
        this.returnedImage2 = returnedImage2;
        this.returnedImage3 = returnedImage3;
        this.image = image;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
    }

    // Getters and Setters below (keep consistent naming and types)

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

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getReturnedImage() {
        return returnedImage;
    }

    public void setReturnedImage(byte[] returnedImage) {
        this.returnedImage = returnedImage;
    }

    public byte[] getReturnedImage1() {
        return returnedImage1;
    }

    public void setReturnedImage1(byte[] returnedImage1) {
        this.returnedImage1 = returnedImage1;
    }

    public byte[] getReturnedImage2() {
        return returnedImage2;
    }

    public void setReturnedImage2(byte[] returnedImage2) {
        this.returnedImage2 = returnedImage2;
    }

    public byte[] getReturnedImage3() {
        return returnedImage3;
    }

    public void setReturnedImage3(byte[] returnedImage3) {
        this.returnedImage3 = returnedImage3;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public MultipartFile getImage1() {
        return image1;
    }

    public void setImage1(MultipartFile image1) {
        this.image1 = image1;
    }

    public MultipartFile getImage2() {
        return image2;
    }

    public void setImage2(MultipartFile image2) {
        this.image2 = image2;
    }

    public MultipartFile getImage3() {
        return image3;
    }

    public void setImage3(MultipartFile image3) {
        this.image3 = image3;
    }
}
