/**
 * The Bookshelf class implements an object that holds a maximum of 20 book objects 
 * 
 * 
 * @author Willie Xu
 *	email: xuwillie1@gmail.com
 *	Class CSE 214-R10
 */

import java.util.*;
import java.io.*;

public class Bookshelf {
	private Book[] books;	//An array holding books
	private int count;
	final int CAPACITY = 20;// the max amount of books a bookshelf object can hold
//Invariants:
//count records the number of books in a bookshelf

	public Bookshelf() {
		this.books = new Book[20];
		this.count = 0;
	}
/**
 * 
 * @return
 * 	gives back the recorded number of books in a bookshelf
 */
	public int numBooks() {
		return count;
	}
/**
 * 
 * @param index
 * 		index is the desired position 
 * @return
 * 		gives the parameters of the book of the desired position
 * @throws ArrayIndexOutOfBoundsException
 * 		Indicates that the desired index is invalid
 */
	public Book getBook(int index) throws ArrayIndexOutOfBoundsException {
		if(index<0||index>20) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return books[index];
	}
/**
 * 
 * @param index
 * 		index is the desired position 
 * @return
 * 		returns book of desired position if index of book is null
 * @throws EmptyShelfException
 * 		Indicates that there are no books in the Bookshelf object
 * @throws ArrayIndexOutOfBoundsException
 * 		Indicates that the desired index is invalid
 */
	public Book removeBook(int index) throws EmptyShelfException, ArrayIndexOutOfBoundsException {
		if(books[index]==null) {
			throw new EmptyShelfException();
		}
		Book temp = books[index];
		if (books[index] != null) {
			for (int i = index; i < count; i++) {
				if (books[i + 1] != null) {
					books[i] = books[i + 1];
				}
			}
		}
		count--;
		return temp;
	}
/**
 * 
 * @param index
 * 		desired position user wants to add book object to
 * @param book
 * 		book object user wants to add
 */
	public void addBook(int index, Book book) {
		if (count != CAPACITY) {
			for (int i = CAPACITY; i < index; i--) {
				if (books[i - 1] != null) {
					books[i] = books[i - 1];
				}
			}
			books[index] = book;
			count++;
		}

	}
/**
 * 
 * @param index1
 * 		one of two indices that user needs to swap location 
 * @param index2
 * 		second index user needs to swap with index1
 */
	public void swapBooks(int index1, int index2) {
		Book temp = books[index1];
		books[index1] = books[index2];
		books[index2] = temp;
	}
/**
 * clones Bookshelf object 
 */
	public Bookshelf clone() {
		Bookshelf bookshelf1 = new Bookshelf();
		for (int i = 0; i < count; i++) {
			bookshelf1.addBook(i, books[i].clone());
			}
		return bookshelf1;
	}
/**
 * checks to see that two bookshelf objects are equal
 */
	public boolean equals(Object obj) {
		if (obj instanceof Bookshelf) {
			Bookshelf tempBookshelf = (Bookshelf) obj;
			for (int i = 0; i < count; i++) {
				if (!books[i].equals(tempBookshelf.getBook(i))) {
					return false;
				}
			}
		}
		return true;
	}
/**
 * returns all book objects in a bookshelf
 */
	public String toString() {
		String s = "";
		for (int i = 0; i < count; i++) {
			s +=books[i].toString() + "\n";
		}
		return s;
	}
}
