package de.indexcards.indexcards.repository;

import de.indexcards.indexcards.classes.Card;
import org.springframework.data.repository.ListCrudRepository;

public interface CardRepository extends ListCrudRepository<Card,Long> {

}
