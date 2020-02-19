/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.CB.guessingGame.Dao;

/**
 *
 * @author cas
 */
public class InvalidGameStatusException extends Exception {

    public InvalidGameStatusException (String message) {
          super(message);
    }
   public InvalidGameStatusException (String message, Throwable inner) {
        super(message, inner);
    
    }
}
