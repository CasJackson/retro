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
import com.sg.Superhero.Repository.HeroRepository;
import com.sg.Superhero.Repository.LocationRepository;
import com.sg.Superhero.Repository.OrganizationRepository;
import com.sg.Superhero.Repository.PowerRepository;
import com.sg.Superhero.Repository.SightingRepository;
import java.util.List;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 *
 * @author cas
 */
@Component
@PersistenceContext(type = PersistenceContextType.EXTENDED)
public class ServiceImpl implements Service {

    @Autowired
    HeroRepository heroes;

    @Autowired
    LocationRepository locations;

    @Autowired
    OrganizationRepository organizations;

    @Autowired
    PowerRepository powers;

    @Autowired
    SightingRepository sightings;
    
    @Override
    public List<Hero> getHeros() {

      List<Hero> sHero = heroes.findAll();
        return sHero;
    }

    @Override
    public Hero addHero(Hero hero) {
        Hero sHero = heroes.save(hero);
        return sHero;
    }

    @Override
    public void deleteHero(Integer id) {
        heroes.deleteById(id);

    }

    @Override
    public Hero editHero(Hero hero) {
       
       int id = hero.getHeroId();
       
       List <Hero> names = heroes.findAll();
       
       for (int i = 0; i < names.size(); i++){
           int oldId = names.get(i).getHeroId();
                    
         if  (oldId == id){ 
             names.remove(i);
             names.add(hero);
            
               }
    }
      
        
        Hero heroEdit = heroes.save(hero);
        return heroEdit;
    }

    @Override
    public Hero getHeroById(Integer id) {
        Hero oneHero = heroes.getOne(id);
        return oneHero;
    }

    @Override
    public List<Sighting> getSightings() {
        List<Sighting> sightingList = sightings.findAll();
        return sightingList;
    }

    @Override
    public List<Power> getPowers() {
        List<Power> powerList = powers.findAll();
        return powerList;
    }

    @Override
    public List<Organization> getOrganization() {
        List<Organization> organizationList = organizations.findAll();
        return organizationList;
    }

    @Override
    public List<Location> getLocation() {
        List<Location> locationList = locations.findAll();
        return locationList;
    }

    @Override
    public Power addPower(Power power) {
        Power sPower = powers.save(power);
        return sPower;

    }

    @Override
    public Organization addOrganization(Organization organization) {
        Organization sOrganization = organizations.save(organization);
        return sOrganization;
    }

    @Override
    public Sighting addSighting(Sighting sighting) {
        Sighting sSighting = sightings.save(sighting);
        return sSighting;
    }

    @Override
    public Location addLocation(Location location) {
        Location sLocation = locations.save(location);
        return sLocation;
    }

    @Override
    public void deletePower(Integer id) {
        powers.deleteById(id);
    }

    @Override
    public void deleteOrganization(Integer id) {
        organizations.deleteById(id);
    }

    @Override
    public void deleteSighting(Integer id) {
        sightings.deleteById(id);
    }

    @Override
    public void deleteLocation(Integer id) {
        locations.deleteById(id);
    }

    @Override
    public Power getPowerById(Integer id) {
         Power onePower = powers.getOne(id);
         return onePower;
        }
 @Override
    public Power editPower(Power power) {
       
       int id = power.getPowerId();
       
       List <Power> names = powers.findAll();
       
       for (int i = 0; i < names.size(); i++){
           int oldId = names.get(i).getPowerId();
                    
         if  (oldId == id){ 
             names.remove(i);
             names.add(power);
            
               }
    }
      
        
        Power powerEdit = powers.save(power);
        return powerEdit;
    }

    @Override
    public Organization editOrganization(Organization o) {
         int id = o.getOrgId();
       
       List <Organization> names = organizations.findAll();
       
       for (int i = 0; i < names.size(); i++){
           int oldId = names.get(i).getOrgId();
                    
         if  (oldId == id){ 
             names.remove(i);
             names.add(o);
            
               }
    }
      
        
        Organization oEdit = organizations.save(o);
        return oEdit;
    }

    @Override
    public Organization getOrgById(Integer id) {
        Organization oneOrg = organizations.getOne(id);
        return oneOrg;
    }

    @Override
    public Location getLocationById(Integer id) {
        Location oneLoc = locations.getOne(id);
        return oneLoc;
    }

    @Override
    public Location editLocation(Location loc) {
             int id = loc.getLocationId();
       
       List <Location> names = locations.findAll();
       
       for (int i = 0; i < names.size(); i++){
           int oldId = names.get(i).getLocationId();
                    
         if  (oldId == id){ 
             names.remove(i);
             names.add(loc);
            
               }
    }
      
        
        Location locEdit = locations.save(loc);
        return locEdit;
    }

    @Override
    public Sighting getSightingById(Integer id) {
         Sighting oneSighting = sightings.getOne(id);
         return oneSighting;
                 
    }

    @Override
    public Sighting editSighting(Sighting s) {
        
                   int id = s.getSightingId();
       
       List <Sighting> names = sightings.findAll();
       
       for (int i = 0; i < names.size(); i++){
           int oldId = names.get(i).getSightingId();
                    
         if  (oldId == id){ 
             names.remove(i);
             names.add(s);
            
               }
    }
      
        
        Sighting sEdit = sightings.save(s);
        return sEdit;
    }


    }

