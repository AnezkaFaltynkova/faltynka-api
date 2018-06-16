package com.faltynka.faltynkaapi.resources;

import com.faltynka.faltynkaapi.model.Vocabulary;
import com.faltynka.faltynkaapi.services.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/secure")
public class VocabularyResource {

    @Autowired
    private VocabularyService vocabularyService;

    @RequestMapping(value = "/vocabulary", method = RequestMethod.POST)
    public Vocabulary createVocabulary(@RequestBody Vocabulary vocabulary) {
        vocabulary.setNewVocabulary(true);
        return vocabularyService.save(vocabulary);
    }

    @RequestMapping(value = "/vocabulary/new", method = RequestMethod.PUT)
    public Vocabulary updateNewVocabulary(@RequestBody Vocabulary vocabulary) {
        return vocabularyService.updateNewVocabulary(vocabulary);
    }

    @RequestMapping(value = "/vocabulary/old", method = RequestMethod.PUT)
    public Vocabulary updateOldVocabulary(@RequestBody Vocabulary vocabulary) {
        return vocabularyService.updateOldVocabulary(vocabulary);
    }
}
