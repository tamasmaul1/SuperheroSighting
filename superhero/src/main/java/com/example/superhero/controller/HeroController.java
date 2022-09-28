package com.example.superhero.controller;

import com.example.superhero.models.Hero;
import com.example.superhero.models.Organization;
import com.example.superhero.models.Sighting;
import com.example.superhero.models.Superpower;
import com.example.superhero.service.HeroService;
import com.example.superhero.service.SuperpowerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HeroController {

    private final HeroService heroService;
    private final SuperpowerService superpowerService;

    
    public HeroController(HeroService heroService, SuperpowerService superpowerService){
        this.heroService = heroService;
        this.superpowerService = superpowerService;
    }
    
    Set<ConstraintViolation<Hero>> violations = new HashSet<>();
    private boolean displayImg = false;
    
    @GetMapping("heroes")
    public String displayHeroes(Model model) {
        List<Hero> heros = heroService.getAllHeros();
        model.addAttribute("heros", heros);
        return "heroes";
    }
    
    @GetMapping("/heroes/addHero")
    public String displayAddHeroes(Model model) {
        List<Superpower> superpowers = superpowerService.getAllSuperpowers();
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("errors", violations);
        return "/heroes/addHero";
    }
    
    @PostMapping("/heroes/addHero")
    public String addHero(HttpServletRequest request, Model model, @RequestParam("image") MultipartFile multipartFile){
        violations.clear();
        
        String name = request.getParameter("heroName");
        boolean attrHero = Boolean.parseBoolean(request.getParameter("attrHero"));
        String description = request.getParameter("heroDescription");
        String[] superpowerIds = request.getParameterValues("superpowerId");
        
        List<Superpower> superpowers = new ArrayList<>();
        if(superpowerIds != null){
            for(String superpowerId : superpowerIds) {
                superpowers.add(superpowerService.getSuperpowerById(Integer.parseInt(superpowerId)));
            }
        }
        
        Hero hero = heroService.createHero(name,attrHero,description,superpowers);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(hero);
        if(violations.isEmpty()) {
            heroService.addHero(hero);
        }
        
        if(!multipartFile.isEmpty()){
            String fileName = hero.getId()+"";

            try {
                heroService.uploadFile(fileName, multipartFile);
            } catch (IOException ex) {
                System.out.println("File could not be saved");
            }
        }
        
        model.addAttribute("errors", violations);
        
        return "redirect:/heroes/addHero";
    }
    
    @GetMapping("/heroes/deleteHero")
    public String deleteHero(Integer id) {
        heroService.deleteHeroById(id);
        return "redirect:/heroes";
    }
    
    @GetMapping("/heroes/editHero")
    public String displayEditHero(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        
        Hero hero = heroService.getHeroById(id);
        model.addAttribute("hero", hero);
        
        List<Superpower> superpowers = superpowerService.getAllSuperpowers();
        model.addAttribute("superpowers", superpowers);
        
        model.addAttribute("errors", violations);
        
        return "heroes/editHero";
    }
    
    @PostMapping("/heroes/editHero")
    public String editHero(HttpServletRequest request, Model model, @RequestParam("image") MultipartFile multipartFile){
        violations.clear();
        int id = Integer.parseInt(request.getParameter("heroId"));
        String name = request.getParameter("heroName");
        boolean attrHero = Boolean.parseBoolean(request.getParameter("attrHero"));
        String description = request.getParameter("heroDescription");
        String[] superpowerIds = request.getParameterValues("superpowerId");
        
        List<Superpower> superpowers = new ArrayList<>();
        if(superpowerIds != null){
            for(String superpowerId : superpowerIds) {
                superpowers.add(superpowerService.getSuperpowerById(Integer.parseInt(superpowerId)));
            }
        }
        
        List<Sighting> sightingTemp = heroService.getHeroById(id).getSightings();
        Hero hero = heroService.createHero(name,attrHero,description,superpowers);
        hero.setSightings(sightingTemp);
        hero.setId(id);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(hero);
        if(violations.isEmpty()) {
            heroService.updateHero(hero);
            if(!multipartFile.isEmpty()){
                String fileName = hero.getId()+"";

                try {
                    heroService.uploadFile(fileName, multipartFile);
                } catch (IOException ex) {
                    System.out.println("File could not be saved");
                }
            }
            return "redirect:/heroes";
        } else {
            hero = heroService.getHeroById(hero.getId());
            model.addAttribute("hero", hero);

            superpowers = superpowerService.getAllSuperpowers();
            model.addAttribute("superpowers", superpowers);
            model.addAttribute("errors", violations);
            return "heroes/editHero";
        }
    }
    
    @GetMapping("heroes/detailsHero")
    public String displayDetailsHero(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        
        Hero hero = heroService.getHeroById(id);
        model.addAttribute("hero", hero);
        
        List<Organization> organizations = heroService.getOrganizationsForHero(hero);
        model.addAttribute("organizations", organizations);  
        
        displayImg = heroService.isImageSet(hero.getId()+"");
        model.addAttribute("displayImg",displayImg);
        
        return "heroes/detailsHero";
    }
}
