package com.example.myapplicationlast;

public class Model_oll_users {

    String name;
    String online;
    int sora_user;
    int menu;


    public Model_oll_users() {}


    public Model_oll_users(String name, String online, int sora_user, int menu) {
        this.name = name;
        this.online = online;
        this.sora_user = sora_user;
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

    public int getSora_user() {
        return sora_user;
    }

    public void setSora_user(int sora_user) {
        this.sora_user = sora_user;
    }

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }
}
