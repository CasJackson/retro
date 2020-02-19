    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.CB.guessingGame.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.assertj.core.util.DateUtil.yesterday;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import sg.CB.guessingGame.ApplicationConfiguration;
import sg.CB.guessingGame.Dao.GuessingGamePersistenceException;
import sg.CB.guessingGame.Dao.InvalidGameStatusException;
import sg.CB.guessingGame.Dao.InvalidGuessNumException;
import sg.CB.guessingGame.Dao.InvalidIdException;
import sg.CB.guessingGame.Dao.InvalidTargetNumberException;
import sg.CB.guessingGame.Dao.RoundDaoException;
import sg.CB.guessingGame.Dto.Game;
import sg.CB.guessingGame.Dto.Round;
import sg.CB.guessingGame.daoTest.DaoInMemo;

/**
 *
 * @author cas
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfiguration.class)

public class ServiceImplTest {

    ServiceImpl service;
    DaoInMemo dao;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        dao = new DaoInMemo();
        service = new ServiceImpl(dao);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createGame method, of class ServiceImpl.
     */
    @Test
    public void goldenPathCreateGame() {

        try {
            Game toTest = new Game();

            toTest.setTargetNum(5678);
            toTest.setGameId(2);
            toTest.setGameDone(false);

            

            Game validationGame = dao.getGameById(2);

            assertEquals(toTest.getTargetNum(), validationGame.getTargetNum());
            assertEquals(toTest.getGameId(), validationGame.getGameId());
            assertEquals(toTest.isGameDone(), validationGame.isGameDone());
        } catch (GuessingGamePersistenceException | InvalidIdException ex) {
            fail();
        }
    }

    /**
     * Test of getGameById method, of class ServiceImpl.
     */
    @Test
    public void goldenPathGetGameById() {

        try {
            
            Game validationGame = service.getGameById(1);

            assertEquals(1234, validationGame.getTargetNum());
            assertEquals(1, validationGame.getGameId());
            assertEquals(false, validationGame.isGameDone());

        } catch (GuessingGamePersistenceException | InvalidIdException ex) {
            fail();
        }
    }

     @Test
    public void GetGameByBadId() {

        try {
            Game validationGame = service.getGameById(-1);
                validationGame.getGameId();
                fail();
            
          

        } catch (GuessingGamePersistenceException ex) {
            fail();
        } catch (InvalidIdException ex) {
            
        }
    }
    /**
     * Test of getGames method, of class ServiceImpl.
     */
    @Test
    public void goldenPathGetGames() {

        try {
            List<Game> listOfGames = service.getGames();

            assertEquals(3, listOfGames.size());
        } catch (GuessingGamePersistenceException ex) {
            fail();
        }
    }
    
   

    /**
     * Test of roundsGetById method, of class ServiceImpl.
     * @param id
     */
    @Test
    public void goldenPathRoundsGetById() {
        try {
           
           
            
            List<Round> roundList = service.roundsGetById(1);
        Round toTest = roundList.get(0);
            
            
            assertEquals(1, toTest.getRoundId());
           


        } catch (GuessingGamePersistenceException | InvalidIdException ex) {
            fail();
        }
       
    }

        @Test
    public void RoundsGetByBadId() {

        try {
            List<Round> roundList = service.roundsGetById(-1);

            assertEquals(0, roundList.size());
            fail();
        } catch (GuessingGamePersistenceException ex) {
                fail();
        } catch (InvalidIdException ex) {
            
        }

    }
    /**
     * Test of userGuess method, of class ServiceImpl.
     */
    @Test
    public void goldenPathUserGuess() {

        Round toTest = new Round();

        toTest.setGameId(1);
        toTest.setGuessNum(4321);

        assertEquals(1, toTest.getGameId());
        assertEquals(4321, toTest.getGuessNum());

    }

     @Test
    public void userBadGuess() {

        
        try {
            Round toAdd = new Round();
            toAdd.setGameId(3);
            toAdd.setGuessNum(1092);
            toAdd.setExactMatch(4);
            toAdd.setPartialMatch(0);
            service.userGuess(toAdd);
            Game toTest = service.getGameById(3);
            
            assertEquals(true, toTest.isGameDone());
            
            
            
            
            
            
            
            
        } catch (GuessingGamePersistenceException | InvalidNumberException | RoundDaoException | InvalidIdException | InvalidGuessNumException ex) {
           fail();
        } catch (InvalidGameException ex) {
          
        }
                
            
    }
    
    @Test 
    public void userGuessInvalid(){
       
        try {
            Round toAdd = new Round();
            toAdd.getRoundId();
            toAdd.setGameId(2);
            toAdd.setGuessNum(96321);
            service.userGuess(toAdd);
            fail();
        } catch (InvalidGameException | GuessingGamePersistenceException | InvalidNumberException | RoundDaoException | InvalidIdException ex) {
            fail();
        } catch (InvalidGuessNumException ex) {
           
        }
    }
    /**
     * Test of hasDuplicates method, of class ServiceImpl.
     */
    @Test
    public void testHasDuplicates() {
    }

    /**
     * Test of computePartialMatches method, of class ServiceImpl.
     */
    @Test
    public void gPComputePartialMatches() {
        try {
            Round toAdd = new Round();
            toAdd.getRoundId();
            toAdd.setGameId(1);
            toAdd.setGuessNum(1243);
            toAdd.setExactMatch(2);
            toAdd.setPartialMatch(4);
            service.userGuess(toAdd);
        } catch (InvalidGameException | GuessingGamePersistenceException | InvalidNumberException | RoundDaoException | InvalidIdException | InvalidGuessNumException ex) {
           fail();
        }

    }

    
     @Test
    public void brokeComputePartialMatches() {
        try {
            Round toAdd = new Round();
            toAdd.getRoundId();
            toAdd.setGameId(1);
            toAdd.setGuessNum(1243);
            toAdd.setExactMatch(0);
            toAdd.setPartialMatch(1);
            service.userGuess(toAdd);
            fail();
;        } catch (InvalidGameException | GuessingGamePersistenceException | InvalidNumberException | RoundDaoException | InvalidIdException | InvalidGuessNumException ex) {
           fail();
        }

    }
}
