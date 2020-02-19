/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.CB.guessingGame.daoTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.transaction.annotation.Transactional;
import sg.CB.guessingGame.Dao.Dao;
import sg.CB.guessingGame.Dao.GuessingGamePersistenceException;
import sg.CB.guessingGame.Dao.InvalidGameStatusException;
import sg.CB.guessingGame.Dao.InvalidGuessNumException;
import sg.CB.guessingGame.Dao.InvalidIdException;
import sg.CB.guessingGame.Dao.InvalidTargetNumberException;
import sg.CB.guessingGame.Dao.RoundDaoException;
import sg.CB.guessingGame.Dao.TemplateDao;
import sg.CB.guessingGame.Dto.Game;
import sg.CB.guessingGame.Dto.Round;

/**
 *
 * @author cas
 */
public class DaoInMemo implements Dao {
    
  

    private List<Game> allGames = new ArrayList<>();

    private List<Round> allRounds = new ArrayList<>();

    public DaoInMemo() {

        Game g1 = new Game();
        g1.setGameId(1);
        g1.setTargetNum(1234);
        g1.setGameDone(false);
        allGames.add(g1);

        Game g2 = new Game();
        g2.setGameId(2);
        g2.setTargetNum(5678);
        g2.setGameDone(false);
        allGames.add(g2);

         Game g3 = new Game();
        g3.setGameId(3);
        g3.setTargetNum(1092);
        g3.setGameDone(true);
        allGames.add(g3);
        
        Round r1 = new Round();
        r1.setRoundId(1);
        r1.setGameId(1);
        r1.setGuessNum(4321);
        LocalDateTime ldt1 = LocalDateTime.of(2019, 10, 11, 15, 50, 30);
        Timestamp yesterday = Timestamp.valueOf(ldt1);
        r1.setGuessTime(yesterday);
        r1.setPartialMatch(4);
        r1.setExactMatch(0);
        allRounds.add(r1);

        Round r2 = new Round();
        r2.setRoundId(2);
        r2.setGameId(2);
        r2.setGuessNum(7638);
        LocalDateTime ldt2 = LocalDateTime.of(2019, 10, 12, 15, 50, 30);
        Timestamp today = Timestamp.valueOf(ldt2);
        r2.setGuessTime(today);
        r2.setPartialMatch(2);
        r2.setExactMatch(1);
        allRounds.add(r2);

    }

    @Override
    @Transactional
    public Game addNewGame(int targetNum) throws GuessingGamePersistenceException, InvalidGameStatusException, InvalidTargetNumberException {

        if (targetNum > 9999) {
            throw new InvalidTargetNumberException("Error: Invalid target number!");
        }

        if (targetNum < 999) {
            throw new InvalidTargetNumberException("Error: Invalid target number!");
        }

        Game newGame = new Game();

        newGame.setTargetNum(1234);
        int gameId = 0;
        gameId++;
        newGame.setGameId(gameId);

        allGames.add(newGame);
        newGame.isGameDone();
        if (newGame.isGameDone() != false) {
            throw new InvalidGameStatusException("Error: Invalid game status.");
        }
        return newGame;
    }

    @Override
    public Game getGameById(int id) throws GuessingGamePersistenceException, InvalidIdException {
       Game toReturn = null;
        

        for (Game toCheck : allGames) {
            if (toCheck.getGameId() == id) {
                toReturn = toCheck;
                
            }
        }
        
        if( toReturn == null ){
            throw new InvalidIdException("Error: Invalid Id.");
        }
        
        
        
        return toReturn;
    }

    @Override
    public List<Game> getGames() throws GuessingGamePersistenceException {

        return allGames;
    }

    @Override
    public List<Round> getRoundsById(int id) throws GuessingGamePersistenceException {

        return allRounds;
    }

    @Override
    public Round userGuess(Round toAdd) throws GuessingGamePersistenceException, RoundDaoException, InvalidGuessNumException {
            
         
        
        if (toAdd.getGuessNum() > 9999) {
            throw new InvalidGuessNumException("Error: Invalid guess.");
        }
        if (toAdd.getGuessNum() < 999) {
            throw new InvalidGuessNumException("Error: Invalid guess.");
        }

        toAdd.setRoundId(0);
        
        if (toAdd == null) {
            throw new RoundDaoException("Could not add a new round.");
        }
        return toAdd;
    }

    @Override
    public Game markGameAsDone(int id) throws GuessingGamePersistenceException, InvalidIdException {

        Game gameOver = getGameById(id);
        gameOver.setGameDone(true);
        return gameOver;
    }

    @Override
    public void deleteAllGames() {
        allGames.clear();
    }
}
