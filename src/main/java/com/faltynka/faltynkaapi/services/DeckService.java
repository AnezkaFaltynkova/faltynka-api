package com.faltynka.faltynkaapi.services;

import com.faltynka.faltynkaapi.model.Deck;
import com.faltynka.faltynkaapi.model.User;
import com.faltynka.faltynkaapi.repositories.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckService {

    @Autowired
    private DeckRepository deckRepository;

    public List<Deck> findByUserId(String userId) {
        return deckRepository.findByUserId(userId);
    }

    public Deck findById(String id) { return deckRepository.findById(id); }

    public Deck save(Deck deck) {
        return deckRepository.save(deck);
    }

    public void delete(String deckId) {
        deckRepository.delete(deckId);
    }
}
