package com.tesla.reposervicesecurity.repo;

import com.tesla.model.Message;
import com.tesla.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    List<Message> findMessagesByUserId(User id);
}

