package com.example.superhero.service;

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
import com.example.superhero.models.Organization;
import com.example.superhero.models.Sighting;

@Service
public class HomeService {
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
    
    public String createGoogleMapUrl(HashMap<Sighting,Hero> heroSightings){
    	
        final String BASE_URL = "https://maps.googleapis.com/maps/api/staticmap?zoom=10&size=400x400&maptype=roadmap";
        
        String markers = "";
        if(heroSightings != null){
            for(Sighting sighting : heroSightings.keySet()){
                markers += "&markers="
                        + "label:"
                        + heroSightings.get(sighting).getName().charAt(0)
                        + "%7C"
                        + sighting.getLocation().getLatitude()
                        + ","
                        + sighting.getLocation().getLongitude();
            }
        }
        
        final String url = BASE_URL + markers + "&key=" + getApiKey();
        
        return url;
    }
    
    private String getApiKey(){
        final String GOOGLE_MAPS_API_KEY = "";
        return GOOGLE_MAPS_API_KEY;
    }
    
    public int getNumberOfSuperheros(){
        int numberOfSuperheros = 0;
        List<Hero> heros = heroDao.getAllHeros();
        for(Hero hero : heros){
            if(hero.getAttrHero()){
                numberOfSuperheros++;
            }
        }
        return numberOfSuperheros;
    }
    
    public int getNumberOfSupervillains(){
        int numberOfSupervillains = 0;
        List<Hero> heros = heroDao.getAllHeros();
        for(Hero hero : heros){
            if(!hero.getAttrHero()){
                numberOfSupervillains++;
            }
        }
        return numberOfSupervillains;
    }
    
    public int getNumberOfHeroOrganization(){
        int numberOfHeroOrganization = 0;
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for(Organization organization : organizations){
            if(organization.getAttrHero()){
                numberOfHeroOrganization++;
            }
        }
        return numberOfHeroOrganization;
    }
    
    public int getNumberOfVillainOrganization(){
        int numberOfVillainOrganization = 0;
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for(Organization organization : organizations){
            if(!organization.getAttrHero()){
                numberOfVillainOrganization++;
            }
        }
        return numberOfVillainOrganization;
    }
    
    public int getNumberOfLocations(){
        return locationDao.getAllLocations().size();
    }
    
    public int getNumberOfSightings(){
        return sightingDao.getAllSightings().size();
    }
    
    public int getNumberOfSuperpowers(){
        return superpowerDao.getAllSuperpowers().size();
    }
}
