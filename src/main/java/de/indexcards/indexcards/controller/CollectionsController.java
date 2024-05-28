package de.indexcards.indexcards.controller;

import de.indexcards.indexcards.classes.Deck;
import de.indexcards.indexcards.classes.Users;
import de.indexcards.indexcards.repository.DeckRepository;
import de.indexcards.indexcards.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class CollectionsController {

    private final UserRepository userRepository;
    private final DeckRepository deckRepository;

    public CollectionsController(UserRepository userRepository, DeckRepository deckRepository) {
        this.userRepository = userRepository;
        this.deckRepository = deckRepository;
    }

    @GetMapping("/collections")
    public String index(Model model) {

        //Nur einen User zurückgeben, aktuell immer den mit ID 1 als Default Test User
        Users myUser = userRepository.findByUserId(1);
        List<Deck> myDecks = deckRepository.findDecksByUserId(myUser.getId());
        model.addAttribute("myUser", myUser);
        model.addAttribute("myDecks", myDecks);

        return "collections";
    }

}
