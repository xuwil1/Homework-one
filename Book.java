/**
 * The Book class implements the book object
 * 
 * 
 * @author Willie Xu
 *	email: xuwillie1@gmail.com
 *	Class CSE 214-R10
 */

import java.util.*;
import java.io.*;

public class Book {
	private String title;
	private String author;
	private String borrower;
	private int condition;
//Invariants:
//title is the name of the book object
//author is the name of the author of the book object
//borrower is the name of the person borrowing the book object
//condition is the condition of the book object 

/**
 * 
 * @param title 
 * 		title of book object
 * @param author
 * 		who wrote the book object
 * @param condition
 * 		current quality of book object
 */		
	
	public Book(String title, String author, int condition) {
		this.title = title;
		this.author = author;
		this.condition = condition;
		this.borrower = "<none>";
	}
/**
 * 
 * @param title
 * 		sets the title of the book object
 */
	public void setTitle(String title) {
		this.title = title;
	}
/**
 * 
 * @param author
 * 		sets the author of the book object
 */
	public void setAuthor(String author) {
		this.author = author;
	}
/**
 * 
 * @param borrower
 * 		sets the borrower of the book object
 */
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
/**
 * 
 * @param condition
 * 		sets the condition of the book object
 */
	public void setCondition(int condition) {
		this.condition = condition;
	}
/**
 * 
 * @return
 * 		gives back the title of the book object
 */
	public String getTitle() {
		return this.title;
	}
/**
 * 
 * @return
 * 		gives back the author of the book object
 */
	public String getAuthor() {
		return this.author;
	}
/**
 * 
 * @return
 * 		gives back the borrower of the book object
 */
	public String getBorrower() {
		return this.borrower;
	}
/**
 * 
 * @return
 * 		gives back the condition of the book object
 */
	public int getCondition() {
		return this.condition;
	}
/**
 * creates a clone of the book object with the same parameters
 */
	public Book clone() {
		Book book1 = new Book(title, author, condition);
		return book1;
	}
/**
 * checks to see if two book objects have the same parameters
 */
	public boolean equals(Object obj) {
		if (obj instanceof Book) {
			Book tempBook = (Book) obj;
			if (title == tempBook.getTitle() && author == tempBook.getAuthor()
					&& condition == tempBook.getCondition()) {
				return obj == this;
			}
		}
		return false;
	}
/**
 * returns the parameters of a book
 */
	public String toString() {
		return "Book title: " + title + "\tAuthor of book: " + author + "\tName of borrower: " + borrower
				+ "\tCondition of book(1-5):" + condition;
	}
}
