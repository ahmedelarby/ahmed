package com.example.myapplicationlast;

public class Model_Recycler {

    String name;
    String time;
    String word;
    String ipAddress;
    String wifiInfo;
    String IMEIphone;
    String sora;
    String post_photo;
    int sora1;
    String Email;
    String online;
    String gender;
    String image_profile;
    String time_open;

    public Model_Recycler() {
    }

    public Model_Recycler(String name, String time, String word, String ipAddress, String wifiInfo, String IMEIphone, String sora, String post_photo, int sora1, String email, String online, String gender, String image_profile, String time_open) {
        this.name = name;
        this.time = time;
        this.word = word;
        this.ipAddress = ipAddress;
        this.wifiInfo = wifiInfo;
        this.IMEIphone = IMEIphone;
        this.sora = sora;
        this.post_photo = post_photo;
        this.sora1 = sora1;
        Email = email;
        this.online = online;
        this.gender = gender;
        this.image_profile = image_profile;
        this.time_open = time_open;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getWifiInfo() {
        return wifiInfo;
    }

    public void setWifiInfo(String wifiInfo) {
        this.wifiInfo = wifiInfo;
    }

    public String getIMEIphone() {
        return IMEIphone;
    }

    public void setIMEIphone(String IMEIphone) {
        this.IMEIphone = IMEIphone;
    }

    public String getSora() {
        return sora;
    }

    public void setSora(String sora) {
        this.sora = sora;
    }

    public String getPost_photo() {
        return post_photo;
    }

    public void setPost_photo(String post_photo) {
        this.post_photo = post_photo;
    }

    public int getSora1() {
        return sora1;
    }

    public void setSora1(int sora1) {
        this.sora1 = sora1;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
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
}

