package com.example.myapplicationlast;

public class Model_oll_users {

    String name;
    String online;
    String image;
    int menu;

    public Model_oll_users() {
    }

    public Model_oll_users(String name, String online, String image, int menu) {
        this.name = name;
        this.online = online;
        this.image = image;
        this.menu = menu;
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