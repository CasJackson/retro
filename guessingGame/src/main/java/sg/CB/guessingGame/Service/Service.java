/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.CB.guessingGame.Service;

import java.sql.Timestamp;
import java.util.List;
import sg.CB.guessingGame.Dao.GuessingGamePersistenceException;
import sg.CB.guessingGame.Dao.InvalidGameStatusException;
import sg.CB.guessingGame.Dao.InvalidGuessNumException;
import sg.CB.guessingGame.Dao.InvalidIdException;
import sg.CB.guessingGame.Dao.InvalidTargetNumberException;
import sg.CB.guessingGame.Dao.RoundDaoException;
import sg.CB.guessingGame.Dto.Game;
import sg.CB.guessingGame.Dto.Round;

/**
 *
 * @author cas
 */
public interface Service {
    public Game createGame() throws GuessingGamePersistenceException, InvalidTargetNumberException,InvalidGameStatusException  ;
    public Game getGameById(int id)throws GuessingGamePersistenceException,InvalidIdException ;
    public List<Round> roundsGetById(int id) throws GuessingGamePersistenceException, InvalidIdException;
     public List<Game>  getGames()throws GuessingGamePersistenceException, InvalidIdException;
     public Round userGuess(Round toAdd)throws InvalidGameException, GuessingGamePersistenceException, InvalidNumberException, RoundDaoException,InvalidIdException,InvalidGuessNumException;
}
