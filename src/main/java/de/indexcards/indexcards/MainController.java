package de.indexcards.indexcards;

import de.indexcards.indexcards.classes.Users;
import de.indexcards.indexcards.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/collections")
    public String collections() {return "collections";}

    @GetMapping("/howto")
    public String howto() {return "howto";}

    @GetMapping("/learning")
    public String learning() {return "learning";}
}
