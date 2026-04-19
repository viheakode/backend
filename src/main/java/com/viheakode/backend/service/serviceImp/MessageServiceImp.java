package com.viheakode.backend.service.serviceImp;

import com.viheakode.backend.model.Message;
import com.viheakode.backend.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MessageServiceImp {

    private final MessageRepository messageRepository;

    public MessageServiceImp(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message sendMessage(Message message){
        Message message1 = new Message();
        message1.setUuid(UUID.randomUUID().toString());
        message1.setName(message.getName());
        message1.setEmail(message.getEmail());
        message1.setMessage(message.getMessage());
        message1.setAppName(message.getAppName());
        message1.setCreatedAt(LocalDateTime.now());
        return messageRepository.save(message1);
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }
}
