/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvd.controller;

import com.mycompany.dvd.dao.DVDDao;
import com.mycompany.dvd.dao.DVDDaoException;
import com.mycompany.dvd.dao.DVDDaoFileImpl;
import com.mycompany.dvd.dto.Dvd;
import com.mycompany.dvd.ui.DVDView;
import com.mycompany.dvd.ui.UserIOConsoleImpl;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cas
 */
public class DVDController {

    DVDView view;
    DVDDao dao;

    public DVDController(DVDView view, DVDDao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {

            try {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        addDvd();
                        break;
                    case 2:
                        removeByTitle();
                        break;
                    case 3:
                        getEditedDvd();
                        break;
                    case 4:
                        listDvdInfo();
                        break;
                    case 5:
                        displaySingleDvd();
                        break;

                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        view.displayUnknownCommand();
                }
            } catch (DVDDaoException ex) {
                view.displayErrorMessage(ex.getMessage());
            }

        }
        view.displayExitMessage();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addDvd() throws DVDDaoException {
        view.displayCreateDvdBanner();
        Dvd newDVDInfo = view.getNewDVDInfo();
        dao.addDvd(newDVDInfo);
        view.displayCreateDVDSuccessBanner();
    }

    private void displaySingleDvd() throws DVDDaoException {

        String title = view.getTitle();
        Dvd movie = dao.getByTitle(title);
        view.displayMovie(movie);
    }

    private void listDvdInfo() throws DVDDaoException {

        List<Dvd> DVDInfoList = dao.getAllDvd();
        view.displayDvdList(DVDInfoList);
    }

    private void removeByTitle() throws DVDDaoException {

        view.displayRemoveTitleBanner();
        String title = view.getTitle();
        dao.removeByTitle(title);
        view.displayRemoveSuccessBanner();

    }

    private void getEditedDvd() throws DVDDaoException {
        view.displayEditDVDInfoBanner();
        String movieToEdit = view.getTitle();
        Dvd movie = dao.getByTitle(movieToEdit);

//        view.displayMovie(movie);
        Dvd editedMovie = view.getEditedDvd(movie);

        dao.editDvd(movieToEdit, editedMovie);

//1. get movieToEdit from user
        //2. ask the dao for the movie with that movieToEdit
        //3. IF there is such a movie, print out its information and get NEW dvd information
        //4. tell the dao to save the new information (and delete the old using the original movieToEdit)
    }

}
