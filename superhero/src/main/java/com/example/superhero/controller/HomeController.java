package com.example.superhero.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.superhero.models.Hero;
import com.example.superhero.models.Sighting;
import com.example.superhero.service.HomeService;
import com.example.superhero.service.SightingService;

@Controller
public class HomeController {
    
    private final SightingService sightingService;
    private final HomeService homeService;


    public HomeController(SightingService sightingService, HomeService homeService){
        this.sightingService = sightingService;
        this.homeService = homeService;
    }
    
    
    private String googleMapUrl;
    private ArrayList<String> insightsList;
    private HashMap<String,Integer> insights;
    
    @GetMapping("/")
    public String displayIndex(Model model) {
        final int SIGHTINGS_PER_PAGE = 10;
        List<Sighting> sightings = sightingService.getLastSightings(SIGHTINGS_PER_PAGE);
        HashMap<Sighting,Hero> heroSightings = sightingService.mapHeroSightings(sightings);
        model.addAttribute("sightings", sightings);
        model.addAttribute("heroSightings", heroSightings);
        
        googleMapUrl = homeService.createGoogleMapUrl(heroSightings);
        
        model.addAttribute("googleMapUrl", googleMapUrl);
        
        insights = new HashMap<>();
        insightsList = new ArrayList<>();
        
        final String NUMBER_SUPERHEROS = "Number of Superheroes";
        final String NUMBER_SUPERVILLAIN = "Number of Supervillains";
        final String NUMBER_HERO_ORGANIZATION = "Number of Hero Organizations";
        final String NUMBER_VILLAIN_ORGANIZATION = "Number of Villain Organizations";
        final String NUMBER_LOCATIONS = "Number of Locations";
        final String NUMBER_SIGHTINGS = "Number of Sightings";
        final String NUMBER_SUPERPOWER = "Number of Superpowers";
        
        insights.put(NUMBER_SUPERHEROS,homeService.getNumberOfSuperheros());
        insightsList.add(NUMBER_SUPERHEROS);
        
        insights.put(NUMBER_SUPERVILLAIN,homeService.getNumberOfSupervillains());
        insightsList.add(NUMBER_SUPERVILLAIN);
        
        insights.put(NUMBER_HERO_ORGANIZATION,homeService.getNumberOfHeroOrganization());
        insightsList.add(NUMBER_HERO_ORGANIZATION);
        
        insights.put(NUMBER_VILLAIN_ORGANIZATION,homeService.getNumberOfVillainOrganization());
        insightsList.add(NUMBER_VILLAIN_ORGANIZATION);
        
        insights.put(NUMBER_LOCATIONS,homeService.getNumberOfLocations());
        insightsList.add(NUMBER_LOCATIONS);
        
        insights.put(NUMBER_SIGHTINGS,homeService.getNumberOfSightings());
        insightsList.add(NUMBER_SIGHTINGS);
        
        insights.put(NUMBER_SUPERPOWER,homeService.getNumberOfSuperpowers());
        insightsList.add(NUMBER_SUPERPOWER);    
        
        model.addAttribute("insightsList",insightsList);
        model.addAttribute("insights",insights);
        
        
        return "index";
    }
}
