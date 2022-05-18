package uz.project.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Service
public class FurnitureBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String resText = (messageText != null && messageText.equals("/start")) ? "Hello " : "My response";

           try {
               sendNotification(chatId, resText);
           }catch (TelegramApiException exception){
               exception.printStackTrace();
           }

        }
    }

    private void sendNotification(Long chatId, String resText) throws TelegramApiException {
        var resMessage = new SendMessage(chatId.toString(), resText);
        resMessage.enableMarkdownV2(true);
        execute(resMessage);

    }
}
