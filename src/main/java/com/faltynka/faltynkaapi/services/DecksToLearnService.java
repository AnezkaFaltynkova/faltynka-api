package com.faltynka.faltynkaapi.services;

import com.faltynka.faltynkaapi.model.Deck;
import com.faltynka.faltynkaapi.model.DecksToLearn;
import com.faltynka.faltynkaapi.model.VocabulariesToLearn;
import com.faltynka.faltynkaapi.repositories.DeckRepository;
import com.faltynka.faltynkaapi.repositories.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DecksToLearnService {

    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private QueryRepository queryRepository;

    public DecksToLearn getDecksToLearn(String userId) {
        List<Deck> decksForUser = deckRepository.findByUserId(userId);
        DecksToLearn decksToLearn = new DecksToLearn();
        for(Deck deck : decksForUser) {
            VocabulariesToLearn vocabulariesToLearn = new VocabulariesToLearn();
            vocabulariesToLearn.setDeckId(deck.getId());
            vocabulariesToLearn.setDeckName(deck.getName());

            vocabulariesToLearn.setNewVocabulariesToLearn(queryRepository.findNewVocabulariesToLearn(deck.getId(), deck.getLimitNewPerDay()));
            vocabulariesToLearn.setOldVocabulariesToLearn(queryRepository.findOldVocabulariesToLearn(deck.getId()));
            decksToLearn.getDecksWithVocabulariesToLearn().add(vocabulariesToLearn);
        }
        return decksToLearn;
    }
}
