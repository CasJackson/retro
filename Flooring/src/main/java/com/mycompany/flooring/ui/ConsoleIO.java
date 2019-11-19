/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooring.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author cas
 */
public class ConsoleIO implements UserIO {

    Scanner scn = new Scanner(System.in);

    @Override
    public double readDouble(String prompt) {
        double toReturn = Double.NaN;
        boolean valid = false;
        while (!valid) {
            String userInput = readString(prompt);
            try {
                toReturn = Double.parseDouble(userInput);
                valid = true;
            } catch (NumberFormatException ex) {
            }
        }
        return toReturn;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double toReturn = Double.NaN;
        boolean valid = false;
        while (!valid) {
            toReturn = readDouble(prompt);
            valid = toReturn >= min && toReturn <= max;
        }
        return toReturn;
    }

    @Override
    public float readFloat(String prompt) {
        float toReturn = Float.NaN;
        boolean valid = false;
        while (!valid) {
            String userInput = readString(prompt);
            try {
                toReturn = Float.parseFloat(userInput);
                valid = true;
            } catch (NumberFormatException ex) {
            }
        }
        return toReturn;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float toReturn = Float.NaN;
        boolean valid = false;
        while (!valid) {
            toReturn = readFloat(prompt);
            valid = toReturn >= min && toReturn <= max;
        }
        return toReturn;
    }

    @Override
    public int readInt(String prompt) {
        int toReturn = Integer.MIN_VALUE;
        boolean valid = false;
        while (!valid) {
            String userInput = readString(prompt);
            try {
                toReturn = Integer.parseInt(userInput);
                valid = true;
            } catch (NumberFormatException ex) {
            }
        }
        return toReturn;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int toReturn = Integer.MIN_VALUE;
        boolean valid = false;
        while (!valid) {
            toReturn = readInt(prompt);
            valid = toReturn >= min && toReturn <= max;
        }
        return toReturn;
    }

    @Override
    public long readLong(String prompt) {
        long toReturn = Long.MIN_VALUE;
        boolean valid = false;
        while (!valid) {
            String userInput = readString(prompt);
            try {
                toReturn = Long.parseLong(userInput);
                valid = true;
            } catch (NumberFormatException ex) {
            }
        }
        return toReturn;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long toReturn = Integer.MIN_VALUE;
        boolean valid = false;
        while (!valid) {
            toReturn = readLong(prompt);
            valid = toReturn >= min && toReturn <= max;
        }
        return toReturn;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        BigDecimal toReturn = null;
        boolean valid = false;
        while (!valid) {
            String userInput = readString(prompt);
            try {
                toReturn = new BigDecimal(userInput);
                valid = true;

            } catch (NumberFormatException ex) {

            }
        }
        return toReturn;

    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        BigDecimal toReturn = new BigDecimal("0");
        boolean valid = false;
        while (!valid) {
            toReturn = readBigDecimal(prompt);
            if (toReturn.compareTo(min) >= 0 && toReturn.compareTo(max) <= 0) {
                valid = true;
            }
        }
        return toReturn;
    }

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        return scn.nextLine();
    }
//    

    @Override
    public LocalDate readDate(String prompt) {
//        
//        
        LocalDate toReturn = LocalDate.MIN;
        boolean valid = false;
        while (!valid) {
            String userInput = readString(prompt);
            try {
                toReturn = LocalDate.parse(userInput, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                valid = true;
            } catch (DateTimeParseException ex) {

            }
        }
        return toReturn;
    }

    @Override
    public LocalDate readDate(String prompt, LocalDate earlier, LocalDate later) {
        LocalDate toReturn = LocalDate.MIN;
        boolean valid = false;
        while (!valid) {
            toReturn = readDate(prompt);
            valid = toReturn.isAfter(earlier) && toReturn.isBefore(later);
        }
        return toReturn;
    }

    @Override
    public BigDecimal editBigDecimal(String prompt, BigDecimal originalVal) {
        BigDecimal toReturn = null;
        boolean valid = false;
        while (!valid) {
            String userInput = readString(prompt);
            if (userInput.isBlank()) {
                toReturn = originalVal;
                valid = true;
            } else {

                try {
                    toReturn = new BigDecimal(userInput);
                    valid = true;

                } catch (NumberFormatException ex) {

                }
            }
        }

        return toReturn;

    }

    @Override
    public String editString(String prompt, String originalVal) {
        print(prompt);
        String toReturn = scn.nextLine();
        if(toReturn.isBlank()) {
            toReturn = originalVal;
        }
        return toReturn;
    }
}
