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
public class Hero {
    private int id;
    private boolean attrHero;
    private String name;
    private String description;
    private List<Superpower> superpowers;
    private List<Sighting> sightings;

    public boolean getAttrHero() {
        return attrHero;
    }
}
