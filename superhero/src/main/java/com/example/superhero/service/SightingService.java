package com.example.superhero.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.superhero.dao.HeroDao;
import com.example.superhero.dao.LocationDao;
import com.example.superhero.dao.OrganizationDao;
import com.example.superhero.dao.SightingDao;
import com.example.superhero.dao.SuperpowerDao;
import com.example.superhero.models.Hero;
import com.example.superhero.models.Location;
import com.example.superhero.models.Sighting;

@Service
public class SightingService {
    
    @Autowired
    HeroDao heroDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    SuperpowerDao superpowerDao;
    
    
    public Sighting createSighting(Hero hero, Location location, Date date){
        Sighting sighting = new Sighting();
        sighting.setHeroId(hero.getId());
        sighting.setLocation(location);
        sighting.setDate(date);

        return sighting;
    }
    
    public boolean isValidDate(String date){
        try{
            Date.valueOf(date);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    
    public List<Sighting> getLastSightings(int number){
        List<Sighting> sightings = sightingDao.getAllSightings();
        Collections.sort(sightings, new SortByDateDesc());
        if(number >= sightings.size()){
            return sightings;
        } else {
            List<Sighting> result = new ArrayList<>();
            for(int i=0;i<sightings.size();i++){
                result.add(sightings.get(i));
            }
            return result;
        }
    }
    public HashMap<Sighting, Hero> mapHeroSightings(List<Sighting> sightings){
        HashMap<Sighting, Hero> heroSightings = new HashMap<>();
        for(int i=0;i<sightings.size();i++){
            heroSightings.put(sightings.get(i), getHeroForSighting(sightings.get(i)));
        }
        return heroSightings;
    }
    
    public static final class SortByDateDesc implements Comparator<Sighting>{

        @Override
        public int compare(Sighting s1, Sighting s2) {
            return s2.getDate().compareTo(s1.getDate());
        }
        
    }
       
    
    public Hero getHeroForSighting(Sighting sighting){
        return heroDao.getHeroForSighting(sighting);
    }
    
    
    public Sighting getSightingById(int id){
        return sightingDao.getSightingById(id);      
    }
    public List<Sighting> getAllSightings(){
        return sightingDao.getAllSightings();
    }
    public Sighting addSighting(Sighting sighting){
        return sightingDao.addSighting(sighting);
    }
    public void updateSighting(Sighting sighting){
        sightingDao.updateSighting(sighting);
    }
    public void deleteSightingById(int id){
        sightingDao.deleteSightingById(id);
    }
}
