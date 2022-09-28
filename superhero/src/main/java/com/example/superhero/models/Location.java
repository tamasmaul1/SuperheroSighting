package com.example.superhero.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Location {
    private int id;
    private String name;
    private double latitude;
    private double longtitude;
    private String description;
    private String addressInfo;
    public void setLongitude(double double1) {
        this.longtitude = double1;
    }
    public void setAddressInformation(String string) {
        this.addressInfo = string;
    }
}
