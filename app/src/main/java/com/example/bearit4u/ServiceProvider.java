package com.example.bearit4u;

import java.io.Serializable;
import java.util.ArrayList;

public class ServiceProvider implements Serializable {
    private String username;
    private String password;
    private String address;
    private String name;
    private String phone;
    private ArrayList<String> service;

    public ServiceProvider(){

    }

    public ServiceProvider(String username, String name, String password, String address,  String phone, ArrayList<String> service) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.service = service;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<String> getService() {
        return service;
    }

    public void setService(ArrayList<String> service) {
        this.service = service;
    }
}
