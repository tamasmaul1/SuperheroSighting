package com.example.superhero.models;

import java.sql.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Sighting {
    private int id;
    private int heroId;
    private Location location;
    private Date date;
}
