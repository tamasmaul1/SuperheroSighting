package com.example.superhero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.superhero.dao.HeroDao;
import com.example.superhero.dao.LocationDao;
import com.example.superhero.dao.OrganizationDao;
import com.example.superhero.dao.SightingDao;
import com.example.superhero.dao.SuperpowerDao;
import com.example.superhero.models.Hero;
import com.example.superhero.models.Superpower;

@Service
public class SuperpowerService {
    
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
    
    
    public Superpower createSuperpower(String name, String description){
        Superpower superpower = new Superpower();
        superpower.setName(name);
        superpower.setDescription(description);

        return superpower;
    }
    
     
    public List<Hero> getHerosForSuperpower(Superpower superpower){
        return heroDao.getHerosForSuperpower(superpower);
    }
    
    
    public Superpower getSuperpowerById(int id){
        return superpowerDao.getSuperpowerById(id);      
    }
    public List<Superpower> getAllSuperpowers(){
        return superpowerDao.getAllSuperpowers();
    }
    public Superpower addSuperpower(Superpower superpower){
        return superpowerDao.addSuperpower(superpower);
    }
    public void updateSuperpower(Superpower superpower){
        superpowerDao.updateSuperpower(superpower);
    }
    public void deleteSuperpowerById(int id){
        superpowerDao.deleteSuperpowerById(id);
    }
}
