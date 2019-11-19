/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.Superhero.Model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 *
 * @author cas
 */
@Entity
@Proxy(lazy=false)      
public class Sighting implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id 
    private int sightingId;
    
    @ManyToOne
    @JoinColumn(name = "locationId", nullable = false)
    private Location location;
    @Column(nullable = false)
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate date;
    @ManyToOne 
    @JoinColumn(name = "heroId")
    private Hero sightedHero;
    
    /**
     * @return the sightingId
     */
    public int getSightingId() {
        return sightingId;
    }

    /**
     * @param sightingId the sightingId to set
     */
    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return the sightedHero
     */
    public Hero getSightedHero() {
        return sightedHero;
    }

    /**
     * @param sightedHero the sightedHero to set
     */
    public void setSightedHero(Hero sightedHero) {
        this.sightedHero = sightedHero;
    }

    /**
     * @return the locationId
     */
}
