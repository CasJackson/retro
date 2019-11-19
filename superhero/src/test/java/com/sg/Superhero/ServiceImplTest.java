package com.sg.Superhero;

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
import com.sg.Superhero.Service.Service;
import com.sg.Superhero.Service.ServiceImpl;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cas
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfiguration.class)
@Component
@PersistenceContext(type = PersistenceContextType.EXTENDED)
public class ServiceImplTest {

    @Autowired
    Service service;
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

    // write test cases here
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    public void tearDown() {
    }

    
    
    
    
  @Test
    public void getHeros() {

        List<Hero> validate = service.getHeros();
        Hero toTest = service.getHeros().get(0);

        assertEquals(1, validate.size());
        assertEquals(1, toTest.getHeroId());
        assertEquals("batman", toTest.getName());
    }
 
      @Test
    public void getSightings() {
        List<Sighting> validate = service.getSightings();
        Hero toTest = service.getHeros().get(0);
        
        assertEquals(2, validate.size());
        assertEquals(1, toTest.getHeroId());
        assertEquals("batman", toTest.getName());
    }

    @Test
    public void getPowers() {
        List<Power> validate = service.getPowers();

        assertEquals(2, validate.size());
    }

    @Test
    public void getOrganization() {
        List<Organization> validate = service.getOrganization();
        Organization toTest = service.getOrganization().get(0);
        
        assertEquals(1, validate.size());
        assertEquals("The Avengers", toTest.getName());
    }

    @Test
    public void getLocation() {
        List<Location> validate = service.getLocation();
        Hero toTest = service.getHeros().get(0);

        assertEquals(2, validate.size());
        assertEquals(1, toTest.getHeroId());
        assertEquals("batman", toTest.getName());
    }
    
    
    
    
    
    
    
      @Test
    public void getHeroById() {
        Hero validate = service.getHeroById(1);
        
        assertEquals(1, validate.getHeroId());
        assertEquals("batman", validate.getName());
        assertEquals("lowTalker", validate.getDescription());
    }
    
    
     @Test
    public void getSightingById() {
        Sighting validate = service.getSightingById(2);
        Location kowalski = service.getSightingById(2).getLocation();
        
        
        assertEquals(2, validate.getSightingId());
        assertEquals(1, kowalski.getLocationId());

    }

        @Test
    public void getPowerById() {
        Power validate = service.getPowerById(1);

        assertEquals(1, validate.getPowerId());
        assertEquals("superSpeed", validate.getName());
    }

     @Test
    public void getOrgById() {
        Organization validate = service.getOrgById(1);

        assertEquals(1, validate.getOrgId());
        assertEquals("The Avengers", validate.getName());
        assertEquals("goodGuys", validate.getDescription());
    }

    @Test
    public void getLocationById() {
        Location validate = service.getLocationById(1);

        assertEquals(1, validate.getLocationId());
        assertEquals("Whole Foods", validate.getName());
    }

    
    
    
    
    
   
  @Test
    public void addHero() {
        Hero toTest = new Hero();

        toTest.setName("goku");
        Power toTestP = new Power();
        

        toTestP.setpowerId(1);
        toTest.setPower(toTestP);
        toTest.setDescription("lowTalker");
        service.addHero(toTest);
        
        Hero validate = heroes.getOne(2);
        
        assertEquals(2, validate.getHeroId());
        assertEquals("lowTalker", validate.getDescription());
        
    }

   

    @Test
        public void addPower() {
              Power toTest = new Power();

        toTest.setName("climateControl");
       
        

        
        service.addPower(toTest);
        Power validate = powers.getOne(3);
        
        assertEquals(3, validate.getPowerId());
        assertEquals("climateControl", validate.getName());
        
    }

    @Test
        public void addOrganization() {
        Organization toTest = new Organization();

        toTest.setName("The Herd");
       
        

        
        service.addOrganization(toTest);
        Organization validate = organizations.getOne(2);
        
        assertEquals(2, validate.getOrgId());
        assertEquals("The Herd", validate.getName());
    }


