package com.example.myapplicationlast;

public class Model_oll_users {

    String Email;
    String gender;
    String image_profile;
    String time_open;
    String word;
    String name;
    String online;
    String image;
    int menu;

    public Model_oll_users() {
    }

    public Model_oll_users(String email, String gender, String image_profile, String time_open, String word, String name, String online, String image, int menu) {
        Email = email;
        this.gender = gender;
        this.image_profile = image_profile;
        this.time_open = time_open;
        this.word = word;
        this.name = name;
        this.online = online;
        this.image = image;
        this.menu = menu;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage_profile() {
        return image_profile;
    }

    public void setImage_profile(String image_profile) {
        this.image_profile = image_profile;
    }

    public String getTime_open() {
        return time_open;
    }

    public void setTime_open(String time_open) {
        this.time_open = time_open;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }
}