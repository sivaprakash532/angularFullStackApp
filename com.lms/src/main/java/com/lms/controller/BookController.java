package com.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.exception.ResourceNotFoundException;
import com.lms.model.Book;
import com.lms.repository.BooksRepo;




@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class BookController {

	@Autowired
	private BooksRepo booksRepository;
	
	// get all books
	@GetMapping("/books")
	public List<Book> getAllBooks(){
		return booksRepository.findAll();
	}		
	
	// create book rest api
	@PostMapping("/books")
	public Book createBook(@RequestBody Book book) {
		return booksRepository.save(book);
	}
	
	// get book by id rest api
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		Book book = booksRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not exist with id :" + id));
		return ResponseEntity.ok(book);
	}
	
	// update book rest api
	
	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails){
		Book book = booksRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not exist with id :" + id));
		
		book.setBookTitle(bookDetails.getBookTitle());
		book.setAuthName(bookDetails.getAuthName());
		book.setIsbn(bookDetails.getIsbn());
		
		Book updatedBook = booksRepository.save(book);
		return ResponseEntity.ok(updatedBook);
	}
	
	// delete book rest api
	@DeleteMapping("/books/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable Long id){
		Book book = booksRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not exist with id :" + id));
		
		booksRepository.delete(book);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}