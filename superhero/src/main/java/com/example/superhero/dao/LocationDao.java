package com.example.superhero.dao;

import java.util.List;

import com.example.superhero.models.Hero;
import com.example.superhero.models.Location;

public interface LocationDao {

    Location getLocationById(int id);
    List<Location> getAllLocations();

    Location addLocation(Location location);

    void updateLocation(Location location);

    void deleteLocationById(int id);
    
    List<Location> getLocationsForHero(Hero hero);
}
