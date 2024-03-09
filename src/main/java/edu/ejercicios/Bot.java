package edu.ejercicios;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

public class Bot extends TelegramLongPollingBot {


    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return "1245223526:AAExfrYCoP1TbqxvSKI6A8FITRlVMGsnf5w";
    }

    @Override
    public void onUpdateReceived(Update update) {
        var usuario = update.getMessage().getFrom();
        var mensaje = update.getMessage().getText();
        var id = usuario.getId();

        mensajes.add(usuario+ ":" +mensaje);

        System.out.println("id: " + usuario.getId() + "nombre:" + usuario.getFirstName() + " "
                + usuario.getLastName() + "escribio" + mensaje);

        if (mensaje.contains("/desplegar")&& mensajes.size() >0) {
            String lista = "";
            for (String mensaje1 : mensajes){
                lista += mensaje1 + "\n";
            }
        }

        sendText(id, mensaje);
    }

        public void sendText(Long who, String what){
            SendMessage sm = SendMessage.builder()
                    .chatId(who.toString()) //Who are we sending a message to
                    .text(what).build();    //Message content
            try {
                execute(sm);                        //Actually sending the message
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);      //Any error will be printed here
            }
        }

    }

