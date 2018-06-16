package com.faltynka.faltynkaapi.model;

import java.util.ArrayList;
import java.util.List;

public class VocabulariesToLearn {

    private String deckId;
    private String deckName;
    private List<Vocabulary> newVocabulariesToLearn = new ArrayList<>();
    private List<Vocabulary> oldVocabulariesToLearn = new ArrayList<>();

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public List<Vocabulary> getNewVocabulariesToLearn() {
        return newVocabulariesToLearn;
    }

    public void setNewVocabulariesToLearn(List<Vocabulary> newVocabulariesToLearn) {
        this.newVocabulariesToLearn = newVocabulariesToLearn;
    }

    public List<Vocabulary> getOldVocabulariesToLearn() {
        return oldVocabulariesToLearn;
    }

    public void setOldVocabulariesToLearn(List<Vocabulary> oldVocabulariesToLearn) {
        this.oldVocabulariesToLearn = oldVocabulariesToLearn;
    }
}
