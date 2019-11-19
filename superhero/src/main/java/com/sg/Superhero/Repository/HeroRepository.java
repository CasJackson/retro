/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.Superhero.Repository;

import com.sg.Superhero.Model.Hero;
import com.sg.Superhero.Model.Location;
import com.sg.Superhero.Model.Organization;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cas
 */
@Repository
public interface HeroRepository extends JpaRepository<Hero,Integer>{
    // do non crud methods for each repository and start serviceLayer
  
    }

