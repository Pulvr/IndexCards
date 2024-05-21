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

        List<Users> myUsers = userRepository.findAll();
        List<Deck> usersDecks = deckRepository.findDecksByUserName(myUsers.getFirst().getUserName());
        model.addAttribute("myUsers", myUsers);
        model.addAttribute("usersDecks", usersDecks);

        return "myUsers";
    }

}
