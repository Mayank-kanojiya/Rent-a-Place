package com.training.model;

import jakarta.persistence.*;

@Entity
@Table(name = "emails", schema = "rent_a_place")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "owner_id")
    private int owner_id;

    @Column(name = "pid")
    private int pid;

    @Column(name = "email_body")
    private String emailBody;

    @Column(name = "user_email")
    private String useremail;

    public Email() {}

    public Email(int id, int user_id, int owner_id, int pid, String emailBody, String useremail) {
        this.id = id;
        this.user_id = user_id;
        this.owner_id = owner_id;
        this.pid = pid;
        this.emailBody = emailBody;
        this.useremail = useremail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
}
