/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvd.dao;

import com.mycompany.dvd.dto.Dvd;
import java.util.List;

/**
 *
 * @author cas
 */
public interface DVDDao {
    public List<Dvd> getAllDvd() throws DVDDaoException;
    Dvd getByTitle (String Title)throws DVDDaoException;
    
    void removeByTitle (String Title)throws DVDDaoException;
    Dvd addDvd(Dvd toAdd)throws DVDDaoException;
    void editDvd(String oldtitle, Dvd toEdit)throws DVDDaoException;
}
