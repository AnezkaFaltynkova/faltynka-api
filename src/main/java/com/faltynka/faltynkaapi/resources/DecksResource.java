package com.faltynka.faltynkaapi.resources;

import com.faltynka.faltynkaapi.model.Deck;
import com.faltynka.faltynkaapi.model.User;
import com.faltynka.faltynkaapi.services.DeckService;
import com.faltynka.faltynkaapi.services.UserService;
import com.faltynka.faltynkaapi.services.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/secure")
public class DecksResource {

    @Autowired
    private UserService userService;

    @Autowired
    private DeckService deckService;

    @Autowired
    private VocabularyService vocabularyService;

    @RequestMapping("/decks")
    public String loginSuccess(final HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        return "These are the decks for" + email;
    }

    @RequestMapping(value = "/decks", method = RequestMethod.POST)
    public Object createDeck(@RequestBody Deck deck, final HttpServletRequest request) {
        User user = userService.findByEmail((String) request.getAttribute("email"));
        deck.setUserId(user.getId());
        deckService.save(deck);
        return null;
    }

    @RequestMapping(value = "/decks/{id}", method = RequestMethod.DELETE)
    public Object deleteDeck(@PathVariable("id") String id) {
        vocabularyService.deleteAllVocabularyWithDeckId(id);
        deckService.delete(id);
        return null;
    }

    @RequestMapping(value = "/decks/{id}", method = RequestMethod.PUT)
    public Object updateDeck(@PathVariable("id") String id, @RequestParam("limit") Long limitNewPerDay) {
        Deck deck = deckService.findById(id);
        deck.setLimitNewPerDay(limitNewPerDay);
        deckService.save(deck);
        return null;
    }
}
