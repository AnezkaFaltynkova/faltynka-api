package com.faltynka.faltynkaapi.repositories;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.faltynka.faltynkaapi.model.Vocabulary;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableScan
@Repository
public interface VocabularyRepository extends CrudRepository<Vocabulary, String> {

    List<Vocabulary> findByDeckId(String deckId);
}
