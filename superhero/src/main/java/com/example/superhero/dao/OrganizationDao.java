package com.example.superhero.dao;

import java.util.List;

import com.example.superhero.models.Hero;
import com.example.superhero.models.Organization;

public interface OrganizationDao {

    Organization getOrganizationById(int id);
    List<Organization> getAllOrganizations();

    Organization addOrganization(Organization organization);

    void updateOrganization(Organization organization);

    void deleteOrganizationById(int id);
    
    List<Organization> getOrganizationsForHero(Hero hero);
}
