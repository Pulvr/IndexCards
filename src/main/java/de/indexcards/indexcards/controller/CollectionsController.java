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

//@Component
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
    boolean addExecuted = false;

    @GetMapping("/collections")
    public String getIndex(Model model) {
        setUserAndDeck();
        model.addAttribute("myUser", myUser);
        model.addAttribute("myDecks", myDecks);
        return "collections";
    }

    @PostMapping("/addDeck")
    public String addDeck(@RequestParam("newDeck") String newDeck, Model model) {

        if (!addExecuted) {
            deckRepository.addDeck(newDeck, myUser.getId());
            System.out.println("Deck wurde erstellt");
            myDecks = deckRepository.findDecksByUserId(myUser.getId());
            addExecuted = true;
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
            model.addAttribute("emptyDeck", "There was no deck curently selected, please pick one from your collection");
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
            model.addAttribute("cardsEmpty", "No cards in deck, add cards in \"edit Deck\" first");
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
    public String editDeck(@RequestParam(name="cardId", required=false, defaultValue = "999") long cardId, @RequestParam(name="deckIdEdit", required=false, defaultValue = "999") int deckId, Model model) {
        setUserAndDeck();
        userRepository.updateCurrDeck(myUser.getId(), deckId);
        changeCurrentDeck(deckId);
        setCardsOfUser(model);
        cardRepository.deleteCard(cardId);
        model.addAttribute("deckIdEdit", deckId);
        model.addAttribute("chosenDeck", myCurrentDeck);
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
        if (addExecuted){
            addExecuted =false;
        }
    }

    @GetMapping("/create")
    public String createNewCard(Model model) {
        setUserAndDeck();
        if(!addExecuted){
            model.addAttribute("chosenDeck", myCurrentDeck);
            addExecuted =true;
        }
        return "create";
    }

    @PostMapping("/create")
    public String createACard(@RequestParam("front") String front, @RequestParam("back") String back) {
        if(!addExecuted){
            cardRepository.addCard(myCurrentDeck.getId(), front, back);
            addExecuted =true;
        }
        return "creationSuccessful";
    }

    @GetMapping("/creationSuccessful")
    public String returnSuccess(){
        return "creationSuccessful";
    }

    private void changeCurrentDeck(int deckId){
        for (Deck deckCounter : myDecks) {
            if (deckCounter.getId() == deckId) {
                myCurrentDeck = deckCounter;
            }
        }
    }

//    @PostMapping("/creationSuccessful")
//    public String addNewCard(@RequestParam("front") String front, @RequestParam("back") String back) {
//        setUserAndDeck();
//        cardRepository.addCard(myCurrentDeck.getId(), front, back);
//        return "creationSuccessful";
//    }

//    @PostMapping("/creationSuccessful")
//    public String placeholderSuccess(){
//        return "creationSuccessful";
//    }


//    @EventListener(CardRepository.class)
//    void refreshEditor(){
//        //hier soll der Editor refresht werden
//        System.out.println("refresh");
//    }
}
