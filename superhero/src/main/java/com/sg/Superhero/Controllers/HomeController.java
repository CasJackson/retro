/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.Superhero.Controllers;

import com.sg.Superhero.Model.EditHeroVM;
import com.sg.Superhero.Model.Hero;
import com.sg.Superhero.Model.Location;
import com.sg.Superhero.Model.Organization;
import com.sg.Superhero.Model.Power;
import com.sg.Superhero.Model.Sighting;
import com.sg.Superhero.Service.Service;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author cas
 */
@Controller

public class HomeController {

    @Autowired
    Service service;

    @GetMapping("/")
    public String landingPage(Model m) {
        List<Sighting> sightingList = service.getSightings();
        m.addAttribute("sightings", sightingList);
        return "Home";
    }

    @GetMapping("/Superheroes")
    public String getHero(Model pageModel) {

        List<Hero> heroList = service.getHeros();
        List<Power> powerList = service.getPowers();
       

        pageModel.addAttribute("heroes", heroList);
        pageModel.addAttribute("powers", powerList);
        
        return "Superheroes";
    }

    @PostMapping("/addHero")
    public String addHero(@Valid Hero hero, BindingResult result) {
        if (result.hasErrors()) {
            return "addHero";
        }
        service.addHero(hero);
        return "redirect:/Superheroes";
    }

    @GetMapping("/deleteHero")
    public String deleteHero(Integer id) {
        service.deleteHero(id);
        return "redirect:/Superheroes";
    }

    @GetMapping("/editHero/{id}")
    public String getHero2Edit(@PathVariable int id, Model pageModel) {

        Hero toEdit = service.getHeroById(id);

        pageModel.addAttribute("hero", toEdit);

        List<Power> powerList = service.getPowers();
        List<Organization> organizationList = service.getOrganization();

        pageModel.addAttribute("powers", powerList);
        pageModel.addAttribute("organizations", organizationList);
        return "editSuperheroes";
    }

    @PostMapping("/editHero")
    public String editHero(Hero hero, HttpServletRequest request) {

            String[] orgIds = request.getParameterValues("orgId");
        List<Organization> organizations = new ArrayList<>();
        for (String oId : orgIds) {
            Organization org = new Organization();
            org.setOrgid(Integer.parseInt(oId));
            organizations.add(org);
        }
        hero.setOrganization(organizations);
        service.editHero(hero);
        return "redirect:/Superheroes";
    }  
    
    
    
    
    
    
    
        @GetMapping("/Superpowers")
    public String getPower(Model pageModel) {

        List<Power> powerList = service.getPowers();
        pageModel.addAttribute("powers", powerList);
        return "Superpowers";
    }

    @PostMapping("/addPower")
    public String addPower(Power power) {
        service.addPower(power);
        return "redirect:/Superpowers";
    }

      @GetMapping("/editPower/{id}")
    public String getPower2Edit(@PathVariable int id, Model pageModel) {

        Power toEdit = service.getPowerById(id);

        pageModel.addAttribute("power", toEdit);

        List<Power> powerList = service.getPowers();
      

        pageModel.addAttribute("powers", powerList);
        
        return "editSuperpowers";
    }

    @PostMapping("/editPower")
    public String editPower(Power p, HttpServletRequest request) {

        String[] pIds = request.getParameterValues("powerId");
        List<Power> powers = new ArrayList<>();
        for (String pId : pIds) {
            p.setpowerId(Integer.parseInt(pId));
            powers.add(p);
        }
        
        service.editPower(p);
        return "redirect:/Superpowers";
    }  
    
     @GetMapping("/deletePower")
    public String deletePower(Integer id) {
        service.deletePower(id);
        return "redirect:/Superpowers";
    }

   
    
    
    
    
    
    
    @GetMapping("/Organizations")
    public String getOrganization(Model pageModel) {

        List<Organization> organizationList = service.getOrganization();
        pageModel.addAttribute("organizations", organizationList);

        return "Organizations";
    }

