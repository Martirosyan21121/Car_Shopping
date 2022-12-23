package com.tesla.generalmodule.controller;

import com.tesla.model.User;
import com.tesla.model.UserType;
import com.tesla.reposervicesecurity.repo.UserRepo;
import com.tesla.reposervicesecurity.service.EmailService;
import com.tesla.reposervicesecurity.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;


@Controller
//@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    public UserRepo userRepo;
    @Autowired
    private EmailService emailService;

    @GetMapping({"/register"})
    public String register() {
        return "register";
    }

    @GetMapping({"/login"})
    public String login() {
        return "login";
    }

    @GetMapping({"/forgotPassword"})
    public String forgotPassword() {
        return "forgotPassword/forgotPassword";
    }

    @PostMapping({"/save"})
    public String userSave(@ModelAttribute User user, ModelMap model, Locale locale) {
        Optional<User> byEmail = userService.findByEmail(user.getEmail());
        if (byEmail.isPresent()) {
//            log.warn("USER email already exist = {}", (byEmail.get()).getEmail());
            String msg = "Your email by < " + (byEmail.get()).getEmail() + " > already exists. Please Login !";
            model.addAttribute("message", msg);
            return "register";
        } else {
            user.setUserType(UserType.USER);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            LocalDate localDate = LocalDate.now();
            user.setLocalDate(LocalDate.from(localDate));
            String link = "https://www.facebook.com/";
            try {
                emailService.sendHtmlEmail(user.getEmail(), "Welcome", user, link, "email/emailRegister.html", locale);
            } catch (javax.mail.MessagingException e) {
                throw new RuntimeException(e);
            }
            userService.saveUser(user);
//            log.info("USER save = user.id = {}", user.getId());
//            log.info("  user.name = {}", user.getName());
//            log.info("  user.surname = {}", user.getSurname());
//            log.info("  user.email = {}", user.getEmail());
//            log.info("  user.localDate = {}", user.getLocalDate());
            return "redirect:/";
        }
    }

    @GetMapping({"/account"})
    public String account(Principal principal, ModelMap modelMap) {
        String email = principal.getName();
        Optional<User> user = userService.findByEmail(email);
        List<User> userList = new ArrayList<>();
        user.ifPresent(userList::add);
        for (User u : userList) {
//            log.info("USER account data - user.name = {}", u.getName());
//            log.info(" user.surname = {}", u.getSurname());
//            log.info(" user.email = {}", u.getEmail());
//            log.info(" user.localDate = {}", u.getLocalDate());
        }
        modelMap.addAttribute("user", userList);
        return "myAccount/myAccount";
    }

    @PostMapping({"/forgotPassword"})
    public String forgotPasswordPost(@ModelAttribute User user, ModelMap modelMap, Random random) {
//        log.debug("USER email = {}", user.getEmail());
        Optional<User> optionalUser = userService.findByEmail(user.getEmail());
        if (optionalUser.isEmpty()) {
//            log.warn("Email does not exist");
            String msg = "Your email by < " + user.getEmail() + " > does not exist";
            modelMap.addAttribute("msg", msg);
            return "forgotPassword/forgotPassword";
        } else {
//            log.info("Email exist");
            User user1 = optionalUser.get();
            int verificationCode = random.nextInt(100000, 999999);
            user1.setToken(verificationCode);
//            log.info("USER token = {}", verificationCode);
            userRepo.save(user1);
            emailService.send(user1.getEmail(), "Email verification", "Dear " + user1.getName() + " " + user1.getSurname() + "  \n This is Your verification code \n " + verificationCode);
            return "forgotPassword/verification";
        }
    }

    @PostMapping({"/resetPassword"})
    private String resetPassword(@RequestParam("token") Integer token, ModelMap modelMap) {
        Optional<User> optionalUser = userService.findByToken(token);
        if (optionalUser.isEmpty()) {
//            log.info("USER token does not right = {}", optionalUser);
            String msg = "Your verify code does not right";
            modelMap.addAttribute("message", msg);
            return "forgotPassword/verification";
        } else {
//            log.info("USER token is right = {}", optionalUser);
            modelMap.addAttribute("email", (optionalUser.get()).getEmail());
            return "forgotPassword/changePassword";
        }
    }

    @PostMapping({"/changePassword"})
    private String changePassword(@RequestParam("email") String email, @RequestParam("password") String password, Locale locale) throws MessagingException {
        Optional<User> byEmail = userService.findByEmail(email);
//        log.info("USER email for change password = {}", email);
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            user.setToken(null);
            user.setPassword(passwordEncoder.encode(password));
            userRepo.save(user);
//            log.info("USER save user.id = {}", user.getId());
//            log.info("USER save user.email = {}", user.getEmail());
            String link = "https://www.facebook.com/";
            try {
                emailService.sendHtmlEmail(user.getEmail(), "Welcome", user, link, "email/changePassword.html", locale);
            } catch (javax.mail.MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        return "redirect:/";
    }
}