/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.CB.guessingGame.Dao;

import java.util.List;
import sg.CB.guessingGame.Dto.Game;
import sg.CB.guessingGame.Dto.Round;

/**
 *
 * @author cas
 */
public interface Dao {

    public Game addNewGame(int targetNum) throws GuessingGamePersistenceException, InvalidTargetNumberException, InvalidGameStatusException ;

    public Game getGameById(int id) throws GuessingGamePersistenceException, InvalidIdException;

    public List<Game> getGames() throws GuessingGamePersistenceException;

    public List<Round> getRoundsById(int id) throws GuessingGamePersistenceException;

    public Round userGuess(Round toAdd) throws GuessingGamePersistenceException, RoundDaoException, InvalidGuessNumException;

    public Game markGameAsDone(int id) throws GuessingGamePersistenceException,InvalidIdException;

    public void deleteAllGames();
}
