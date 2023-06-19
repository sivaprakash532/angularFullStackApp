package com.lms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "book_title")
	private String bookTitle;

	@Column(name = "auth_name")
	private String authName;
	
	@Column(name = "isbn")
	private String isbn;
	
	public Book() {
		
	}
	
	public Book(String bookTitle, String authName, String isbn) {
		super();
		this.bookTitle = bookTitle;
		this.authName = authName;
		this.isbn = isbn;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
