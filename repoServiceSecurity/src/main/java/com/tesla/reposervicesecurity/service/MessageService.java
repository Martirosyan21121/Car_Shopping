package com.tesla.reposervicesecurity.service;

import com.tesla.model.Message;
import com.tesla.model.User;
import com.tesla.reposervicesecurity.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    public MessageRepo messageRepo;

    public MessageService() {
    }

    public void saveMessage(Message message) {
        messageRepo.save(message);
    }

    public List<Message> findByUserId(User id) {
        return messageRepo.findMessagesByUserId(id);
    }

    public void deleteMessage(Long id) {
      messageRepo.deleteById(id);
    }
}
