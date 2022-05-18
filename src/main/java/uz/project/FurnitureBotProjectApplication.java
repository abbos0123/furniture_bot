package uz.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@SpringBootApplication
public class FurnitureBotProjectApplication {

    public static void main(String[] args) throws TelegramApiException {

        SpringApplication.run(FurnitureBotProjectApplication.class, args);
    }

}
