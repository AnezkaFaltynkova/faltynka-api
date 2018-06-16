package com.faltynka.faltynkaapi.repositories;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.faltynka.faltynkaapi.model.VocabulariesToLearn;
import com.faltynka.faltynkaapi.model.Vocabulary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QueryRepository {

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    public List<Vocabulary> findNewVocabulariesToLearn(String deckId, Long limitNewPerDay) {
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(deckId));
        eav.put(":val2", new AttributeValue().withN("1"));

        DynamoDBQueryExpression<Vocabulary> queryExpression = new DynamoDBQueryExpression<Vocabulary>()
                .withIndexName("deckId-index")
                .withKeyConditionExpression("deckId = :val1")
                .withFilterExpression("newVocabulary = :val2")
                .withExpressionAttributeValues(eav)
                .withConsistentRead(false);

        List<Vocabulary> allNewVocabulariesForDeck = mapper.query(Vocabulary.class, queryExpression);

        List<Vocabulary> limitedNumberOfNewVocabularies = new ArrayList<>();

        for (int i = 0; i < allNewVocabulariesForDeck.size() && i < limitNewPerDay; i++) {
            limitedNumberOfNewVocabularies.add(allNewVocabulariesForDeck.get(i));
        }

        return limitedNumberOfNewVocabularies;
    }

    public List<Vocabulary> findOldVocabulariesToLearn(String deckId) {
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);

        LocalDateTime todayMidnight = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        String todayMidnightStr = DateTimeFormatter.ISO_DATE_TIME.format(todayMidnight);

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(deckId));
        eav.put(":val2", new AttributeValue().withN("0"));
        eav.put(":val3", new AttributeValue().withS(todayMidnightStr));

        DynamoDBQueryExpression<Vocabulary> queryExpression = new DynamoDBQueryExpression<Vocabulary>()
                .withIndexName("deckId-index")
                .withKeyConditionExpression("deckId = :val1")
                .withFilterExpression("newVocabulary = :val2 AND nextLearnedAt < :val3")
                .withExpressionAttributeValues(eav)
                .withConsistentRead(false);

        return mapper.query(Vocabulary.class, queryExpression);
    }

    public Vocabulary updateVocabulary(Vocabulary vocabulary) {
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);

        Vocabulary vocabularyRetrieved = mapper.load(Vocabulary.class, vocabulary.getId());
        vocabularyRetrieved.setNewVocabulary(vocabulary.isNewVocabulary());
        vocabularyRetrieved.setFront(vocabulary.getFront());
        vocabularyRetrieved.setBack(vocabulary.getBack());
        vocabularyRetrieved.setCurrentInterval(vocabulary.getCurrentInterval());
        vocabularyRetrieved.setNextLearnedAt(vocabulary.getNextLearnedAt());
        mapper.save(vocabularyRetrieved);

        DynamoDBMapperConfig config = new DynamoDBMapperConfig(DynamoDBMapperConfig.ConsistentReads.CONSISTENT);
        return mapper.load(Vocabulary.class, vocabulary.getId(), config);
    }
}
