package com.example.myapplicationlast;

public class model_Problem_login {

    String text_probelm;
    String time;
    String ipAddress;
    String wifiInfo;
    String IMEIphone;
   String email;

    public model_Problem_login() {
    }

    public model_Problem_login(String text_probelm, String time, String ipAddress, String wifiInfo, String IMEIphone, String email) {
        this.text_probelm = text_probelm;
        this.time = time;
        this.ipAddress = ipAddress;
        this.wifiInfo = wifiInfo;
        this.IMEIphone = IMEIphone;
        this.email = email;
    }

    public String getText_probelm() {
        return text_probelm;
    }

    public void setText_probelm(String text_probelm) {
        this.text_probelm = text_probelm;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
