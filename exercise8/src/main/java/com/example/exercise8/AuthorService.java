package com.example.exercise8;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthorService {
	
	@Autowired
	AuthorRepository authorRepository;

	// crud basic operations
	public Iterable<Author> findAll() {
		return authorRepository.findAll();
	}

	public Author save(Author author) {
		
		authorRepository.save(author);

		return author;
	}

	public String findAndDeleteById(String id) {

		String response = "";
		Optional<Author> authorFound = authorRepository.findById(id);

		if (authorFound.isPresent()) {

			authorRepository.delete(authorFound.get());
			response += "author deleted";
		} else {

			response += "author not found";
		}

		return response;
	}

	public void deleteById(String id) {

		// String response = "";

		authorRepository.deleteById(id);

	}

	public String update(String id, Author author) {

		String response = "";
		Boolean changedValues = false;
		Optional<Author> authorFound = authorRepository.findById(id);

		if (authorFound.isPresent()) {

			if ((author.getName() != null) && (author.getName() != null)) {
				authorFound.get().setName(author.getName());
				changedValues = true;
			}

			if ((author.getCountry() != null) && (author.getCountry() != "")) {
				authorFound.get().setCountry(author.getCountry());
				changedValues = true;
			}

			if ((authorFound.get().getDob() != author.getDob()) && author.getDob() != 0) {
				authorFound.get().setDob(author.getDob());
				changedValues = true;
			}

			if ((authorFound.get().getQtyBooks() != author.getQtyBooks()) && (author.getQtyBooks() != 0)) {
				authorFound.get().setQtyBooks(author.getQtyBooks());
				changedValues = true;
			}

			if ((authorFound.get().getAlive() != author.getAlive()) && (author.getAlive() != null)) {
				authorFound.get().setAlive(author.getAlive());
				changedValues = true;
			}

			if (changedValues == true) {
				authorRepository.save(authorFound.get());
			} else
				response += "There are no fields to update";

		} else
			response += "Author not found";

		return response;

	}

	// other options
	public long count() {

		long quantity = authorRepository.count();

		return quantity;
	}

	public boolean existsById(String id) {

		boolean isAuthor = authorRepository.existsById(id);

		return isAuthor;
	}
	public List<Author>findItemByName(String name){
		
		return authorRepository.findItemByName(name);
	}
	
	public List<Author> findItemByNameAndCountry(String name, String country){
		
		return authorRepository.findItemByNameAndCountry(name, country);
	}
}
