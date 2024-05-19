package de.indexcards.indexcards.controller;

import de.indexcards.indexcards.classes.Deck;
import de.indexcards.indexcards.classes.Users;
import de.indexcards.indexcards.repository.DeckRepository;
import de.indexcards.indexcards.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserToDeckController {
    private final UserRepository userRepository;
    private final DeckRepository deckRepository;

    public UserToDeckController(UserRepository userRepository, DeckRepository deckRepository) {
        this.userRepository = userRepository;
        this.deckRepository = deckRepository;
    }

    @GetMapping("/myUsers")
    String serveTemplate(Model model) {
        //var newUser = new Users("Pietro","lobo","bestman","mymail@mail.de","123");
        //userRepository.save(newUser);

        List<Users> myUsers = userRepository.findAll();
        List<Deck> myDecks = deckRepository.findAll();
        model.addAttribute("myUsers", myUsers);
        model.addAttribute("myDecks", myDecks);

        return "myUsers";
    }

}
