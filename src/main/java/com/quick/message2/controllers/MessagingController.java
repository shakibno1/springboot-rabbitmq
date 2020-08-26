package com.quick.message2.controllers;

import com.quick.message2.Model.Message;
import com.quick.message2.Model.TransactionType;
import com.quick.message2.SpringBootRabbitMQApplication;
import com.quick.message2.services.MessagingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class MessagingController {

    private static final Logger log = LogManager.getLogger(MessagingController.class);

    @Autowired
    private MessagingService messageService;

    @RequestMapping("/sendMessage")
    public String indexProduct(@RequestParam(value="message", defaultValue="hi") String message){
        if(SpringBootRabbitMQApplication.messageSent){
            SpringBootRabbitMQApplication.messageSent = false;
            SpringBootRabbitMQApplication.message = SpringBootRabbitMQApplication.message +" Sent message: "+message;
            messageService.sendMessage(message);

            Message newMessage = new Message();
            newMessage.setMessage(message);
            newMessage.setMessageHost(SpringBootRabbitMQApplication.messageHost);
            newMessage.setTransactionType(TransactionType.SENT_MESSAGE);

            messageService.saveOrUpdate(newMessage);

            return SpringBootRabbitMQApplication.message;
        }
        else{
            SpringBootRabbitMQApplication.messageSent = true;
            return SpringBootRabbitMQApplication.message;

        }

    }
}
