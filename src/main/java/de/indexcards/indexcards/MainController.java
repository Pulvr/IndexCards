package de.indexcards.indexcards;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/realdashboard")
    public String dashboard(Model model) {return "dashboard";}

    @GetMapping("/howto")
    public String howto() {return "howto";}

    @GetMapping("/learning")
    public String learning() {return "learning";}

    @GetMapping("/imprintandprivacy")
    public String imprintandprivacy() {return "imprintAndPrivacy";}
}
