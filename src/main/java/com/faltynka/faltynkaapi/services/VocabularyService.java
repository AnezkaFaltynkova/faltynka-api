package com.faltynka.faltynkaapi.services;

import com.faltynka.faltynkaapi.model.Deck;
import com.faltynka.faltynkaapi.model.Vocabulary;
import com.faltynka.faltynkaapi.repositories.DeckRepository;
import com.faltynka.faltynkaapi.repositories.QueryRepository;
import com.faltynka.faltynkaapi.repositories.VocabularyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class VocabularyService {

    @Autowired
    private VocabularyRepository vocabularyRepository;

    @Autowired
    private QueryRepository queryRepository;

    public List<Vocabulary> findByDeckId(String deckId) {
        return vocabularyRepository.findByDeckId(deckId);
    }

    public Vocabulary save(Vocabulary vocabulary) {
        return vocabularyRepository.save(vocabulary);
    }

    public Vocabulary updateNewVocabulary(Vocabulary vocabulary) {
        vocabulary.setNewVocabulary(false);
        vocabulary.setNextLearnedAt(countNextInterval(vocabulary.getCurrentInterval()));
        return queryRepository.updateVocabulary(vocabulary);
    }

    public Vocabulary updateOldVocabulary(Vocabulary vocabulary) {
        vocabulary.setNextLearnedAt(countNextInterval(vocabulary.getCurrentInterval()));
        return queryRepository.updateVocabulary(vocabulary);
    }

    public void deleteAllVocabularyWithDeckId(String deckId) {
        List<Vocabulary> vocabulariesToDelete = findByDeckId(deckId);
        for(Vocabulary vocabulary : vocabulariesToDelete) {
            vocabularyRepository.delete(vocabulary.getId());
        }
    }

    private String countNextInterval(Long currentInterval){
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime nextLearnedAtDate = today.plusDays(currentInterval);
        return DateTimeFormatter.ISO_DATE_TIME.format(nextLearnedAtDate);
    }
}
