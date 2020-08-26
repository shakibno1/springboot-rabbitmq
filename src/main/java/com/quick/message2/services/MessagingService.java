package com.quick.message2.services;

import com.quick.message2.Model.Message;

import java.util.List;

public interface MessagingService {

    List<Message> listAll();

    Message getById(Long id);

    Message saveOrUpdate(Message message);

    void delete(Long id);

    String sendMessage(String message);
}
