/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.Superhero.Model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Proxy;

/**
 *
 * @author cas
 */
@Entity
@Table(name = "hero")
@Proxy(lazy=false)  
public class Hero implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int heroId;
    
    @Column(nullable = false)
    @NotBlank(message = "Name must not be empty.")
    @Size(max = 20, message = "Name must be less than 30 characters.")
    private String name;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "powerId")
    private Power power;
    @ManyToMany
    @JoinTable(name = "heroOrganization",
            joinColumns = {
                @JoinColumn(name = "heroId")},
            inverseJoinColumns = {
                @JoinColumn(name = "orgId")})
    private List<Organization> organizations;

    /**
     * @return the heroId
     */
    public int getHeroId() {
        return heroId;
    }

    /**
     * @param heroid the heroid to set
     */
    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the power
     */
    public Power getPower() {
        return power;
    }

    /**
     * @param power the power to set
     */
    public void setPower(Power power) {
        this.power = power;
    }

    /**
     * @return the organization
     */
    public List<Organization> getOrganizations() {
        return organizations;
    }

    /**
     * @param organization the organization to set
     */
    public void setOrganization(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public Boolean heroInOrg (Integer orgId){
        
        for (int i =0; i< organizations.size(); i++) {
          int id = organizations.get(i).getOrgId();
          if(id == orgId){
             return true; 
          }
        }
            
        return false;
    }
    
}
