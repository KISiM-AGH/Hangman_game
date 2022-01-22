package com.inzynieria.wisielec.repository;

import com.inzynieria.wisielec.entity.Phrase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhraseRepository extends CrudRepository<Phrase, Integer> {

    @Query("select p from Phrase p where p.phrase = :phrase")
    public Phrase findByPhrase(@Param("phrase") String phrase);
}
