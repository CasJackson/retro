/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.CB.guessingGame.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.transaction.annotation.Transactional;
import sg.CB.guessingGame.Dto.Game;
import sg.CB.guessingGame.Dto.Round;

/**
 *
 * @author cas
 */
@Component
@Profile({"production","DaoTest"})
public class TemplateDao implements Dao {

    @Autowired
    JdbcTemplate template;
//    add new game should return the id of the game that was added
//   also incorporate time stamp

    private String gameQuery = "SELECT *, g.gameId,g.gameDone,g.TargetNum"
            + " FROM Game g";

    private String roundQuery = "SELECT *, r.RoundId, r.gameId, r.guessNum, r.GuessTime, r.partialMatch, r.ExactMatch"
            + " FROM Round r ";

    @Override
    @Transactional
    public Game addNewGame(int targetNum) throws GuessingGamePersistenceException, InvalidTargetNumberException, InvalidGameStatusException  {
            if (targetNum > 9999) {
                throw new InvalidTargetNumberException("Error: Invalid target number!");
            }
            
             if (targetNum < 999) {
                throw new InvalidTargetNumberException("Error: Invalid target number!");
            }
        String insert = "INSERT INTO Game (TargetNum,gameDone) VALUES (?,?)";

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement toReturn = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

                toReturn.setInt(1, targetNum);
                toReturn.setBoolean(2, false);
                    
                return toReturn;
            }

        };
                
        template.update(psc, holder);

        int generatedId = holder.getKey().intValue();

        Game newGame = new Game(); 
      
        newGame.setTargetNum(targetNum);

        newGame.setGameId(generatedId);
        newGame.setGameDone(false);
            if (newGame.isGameDone() != false){
                throw new InvalidGameStatusException("Error: Invalid game status.");
            }
        return newGame;

    }
    

    @Override
    public Game getGameById(int id) throws GuessingGamePersistenceException, InvalidIdException {
        Game toReturn = null;
        String query = gameQuery + " WHERE g.gameId = ?";

        try{
            toReturn = template.queryForObject(query, new GameMapper(), id);
        } catch( EmptyResultDataAccessException ex ) {

                throw new InvalidIdException("Error: Invalid Id.", ex);
            }
        return toReturn;
    }

    @Override
    public List<Game> getGames() throws GuessingGamePersistenceException {

        String query = gameQuery;

       List<Game> toReturn = template.query(query, new GameMapper());

        return toReturn;
    }

    @Override
    public List<Round> getRoundsById(int id) throws GuessingGamePersistenceException {

        String query = roundQuery + " WHERE r.gameId = ?";

        List<Round> toReturn = template.query(query, new RoundMapper(), id);

        return toReturn;
    }
    
     @Override
     public Round userGuess(Round toAdd)throws GuessingGamePersistenceException, RoundDaoException, InvalidGuessNumException {
            
     
         
         String insert = "INSERT INTO Round (guessNum, gameId, guessTime, partialMatch, ExactMatch) VALUES (?,?,?,?,?)";

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement toReturn = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

                toReturn.setInt(1, toAdd.getGuessNum());
                toReturn.setInt(2, toAdd.getGameId());
                toReturn.setTimestamp(3, toAdd.getGuessTime());
                toReturn.setInt(4, toAdd.getPartialMatch());
                toReturn.setInt(5, toAdd.getExactMatch());
                
                
                
         
                return toReturn;
            }

        };

        
        template.update(psc, holder);

        int generatedId = holder.getKey().intValue();

        if (toAdd.getGuessNum() > 9999) {
            throw new InvalidGuessNumException("Error: Invalid guess.");
        }
         if (toAdd.getGuessNum() < 999) {
            throw new InvalidGuessNumException("Error: Invalid guess.");
        }
            
        toAdd.setRoundId(generatedId);
             if (toAdd == null){
          throw new RoundDaoException("Could not add a new round.");
      }   
//              int eMatch = toAdd.getExactMatch();
//                 int userGuess = toAdd.getGuessNum();
//          if (eMatch == userGuess) {
//                
//               System.out.println("Game over! you guessed the correct number: " + eMatch);
//              
//          }
        return toAdd;


    }

   

    @Override
    public Game markGameAsDone(int id)throws GuessingGamePersistenceException, InvalidIdException  {
        
        
         String update = "update Game SET gameDone = 1 WHERE gameId = ? ";

//       update TABLENAME set WhateverColumn = SomeValue, OtherColumn = AnotherValue where CONDITION

       
        
        template.update(update,id);

        Game gameOver = getGameById(id);
        

    return gameOver;
    
    }
    
    
    @Override
   @Transactional
   public void deleteAllGames() {
       String deleteRounds = " Delete  from Round ";
       String deleteGames = " Delete from Game ";
       template.update(deleteRounds);
       template.update(deleteGames);
   }
    
    private class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet results, int rowNum) throws SQLException {
            Game toReturn = new Game();
            toReturn.setGameId(results.getInt("GameId"));
            toReturn.setGameDone(results.getBoolean("GameDone"));
            toReturn.setTargetNum(results.getInt("targetNum"));
            return toReturn;
        }

    }

    private class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet results, int rowNum) throws SQLException {
            Round toReturn = new Round();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy, MM, dd, HH, mm");
            toReturn.setGameId(results.getInt("GameId"));
            toReturn.setRoundId(results.getInt("RoundId"));
            toReturn.setGuessNum(results.getInt("guessNum"));
            LocalDateTime date = LocalDateTime.parse(results.getString("guessTime"), formatter);
            toReturn.setPartialMatch(results.getInt("PartialMatch"));
            toReturn.setExactMatch(results.getInt("ExactMatch"));
            return toReturn;
        }
    }
}
