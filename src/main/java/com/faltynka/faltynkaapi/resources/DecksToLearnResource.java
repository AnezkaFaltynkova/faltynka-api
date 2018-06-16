package com.faltynka.faltynkaapi.resources;

import com.faltynka.faltynkaapi.model.Deck;
import com.faltynka.faltynkaapi.model.DecksToLearn;
import com.faltynka.faltynkaapi.model.User;
import com.faltynka.faltynkaapi.services.DeckService;
import com.faltynka.faltynkaapi.services.DecksToLearnService;
import com.faltynka.faltynkaapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/secure")
public class DecksToLearnResource {

    @Autowired
    private UserService userService;

    @Autowired
    private DecksToLearnService decksToLearnService;

    @RequestMapping(value = "/decks-to-learn", method = RequestMethod.GET)
    public DecksToLearn createDeck(final HttpServletRequest request) {
        User user = userService.findByEmail((String) request.getAttribute("email"));
        return decksToLearnService.getDecksToLearn(user.getId());
    }
}
