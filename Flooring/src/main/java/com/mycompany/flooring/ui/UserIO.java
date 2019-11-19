/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooring.ui;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author cas
 */
public interface UserIO {

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    String readString(String prompt);
    
    String editString(String prompt, String originalVal);

    BigDecimal readBigDecimal(String prompt);
    
    BigDecimal editBigDecimal(String prompt, BigDecimal original );
    
    BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max);

    public void print(String main_Menu);

    LocalDate readDate(String prompt);

   LocalDate readDate(String prompt, LocalDate earlier, LocalDate later);
}
