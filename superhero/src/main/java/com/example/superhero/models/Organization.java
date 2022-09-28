package com.example.superhero.models;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Organization {
    private int id;
    private String name;
    private boolean attrHero;
    private String description;
    private String address;
    private String contact;
    private List<Hero> members;

    public boolean getAttrHero() {
        return attrHero;
    }
}
