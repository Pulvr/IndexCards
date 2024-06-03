package de.indexcards.indexcards.controller;

import de.indexcards.indexcards.classes.Deck;
import de.indexcards.indexcards.classes.Users;
import de.indexcards.indexcards.repository.CardRepository;
import de.indexcards.indexcards.repository.DeckRepository;
import de.indexcards.indexcards.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class CollectionsController {

    private final UserRepository userRepository;
    private final DeckRepository deckRepository;
    private final CardRepository cardRepository;

    public CollectionsController(UserRepository userRepository, DeckRepository deckRepository, CardRepository cardRepository) {
        this.userRepository = userRepository;
        this.deckRepository = deckRepository;
        this.cardRepository = cardRepository;
    }

    Users myUser;
    List<Deck> myDecks;
    Deck myCurrentDeck;

    @GetMapping("/collections")
    public String index(Model model) {
        setUserAndDeck();
        model.addAttribute("myUser", myUser);
        model.addAttribute("myDecks", myDecks);
        return "collections";
    }

    @PostMapping("/learning")
    public String activateDeck(@RequestParam("deckId") int deckId, Model model) {
        setUserAndDeck();

        userRepository.updateCurrDeck(myUser.getId(), deckId);
        myCurrentDeck = myDecks.get(deckId-1); //-1 wegen off by one in der Liste.

        model.addAttribute("chosenDeck", myCurrentDeck);

        return "learning";
    }

    @GetMapping("/learning")
    public String learning(Model model) {
       setUserAndDeck();
        //funktioniert einigermaßen, nur muss ich noch prüfen, wie man in mustache sagt
        //bitte nur ausgeben, wenn container nicht leer.
        if (deckRepository.findCurrentDeckId(myUser.getId())==0) {
            model.addAttribute("chosenDeck", "Kein Deck ausgewählt");
            return "learning";
        }else {
            myCurrentDeck = myDecks.get(deckRepository.findCurrentDeckId(myUser.getId()) - 1);
            model.addAttribute("chosenDeck", myCurrentDeck);
            return "learning";
        }
        //return "learning";
    }

    @PostMapping("/editor")
    public String editDeck(@RequestParam("deckIdEdit") int deckId, Model model) {
        userRepository.updateCurrDeck(myUser.getId(), deckId);
        return "editor";
    }

    @GetMapping("/editor")
    public String editor() {
        return "editor";
    }

    public void setUserAndDeck(){
        //aktuell wird immer der User mit der ID 1 ausgegeben für Testzwecke
        myUser = userRepository.findByUserId(1);
        myDecks = deckRepository.findDecksByUserId(myUser.getId());
    }

}
