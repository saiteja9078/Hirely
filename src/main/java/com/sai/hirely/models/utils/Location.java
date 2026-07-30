package com.sai.hirely.models.utils;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Location {
    private String country;
    private String state;
    private String city;

    // implement range search later
//    private double latitude;
//    private double longitude;

}
