package de.indexcards.indexcards.controller;

import de.indexcards.indexcards.classes.Users;
import de.indexcards.indexcards.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignupController {

    private final UserRepository userRepository;

    public SignupController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/dashboard")
    public String register(Model model, @RequestParam("username") String username,
                           @RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("passwordRep") String passwordRep) {

        //additional security needed, so that you can't create endless users.
        //also have to check that the passwords are matching before creating.
        Users user = new Users(username, firstName, lastName, email, password);
        userRepository.save(user);

        return "dashboard";
    }
}
