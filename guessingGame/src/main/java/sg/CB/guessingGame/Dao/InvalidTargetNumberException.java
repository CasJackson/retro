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
public class InvalidTargetNumberException extends Exception {

    public InvalidTargetNumberException (String message) {
          super(message);
    }
   public InvalidTargetNumberException (String message, Throwable inner) {
        super(message, inner);
    
    }
}
