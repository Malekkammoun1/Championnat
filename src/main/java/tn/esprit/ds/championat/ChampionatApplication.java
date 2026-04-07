package tn.esprit.ds.championat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ChampionatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChampionatApplication.class, args);
    }
}