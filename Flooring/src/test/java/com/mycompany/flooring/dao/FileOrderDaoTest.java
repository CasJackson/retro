/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooring.dao;

import com.mycompany.flooring.dto.Order;
import com.mycompany.flooring.dto.Tax;
import com.mycompany.flooring.service.FlooringServiceException;
import com.mycompany.flooring.service.InvalidAreaException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author cas
 */
public class FileOrderDaoTest {

    public FileOrderDaoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws IOException {
        Path testPath = Paths.get("testData");
        Path seedPath = Paths.get("seedData");

        File testFolder = testPath.toFile();
        File seedFolder = seedPath.toFile();

        if (!testFolder.exists()) {
            testFolder.mkdir();
        }

        File[] testFiles = testFolder.listFiles();
        for (File testFile : testFiles) {
            testFile.delete();

        }
        File[] seedFiles = seedFolder.listFiles();
        for (File seedFile : seedFiles) {
            Files.copy(seedFile.toPath(), Paths.get(testPath.toString(), seedFile.getName()), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of displayAllByDate method, of class orderDaoFileImpl.
     */
    @Test
    public void testDisplayAllByDateGoldenPath() {

        try {
            FileOrderDao oDao = new FileOrderDao("testData");
            List<Order> allOrders = oDao.displayAllByDate(LocalDate.of(2019, Month.SEPTEMBER, 1));

            assertEquals(1, allOrders.size());
            Order toTest = allOrders.get(0);

            assertEquals(1, toTest.getOrderNumber());
            assertEquals("Wise", toTest.getCustomerName());
            assertEquals("OH", toTest.getState());
            assertEquals(new BigDecimal("6.25"), toTest.getTaxRate());
            assertEquals(new BigDecimal("100.00"), toTest.getArea());
            assertEquals("Wood", toTest.getProductType());
            assertEquals(new BigDecimal("5.15"), toTest.getCostPsf());
            assertEquals(new BigDecimal("4.75"), toTest.getLaborCostPsf());
            assertEquals(new BigDecimal("515.00"), toTest.getTotalMaterialCost());
            assertEquals(new BigDecimal("475.00"), toTest.getTotalLaborCost());
            assertEquals(new BigDecimal("61.88"), toTest.getTax());
            assertEquals(new BigDecimal("1051.88"), toTest.getTotalCost());
        } catch (OrderDaoException ex) {

        }

    }

    @Test
    public void testDisplayAllByDateNoOrders() {

        try {
            FileOrderDao oDao = new FileOrderDao("testData");
            List<Order> allOrders = oDao.displayAllByDate(LocalDate.of(1019, Month.SEPTEMBER, 1));

            assertEquals(0, allOrders.size());

        } catch (OrderDaoException ex) {
            fail();

        }
    }

    @Test
    public void testaddOrderGoldenPath() {

        try {
            LocalDate date = LocalDate.of(2019, 9, 1);

            FileOrderDao oDao = new FileOrderDao("testData");
            //Tax userTax = tdao.getTaxByState(toAdd.getState());

            Order toTest = new Order();

            toTest.setCustomerName("Mike");
            toTest.setProductType("Tile");
            toTest.setArea(new BigDecimal("2000"));
            toTest.setState("IN");
            toTest.setDate(date);
            toTest.setTaxRate(new BigDecimal("6.00"));
            toTest.setCostPsf(new BigDecimal("3.50"));
            toTest.setLaborCostPsf(new BigDecimal("4.15"));

            oDao.addOrder(toTest);

            Order validationOrder = oDao.getOrder(date, 2);

            assertEquals("Mike", validationOrder.getCustomerName());
            assertEquals("Tile", validationOrder.getProductType());
            assertEquals(new BigDecimal("2000"), validationOrder.getArea());
            assertEquals("IN", validationOrder.getState());
            assertEquals(date, validationOrder.getDate());
            assertEquals(new BigDecimal("3.50"), validationOrder.getCostPsf());
            assertEquals(new BigDecimal("4.15"), validationOrder.getLaborCostPsf());
            assertEquals(new BigDecimal("6.00"), validationOrder.getTaxRate());

            assertEquals(new BigDecimal("7000.00"), validationOrder.getTotalMaterialCost());
            assertEquals(new BigDecimal("8300.00"), validationOrder.getTotalLaborCost());
            assertEquals(new BigDecimal("918.00"), validationOrder.getTax());
            assertEquals(new BigDecimal("16218.00"), validationOrder.getTotalCost());
        } catch (OrderDaoException ex) {
            fail();
        }

    }

    @Test
    public void testaddOrderNullOrder() {

        try {
            LocalDate date = LocalDate.of(2019, 9, 1);

            FileOrderDao oDao = new FileOrderDao("testData");
            //Tax userTax = tdao.getTaxByState(toAdd.getState());

            Order toTest = null;
            

            oDao.addOrder(toTest);
            fail();

        } catch (OrderDaoException ex) {
        }
    }

    @Test
    public void editOrderGoldenPath() {
        try {
            LocalDate date = LocalDate.of(2019, 9, 1);

            FileOrderDao oDao = new FileOrderDao("testData");

            Order toEdit = oDao.getOrder(date, 1);

            toEdit.setCustomerName("Wise");
            toEdit.setProductType("Wood");
            toEdit.setArea(new BigDecimal("1000"));
            toEdit.setState("OH");
            toEdit.setDate(date);
            toEdit.setTaxRate(new BigDecimal("6.25"));
            toEdit.setCostPsf(new BigDecimal("5.15"));
            toEdit.setLaborCostPsf(new BigDecimal("4.75"));

            oDao.editOrder(toEdit);

            Order validationOrder = oDao.getOrder(date, 1);

            assertEquals("Wise", validationOrder.getCustomerName());
            assertEquals("Wood", validationOrder.getProductType());
            assertEquals(new BigDecimal("1000"), validationOrder.getArea());
            assertEquals("OH", validationOrder.getState());
            assertEquals(date, validationOrder.getDate());
            assertEquals(new BigDecimal("5.15"), validationOrder.getCostPsf());
            assertEquals(new BigDecimal("4.75"), validationOrder.getLaborCostPsf());
            assertEquals(new BigDecimal("6.25"), validationOrder.getTaxRate());

            assertEquals(new BigDecimal("5150.00"), validationOrder.getTotalMaterialCost());
            assertEquals(new BigDecimal("4750.00"), validationOrder.getTotalLaborCost());
            assertEquals(new BigDecimal("618.75"), validationOrder.getTax());
            assertEquals(new BigDecimal("10518.75"), validationOrder.getTotalCost());
        } catch (OrderDaoException ex) {
            fail();
        }
    }

    @Test
    public void editOrderBadDate() {
        try {
            LocalDate date = LocalDate.of(2019, 9, 1);

            FileOrderDao oDao = new FileOrderDao("testData");

            Order toEdit = oDao.getOrder(date, 1);

//            toEdit.setCustomerName("Wise");
//            toEdit.setProductType("Wood");
//            toEdit.setArea(new BigDecimal("1000"));
//            toEdit.setState("OH");
//            toEdit.setDate(date);
//            toEdit.setTaxRate(new BigDecimal("6.25"));
//            toEdit.setCostPsf(new BigDecimal("5.15"));
//            toEdit.setLaborCostPsf(new BigDecimal("4.75"));

                toEdit.setDate(LocalDate.of(3019, 9, 1));

            oDao.editOrder(toEdit);
            fail();

        } catch (OrderDaoException ex) {
        }
    }

    @Test
    public void editOrderNullOrder() {
        try {
            LocalDate date = LocalDate.of(2019, 9, 1);

            FileOrderDao oDao = new FileOrderDao("testData");

            Order toEdit = null;


            oDao.editOrder(toEdit);
            fail();
        } catch (OrderDaoException ex) {
        }
    }

    @Test
    public void removeOrderGoldenPath() {

        try {
            LocalDate date = LocalDate.of(2019, 9, 1);

            FileOrderDao oDao = new FileOrderDao("testData");

            oDao.removeOrder(date, 1);
            List<Order> orderList = oDao.displayAllByDate(date);
            assertEquals(0, orderList.size());

        } catch (OrderDaoException ex) {
            Logger.getLogger(FileOrderDaoTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }

    @Test
    public void getOrder() {
        try {
            LocalDate date = LocalDate.of(2019, 9, 1);

            FileOrderDao oDao = new FileOrderDao("testData");

            List<Order> allOrders = oDao.displayAllByDate(LocalDate.of(2019, Month.SEPTEMBER, 1));

            assertEquals(1, allOrders.size());
            Order toTest = allOrders.get(0);

            assertEquals(1, toTest.getOrderNumber());
            assertEquals("Wise", toTest.getCustomerName());
            assertEquals("OH", toTest.getState());
            assertEquals(new BigDecimal("6.25"), toTest.getTaxRate());
            assertEquals(new BigDecimal("100.00"), toTest.getArea());
            assertEquals("Wood", toTest.getProductType());
            assertEquals(new BigDecimal("5.15"), toTest.getCostPsf());
            assertEquals(new BigDecimal("4.75"), toTest.getLaborCostPsf());
            assertEquals(new BigDecimal("515.00"), toTest.getTotalMaterialCost());
            assertEquals(new BigDecimal("475.00"), toTest.getTotalLaborCost());
            assertEquals(new BigDecimal("61.88"), toTest.getTax());
            assertEquals(new BigDecimal("1051.88"), toTest.getTotalCost());

        } catch (OrderDaoException ex) {
            fail();
        }

    }

    @Test
    public void getOrderBadDate() {
        try {
            LocalDate date = LocalDate.of(2019, 9, 1);

            FileOrderDao oDao = new FileOrderDao("testData");

            List<Order> allOrders = oDao.displayAllByDate(LocalDate.of(29, Month.SEPTEMBER, 1));
            assertEquals(0, allOrders.size());

        } catch (OrderDaoException ex) {
            fail();
        }
    }

}
