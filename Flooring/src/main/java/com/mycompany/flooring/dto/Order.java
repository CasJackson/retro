/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooring.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author cas
 */
public class Order {
    
    private int orderNumber;

    private LocalDate date;

    private String customerName;

    private String state;

    private BigDecimal taxRate;

    private String productType;

    private BigDecimal area;

    private BigDecimal costPsf;

    private BigDecimal laborCostPsf;
//    
//    private BigDecimal getTotalMaterialCost;
//    
//    private BigDecimal getTax;
//    
//    private BigDecimal getTotalCost;
//    
//    private BigDecimal getTotalLaborCost;
//    

    /**
     * @return the orderNumber
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the taxRate
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * @param taxRate the taxRate to set
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * @return the productType
     */
    public String getProductType() {
        return productType;
    }

    /**
     * @param productType the productType to set
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * @return the area
     */
    public BigDecimal getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(BigDecimal area) {
        this.area = area;
    }

    /**
     * @return the costPsf
     */
    public BigDecimal getCostPsf() {
        return costPsf;
    }

    /**
     * @param costPsf the costPsf to set
     */
    public void setCostPsf(BigDecimal costPsf) {
        this.costPsf = costPsf;
    }

    /**
     * @return the laborCostPsf
     */
    public BigDecimal getLaborCostPsf() {
        return laborCostPsf;
    }

    /**
     * @param laborCostPsf the laborCostPsf to set
     */
    public void setLaborCostPsf(BigDecimal laborCostPsf) {
        this.laborCostPsf = laborCostPsf;
    }
    
     public BigDecimal getTotalLaborCost(){
         BigDecimal totalLabor = area.multiply(laborCostPsf).setScale(2, RoundingMode.HALF_UP);
         return totalLabor;
     }

    public BigDecimal getTotalMaterialCost(){
       BigDecimal totalMat =area.multiply(costPsf).setScale(2, RoundingMode.HALF_UP);
       return totalMat;
     }


    public BigDecimal getTax(){
        BigDecimal Tax = taxRate.multiply(getTotalMaterialCost().add(getTotalLaborCost())).divide(new BigDecimal ("100"),2, RoundingMode.HALF_UP);
        return Tax;
     }


    public BigDecimal getTotalCost(){
        BigDecimal totalCost =  getTotalMaterialCost().add(getTotalLaborCost()).add(getTax()).setScale(2, RoundingMode.HALF_UP) ;
                return totalCost;
     }

}