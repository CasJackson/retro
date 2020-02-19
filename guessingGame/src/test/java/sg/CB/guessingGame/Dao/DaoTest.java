/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.CB.guessingGame.Dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import sg.CB.guessingGame.ApplicationConfiguration;
import sg.CB.guessingGame.Dto.Game;
import sg.CB.guessingGame.Dto.Round;

/**
 *
 * @author cas
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfiguration.class)
@Profile("DaoTest")
public class DaoTest {

    @Autowired
    Dao dao;
//    add new game should return the id of the game that was added
//   also incorporate time stamp

    private String gameQuery = "SELECT *, g.GameId,g.gameDone,g.TargetNum"
            + " FROM Game g";

    private String roundQuery = "SELECT *, r.RoundId, g.GameId, r.guessNum, r.GuessTime, r.partialMatch, r.ExactMatch"
            + " FROM Round r ";

    public DaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        dao.deleteAllGames();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addNewGame method, of class Dao.
     */
    @Test
    public void GoldenPathAddNewGame() {

        try {
            Game toTest = dao.addNewGame(1984);
            Game validate = dao.getGameById(toTest.getGameId());

            assertEquals(1984, validate.getTargetNum());
            assertEquals(toTest.getGameId(), validate.getGameId());
            assertEquals(false, validate.isGameDone());
        } catch (GuessingGamePersistenceException | InvalidTargetNumberException | InvalidIdException| InvalidGameStatusException ex) {
            fail();
        } 
    }
    /**
     * Test of getGameById method, of class Dao.
     */
    @Test
    public void testGoldenPathGetGameById() {
        try {
            Game toTest = dao.addNewGame(9876);
            Game validate = dao.getGameById(toTest.getGameId());
            assertEquals(toTest.getGameId(), validate.getGameId());
        } catch (GuessingGamePersistenceException | InvalidIdException | InvalidTargetNumberException | InvalidGameStatusException ex) {
           fail();
        }
    }
    
    
    
    
     @Test
    public void GetGameByIdBadId()  {
        try {
            Game validate = dao.getGameById(-1);
            assertEquals(345, validate.getGameId());
            fail();
        } catch (GuessingGamePersistenceException ex) {
           fail();
        } catch (InvalidIdException ex) {
          
        }
        
    }

    /**
     * Test of getGames method, of class Dao.
     */
    @Test
    public void testGoldenPathGetGames()  {
        try {
            Game toAdd = dao.addNewGame(1234);
            List<Game> toTest = dao.getGames();
            Game validate = toTest.get(0);
            
            assertEquals(toAdd.getGameId(), validate.getGameId());
            assertEquals(toAdd.getTargetNum(), validate.getTargetNum());
        } catch (GuessingGamePersistenceException | InvalidTargetNumberException | InvalidGameStatusException ex) {
           fail();
        }
   }
    

    /**
     * Test of getRoundsById method, of class Dao.
     */
    @Test
    public void testGoldenPathGetRoundsById() {
        try {
            Game toAdd = dao.addNewGame(1234);
            Round guess1 = new Round();
            guess1.setGuessNum(4321);
            guess1.setGameId(toAdd.getGameId());
            guess1.setPartialMatch(4);
            guess1.setExactMatch(0);
            
            LocalDateTime ldt = LocalDateTime.of(2019, 10, 11, 15, 50, 30);
            Timestamp today = Timestamp.valueOf(ldt);
            
            guess1.setGuessTime(today);
            
            Round toTest = dao.userGuess(guess1);
            
            assertEquals(guess1.getGameId(), toTest.getGameId());
            assertEquals(guess1.getRoundId(), toTest.getRoundId());
            assertEquals(guess1.getGuessNum(), toTest.getGuessNum());
            assertEquals(guess1.getPartialMatch(), toTest.getPartialMatch());
            assertEquals(guess1.getExactMatch(), toTest.getExactMatch());
            
            Round guess2 = new Round();
            guess2.setGuessNum(6789);
            guess2.setGameId(toAdd.getGameId());
            guess2.setPartialMatch(0);
            guess2.setExactMatch(0);
            
            guess2.setGuessTime(today);
            
            Round toTest2 = dao.userGuess(guess2);
            
            assertEquals(guess2.getGameId(), toTest2.getGameId());
            assertEquals(guess2.getRoundId(), toTest2.getRoundId());
            assertEquals(guess2.getGuessNum(), toTest2.getGuessNum());
            assertEquals(guess2.getPartialMatch(), toTest2.getPartialMatch());
            assertEquals(guess2.getExactMatch(), toTest2.getExactMatch());
            
//        dao.getRoundsById(guess);
//            assertEquals(1, toTest.getRoundId());
        } catch (GuessingGamePersistenceException | InvalidTargetNumberException | InvalidGameStatusException | RoundDaoException | InvalidGuessNumException ex) {
           fail();
        }
    }

    /**
     * // * Test of userGuess method, of class Dao.
     */
    @Test
    public void testGoldenPathUserGuess()  {
        try {
            Game toAdd = dao.addNewGame(1234);
            Round guess1 = new Round();
            
            guess1.setGuessNum(4321);
            guess1.setGameId(toAdd.getGameId());
            guess1.setPartialMatch(4);
            guess1.setExactMatch(0);
            
            Round toTest = dao.userGuess(guess1);
            
            assertEquals(guess1.getGameId(), toTest.getGameId());
            assertEquals(guess1.getGameId(), toTest.getGameId());
            assertEquals(guess1.getGuessNum(), toTest.getGuessNum());
            assertEquals(guess1.getPartialMatch(), toTest.getPartialMatch());
            assertEquals(guess1.getExactMatch(), toTest.getExactMatch());
        } catch (GuessingGamePersistenceException | InvalidTargetNumberException | InvalidGameStatusException | RoundDaoException | InvalidGuessNumException ex) {
            fail();
        }
        
    }
            @Test
    public void UserGuessbadGuessNum()  {
        try {
            Game toAdd = dao.addNewGame(1234);
            Round guess1 = new Round();
            
            guess1.setGuessNum(541298);
            guess1.setGameId(toAdd.getGameId());
            guess1.setPartialMatch(4);
            guess1.setExactMatch(0);
            
            Round toTest = dao.userGuess(guess1);
            
            assertEquals(541298, toTest.getGuessNum());
            fail();
        } catch (GuessingGamePersistenceException | InvalidTargetNumberException | InvalidGameStatusException | RoundDaoException ex) {
            fail();
        }catch (InvalidGuessNumException ex){
            
        }
    }
    /**
     * Test of markGameAsDone method, of class Dao.
     */
    @Test
    public void testGoldenPathMarkGameAsDone() {
        try {
            Game toTest = dao.addNewGame(1234);
                    
            dao.markGameAsDone(toTest.getGameId());
            Game validate = dao.getGameById(toTest.getGameId());
          

          
            
            assertEquals(toTest.getGameId(), validate.getGameId());
            
           
            assertEquals(true, validate.isGameDone());
            

        } catch (GuessingGamePersistenceException | InvalidIdException | InvalidTargetNumberException | InvalidGameStatusException ex) {
            fail(ex.getMessage());
        }

        
        
    }

    
//      @Test
//    public void brokeMarkGameAsDone() {
//        
//        try {
//            Game toTest = dao.addNewGame(1234);
//                    
//            dao.markGameAsDone(toTest.getGameId());
//            Game validate = dao.getGameById(toTest.getGameId());
//            
//            
//            
//            
//            assertEquals(toTest.getGameId(), validate.getGameId());
//            
//           
//            assertEquals(true, validate.isGameDone());
//            fail();
//        } catch (GuessingGamePersistenceException | InvalidTargetNumberException |  InvalidIdException ex) {
//           fail();
//        }catch(InvalidGameStatusException ex){
//            
 //     }               
             
}