    @PostMapping("/addOrganization")
    public String addOrganization(Organization organization) {
        service.addOrganization(organization);
        return "redirect:/Organizations";
    }

    
      @GetMapping("/editOrganization/{id}")
    public String getOrganization2Edit(@PathVariable int id, Model pageModel) {

        Organization toEdit = service.getOrgById(id);

        pageModel.addAttribute("organization", toEdit);

        List<Organization> orgList = service.getOrganization();
      

        pageModel.addAttribute("organizations", orgList);
        
        return "editOrganizations";
    }

    @PostMapping("/editOrganization")
    public String editOrganization(Organization o, HttpServletRequest request) {

        String[] oIds = request.getParameterValues("orgId");
        List<Organization> organizations = new ArrayList<>();
        for (String oId : oIds) {
            o.setOrgid(Integer.parseInt(oId));
            organizations.add(o);
        }
        
        service.editOrganization(o);
        return "redirect:/Organizations";
    }  
    
    
    @GetMapping("/deleteOrganization")
    public String deleteOrganization(Integer id) {
        service.deleteOrganization(id);
        return "redirect:/Organizations";
    }

    
    
    
    
    
    
    
    @GetMapping("/Sightings")
    public String getSighting(Model pageModel) {

        List<Sighting> sightingList = service.getSightings();
        pageModel.addAttribute("sightings", sightingList);
        
          List<Hero> heroList = service.getHeros();
         pageModel.addAttribute("heroes", heroList);
        
        List <Location> toEdit = service.getLocation();
         pageModel.addAttribute("locations", toEdit);
       
        return "Sightings";
    }

    @PostMapping("/addSighting")
    public String addSighting(Sighting sighting) {
        service.addSighting(sighting);
        return "redirect:/Sightings";
    }

    @GetMapping("/deleteSighting")
    public String deleteSighting(Integer id) {
        service.deleteSighting(id);
        return "redirect:/Sightings";
    }

          @GetMapping("/editSighting/{id}")
    public String getSighting2Edit(@PathVariable int id, Model pageModel) {

         Sighting toEdit = service.getSightingById(id);

        pageModel.addAttribute("sighting", toEdit);

        List<Sighting> sightingList = service.getSightings();
      

        pageModel.addAttribute("Sightings", sightingList);
        
        return "editSightings";
    }

    @PostMapping("/editSighting")
    public String editSightng(Sighting s, HttpServletRequest request) {

            String[] sIds = request.getParameterValues("sightingId");
        List<Sighting> sightings = new ArrayList<>();
        for (String sId : sIds) {
            s.setSightingId(Integer.parseInt(sId));
            sightings.add(s);
        }
        
        service.editSighting(s);
        return "redirect:/Sightings";
    }  
    
    
    
    
    
    
    
    @GetMapping("/Locations")
    public String getLocation(Model pageModel) {

        List<Location> locationList = service.getLocation();
        pageModel.addAttribute("locations", locationList);

        return "Locations";
    }

    @PostMapping("/addLocation")
    public String addLocation(Location location) {
        service.addLocation(location);
        return "redirect:/Locations";
    }

       @GetMapping("/editLocation/{id}")
    public String getlocation2Edit(@PathVariable int id, Model pageModel) {

        Location toEdit = service.getLocationById(id);

        pageModel.addAttribute("location", toEdit);

        List<Location> locationList = service.getLocation();
      

        pageModel.addAttribute("locations", locationList);
        
        return "editLocations";
    }

    @PostMapping("/editLocation")
    public String editLocation(Location loc, HttpServletRequest request) {

        String[] lIds = request.getParameterValues("locationId");
        List<Location> locations = new ArrayList<>();
        for (String lId : lIds) {
            loc.setLocationId(Integer.parseInt(lId));
            locations.add(loc);
        }
        
        service.editLocation(loc);
        return "redirect:/Locations";
    }  
    
    @GetMapping("/deleteLocation")
    public String deleteLocatin(Integer id) {
        service.deleteLocation(id);
        return "redirect:/Locations";
    }

}
