package com.example.superhero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.superhero.dao.HeroDao;
import com.example.superhero.dao.LocationDao;
import com.example.superhero.dao.OrganizationDao;
import com.example.superhero.dao.SightingDao;
import com.example.superhero.dao.SuperpowerDao;
import com.example.superhero.models.Organization;

@Service
public class OrganizationService {
    
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
    

    public Organization createOrganization(String name, boolean attrHero, String description, String address, String contact, List<Hero> heros){
        Organization organization = new Organization();
        organization.setName(name);
        organization.setAttrHero(attrHero);
        organization.setDescription(description);
        organization.setAddress(address);
        organization.setContact(contact);
        organization.setMembers(heros);

        return organization;
    }
    
    
    public Organization getOrganizationById(int id){
        return organizationDao.getOrganizationById(id);      
    }

    public List<Organization> getAllOrganizations(){
        return organizationDao.getAllOrganizations();
    }
    
    public Organization addOrganization(Organization organization){
        return organizationDao.addOrganization(organization);
    }
    
    public void updateOrganization(Organization organization){
        organizationDao.updateOrganization(organization);
    }
    
    public void deleteOrganizationById(int id){
        organizationDao.deleteOrganizationById(id);
    }
}
