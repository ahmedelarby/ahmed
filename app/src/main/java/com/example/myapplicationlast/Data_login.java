package com.example.myapplicationlast;

public class Data_login {

    String Email;
    String password;
    String time;
    String ipAddress;
    String wifiInfo;
    String IMEIphone;

    public Data_login() {
    }

    public Data_login( String email, String password, String time, String ipAddress, String wifiInfo, String IMEIphone) {

        Email = email;
        this.password = password;
        this.time = time;
        this.ipAddress = ipAddress;
        this.wifiInfo = wifiInfo;
        this.IMEIphone = IMEIphone;
    }





    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
