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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.ListIterator;

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
    Card myCurrentCard;
    List<Card> cardsOfUser;
    ListIterator<Card> usersCardsIterator;
    boolean addDeckExecuted = false;

    @GetMapping("/collections")
    public String getIndex(Model model) {
        setUserAndDeck();
        model.addAttribute("myUser", myUser);
        model.addAttribute("myDecks", myDecks);
        return "collections";
    }

    @PostMapping("/addDeck")
    public String addDeck(@RequestParam("newDeck") String newDeck, Model model) {

        if (!addDeckExecuted) {
            deckRepository.addDeck(newDeck, myUser.getId());
            System.out.println("Deck wurde erstellt");
            myDecks = deckRepository.findDecksByUserId(myUser.getId());
            addDeckExecuted = true;
        }

        model.addAttribute("myUser", myUser);
        model.addAttribute("myDecks", myDecks);
        return "addDeckSuccess";
    }

    @PostMapping("/learning")
    public String postActivateDeck(@RequestParam("deckId") int deckId, Model model) {
        setUserAndDeck();

        userRepository.updateCurrDeck(myUser.getId(), deckId);

        changeCurrentDeck(deckId);
        setCardsOfUser(model);
        return "learning";
    }

    @GetMapping("/learning")
    public String getActivateDeck(Model model) {
       setUserAndDeck();
        //Wenn Current ID = 0 ist (default wert) dann hat der User kein Deck ausgewählt, also kein Deck ausgeben.
        if (deckRepository.findCurrentDeckId(myUser.getId()) == 0) {
            model.addAttribute("emptyDeck", "Kein Deck ausgewählt");
        }else{
            // Finde aktuelles Deck anhand der gegebenen Deck ID
            changeCurrentDeck(deckRepository.findCurrentDeckId(myUser.getId()));

            setCardsOfUser(model);

        }
            return "learning";
    }

    private void setCardsOfUser(Model model) {
        cardsOfUser = cardRepository.findAllCardsByUserAndDeckId(myUser.getId(),myCurrentDeck.getId());
        if (!cardsOfUser.isEmpty()){
            myCurrentCard = cardsOfUser.getFirst();
            usersCardsIterator = cardsOfUser.listIterator();
            model.addAttribute("chosenDeck", myCurrentDeck);
            model.addAttribute("cardsOfUser", myCurrentCard);
        }else{
            model.addAttribute("chosenDeck", myCurrentDeck);
            model.addAttribute("cardsEmpty", "no cards in deck, add cards under \" edit Deck\" first");
        }
    }

    @PostMapping("/continue")
    public String nextCard( Model model) {

        if (usersCardsIterator.hasNext()) {
            myCurrentCard = usersCardsIterator.next();
        }

        model.addAttribute("chosenDeck", myCurrentDeck);
        model.addAttribute("cardsOfUser",myCurrentCard);

        return "learning";
    }

    @PostMapping("/back")
    public String prevCard( Model model) {

        if (usersCardsIterator.hasPrevious()) {
            myCurrentCard = usersCardsIterator.previous();
        }

        model.addAttribute("chosenDeck", myCurrentDeck);
        model.addAttribute("cardsOfUser",myCurrentCard);

        return "learning";
    }

    @PostMapping("/editor")
    public String editDeck(@RequestParam("deckIdEdit") int deckId, Model model) {
        userRepository.updateCurrDeck(myUser.getId(), deckId);

        changeCurrentDeck(deckId);

        setCardsOfUser(model);
        model.addAttribute("cardsOfUser", cardsOfUser);
        model.addAttribute("front", "placeholderFront");
        model.addAttribute("back", "placeholderBack");
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
        // wenn ein Deck geaddet wurde, ist der Flag immer true. Diese Hilfsfunktion wird bei jedem anderen Mapping
        // ausgeführt, sollte also dazu führen, dass das adden erneut möglich wird,
        // sobald man die "addDeck" Funktion verlässt
        if (addDeckExecuted){
            addDeckExecuted =false;
        }
    }

    private void changeCurrentDeck(int deckId){
        for (Deck deckCounter : myDecks) {
            if (deckCounter.getId() == deckId) {
                myCurrentDeck = deckCounter;
            }
        }
    }


}
