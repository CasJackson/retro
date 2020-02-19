/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.CB.guessingGame.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sg.CB.guessingGame.Dao.Dao;
import sg.CB.guessingGame.Dao.GuessingGamePersistenceException;
import sg.CB.guessingGame.Dao.InvalidGameStatusException;
import sg.CB.guessingGame.Dao.InvalidGuessNumException;
import sg.CB.guessingGame.Dao.InvalidIdException;
import sg.CB.guessingGame.Dao.InvalidTargetNumberException;
import sg.CB.guessingGame.Dao.RoundDaoException;
import sg.CB.guessingGame.Dto.Game;
import sg.CB.guessingGame.Dto.Round;
import sg.CB.guessingGame.Service.InvalidGameException;
import sg.CB.guessingGame.Service.Service;
import sg.CB.guessingGame.Service.InvalidNumberException;

/**
 *
 * @author cas
 */
@RestController
@RequestMapping("/api")
public class Controller {

//      mysql tables for games and guess
    @Autowired
    Service service;

    @PostMapping("/begin")
    public int getNewGameId() {

        try {
            Game newGameId = service.createGame();

            return newGameId.getGameId();
        } catch (GuessingGamePersistenceException | InvalidTargetNumberException | InvalidGameStatusException ex) {
            return -1;
        }
    }

    //game/{gameId} - GET -- returns a specific game based on ID.  Be sure in-progress
    //                       games do not display their answer
    @GetMapping("games/{gameId}")
    public Game getGameById(@PathVariable int gameId) {
        Game toReturn = null;
        try {
            toReturn = service.getGameById(gameId);

            return toReturn;
        } catch (GuessingGamePersistenceException | InvalidIdException ex) {
            return null;
        }
    }

    @GetMapping("/game")
    public List<Game> getGames() {
        List<Game> toReturn = null;
        try {
            toReturn = service.getGames();

            return toReturn;
        } catch (GuessingGamePersistenceException | InvalidIdException ex) {
            return null;
        }
    }
//    "rounds/{gameId} – GET – Returns a list of rounds for the specified game sorted by time.

    @GetMapping("rounds/{gameId}")
    public List<Round> getRoundsById(@PathVariable int gameId) {

        try {
            List<Round> roundId = service.roundsGetById(gameId);

            return roundId;
        } catch (GuessingGamePersistenceException | InvalidIdException ex) {
            return null;
        }
    }

    @PostMapping("/guess")
    public Round getGuess(@RequestBody Round toAdd) throws InvalidNumberException, RoundDaoException, InvalidGameException {

        Round toReturn;

        try {
            toReturn = service.userGuess(toAdd);

            return toReturn;
        } catch (GuessingGamePersistenceException | InvalidIdException | InvalidGuessNumException ex) {
            return null;

        }

    }

}
