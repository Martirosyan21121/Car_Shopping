package com.tesla.generalmodule.controller;

import com.tesla.model.Message;
import com.tesla.model.User;
import com.tesla.reposervicesecurity.repo.MessageRepo;
import com.tesla.reposervicesecurity.repo.UserRepo;
import com.tesla.reposervicesecurity.service.MessageService;
import com.tesla.reposervicesecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class MessageController {
    public static LocalTime localTime = LocalTime.now();
    @Autowired
    public MessageRepo messageRepo;
    @Autowired
    public MessageService messageService;
    @Autowired
    public UserRepo userRepo;
    @Autowired
    public UserService userService;


    @GetMapping({"/message"})
    public String message(ModelMap modelMap, Principal principal) {
        String email = principal.getName();
        Optional<User> user = userService.findByEmail(email);
        List<User> userList = new ArrayList<>();
        user.ifPresent(userList::add);
        for (User user1 : userList) {
            List<Message> message = messageService.findByUserId(user1);
            modelMap.addAttribute("msg", message);
        }
        return "message/message";
    }

    @PostMapping({"/send"})
    public String sendMessage(@ModelAttribute Message message, Principal principal, ModelMap modelMap) {
        String email = principal.getName();
        String messageEmail = message.getEmail();
        if (!email.equals(messageEmail)) {
            String msg = "Wrong Email !!!";
            modelMap.addAttribute("message", msg);
            return "index/contact";
        } else {
            Optional<User> user = userService.findByEmail(email);
            List<User> userList = new ArrayList<>();
            user.ifPresent(userList::add);
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
            message.setLocalTime(LocalTime.parse(localTime.format(myFormatObj)));
            messageService.saveMessage(message);
            return "redirect:/message";
        }
    }

    @GetMapping({"/message/delete"})
    public String deleteMessage(@RequestParam("id") Long id) {
        this.messageService.deleteMessage(id);
        return "redirect:/message";
    }
}
