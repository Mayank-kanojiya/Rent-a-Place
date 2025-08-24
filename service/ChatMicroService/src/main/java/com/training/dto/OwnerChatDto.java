package com.training.dto;

public class OwnerChatDto {
    private String userName;
    private String message;
    private int userId;

    public OwnerChatDto() {}

    public OwnerChatDto(String userName, String message, int userId) {
        this.userName = userName;
        this.message = message;
        this.userId = userId;
    }

    // Getters and Setters
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}
