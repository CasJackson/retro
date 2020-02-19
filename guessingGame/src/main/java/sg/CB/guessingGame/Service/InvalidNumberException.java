/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.CB.guessingGame.Service;

/**
 *
 * @author cas
 */
public class InvalidNumberException extends Exception {

    public InvalidNumberException(String message) {
          super(message);
    }
   public InvalidNumberException (String message, Throwable inner) {
        super(message, inner);
    
    }
    
}
