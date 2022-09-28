package com.example.superhero.dao;

import java.util.List;

import com.example.superhero.models.Superpower;

public interface SuperpowerDao {
    
    Superpower getSuperpowerById(int id);
    List<Superpower> getAllSuperpowers();

    Superpower addSuperpower(Superpower superpower);

    void updateSuperpower(Superpower superpower);

    void deleteSuperpowerById(int id);
}
