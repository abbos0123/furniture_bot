package uz.project.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.project.models.Language;
import uz.project.services.ProductService;
import uz.project.services.UserService;
import uz.project.utilds.BotService;


@Service
public class FurnitureBot extends TelegramLongPollingBot {

    private Language language = Language.UZBEK;
    private Long currentChatId = -1L;

    @Autowired
    public UserService userService;
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

        if (update.hasMessage()) {
            Message message = update.getMessage();
            this.currentChatId = message.getChatId();

            if (message.hasText()) {
                String text = message.getText();

                if (text.equals("/start")) {
                    startBot(message);
                    System.out.println();
                }

                if (text.equals("Uzbek") || text.equals("Russian") || text.equals("English") || text.equals("Krill")) {
                    BotService.setLanguage(this, text);
                }

                if (text.equals("user")){
                    var user = userService.getUserById(6);
                    sendMessage(message, user.toString(), false);
                }
            }

        }
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Long getCurrentChatId() {
        return currentChatId;
    }

    public void setCurrentChatId(Long currentChatId) {
        this.currentChatId = currentChatId;
    }

    private void startBot(Message message) {
        sendMessage(message,
                " Uzbek    ->   Iltimos tilni tanlan " +
                        "\n??????????????  ->  ????????????????????, ???????????????? ???????? " +
                        "\nEnglish ->  Please choose Language " +
                        "\n??????????    ->  ?????????????? ?????????? ??????????????)", true);
    }

    private void sendMessage(Message message, String text, Boolean openKey) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setChatId(message.getChatId().toString());

        if (openKey)
        BotService.setLanguageKeyboardButton(sendMessage);

        try {
            execute(sendMessage);
            System.out.println(message.getChatId().toString());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

}
