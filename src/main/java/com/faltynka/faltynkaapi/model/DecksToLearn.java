package com.faltynka.faltynkaapi.model;

import java.util.ArrayList;
import java.util.List;

public class DecksToLearn {

    private List<VocabulariesToLearn> decksWithVocabulariesToLearn = new ArrayList<>();

    public List<VocabulariesToLearn> getDecksWithVocabulariesToLearn() {
        return decksWithVocabulariesToLearn;
    }

    public void setDecksWithVocabulariesToLearn(List<VocabulariesToLearn> decksWithVocabulariesToLearn) {
        this.decksWithVocabulariesToLearn = decksWithVocabulariesToLearn;
    }
}
