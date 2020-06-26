package space.yjeong.yourday.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @GetMapping("/user-info")
    public String userInfo(Model model) {
        return "user-info";
    }

    @GetMapping("/diary-list")
    public String diaries(Model model) {
        return "diary-list";
    }

    @GetMapping("/diary-write")
    public String diaryWrite(Model model) {
        return "diary-write";
    }

    @GetMapping("/todo-list")
    public String todos(Model model) {
        return "todo-list";
    }

    @GetMapping("/todo-write")
    public String todoWrite(Model model) {
        return "todo-write";
    }
}