/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooring.service;

import com.mycompany.flooring.dao.FileOrderDao;
import com.mycompany.flooring.dao.FileProductDaoException;
import com.mycompany.flooring.dao.InMemoOrderDao;
import com.mycompany.flooring.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.flooring.dao.OrderDao;
import com.mycompany.flooring.dao.ProductDao;
import com.mycompany.flooring.dao.InMemoProductDao;
import com.mycompany.flooring.dao.TaxDao;
import com.mycompany.flooring.dao.FileTaxDao;
import com.mycompany.flooring.dao.FileTaxDaoException;
import com.mycompany.flooring.dao.InMemoTaxDao;
import com.mycompany.flooring.dao.OrderDaoException;

/**
 *
 * @author cas
 */
public class FlooringServiceImplTest {

    private FlooringServiceImpl service;

    public FlooringServiceImplTest() {
        OrderDao dao = new InMemoOrderDao();
        TaxDao tdao = new InMemoTaxDao();
        ProductDao pdao = new InMemoProductDao();
        service = new FlooringServiceImpl(dao, tdao, pdao);
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllOrders method, of class flooringServiceLayerImpl.
     */
    @Test
    public void GetOrdersByDateGoldenPath() {
        try {
            OrderDao dao = new InMemoOrderDao();
            TaxDao tdao = new InMemoTaxDao();
            ProductDao pdao = new InMemoProductDao();
            // test every thing that the user is allowed to put in
            FlooringServiceImpl toTest = new FlooringServiceImpl(dao, tdao, pdao);

            List<Order> allOrders = service.getOrdersByDate(LocalDate.of(2019, 9, 1));

            assertEquals(2, allOrders.size());

            Order f1 = allOrders.get(0);
            assertEquals(1, f1.getOrderNumber());
            assertEquals("Wise", f1.getCustomerName());
            assertEquals("OH", f1.getState());
            assertEquals(new BigDecimal("6.25"), f1.getTaxRate());
            assertEquals("Wood", f1.getProductType());
            assertEquals(new BigDecimal("100.00"), f1.getArea());
            assertEquals(new BigDecimal("5.15"), f1.getCostPsf());
            assertEquals(new BigDecimal("4.75"), f1.getLaborCostPsf());

            Order f2 = allOrders.get(1);
            assertEquals(2, f2.getOrderNumber());
            assertEquals("Guy", f2.getCustomerName());
            assertEquals("IN", f2.getState());
            assertEquals(new BigDecimal("6.25"), f2.getTaxRate());
            assertEquals("Carpet", f2.getProductType());
            assertEquals(new BigDecimal("100.00"), f2.getArea());
            assertEquals(new BigDecimal("5.15"), f2.getCostPsf());
            assertEquals(new BigDecimal("4.75"), f2.getLaborCostPsf());
        } catch (FlooringServiceException ex) {
            fail();
        } catch (InvalidDateException ex) {
            fail();
        }

    }

    @Test
    public void GetOrdersByDateNoOrders() {
        OrderDao dao = new InMemoOrderDao();
        TaxDao tdao = new InMemoTaxDao();
        ProductDao pdao = new InMemoProductDao();
        // test every thing that the user is allowed to put in
        FlooringServiceImpl toTest = new FlooringServiceImpl(dao, tdao, pdao);

        try {

            List<Order> allOrders = service.getOrdersByDate(LocalDate.of(1019, 9, 1));
            assertEquals(0, allOrders.size());

        } catch (FlooringServiceException ex) {
            fail();
        } catch (InvalidDateException ex) {
                        fail();

        }
    }

    @Test
    public void addOrderGoldenPath() {
        try {
            OrderDao dao = new InMemoOrderDao();
            TaxDao tdao = new InMemoTaxDao();
            ProductDao pdao = new InMemoProductDao();
            // test every thing that the user is allowed to put in
            FlooringServiceImpl toTest = new FlooringServiceImpl(dao, tdao, pdao);
            LocalDate date = LocalDate.of(2020, 9, 1);
//            List<Order> allOrders = service.getOrdersByDate(LocalDate.of(2019, 9, 1));
//            Order added = service.addOrder(allOrders);
//
//            assertEquals(2, allOrders.size());
            Order toTest1 = new Order();
            toTest1.setCustomerName("Jim");
            toTest1.setArea(new BigDecimal("500"));
            toTest1.setProductType("Laminate");
            toTest1.setState("IN");
            toTest1.setDate(date);
            //toTest1.setTaxRate(new BigDecimal("6.00"));
            //toTest1.setCostPsf(new BigDecimal("3.50"));
            //toTest1.setLaborCostPsf(new BigDecimal("4.15"));
            service.addOrder(toTest1);

            Order validationOrder = service.getOrder(date, 0);

            assertEquals("Jim", validationOrder.getCustomerName());
            assertEquals("Laminate", validationOrder.getProductType());
            assertEquals(new BigDecimal("500"), validationOrder.getArea());
            assertEquals("IN", validationOrder.getState());
            assertEquals(date, validationOrder.getDate());
            assertEquals(new BigDecimal("1.75"), validationOrder.getCostPsf());
            assertEquals(new BigDecimal("2.10"), validationOrder.getLaborCostPsf());
            assertEquals(new BigDecimal("6.00"), validationOrder.getTaxRate());

            assertEquals(new BigDecimal("875.00"), validationOrder.getTotalMaterialCost());
            assertEquals(new BigDecimal("1050.00"), validationOrder.getTotalLaborCost());
            assertEquals(new BigDecimal("115.50"), validationOrder.getTax());
            assertEquals(new BigDecimal("2040.50"), validationOrder.getTotalCost());

        } catch (FlooringServiceException | InvalidStateException | InvalidProductException | InvalidAreaException | FileTaxDaoException | FileProductDaoException | OrderDaoException | InvalidDateException | InvalidOrderException ex) {
            fail();
        }

    }

    @Test
    public void addOrderBadState() {

        try {
            OrderDao dao = new InMemoOrderDao();
            TaxDao tdao = new InMemoTaxDao();
            ProductDao pdao = new InMemoProductDao();
            FlooringServiceImpl toTest = new FlooringServiceImpl(dao, tdao, pdao);
            LocalDate date = LocalDate.of(2020, 9, 1);
            Order toTest1 = new Order();
            toTest1.setCustomerName("Jim");
            toTest1.setArea(new BigDecimal("500"));
            toTest1.setProductType("Laminate");
            toTest1.setState("MN");
            toTest1.setDate(date);

            service.addOrder(toTest1);
            fail();
        } catch (InvalidProductException | InvalidAreaException | FlooringServiceException | FileTaxDaoException | InvalidDateException | FileProductDaoException ex) {
            fail();
        } catch (InvalidStateException ex) {

        }
    }

    @Test
    public void addOrderBadProduct() {

        try {
            OrderDao dao = new InMemoOrderDao();
            TaxDao tdao = new InMemoTaxDao();
            ProductDao pdao = new InMemoProductDao();
            FlooringServiceImpl toTest = new FlooringServiceImpl(dao, tdao, pdao);
            LocalDate date = LocalDate.of(2020, 9, 1);
            Order toTest1 = new Order();
            toTest1.setCustomerName("Jim");
            toTest1.setArea(new BigDecimal("500"));
            toTest1.setProductType("steel");
            toTest1.setState("IN");
            toTest1.setDate(date);

            service.addOrder(toTest1);
            fail();
        } catch (InvalidStateException | InvalidAreaException | FlooringServiceException | FileTaxDaoException | InvalidDateException | FileProductDaoException ex) {
            fail();
        } catch (InvalidProductException ex) {

        }
    }

    @Test
    public void addOrderNegativeArea() {

        try {
            OrderDao dao = new InMemoOrderDao();
            TaxDao tdao = new InMemoTaxDao();
            ProductDao pdao = new InMemoProductDao();
            FlooringServiceImpl toTest = new FlooringServiceImpl(dao, tdao, pdao);
            LocalDate date = LocalDate.of(2020, 9, 1);
            Order toTest1 = new Order();
            toTest1.setCustomerName("Jim");
            toTest1.setArea(new BigDecimal("-10"));
            toTest1.setProductType("Laminate");
            toTest1.setState("IN");
            toTest1.setDate(date);

            service.addOrder(toTest1);
            fail();
        } catch (InvalidStateException | InvalidProductException | FlooringServiceException | FileTaxDaoException | InvalidDateException | FileProductDaoException ex) {
            fail();
        } catch (InvalidAreaException ex) {

        }
    }

    @Test
    public void addOrderPastDate() {

        try {
            OrderDao dao = new InMemoOrderDao();
            TaxDao tdao = new InMemoTaxDao();
            ProductDao pdao = new InMemoProductDao();
            FlooringServiceImpl toTest = new FlooringServiceImpl(dao, tdao, pdao);
            LocalDate date = LocalDate.of(1958, 9, 1);
            Order toTest1 = new Order();
            toTest1.setCustomerName("Jim");
            toTest1.setArea(new BigDecimal("500"));
            toTest1.setProductType("Laminate");
            toTest1.setState("IN");
            toTest1.setDate(date);

            service.addOrder(toTest1);
            fail();
        } catch (InvalidProductException | InvalidAreaException | FlooringServiceException | FileTaxDaoException | InvalidStateException | FileProductDaoException ex) {
            fail();
        } catch (InvalidDateException ex) {

        }
    }

    @Test
    public void editOrderGoldenPath() {

        try {
            OrderDao dao = new InMemoOrderDao();
            TaxDao tdao = new InMemoTaxDao();
            ProductDao pdao = new InMemoProductDao();
            // test every thing that the user is allowed to put in
            FlooringServiceImpl toTest = new FlooringServiceImpl(dao, tdao, pdao);
            LocalDate date = LocalDate.of(2019, 9, 1);

            Order toEdit = dao.getOrder(date, 1);

            toEdit.setCustomerName("EDITED CUSTOMER");
            toEdit.setProductType("Laminate");
            toEdit.setArea(new BigDecimal("1000"));
            toEdit.setState("IN");

            service.editOrder(toEdit);

            Order validationOrder = dao.getOrder(date, 1);

            assertEquals("EDITED CUSTOMER", validationOrder.getCustomerName());
            assertEquals("Laminate", validationOrder.getProductType());
            assertEquals(new BigDecimal("1000"), validationOrder.getArea());
            assertEquals("IN", validationOrder.getState());
            assertEquals(date, validationOrder.getDate());
            assertEquals(new BigDecimal("1.75"), validationOrder.getCostPsf());
            assertEquals(new BigDecimal("2.10"), validationOrder.getLaborCostPsf());
            assertEquals(new BigDecimal("6.00"), validationOrder.getTaxRate());

            assertEquals(new BigDecimal("1750.00"), validationOrder.getTotalMaterialCost());
            assertEquals(new BigDecimal("2100.00"), validationOrder.getTotalLaborCost());
            assertEquals(new BigDecimal("231.00"), validationOrder.getTax());
            assertEquals(new BigDecimal("4081.00"), validationOrder.getTotalCost());
        } catch (InvalidStateException | InvalidProductException | InvalidAreaException | FlooringServiceException | FileTaxDaoException | FileProductDaoException | InvalidDateException | OrderDaoException ex) {
            fail(ex.getMessage());
        }
        
    }

    @Test
    public void editOrderBadState() {

        try {
            OrderDao dao = new InMemoOrderDao();
            TaxDao tdao = new InMemoTaxDao();
            ProductDao pdao = new InMemoProductDao();
            // test every thing that the user is allowed to put in
            FlooringServiceImpl toTest = new FlooringServiceImpl(dao, tdao, pdao);
            LocalDate date = LocalDate.of(2019, 9, 1);

            Order toEdit = dao.getOrder(date, 1);

            toEdit.setCustomerName("EDITED CUSTOMER");
            toEdit.setProductType("Laminate");
            toEdit.setArea(new BigDecimal("1000"));
            toEdit.setState("MN");
            //dao.editOrder(toEdit);
            service.editOrder(toEdit);
            fail();


        } catch (InvalidStateException ex) {

        } catch (InvalidProductException | InvalidAreaException | FlooringServiceException | FileTaxDaoException | FileProductDaoException | InvalidDateException ex) {
            fail();
        } catch (OrderDaoException ex) {
            fail();
        } 
    }

    public void deleteOrderGoldenPath() {
        try {
            LocalDate date = LocalDate.of(2019, 9, 1);
            OrderDao dao = new InMemoOrderDao();
            TaxDao tdao = new InMemoTaxDao();
            ProductDao pdao = new InMemoProductDao();
            // test every thing that the user is allowed to put in
            FlooringServiceImpl toTest = new FlooringServiceImpl(dao, tdao, pdao);

            toTest.deleteOrder(date, 1);
            List<Order> orderList = dao.displayAllByDate(date);
            assertEquals(0, orderList.size());
        } catch (OrderDaoException | InvalidOrderException | InvalidDateException ex) {
            fail();
        }
    }

    @Test
    public void deleteOrderBadDate() {
        LocalDate date = LocalDate.of(1019, 9, 1);
        OrderDao dao = new InMemoOrderDao();
        TaxDao tdao = new InMemoTaxDao();
        ProductDao pdao = new InMemoProductDao();
        FlooringServiceImpl toTest = new FlooringServiceImpl(dao, tdao, pdao);
        try {

            toTest.deleteOrder(date, 1);
            fail();
        } catch (OrderDaoException | InvalidOrderException ex) {
            fail();

        } catch (InvalidDateException ex) {

        }
    }

    @Test
    public void getOrderGoldenPath() {

        try {
            LocalDate date = LocalDate.of(2019, 9, 1);
            OrderDao dao = new InMemoOrderDao();
            TaxDao tdao = new InMemoTaxDao();
            ProductDao pdao = new InMemoProductDao();
            // test every thing that the user is allowed to put in
            FlooringServiceImpl toTest = new FlooringServiceImpl(dao, tdao, pdao);

            Order toCheck = toTest.getOrder(date, 1);

            assertEquals(1, toCheck.getOrderNumber());
            assertEquals("Wise", toCheck.getCustomerName());
            assertEquals("OH", toCheck.getState());
            assertEquals(new BigDecimal("6.25"), toCheck.getTaxRate());
            assertEquals(new BigDecimal("100.00"), toCheck.getArea());
            assertEquals("Wood", toCheck.getProductType());
            assertEquals(new BigDecimal("5.15"), toCheck.getCostPsf());
            assertEquals(new BigDecimal("4.75"), toCheck.getLaborCostPsf());
            assertEquals(new BigDecimal("515.00"), toCheck.getTotalMaterialCost());
            assertEquals(new BigDecimal("475.00"), toCheck.getTotalLaborCost());
            assertEquals(new BigDecimal("61.88"), toCheck.getTax());
            assertEquals(new BigDecimal("1051.88"), toCheck.getTotalCost());
        } catch (OrderDaoException | InvalidOrderException | InvalidDateException ex) {
            fail();
        }

    }

    @Test
    public void getOrderBadDate() {

        LocalDate date = LocalDate.of(19, 9, 1);
        OrderDao dao = new InMemoOrderDao();
        TaxDao tdao = new InMemoTaxDao();
        ProductDao pdao = new InMemoProductDao();
        // test every thing that the user is allowed to put in
        FlooringServiceImpl toTest = new FlooringServiceImpl(dao, tdao, pdao);

        try {

            Order toCheck = toTest.getOrder(date, 0);
            fail();
        } catch (OrderDaoException | InvalidOrderException ex) {
            fail();
        } catch (InvalidDateException ex) {
        }
    }
}
