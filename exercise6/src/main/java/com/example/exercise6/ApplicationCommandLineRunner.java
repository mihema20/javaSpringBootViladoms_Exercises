package com.example.exercise6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;

@Component
public class ApplicationCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	AuthorService authorService;
	
	@Override
	public void run(String... args) throws Exception {

		//create authors
		createAuthors();
		System.out.println(authorService.queryAuthorsFromH2());
		
		//update author
		Author newAuthorfields  = new Author();
		newAuthorfields.setName("Ranni");
		newAuthorfields.setCountry("Francia");
		newAuthorfields.setDob(1996);
		System.out.println(authorService.update(1,newAuthorfields));
		System.out.println(authorService.queryAuthorsFromH2());
		
		//delete author
		System.out.println(authorService.findAndDeleteById(6));
		System.out.println(authorService.queryAuthorsFromH2());
	}
	
	public void createAuthors() {
		Faker faker = new Faker();
		for (int i = 1; i <= 10; i++) {
			
			authorService.addAuthorToH2(new Author (faker.name().firstName(),
					                                faker.country().name(),
					                                faker.number().numberBetween(1867, 2002),
					                                faker.number().numberBetween(1000, 3000),
					                                faker.random().nextBoolean()));
		}
	}

}
