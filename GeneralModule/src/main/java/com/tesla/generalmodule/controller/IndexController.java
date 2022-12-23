package com.tesla.generalmodule.controller;

import com.tesla.reposervicesecurity.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    public UserRepo userRepo;

    @GetMapping({"/"})
    public String index() {
        return "index/index";
    }

    @GetMapping({"/about"})
    public String about() {
        return "index/about";
    }

    @GetMapping({"/contact"})
    public String contact() {
        return "index/contact";
    }

    @GetMapping({"/teslaMS"})
    public String teslaMS() {
        return "about/teslaMS";
    }

    @GetMapping({"/teslaMY"})
    public String teslaMY() {
        return "about/teslaMY";
    }

    @GetMapping({"/teslaMX"})
    public String teslaMX() {
        return "about/teslaMX";
    }

    @GetMapping({"/teslaM3"})
    public String teslaM3() {
        return "about/teslaM3";
    }

    @GetMapping({"/teslaHistory"})
    public String teslaHistory() {
        return "about/teslaHistory";
    }

    @GetMapping({"/teslaAutopilot"})
    public String teslaAutopilot() {
        return "about/teslaAutopilot";
    }

    @GetMapping({"/exe"})
    public String exe() {
        return "exe";
    }
}
