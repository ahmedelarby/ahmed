package com.example.myapplicationlast;

public class Model_Recycler {

    String name;
    String time;
    String word;
    String ipAddress;
    String wifiInfo;
    String IMEIphone;
    int sora;
    int sora1;

    public Model_Recycler() {
    }

    public Model_Recycler(String name, String time, String word, String ipAddress, String wifiInfo, String IMEIphone, int sora, int sora1) {
        this.name = name;
        this.time = time;
        this.word = word;
        this.ipAddress = ipAddress;
        this.wifiInfo = wifiInfo;
        this.IMEIphone = IMEIphone;
        this.sora = sora;
        this.sora1 = sora1;
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

    public int getSora() {
        return sora;
    }

    public void setSora(int sora) {
        this.sora = sora;
    }

    public int getSora1() {
        return sora1;
    }

    public void setSora1(int sora1) {
        this.sora1 = sora1;
    }
}
