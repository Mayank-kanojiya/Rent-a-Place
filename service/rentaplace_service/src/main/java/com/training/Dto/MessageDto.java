package com.training.Dto;

public class MessageDto {
    private int id;               // message id
    private int propertyId;       // pid
    private int senderId;         // sid
    private String senderName;    // username (for tenant/user) OR ownername (for owner)
    private String message;       // message text

    // Optionally include these if needed (for advanced frontend display)
    private int ownerId;          // oid
    private String ownerName;     // ownername
    private int userId;           // uid
    private String username;      // username

    public MessageDto() {}

    public MessageDto(int id, int propertyId, int senderId, String senderName, String message,
                      int ownerId, String ownerName, int userId, String username) {
        this.id = id;
        this.propertyId = propertyId;
        this.senderId = senderId;
        this.senderName = senderName;
        this.message = message;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.userId = userId;
        this.username = username;
    }

    // Getters and Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPropertyId() { return propertyId; }
    public void setPropertyId(int propertyId) { this.propertyId = propertyId; }

    public int getSenderId() { return senderId; }
    public void setSenderId(int senderId) { this.senderId = senderId; }

    public String getSenderName() { return senderName; }
    public void setSenderName(String senderName) { this.senderName = senderName; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
