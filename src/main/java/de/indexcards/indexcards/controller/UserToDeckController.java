package de.indexcards.indexcards.controller;

import de.indexcards.indexcards.classes.Card;
import de.indexcards.indexcards.classes.Deck;
import de.indexcards.indexcards.classes.Users;
import de.indexcards.indexcards.repository.CardRepository;
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
    private final CardRepository cardRepository;

    public UserToDeckController(UserRepository userRepository,
                                DeckRepository deckRepository,
                                CardRepository cardRepository) {
        this.userRepository = userRepository;
        this.deckRepository = deckRepository;
        this.cardRepository = cardRepository;
    }

    @GetMapping("/myUsers")
    String serveTemplate(Model model) {

        List<Users> myUsers = userRepository.findAll();
        List<Deck> allDecks = deckRepository.findAll();
        List<Card> allCards = cardRepository.findAll();
        List<Deck> decksFirstUser = deckRepository.findDecksByUserId(myUsers.getFirst().getId());

        List<Card> cardsOfUser = cardRepository.findAllCardsByUserId(myUsers.getFirst().getId());

        model.addAttribute("myUsers", myUsers);
        model.addAttribute("allDecks", allDecks);
        model.addAttribute("firstUserID", decksFirstUser);
        model.addAttribute("allCards", allCards);
        model.addAttribute("cardsOfUser", cardsOfUser);
        return "myUsers";
    }

}
