package com.example.exercise6;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class AuthorRestController {
	@Autowired
	AuthorService authorservice;

	@GetMapping("/authors")
	public ResponseEntity<List<Author>> getAllAuthor() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("ResponseGet", "getAllAuthor executed");
		headers.add("version", "1.0 Api Rest Author Object");

		return ResponseEntity.accepted().headers(headers).body(authorservice.queryAuthors());
	}

	@PostMapping(path = "/addauthor", consumes = "application/json")
	public ResponseEntity<Author> createAuthor(@Valid @RequestBody Author author) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("ResponseCreate", "createAuthor executed");
		headers.add("version", "1.0 Api Rest Author Object");
		headers.add("Executed Output", "author created");
		
		authorservice.addAuthorToArray(author);

		return ResponseEntity.accepted().headers(headers).body(author);
	}
	
	@PostMapping("/updateauthor/{id}")
	public ResponseEntity<String> updateAuthor (@PathVariable int id, @RequestBody Author AuthorFromRest ) {
		
		String responseUpdate = ""; 
        int indexAuthor = authorservice.findAuthorById(id);
        boolean valueChange = false;
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("ResponseUpdate", "updateAuthor executed");
		headers.add("version", "1.0 Api Rest Author Object");
		
		
		if ( indexAuthor == -1 ){
			
			responseUpdate = responseUpdate + "Author not found";
			
		} else {
			Author authorToUpdate = authorservice.getAuthorByIndex(indexAuthor);
			
			responseUpdate += "Author found"+"\n";
			
			if  (AuthorFromRest.getId() != authorToUpdate.getId()) {
				
				responseUpdate += " - id value updated: " + AuthorFromRest.getId() +  "( old value: " + authorToUpdate.getId() + ")"+"\n" ;
				authorToUpdate.setId( AuthorFromRest.getId() );
				valueChange= true;
			}
			
			if  (!AuthorFromRest.getName().equals(authorToUpdate.getName())) {
				
				responseUpdate += " - name value updated: " + AuthorFromRest.getName() +  "( old value: " + authorToUpdate.getName() + ")"+"\n" ;
				authorToUpdate.setName( AuthorFromRest.getName());
				valueChange= true;
			}
			
			if  (!AuthorFromRest.getCountry().equals(authorToUpdate.getCountry())) {
				
				responseUpdate += " - country value updated: " + AuthorFromRest.getCountry() +  "( old value: " + authorToUpdate.getCountry() + ")"+"\n" ;
				authorToUpdate.setCountry( AuthorFromRest.getCountry());
				valueChange= true;
			}
			
			if  (AuthorFromRest.getDob() != authorToUpdate.getDob()) {
				
				responseUpdate += " - dob value updated: " + AuthorFromRest.getDob() +  "( old value: " + authorToUpdate.getDob() + ")"+"\n" ;
				authorToUpdate.setDob( AuthorFromRest.getDob());
				valueChange= true;
			}
			
			if  (AuthorFromRest.getQtyBooks() != authorToUpdate.getQtyBooks()) { 
				
				responseUpdate += " - QtyBooks value updated: " + AuthorFromRest.getQtyBooks() +  "( old value: " + authorToUpdate.getQtyBooks() + ")"+"\n" ;
				authorToUpdate.setQtyBooks( AuthorFromRest.getQtyBooks() );
				valueChange= true;
			}
			
			if  (AuthorFromRest.getAlive() != authorToUpdate.getAlive()) { 
				
				responseUpdate += " - Alive value updated: " + AuthorFromRest.getAlive() +  "( old value: " + authorToUpdate.getAlive() + ")"+"\n" ;
				authorToUpdate.setAlive( AuthorFromRest.getAlive() );
				valueChange= true;
			}
			
			if (valueChange) {
				
				authorservice.replaceAuthor (indexAuthor, authorToUpdate);
				
			} else responseUpdate += "There are no fields to update"+"\n";
		}
		
		headers.add("Executed Output", responseUpdate);
		
		return ResponseEntity.accepted().headers(headers).body(responseUpdate);

	}
	
	@DeleteMapping ("/deleteauthor/{id}")
	public ResponseEntity<String> deleteAuthor  (@PathVariable int id ) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("ResponseDelete", "deleteAuthor executed");
		headers.add("version", "1.0 Api Rest Author Object");
		
		
		String responsedelete = ""; 
		if ( authorservice.findAuthorById (id) != -1 ) {
			
			authorservice.deleteAuthorFromArray(id);
			responsedelete = responsedelete + "author: " + id  + " - deleted #succes";
			
			}
		else responsedelete = responsedelete + "author: " + id  + " - not deleted author not found #fail";
		headers.add("Executed Output", responsedelete);
		return ResponseEntity.accepted().headers(headers).body(responsedelete);
	}
		
	
}