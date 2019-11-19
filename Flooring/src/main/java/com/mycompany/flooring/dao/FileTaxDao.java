/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooring.dao;

import com.mycompany.flooring.dto.Tax;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author cas
 */
public class FileTaxDao implements TaxDao {

    String file;

    public FileTaxDao(String file) {
        this.file = file;

    }

    @Override
    public Tax getTaxByState(String state) throws FileTaxDaoException {
        List<Tax> allTaxData = getAllTaxes();
        Tax custTax = null;

        for (int i = 0; i < allTaxData.size(); i++) {
            Tax toCheck = allTaxData.get(i);

            if (toCheck.getState().equalsIgnoreCase(state)) {
                custTax = toCheck;
            }
        }
        return custTax;
    }

    private List<Tax> getAllTaxes() throws FileTaxDaoException {
        List<Tax> allTaxes = new ArrayList<>();;
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            Scanner scn = new Scanner(reader);
            scn.nextLine();
            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                if (line.length() > 0) {
                    String[] cells = line.split(",");

                    Tax toAdd = new Tax();

                    toAdd.setState(cells[0]);
                    toAdd.setTaxRate(new BigDecimal(cells[1]));
                    allTaxes.add(toAdd);

                }

            }

        } catch (FileNotFoundException ex) {
            throw new FileTaxDaoException("Could not find taxes for state entered.", ex);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                throw new FileTaxDaoException("could not close reader for " + file, ex);
            }
        }
        return allTaxes;
    }
}
