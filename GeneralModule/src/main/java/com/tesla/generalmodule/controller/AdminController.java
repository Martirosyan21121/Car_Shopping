package com.tesla.generalmodule.controller;

import com.tesla.model.Car;
import com.tesla.model.Message;
import com.tesla.model.User;
import com.tesla.reposervicesecurity.repo.CarRepo;
import com.tesla.reposervicesecurity.repo.MessageRepo;
import com.tesla.reposervicesecurity.repo.UserRepo;
import com.tesla.reposervicesecurity.service.CarService;
import com.tesla.reposervicesecurity.service.MessageService;
import com.tesla.reposervicesecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
//@Slf4j
public class AdminController {
    @Autowired
    public MessageRepo messageRepo;
    @Autowired
    public MessageService messageService;
    @Autowired
    public UserRepo userRepo;
    @Autowired
    public UserService userService;
    @Autowired
    public CarService carService;
    @Autowired
    public CarRepo carRepo;

    public AdminController() {
    }

    @GetMapping({"/adminMessageSender"})
    public String adminMessagesSender() {
        return "message/adminMessageSender";
    }

    @GetMapping({"/admin111"})
    public String admin(Model modelMap) {
        List<User> user = this.userRepo.findAll();
        modelMap.addAttribute("usersAdmin", user);
//        log.info("All user size = {}", user.size());
        modelMap.addAttribute("usersSize", user.size());
        return "myAccount/admin";
    }

    @GetMapping({"/allCars"})
    public String allCars(ModelMap modelMap) {
        List<Car> cars = this.carRepo.findAll();
        modelMap.addAttribute("cars", cars);
        return "index/allCars";
    }

    @GetMapping({"/user/delete"})
    public String deleteUser(@RequestParam("id") Long id) {
        this.userService.deleteUser(id);
//        log.info("deleted USER id = " + id);
        return "redirect:/admin111";
    }

//    @GetMapping({"/user/messages"})
//    public String userMessages(@RequestParam("id") Long id, ModelMap modelMap) {
//        System.out.println(id);
//        Optional<User> user = this.userRepo.findById(id);
//        List<User> userList = new ArrayList();
//        Objects.requireNonNull(userList);
//        user.ifPresent(userList::add);
//        Iterator var5 = userList.iterator();
//
//        while(var5.hasNext()) {
//            User user1 = (User)var5.next();
//            List<Message> messageList = this.messageService.findByUserId(user1);
//            modelMap.addAttribute("messages", messageList);
//        }
//
//        return "message/adminMessages";
//    }

    @GetMapping({"/addCars"})
    private String addCars() {
        return "myAccount/addCars";
    }

    @PostMapping("/addCars/add")
    private String addCarsPost(Car car, ModelMap modelMap) {
        String careCode = UUID.randomUUID().toString();
//        log.info("Car code is {}", careCode);
        car.setCarCode(careCode);
        Optional<User> user = this.userService.findByEmail("narek@nm");
        List<User> userList = new ArrayList<>();
        user.ifPresent(userList::add);
//        log.info("car data id {}", car.getId());
//        log.info("  car model {}", car.getModel());
//        log.info("  car date {}", car.getDateOfCreation());
//        log.info("  car color {}", car.getColor());
//        log.info("  car weight {}", car.getWeight());
//        log.info("  car autopilot {}", car.getAutopilot());
//        log.info("  car data Price {}", car.getPrice());
//        log.info("  car data PowerReserve {}", car.getPowerReserve());
//        log.info("  car data InventoryType {}", car.getInventoryType());
//        log.info("  car data MaxSpeed {}", car.getMaxSpeed());
//        log.info("  car data UpToSixty {}", car.getUpToSixty());
//        log.info("  car data User {}", car.getUserId());
        carService.saveCar(car);
//        log.info("car was saved {}", car);
        String msg = "The car is successfully added !!!";
        modelMap.addAttribute("message", msg);
        return "myAccount/addCars";
    }
}