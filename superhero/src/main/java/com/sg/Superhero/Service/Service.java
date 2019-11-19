/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.Superhero.Service;

import com.sg.Superhero.Model.Hero;
import com.sg.Superhero.Model.Location;
import com.sg.Superhero.Model.Organization;
import com.sg.Superhero.Model.Power;

import com.sg.Superhero.Model.Sighting;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 *
 * @author cas
 */
public interface Service {
   public List<Hero> getHeros(); 
   
   public Hero getHeroById(Integer id);
   
  
           public Hero addHero(Hero hero);

    public List<Sighting> getSightings();

    public void deleteHero(Integer id);

    public Hero editHero(Hero hero);
    
   public List<Power> getPowers();
   
   public Power getPowerById(Integer id);
   
    public Power editPower(Power power);
   
   public List<Organization> getOrganization();
   
   public List<Location> getLocation();

    public Power addPower(Power power);

    public Organization addOrganization(Organization organization);

    public Sighting addSighting(Sighting sighting);

    public Location addLocation(Location location);

    public void deletePower(Integer id);

    public void deleteOrganization(Integer id);

    public void deleteSighting(Integer id);

    public void deleteLocation(Integer id);

    public Organization editOrganization(Organization o);

    public Organization getOrgById(Integer id);

    public Location getLocationById(Integer id);

    public Location editLocation(Location loc);

    public Sighting getSightingById(Integer id);

    public Sighting editSighting(Sighting s);
   
}
