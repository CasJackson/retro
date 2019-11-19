/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvd.dao;

import com.mycompany.dvd.dto.Dvd;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author cas
 */
public class DVDDaoFileImpl implements DVDDao {

    String path;
    private Map<String, Dvd> allDvds = new HashMap<>();

    /**
     *
     * @param path
     */
    public DVDDaoFileImpl(String path) {
        this.path = path;
    }

    @Override
    public List<Dvd> getAllDvd() throws DVDDaoException {
        List<Dvd> toReturn = new ArrayList<>();
        FileReader reader = null;
        try {
            reader = new FileReader(path);
            Scanner scn = new Scanner(reader);

            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                if (line.length() > 0) {
                    String[] cells = line.split("::");

                    Dvd toAdd = new Dvd();
                    toAdd.setTitle(cells[0]);
                    toAdd.setReleasedate(Integer.parseInt(cells[1]));
                    toAdd.setMPAArating(cells[2]);
                    toAdd.setDirectorsname(cells[3]);
                    toAdd.setStudio(cells[4]);
                    toAdd.setUserrating(cells[5]);
                    toReturn.add(toAdd);
                }
            }
        } catch (FileNotFoundException ex) {
            throw new DVDDaoException("could not get all DVDInfo", ex);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }

            } catch (IOException ex) {
                throw new DVDDaoException("could not close reader " + path, ex);

            }
        }
        return toReturn;
    }

    @Override
    public Dvd getByTitle(String Title) throws DVDDaoException {
        Dvd toReturn = null;

        List<Dvd> allDvd = getAllDvd();
        for (Dvd toCheck : allDvd) {

            if (toCheck.getTitle().equals(Title)) {
                toReturn = toCheck;

                break;
            }
        }

        return toReturn;
    }

    @Override
    public void removeByTitle(String Title) throws DVDDaoException {
        List<Dvd> allDvd = getAllDvd();

        int matchIndex = -1;
        for (int i = 0; i < allDvd.size(); i++) {
            Dvd toCheck = allDvd.get(i);

            if (toCheck.getTitle().equals(Title)) {
                matchIndex = i;
                break;
            }
        }
        if (matchIndex == -1) {
            throw new DVDDaoException("ERROR: could not remove DVDInfo with Title" + Title);

        }
        allDvd.remove(matchIndex);
        writeFile(allDvd);
    }

    @Override
    public Dvd addDvd(Dvd toAdd) throws DVDDaoException {
        List<Dvd> allDvd = getAllDvd();

        allDvd.add(toAdd);

        writeFile(allDvd);
        return toAdd;

    }

    @Override
    public void editDvd(String oldtitle, Dvd edited) throws DVDDaoException {
        List<Dvd> allDvd = getAllDvd();

        int matchIndex = -1;

        for (int i = 0; i < allDvd.size(); i++) {
            Dvd toCheck = allDvd.get(i);

            if (toCheck.getTitle().equals(oldtitle)) {
                matchIndex = i;
                break;
            }
        }

        if (matchIndex == -1) { //we didn't find a match

            throw new DVDDaoException("ERROR: could not edit DVDInfo with title" + oldtitle);
        }

        allDvd.remove(matchIndex);
        allDvd.add(edited);

        writeFile(allDvd);
    }

    private void writeFile(List<Dvd> allDvd) throws DVDDaoException {

        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
            PrintWriter pw = new PrintWriter(writer);

            for (Dvd toWrite : allDvd) {
                String line = convertToLine(toWrite);
                pw.println(line);
            }
        } catch (IOException ex) {
            throw new DVDDaoException("ERROR: could not write to " + path, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                throw new DVDDaoException("ERROR: could not close writer for " + path, ex);
            }
        }
    }

    private String convertToLine(Dvd toWrite) {

        String line
                = toWrite.getTitle() + "::"
                + toWrite.getReleasedate() + "::"
                + toWrite.getMPAArating() + "::"
                + toWrite.getDirectorsname() + "::"
                + toWrite.getStudio() + "::"
                + toWrite.getUserrating();

        return line;

    }

}
