package com.example.exercise6;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;



@Service
public class AuthorService {
static List <Author> authors = new ArrayList<Author>();

	static {
		//int id, String name, String country, int dob, int qtyBooks, Boolean alive
		Author author1 = new Author (1, "Paco", "Canada", 1980, 745891, true);
		Author author2 = new Author (2, "Alfred", "Portugal", 1800, 745891, false);
		authors.add(author1);
		authors.add(author2);
	}
	
	public List<Author> queryAuthors(){
		
		return authors;
	}

	public void addAuthorToArray(Author author) {
		authors.add(author);
	}

	public int findAuthorById(int id) {
		
		int indexAuthor = -1;
		
		for(Author authorTemporal : authors) {
			
			if (authorTemporal.getId() == id) {
				
				indexAuthor = authors.indexOf(authorTemporal);
			}
		}
		return indexAuthor;
	}

	public void deleteAuthorFromArray(int id) {
		
		int index = findAuthorById(id);
		authors.remove(index);
		
	}

	public Author getAuthorByIndex(int indexAuthor) {
		
		return authors.get(indexAuthor);
	}

	public void replaceAuthor(int indexAuthor, Author auhtorToUpdate) {
		
		authors.set(indexAuthor, auhtorToUpdate);
	}
}