    @Test
        public void addLocation() {
              Location toTest = new Location();

        toTest.setName("Target Center");
        toTest.setAddress(23456);
      
        BigDecimal latitude = new BigDecimal(41.8789);
        toTest.setLatitude(latitude);
        
        BigDecimal longitude = new BigDecimal(87.6359);
        toTest.setLongitude(longitude);
        

        
        service.addLocation(toTest);
        Location validate = locations.getOne(3);
        
        assertEquals(3, validate.getLocationId());
        assertEquals("Target Center", validate.getName());
        
    }

        
   
        
        
        
       
        
        @Test
    public void editHero() {
        Hero toEdit = heroes.getOne(2);

        toEdit.setName("Creepy");
         
        Power power = powers.getOne(2);
        
        toEdit.setPower(power);
        service.editHero(toEdit);

        
        heroes.save(toEdit);
        Hero validate = heroes.getOne(2);
        
        assertEquals("Creepy", validate.getName());
        assertEquals(2,validate.getPower().getPowerId());
    }

  
    
    @Test
    public void editPower() {

        Power toEdit = powers.getOne(1);
            
         toEdit.setName("superSpeed");
        service.editPower(toEdit);    
       

       
       
         powers.save(toEdit);
         Power validate = powers.getOne(1);
         
         assertEquals(1,validate.getPowerId());
        assertEquals("superSpeed", validate.getName());
        
    }

    @Test
        public void editOrganization() {
            Organization toEdit = organizations.getOne(1);
            
              toEdit.setName("The Goonies");
        service.editOrganization(toEdit); 
            
      
       
       
       
     
         organizations.save(toEdit);
            Organization validate = organizations.getOne(1);
            
         assertEquals(1,validate.getOrgId());   
         assertEquals("The Goonies", validate.getName());
         assertEquals("goodGuys", validate.getDescription());
        
    }


   
    @Test
        public void editLocation() {
            
            Location toEdit = locations.getOne(1);
            toEdit.setName("Whole Foods");
            service.editLocation(toEdit);

       locations.save(toEdit);
       Location validate = locations.getOne(1);
       
        assertEquals(1,validate.getLocationId());
        assertEquals("Whole Foods", validate.getName());
    }


  
        
        
        
        
        
           @Test
    public void deleteHero() {

        
        
        heroes.deleteById(2);
        
      List <Hero> validate = heroes.findAll();
        assertEquals(1,  validate.size());
    }
    
        
   @Test
    public void deleteOrganization() {

        
        
        organizations.deleteById(2);
      
        List<Organization> validate = organizations.findAll();
        assertEquals(1, validate.size());
    }
    @Test
    public void deletePower() {

        
        
        powers.deleteById(3);
        
        
        List<Power> validate = powers.findAll();    
        assertEquals(2, validate.size());
    }
    
    
    
    

 @Test
    public void deleteLocation() {
        Location toDelete = locations.getOne(2);
       
        toDelete.getName();
       
        service.deleteLocation(2);
           
        
        List<Location> validate = locations.findAll();    
        assertEquals(1, validate.size());
    }



    
 
    
        @Test
        public void addSighting() {
        Sighting toTest = new Sighting();
        
        Hero batman = heroes.getOne(1);

        toTest.setSightedHero(batman);
        LocalDate date = LocalDate.of( 2014 , 2 , 11 );
        toTest.setDate(date);
        Location location = locations.getOne(1);
        toTest.setLocation(location);
        
        
        service.addSighting(toTest);
        Sighting validate = sightings.getOne(2);
        
        assertEquals(2, validate.getSightingId());
        assertEquals(1, validate.getLocation().getLocationId());
    }

      @Test
        public void editSighting() {
            
            Sighting toEdit = sightings.getOne(2);
            Location SearsTower = locations.getOne(1);
            
             toEdit.setLocation(SearsTower);
            service.editSighting(toEdit);

         sightings.save(toEdit);
       Sighting validate = sightings.getOne(2);
       assertEquals(1, validate.getLocation().getLocationId());
       assertEquals(2, validate.getSightingId());
       assertEquals(1, validate.getSightedHero().getHeroId());
    }


    
     @Test
    public void deleteSighting() {

        sightings.deleteById(1);
        
        List<Sighting> validate = sightings.findAll();
        assertEquals(1, validate.size());
    }
    
    

}
