 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.Service.InvalidIdException;
import com.mycompany.vendingmachine.dto.VMItems;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cas
 */
public class VMDaoFileImpl implements VMDao {

    String path;

    public VMDaoFileImpl(String path) {
        this.path = path;
    }

    

   

    @Override
    public List<VMItems> getAllVMItems() throws VMDaoException {

        List<VMItems> allItems = new ArrayList<>();

        FileReader reader = null;
        try {
            reader = new FileReader(path);
            Scanner scn = new Scanner(reader);

            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                if (line.length() > 0) {
                    String[] cells = line.split("::");

                    VMItems toAdd = new VMItems();
                    toAdd.setId(Integer.parseInt(cells[0]));
                    toAdd.setName(cells[1]);
                    toAdd.setPrice(new BigDecimal(cells[2]));
                    toAdd.setQuanity(Integer.parseInt(cells[3]));
                    allItems.add(toAdd);

                }

            }

        } catch (FileNotFoundException ex) {
            throw new VMDaoException("could not get all VMItems", ex);
        } finally {
            try {
                if(reader != null) {
                reader.close();
                }
            } catch (IOException ex) {
                throw new VMDaoException("could not close reader for " + path, ex);
            }
        }
        return allItems;
    }

    @Override
    public VMItems getById(int id) throws VMDaoException {
        VMItems toReturn = null;

        List<VMItems> allVMItems = getAllVMItems();

        for (VMItems toCheck : allVMItems) {
            if (toCheck.getId() == id) {
                toReturn = toCheck;
                break;

            }
            if (toReturn == null ){
                try {
                    throw new InvalidIdException("couldnt find id ");
                } catch (InvalidIdException ex) {
                    
                }
                        
            }
        }
        return toReturn;
    }

   
    @Override
    public void editVMItems( VMItems toEdit) throws VMDaoException {

        List<VMItems> allVMItems = getAllVMItems();

        int matchIndex = -1;

        for (int i = 0; i < allVMItems.size(); i++) {
            VMItems toCheck = allVMItems.get(i);

            if (toCheck.getId() == toEdit.getId()) {
                matchIndex = i;
                break;
            }
        }

        if (matchIndex == -1) { //we didn't find a match

            throw new VMDaoException("ERROR: could not edit item with id " + toEdit.getId());
        }

        allVMItems.remove(matchIndex);
        allVMItems.add(toEdit);

        writeFile(allVMItems);
    }

    private void writeFile(List<VMItems> allVMItems) throws VMDaoException {

        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
            PrintWriter pw = new PrintWriter(writer);

            for (VMItems toWrite : allVMItems) {
                String line = convertToLine(toWrite);
                pw.println(line);
            }
        } catch (IOException ex) {
            throw new VMDaoException("ERROR: could not write to " + path, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                throw new VMDaoException("ERROR: could not close writer for " + path, ex);
            }
        }
    }

    private String convertToLine(VMItems toWrite) {

        String line
                = toWrite.getId() + "::"
                + toWrite.getName() + "::"
                + toWrite.getPrice() + "::"
                + toWrite.getQuanity();

        return line;
    }
}
