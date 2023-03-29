package com.example.bearit4u;

public class ServiceModel {

    private String serviceName;
    private String date;
    private boolean isSelected;


    public ServiceModel(String name, String date, boolean isSelected) {
        this.serviceName = name;
        this.date = date;
        this.isSelected = isSelected;
    }

    public String getName() {
        return serviceName;
    }

    public void setName(String name) {
        this.serviceName = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    
}
