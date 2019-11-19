/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvd.ui;

import com.mycompany.dvd.controller.DVDController;
import com.mycompany.dvd.dto.Dvd;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author cas
 */
public class DVDView {

    UserIO io = new UserIOConsoleImpl();
    Scanner scn = new Scanner(System.in);

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add a DVD to the Collection");
        io.print("2. Remove a DVD from the collection ");
        io.print("3. Edit info for existing collection ");
        io.print("4. List the DVD's in a collection");
        io.print("5. Display info for a particular DVD");
        io.print("6. Exit ");
        return io.readInt("Please select from the above choices.", 1, 6);

    }

    public Dvd getNewDVDInfo() {

        String Title = io.readString("Please enter Title");
        Integer ReleaseDate = io.readInt("Please enter Release Date");
        String MPAARating = io.readString("Please enter MPAA rating");
        String Directorsname = io.readString("Please enter Director's name");
        String Studio = io.readString("Please enter Studio name");
        String Userrating = io.readString("Please enter Userrating");

        Dvd currentDVDInfo = new Dvd();
        currentDVDInfo.setTitle(Title);
        currentDVDInfo.setReleasedate(ReleaseDate);
        currentDVDInfo.setMPAArating(MPAARating);
        currentDVDInfo.setDirectorsname(Directorsname);
        currentDVDInfo.setStudio(Studio);
        currentDVDInfo.setUserrating(Userrating);
        return currentDVDInfo;
    }

    public void displayDvdList(List<Dvd> DVDInfoList) {
        for (Dvd currentDVDInfo : DVDInfoList) {
            io.print("Movie Title: " + currentDVDInfo.getTitle());
            io.print("Release Date: " + currentDVDInfo.getReleasedate());

        }
        io.readString("Please hit enter to continue.");
    }

    public Dvd getEditedDvd(Dvd movie) {

        //       io.print("=== Edit DVD Info ===");
        if (movie != null) {
            String Title = io.readString("Please enter new Title .");
            Integer ReleaseDate = io.readInt("Please enter new Release Date");
            String MPAARating = io.readString("Please enter new MPAA rating");
            String Directorsname = io.readString("Please enter new Director's name");
            String Studio = io.readString("Please enter new Studio name");
            String Userrating = io.readString("Please enter new "
                    + "Userrating");

            Dvd currentDVDInfo = new Dvd();
            currentDVDInfo.setTitle(Title);
            currentDVDInfo.setReleasedate(ReleaseDate);
            currentDVDInfo.setMPAArating(MPAARating);
            currentDVDInfo.setDirectorsname(Directorsname);
            currentDVDInfo.setStudio(Studio);
            currentDVDInfo.setUserrating(Userrating);

            io.readString("DVD Info sucessfully edited. Please hit enter to continue.");
            return currentDVDInfo;
        } else {
            System.out.println("Error: Unknown Movie Title!");

        }
        return movie;
    }

    public void displayMovie(Dvd movie) {

        if (movie != null) {
            io.print("Movie Title: " + movie.getTitle());
            io.print("Release Date: " + movie.getReleasedate());
            io.print("MPAA rting: " + movie.getMPAArating());
            io.print("Directors name: " + movie.getDirectorsname());
            io.print("Studio: " + movie.getStudio());
            io.print("User rating: " + movie.getUserrating());

            io.readString("Please hit enter to continue.");

        } else {
            System.out.println("Error: Unknown Movie Title!");

        }

    }

    public void displayCreateDvdBanner() {
        io.print("=== Store DVD profile ===");
    }

    public void displayCreateDVDSuccessBanner() {
        io.readString(
                "DVD profile was successfully created.  Please hit enter to continue");
    }

    public void displayRemoveTitleBanner() {
        io.print("=== Remove Title ===");
    }

    public void displayRemoveSuccessBanner() {
        io.print("===Title successfully removed===");
        io.readString(" Please hit enter to continue.");
    }

    public void displayEditDVDInfoBanner() {
        io.print("=== Edit DVD Info ===");
    }

    public void displayserachByTitleBanner() {
        io.print("=== Search by Title ===");
    }

    public void displayserachByTitleSucessBanner() {
        io.readString("===Title found===");
    }

    public String getTitle() {
        String title = io.readString("Enter Title");
        return title;
    }

    public void displayErrorMessage(String message) {
        io.print("Error:");
    }

    public void displayUnknownCommand() {
        io.print("Unknown command:");
    }

    public void displayExitMessage() {
        io.print("goodbye");
    }

    public void displayDisplayAllBanner() {
        io.print("===DisplayAll===");
    }

}
