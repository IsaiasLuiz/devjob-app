package com.devjob.devjob_app.model;

public class Contact {

    private String phone;

    private String mail;

    private String whatsapp;

    private String linkedin;

    public Contact(String phone, String mail, String whatsapp, String linkedin) {
        this.phone = phone;
        this.mail = mail;
        this.whatsapp = whatsapp;
        this.linkedin = linkedin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
}
