package com.example.superhero.dao;

import java.util.List;

import com.example.superhero.models.Location;
import com.example.superhero.models.Sighting;

public interface SightingDao {

    Sighting getSightingById(int id);
    List<Sighting> getAllSightings();

    Sighting addSighting(Sighting sighting);

    void updateSighting(Sighting sighting);

    void deleteSightingById(int id);
    
    List<Sighting> getSightingsForLocation(Location location);
}
