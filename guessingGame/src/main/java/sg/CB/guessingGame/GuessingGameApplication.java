package sg.CB.guessingGame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sg.CB.guessingGame.Dao.Dao;

@SpringBootApplication
public class GuessingGameApplication {

    @Autowired
    Dao dao;
    
	public static void main(String[] args) {
		SpringApplication.run(GuessingGameApplication.class, args);
	}

}
