/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.VMItems;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
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
public class VMDaoTest {

    private VMDao dao = new VMDaoFileImpl("test.txt");

    public VMDaoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws IOException {

        Path testPath = Path.of("test.txt");
        Path seedPath = Path.of("seed.txt");
        if (Files.exists(testPath, LinkOption.NOFOLLOW_LINKS)) {
            Files.delete(testPath);
        }

        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllVMItems method, of class VMDao.
     */
    @Test
    public void testGetAllGoldenPath() {
        try {
            VMDao dao = new VMDaoFileImpl("test.txt");
            List<VMItems> allItems = dao.getAllVMItems();

            assertEquals(4, allItems.size());
            VMItems only = allItems.get(0);

            assertEquals(1, only.getId());
            assertEquals("Skittles", only.getName());
            assertEquals(10, only.getQuanity());
            assertEquals(new BigDecimal("1.25"), only.getPrice());

        } catch (VMDaoException ex) {
            fail();
        }
    }

    /**
     * Test of getById method, of class VMDao.
     */
    @Test
    public void goldenPathTestGetById() {
        try {
            VMDao dao = new VMDaoFileImpl("test.txt");

            VMItems idTest = dao.getById(1);

            assertEquals(1, idTest.getId());
            assertEquals("Skittles", idTest.getName());
            assertEquals(10, idTest.getQuanity());
            assertEquals(new BigDecimal("1.25"), idTest.getPrice());

        } catch (VMDaoException ex) {
            fail();
        }

    }

    @Test
    public void InvalidIdTestGetById() {
        try {
            VMDao dao = new VMDaoFileImpl("test.txt");

            VMItems idTest = dao.getById(19);

            if (idTest != null) {
                fail();
            }

        } catch (VMDaoException ex) {
            fail();
        }

    }

    /**
     * Test of editVMItems method, of class VMDao.
     */
    @Test
    public void goldenPathTestEditVMItems() throws Exception {
        try {
            VMDao dao = new VMDaoFileImpl("test.txt");

            VMItems toTest = dao.getById(4);

            toTest.setQuanity(1234);

            dao.editVMItems(toTest);

            VMItems validationItem = dao.getById(4);

            assertEquals(1234, validationItem.getQuanity());

        } catch (VMDaoException ex) {
            fail();
        }
    }

    @Test
    public void testEditVMItemsItem() throws Exception {
        try {
            VMDao dao = new VMDaoFileImpl("test.txt");

            VMItems toTest = new VMItems();
            toTest.setId(1234);
            toTest.setName("oreo");
            toTest.setPrice(BigDecimal.ZERO);

            toTest.setQuanity(1234);

            dao.editVMItems(toTest);
            fail();

        } catch (VMDaoException ex) {

        }
    }

}
