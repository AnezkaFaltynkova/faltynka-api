package com.faltynka.faltynkaapi.repositories;


import com.faltynka.faltynkaapi.model.Deck;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableScan
@Repository
public interface DeckRepository extends CrudRepository<Deck, String> {

    List<Deck> findByUserId(String userId);

    Deck findById(String id);
}
