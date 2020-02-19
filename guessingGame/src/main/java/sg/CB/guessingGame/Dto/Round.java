/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.CB.guessingGame.Dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import static java.time.temporal.TemporalQueries.localDate;

/**
 *
 * @author cas
 */
public class Round {

    private int roundId;
    private Timestamp guessTime;
    private int guessNum;
    private int partialMatch;
    private int exactMatch;
    private int gameId;

    /**
     * @return the roundId
     */
    public int getRoundId() {
        return roundId;
    }

    /**
     * @param roundId the roundId to set
     */
    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    /**
     * @return the guessTime
     */
    public Timestamp getGuessTime() {
//         LocalDateTime now = LocalDateTime.now();
//        Timestamp guessTime = Timestamp.valueOf(now);

       
//        LocalDateTime guessTime = timestamp.toLocalDateTime();
        return guessTime;
    }

    /**
     * @param guessTime the guessTime to set
     */
    public void setGuessTime(Timestamp guessTime) {
        this.guessTime = guessTime;
    }

    /**
     * @return the guessNum
     */
    public int getGuessNum() {
        return guessNum;
    }

    /**
     * @param guessNum the guessNum to set
     */
    public void setGuessNum(int guessNum) {
        this.guessNum = guessNum;
    }

    /**
     * @return the partialMatch
     */
    public int getPartialMatch() {
        return partialMatch;
    }

    /**
     * @param partialMatch the partialMatch to set
     */
    public void setPartialMatch(int partialMatch) {
        this.partialMatch = partialMatch;
    }

    /**
     * @return the exactMatch
     */
    public int getExactMatch() {
        return exactMatch;
    }

    /**
     * @param exactMatch the exactMatch to set
     */
    public void setExactMatch(int exactMatch) {
        this.exactMatch = exactMatch;
    }

    /**
     * @return the gameId
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * @param gameId the gameId to set
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
