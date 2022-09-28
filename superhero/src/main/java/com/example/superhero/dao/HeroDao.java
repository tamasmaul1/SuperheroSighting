package com.example.superhero.dao;

import java.util.List;

import com.example.superhero.models.Hero;
import com.example.superhero.models.Location;
import com.example.superhero.models.Sighting;
import com.example.superhero.models.Superpower;

public interface HeroDao {

    Hero getHeroById(int id);
    List<Hero> getAllHeros();
    List<Hero> getHerosForSuperpower(Superpower superpower);
    Hero getHeroForSighting(Sighting sighting);
    List<Hero> getHerosForLocation(Location location);

    Hero addHero(Hero hero);
    
    void updateHero(Hero hero);
    
    void deleteHeroById(int id);   
}
