package tn.esprit.ds.championat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import tn.esprit.ds.championat.services.ChampionatService;

@SpringBootApplication
@EnableScheduling
public class ChampionatApplication {

    public static void main(String[] args) {

        SpringApplication.run(ChampionatApplication.class, args);
    }
    @Bean
    CommandLineRunner testAOP(ChampionatService championatService) {
        return args -> {
            System.out.println("\n=== TEST AOP : appel de planifierMatch ===\n");
            championatService.planifierMatch("Ferrari", "Red Bull");
        };
    }
}