/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.Superhero.Repository;

import com.sg.Superhero.Model.Sighting;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cas
 */
@Repository
public interface SightingRepository  extends JpaRepository<Sighting,Integer>{
    Sighting findByDate(LocalDate date);
}
