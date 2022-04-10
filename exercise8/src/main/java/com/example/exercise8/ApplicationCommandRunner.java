package com.example.exercise8;

import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;

@Component
@EnableMongoRepositories
public class ApplicationCommandRunner implements CommandLineRunner {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	AuthorService authorService;

	@Override
	public void run(String... args) throws Exception {

		logger.info("Welcome to the runner from commandLineRunner to test faker JAVA");
		
		//createAuthors();
		
		
		  List<Author> authors;
		  System.out.println(authorService.findAll() + "\n");
		  
		  authors = authorService.findItemByName("Micheal");
		  System.out.println ("Author name Micheal: " + authors.size());
		  System.out.println("Authors: \n" + authors + "\n" );
		  

		  authors = authorService.findItemByNameAndCountry("Michael", "Cameroon");
		  
		  System.out.println ("employees name Michael and country Cameroon: " + authors.size());
		  System.out.println("Auhtors: \n" + authors + "\n");
		  
		  //update
		  authorService.update("624c7413f1813e086ebc1012", new Author("Ranni","France",0,0,true));
		
		}
		
	public void createAuthors() {
		Faker faker = new Faker();
		for (int i = 1; i <= 10; i++) {
			
			authorService.save(new Author (faker.name().firstName(),
					                                faker.country().name(),
					                                faker.number().numberBetween(1867, 2002),
					                                faker.number().numberBetween(1000, 3000),
					                                faker.random().nextBoolean()));
		}
	}
}
