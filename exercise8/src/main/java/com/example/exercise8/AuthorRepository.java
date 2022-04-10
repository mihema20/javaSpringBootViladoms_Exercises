package com.example.exercise8;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AuthorRepository extends MongoRepository<Author, String> {

	@Query("{name: ?0 }")
	List<Author> findItemByName(String surname);

	@Query("{name: {$gt : ?0} , country: ?1 }")
	List<Author> findItemByNameAndCountry(String name, String country);

}
