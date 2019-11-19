/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvd.dto;

/**
 *
 * @author cas
 */
public class Dvd {

    private String Title;
    private Integer Releasedate;
    private String MPAArating;
    private String Directorsname;
    private String Studio;
    private String Userrating;

    /**
     * @return the Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * @param Title the Title to set
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     * @return the Releasedate
     */
    public Integer getReleasedate() {
        return Releasedate;
    }

    /**
     * @param Releasedate the Releasedate to set
     */
    public void setReleasedate(Integer Releasedate) {
        this.Releasedate = Releasedate;
    }

    /**
     * @return the MPAArating
     */
    public String getMPAArating() {
        return MPAArating;
    }

    /**
     * @param MPAArating the MPAArating to set
     */
    public void setMPAArating(String MPAArating) {
        this.MPAArating = MPAArating;
    }

    /**
     * @return the Directorsname
     */
    public String getDirectorsname() {
        return Directorsname;
    }

    /**
     * @param Directorsname the Directorsname to set
     */
    public void setDirectorsname(String Directorsname) {
        this.Directorsname = Directorsname;
    }

    /**
     * @return the Studio
     */
    public String getStudio() {
        return Studio;
    }

    /**
     * @param Studio the Studio to set
     */
    public void setStudio(String Studio) {
        this.Studio = Studio;
    }

    /**
     * @return the Userrating
     */
    public String getUserrating() {
        return Userrating;
    }

    /**
     * @param Userrating the Userrating to set
     */
    public void setUserrating(String Userrating) {
        this.Userrating = Userrating;
    }

    public Object getByDVDId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
