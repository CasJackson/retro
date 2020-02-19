/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.CB.guessingGame.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sg.CB.guessingGame.Dao.Dao;
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
@Component
    
public class ServiceImpl implements Service {

    Dao dao;

    @Autowired
    public ServiceImpl(Dao dao) {
        this.dao = dao;
    }

    @Override
    public Game createGame() throws GuessingGamePersistenceException, InvalidTargetNumberException, InvalidGameStatusException {

        int targetNum = GenNewNum();

        Game newGame = dao.addNewGame(targetNum);

        return newGame;

    }

    @Override
    public Game getGameById(int id) throws GuessingGamePersistenceException, InvalidIdException {
            
        Game foundGame = dao.getGameById(id);
         if (id <= 0){
                throw new InvalidIdException("Could not find Id");
            }
       if (foundGame == null) {
                throw new InvalidIdException("Could not find Id");
//            }
//        if (!foundGame.isGameDone()) {
//            foundGame.setTargetNum(5);
//            
//
//        }
//        return foundGame;

    }
        return foundGame;
    }
    @Override
    public List<Game> getGames() throws GuessingGamePersistenceException {

        List<Game> foundGame = dao.getGames();

        return foundGame;

    }

    @Override
    public List<Round> roundsGetById(int id) throws GuessingGamePersistenceException, InvalidIdException  {

        List<Round> rounds = dao.getRoundsById(id);
        
         
        if (!rounds.equals(id) ) {
             throw new InvalidIdException("Could not find ");
        }
        return rounds;

    }

    @Override
    public Round userGuess(Round toAdd) throws InvalidGameException, GuessingGamePersistenceException, InvalidNumberException, RoundDaoException, InvalidIdException, InvalidGuessNumException {

        Game toGet = dao.getGameById(toAdd.getGameId());

        int guess = toAdd.getGuessNum();

        if (guess > 9999) {
            throw new InvalidNumberException("ERROR: Invalid number, please pick a 4 digit number.");
        }

        if (guess < 999) {
            throw new InvalidNumberException("ERROR: Invalid number, please pick a 4 digit number.");
        }
        Timestamp getTime = toAdd.getGuessTime();

        int eMatch = computeExact(toGet.getTargetNum(), guess);
        int pMatch = computePartialMatches(toGet.getTargetNum(), guess);

        toAdd.setGuessTime(getTime);
        toAdd.setExactMatch(eMatch);
        toAdd.setPartialMatch(pMatch);
        dao.userGuess(toAdd);
        // tell dao to save round

        int targetNum = toGet.getTargetNum();
        if (guess == targetNum) {

            int winnerId = toAdd.getGameId();
            dao.markGameAsDone(winnerId);

        }

        //check if we got 4 exact matches
        //if so, tell the dao to update that row in the games table to set the game as done
        return toAdd;
    }

    private int GenNewNum() throws GuessingGamePersistenceException {

        int tNum = 0;
        Random rng = new Random();

        List<Integer> avail = new ArrayList();
//            int toReturn = 0;
        for (int i = 0; i < 10; i++) {
            avail.add(i);
        }
        for (int i = 0; i < 4; i++) {
            tNum *= 10;
            int digitIndex = rng.nextInt(avail.size());
            int digit = avail.get(digitIndex);
            tNum += digit;
            avail.remove(digitIndex);
        }
        //Game addedGame = dao.addNewGame(tNum);
        //return addedGame.getGameId();

        return tNum;
    }

    boolean hasDuplicates(int n) {
        boolean toReturn = false;

        boolean[] seenDigit = new boolean[]{false, false, false, false, false, false, false, false, false};

        for (int i = 0; i < 4; i++) {
            int onesPlace = n % 10;
            n /= 10;
            boolean seenBefore = seenDigit[onesPlace];
            if (seenBefore) {
                toReturn = true;
                break;
            } else {
                seenDigit[onesPlace] = true;
            }

        }
        return toReturn;

    }

    private int computeExact(int target, int guess) {
        int toReturn = 0;
        for (int i = 0; i < 4; i++) {
            int targetOnes = target % 10;
            int guessOnes = guess % 10;

            if (targetOnes == guessOnes) {
                toReturn++;

            }
            target /= 10;
            guess /= 10;
            targetOnes = target % 10;
            guessOnes = guess % 10;

        }
        return toReturn;
    }

    int computePartialMatches(int target, int guess) {
        int toReturn = 0;
        //fill in validation checks for wrong numbers
        String t = target + "";
        String g = guess + "";
        if (target < 1000) {
            t = "0" + t;
        }
        if (guess < 1000) {
            g = "0" + g;
        }
        for (int i = 0; i < 4; i++) {
            char gc = g.charAt(i);
            for (int j = 0; j < 4; j++) {
                if (i != j) {
                    char tc = t.charAt(j);
                    if (gc == tc) {
                        toReturn++;
                    }
                }
            }

        }
        return toReturn;

    }

}
