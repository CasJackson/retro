/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.CB.guessingGame.Dto;

/**
 *
 * @author cas
 */
public class Game {

    private int gameId;
    private boolean gameDone;
    private int targetNum;

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

    /**
     * @return the targetNum
     */
    public int getTargetNum() {
        return targetNum;
    }

    /**
     * @param targetNum the targetNum to set
     */
    public void setTargetNum(int targetNum) {
        this.targetNum = targetNum;
    }

    /**
     * @return the gameDone
     */
    public boolean isGameDone() {
        return gameDone;
    }

    /**
     * @param gameDone the gameDone to set
     */
    public void setGameDone(boolean gameDone) {
        this.gameDone = gameDone;
    }

}
