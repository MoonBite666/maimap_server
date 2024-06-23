package com.moonfeather.maimap_server;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Location(String name){
        this.name = name;
    }

    public Location() {name = null;}

    public String getName() {
        return name;
    }
}
