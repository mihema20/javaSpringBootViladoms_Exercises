package com.example.exercise6;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

	@Autowired
	AuthorRepository authorRepository;

	public Iterable<Author> queryAuthorsFromH2() {

		return authorRepository.findAll();
	}

	public Author addAuthorToH2(Author author) {

		authorRepository.save(author);

		return author;
	}

	public Optional<Author> findAuthorById(int id) {

		return authorRepository.findById(id);
	}
	
	public String update(int id, Author author) {

		String response = "";
		Boolean changedValues = false;
		Optional<Author> authorFound = authorRepository.findById(id);

		if (authorFound.isPresent()) {

			if (author.getName() != null) {
				authorFound.get().setName(author.getName());
				changedValues = true;
			}

			if (author.getCountry() != null) {
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

	public String findAndDeleteById(int id) {

		String response = "";
		Optional<Author> authorFound = authorRepository.findById(id);

		if (authorFound.isPresent()) {

			authorRepository.delete(authorFound.get());
			response += "Author deleted";

		} else
			response += "Author not found";

		return response;
	}

	public void deleteAuthorFromH2(int id) {
		
		authorRepository.deleteById(id);
	}

}
