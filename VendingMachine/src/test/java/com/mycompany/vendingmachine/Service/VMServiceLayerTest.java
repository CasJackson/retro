/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.Service;

import com.mycompany.vendingmachine.dao.AlwaysFailDao;
import com.mycompany.vendingmachine.dao.VMDao;
import com.mycompany.vendingmachine.dao.VMDaoException;
import com.mycompany.vendingmachine.dao.VMDaoInMemo;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.VMItems;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author cas
 */
public class VMServiceLayerTest {

    private VMServiceLayer service;

    public VMServiceLayerTest() {
        VMDao dao = new VMDaoInMemo();

        service = new VMServiceLayerImpl(dao);

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
     * Test of getAllVMItems method, of class VMServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllVMItems() throws Exception {

        VMDao dao = new VMDaoInMemo();

        service = new VMServiceLayerImpl(dao);

        List<VMItems> allItems = service.getAllVMItems();
        assertEquals(2, allItems.size());

        //pull out the last item in the list and write asserts for its properties
    }

    /**
     * Test of vend method, of class VMServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testgoldenPathVend() throws Exception {

        VMDao dao = new VMDaoInMemo();

        service = new VMServiceLayerImpl(dao);

        BigDecimal money = service.deposit(new BigDecimal("5.00"));
        service.vend(8);

        Change returned = service.getUserChange();

        assertEquals(3, returned.getDollars());
        assertEquals(3, returned.getQuarters());
        assertEquals(0, returned.getDimes());
        assertEquals(0, returned.getNickels());
        assertEquals(0, returned.getPennies());

        VMItems toCheck = dao.getById(8);
        assertEquals(19, toCheck.getQuanity());

    }

    @Test
    public void testVendInsufficientFunds() throws OutOfStockException, VMDaoException, InsufficientFundsException, InvalidIdException {
        VMDao dao = new VMDaoInMemo();

        service = new VMServiceLayerImpl(dao);
        try {
            BigDecimal money = service.deposit(new BigDecimal(".90"));
            service.vend(8);
            fail();

        } catch (OutOfStockException | VMDaoException ex) {
            fail();

        } catch (InsufficientFundsException ex) {
            try {

                Change returned = service.getUserChange();

                assertEquals(0, returned.getDollars());
                assertEquals(3, returned.getQuarters());
                assertEquals(1, returned.getDimes());
                assertEquals(1, returned.getNickels());
                assertEquals(0, returned.getPennies());

                VMItems toCheck = dao.getById(8);
                assertEquals(20, toCheck.getQuanity());

            } catch (VMDaoException ex1) {
                fail();
            }

        }
    }

    @Test
    public void testVendOutOfStock() throws InsufficientFundsException, VMDaoException {

        VMDao dao = new VMDaoInMemo();

        service = new VMServiceLayerImpl(dao);
        try {
            BigDecimal money = service.deposit(new BigDecimal("5.00"));
            service.vend(9);
            fail();
        } catch (InvalidIdException | VMDaoException | InsufficientFundsException ex) {
            fail(ex.getMessage());
        } catch (OutOfStockException ex) {
            try {

                Change returned = service.getUserChange();
                assertEquals(5, returned.getDollars());
                assertEquals(0, returned.getQuarters());
                assertEquals(0, returned.getDimes());
                assertEquals(0, returned.getNickels());
                assertEquals(0, returned.getPennies());

                VMItems toCheck = dao.getById(9);
                assertEquals(0, toCheck.getQuanity());

            } catch (VMDaoException ex1) {
                fail();
            }

        }
    }

    @Test
    public void testInvalidId() {

        VMDao dao = new VMDaoInMemo();

        service = new VMServiceLayerImpl(dao);
        try {
            BigDecimal money = service.deposit(new BigDecimal("5.00"));
            service.vend(45);
            fail();
        } catch (VMDaoException | InsufficientFundsException | OutOfStockException ex) {
            fail("InvalidException not thrown");
        } catch (InvalidIdException ex) {

            Change returned = service.getUserChange();

            assertEquals(5, returned.getDollars());
            assertEquals(0, returned.getQuarters());
            assertEquals(0, returned.getDimes());
            assertEquals(0, returned.getNickels());
            assertEquals(0, returned.getPennies());

//                VMItems toCheck = dao.getById(28);
//                assertEquals(0, toCheck.getQuanity());
        }
    }

    @Test
    public void testBadDao() {
        VMDao dao = new AlwaysFailDao();

        service = new VMServiceLayerImpl(dao);
        try {

            BigDecimal money = service.deposit(new BigDecimal("5.00"));
            service.vend(8);
            fail();

        } catch (VMDaoException ex) {

            Change returned = service.getUserChange();

            assertEquals(5, returned.getDollars());
            assertEquals(0, returned.getQuarters());
            assertEquals(0, returned.getDimes());
            assertEquals(0, returned.getNickels());
            assertEquals(0, returned.getPennies());

        } catch (InsufficientFundsException | OutOfStockException | InvalidIdException ex) {
            fail();
        }

    }

    /**
     * Test of deposit method, of class VMServiceLayer.
     */
    @Test
    public void testDeposit() throws Exception {

        VMDao dao = new VMDaoInMemo();

        service = new VMServiceLayerImpl(dao);

        BigDecimal money = service.deposit(new BigDecimal("5.00"));
        service.vend(8);

        Change returned = service.getUserChange();

        assertEquals(3, returned.getDollars());
        assertEquals(3, returned.getQuarters());
        assertEquals(0, returned.getDimes());
        assertEquals(0, returned.getNickels());
        assertEquals(0, returned.getPennies());

        VMItems toCheck = dao.getById(8);
        assertEquals(19, toCheck.getQuanity());

    }

    @Test
    public void testInvalidDeposit() {

        try {
            VMDao dao = new VMDaoInMemo();
            
            service = new VMServiceLayerImpl(dao);
            
            BigDecimal money = service.deposit(new BigDecimal("-1"));
            fail();
           
        } catch (InsufficientFundsException ex) {
            Change returned = service.getUserChange();

            assertEquals(0, returned.getDollars());
            assertEquals(0, returned.getQuarters());
            assertEquals(0, returned.getDimes());
            assertEquals(0, returned.getNickels());
            assertEquals(0, returned.getPennies());
        }

    }

    /**
     * Test of getUserChange method, of class VMServiceLayer.
     */
    @Test
    public void testGetUserChange() throws InsufficientFundsException {

        VMDao dao = new VMDaoInMemo();

        service = new VMServiceLayerImpl(dao);
       
        BigDecimal money = service.deposit(new BigDecimal("4.63"));
        Change returned = service.getUserChange();

        assertEquals(4, returned.getDollars());
        assertEquals(2, returned.getQuarters());
        assertEquals(1, returned.getDimes());
        assertEquals(0, returned.getNickels());
        assertEquals(3, returned.getPennies());

    }

}
