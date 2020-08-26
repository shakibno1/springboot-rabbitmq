package com.quick.message2.listener;

import com.quick.message2.Model.Message;
import com.quick.message2.Model.TransactionType;
import com.quick.message2.SpringBootRabbitMQApplication;
import com.quick.message2.services.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class MessageListener {

    private static final Logger log = LogManager.getLogger(MessageListener.class);

    @Autowired
    private MessagingService messagingService;


    public void receiveMessage(String message) {
        log.info("Received <" + message + ">");
        SpringBootRabbitMQApplication.message = SpringBootRabbitMQApplication.message + " Received message: "+message;

        Message newMessage = new Message();
        newMessage.setMessageHost(SpringBootRabbitMQApplication.messageHost);
        newMessage.setMessage(message);
        newMessage.setTransactionType(TransactionType.RECEIVED_MESSAGE);

        messagingService.saveOrUpdate(newMessage);

    }
}
