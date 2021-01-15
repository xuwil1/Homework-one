/**
 * The RipoffRental class instantiates three bookshelf objects for user to manipulate
 * also allows users to manipulate different options according to the homework 
 * 
 * @author Willie Xu
 *	email: xuwillie1@gmail.com
 *	Class CSE 214-R10
 */
import java.util.*;
import java.io.*;
public class RipoffRental {
	public static void main(String[] args) {
		Bookshelf shelfA = new Bookshelf();	//Instantiates a Bookshelf object
		Bookshelf shelfB = new Bookshelf();	//Instantiates a Bookshelf object
		Bookshelf shelfC = new Bookshelf();	//Instantiates a Bookshelf object
		Bookshelf bkshelf = shelfA;			//creates a holder bookshelf equal to ShelfA
		Scanner sc = new Scanner(System.in);//creates Scanner sc 
		boolean quit = false;				//create a boolean quit with value false
		System.out.println("Welcome to Jack's Textbook Rentals!\n");
		System.out.println("Menu:\n \tA) Add Book\n" + "\tS) Swap Books\n \tL) Loan Book\n"
				+ "\tR) Remove Book\n \tD) Duplicate Book\n"
				+ "\tC) Change Shelf\n \tO) Overwrite shelf with clone of current shelf\n"
				+ "\tE) Check if two shelves are equal\n \tP) Print current bookshelf\n" + "\tQ) Quit");
	/**
	 * a loop is created to allow user to cycle through program 
	 * as desired until user decides to quit
	 */
		while (quit == false) {
			System.out.print("Please choose an option: ");
			char ch = sc.nextLine().charAt(0);
			if (ch == 'A' || ch == 'a') {
				System.out.print("Please enter a title: ");
				String title = sc.nextLine();//allow user to input desired title of book object
				System.out.print("Please enter an author: ");
				String author = sc.nextLine();//allow user to input desired author of book object
				System.out.print("Please enter condition (1-5): ");
				int condition = sc.nextInt();//allow user to input condition of book object
				sc.nextLine();
				System.out.print("Please enter position on shelf(0-19): ");
				int position = sc.nextInt();//allow user to input position of book object
				sc.nextLine();
				Book book = new Book(title, author, condition);// create new book with parameters
				bkshelf.addBook(position, book);			   // specified by user
			} else if (ch == 'S' || ch == 's') {
				try {
					System.out.print("Please enter an index: ");
					int index1 = sc.nextInt();//allow user to input first index
					sc.nextLine();
					System.out.print("Please enter another index: ");
					int index2 = sc.nextInt();//allow user to input second index
					sc.nextLine();
					bkshelf.swapBooks(index1, index2);//swaps positions of the two indices that was input
				} catch (ArrayIndexOutOfBoundsException e) {//catches ArrayIndexOutOfBoundsException
					System.out.println("Index invalid!");
				}
			} else if (ch == 'L' || ch == 'l') {
				try {
					System.out.println("Please enter an index: ");
					int index1 = sc.nextInt();
					sc.nextLine();
					System.out.println("Please enter a recipient: ");
					String recipient = sc.nextLine();
					System.out.println("Please enter condition (1-5): ");
					int condition = sc.nextInt();
					sc.nextLine();
					if (bkshelf.getBook(index1).getBorrower().equals("<none>")) {
						bkshelf.getBook(index1).setBorrower(recipient);
						bkshelf.getBook(index1).setCondition(condition);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Index invalid!");
				}
			} else if (ch == 'R' || ch == 'r') {
				try {
					System.out.println("Please enter an index: ");
					int index1 = sc.nextInt();
					sc.nextLine();
					bkshelf.removeBook(index1);
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Index invalid!");
				} catch (EmptyShelfException e) {

				}
			} else if (ch == 'D' || ch == 'd') {
				try {
					System.out.println("Please enter a source index: ");
					int srcindex = sc.nextInt();
					sc.nextLine();
					System.out.println("Please enter a destination index: ");
					int destindex = sc.nextInt();
					sc.nextLine();
					Book b = bkshelf.getBook(srcindex).clone();
					bkshelf.addBook(destindex, b);
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Index invalid!");
				}
			} else if (ch == 'C' || ch == 'c') {
				System.out.print("Please select shelf to look at: ");
				char shelf = sc.nextLine().charAt(0);
				if (shelf == 'A' || shelf == 'a') {
					bkshelf = shelfA;
					System.out.println("Shelf A Selected.");
				} else if (shelf == 'B' || shelf == 'b') {
					bkshelf = shelfB;
					System.out.println("Shelf B Selected.");
				} else if (shelf == 'C' || shelf == 'c') {
					bkshelf = shelfC;
					System.out.println("Shelf C Selected.");
				} else {
					System.out.println("Shelf invalid!");
				}
			} else if (ch == 'O' || ch == 'o') {
				System.out.print("Please select shelf to overwrite: ");
				char shelf = sc.nextLine().charAt(0);
				if (shelf == 'A' || shelf == 'a') {
					shelfA = bkshelf.clone();
					System.out.println("Shelf A is overwritten.");
				} else if (shelf == 'B' || shelf == 'b') {
					shelfB = bkshelf.clone();
					System.out.println("Shelf B is overwritten.");
				} else {
					shelfC = bkshelf.clone();
					System.out.println("Shelf C is overwritten.");
				}

			} else if (ch == 'E' || ch == 'e') {
				Bookshelf temp1;
				Bookshelf temp2;
				System.out.print("Please select a shelf: ");
				char bkshelf1 = sc.nextLine().charAt(0);
				if (bkshelf1 == 'A' || bkshelf1 == 'a') {
					temp1 = shelfA;
					System.out.println("Shelf A Selected.");
				} else if (bkshelf1 == 'B' || bkshelf1 == 'b') {
					temp1 = shelfB;
					System.out.println("Shelf B Selected.");
				} else {
					temp1 = shelfC;
					System.out.println("Shelf C Selected.");
				}
				System.out.print("Please select another shelf: ");
				char bkshelf2 = sc.nextLine().charAt(0);
				if (bkshelf2 == 'A' || bkshelf2 == 'a') {
					temp2 = shelfA;
					System.out.println("Shelf A Selected.");
				} else if (bkshelf2 == 'B' || bkshelf2 == 'b') {
					temp2 = shelfB;
					System.out.println("Shelf B Selected.");
				} else {
					temp2 = shelfC;
					System.out.println("Shelf C Selected.");
				}
				if (temp1.equals(temp2)) {
					System.out.println("Bookshelf is equal");
				} else {
					System.out.println("Bookshelf is not equal");
				}
			} else if (ch == 'P' || ch == 'p') {
				for (int i = 0; i < bkshelf.numBooks(); i++) {
					String spot="spot";
					String Title="Title";
					String Author="Author";
					String Cond="Cond.";
					String Borrower="Borrower";
					System.out.printf("%5s %20s %20s %5s %10s\n",spot,Title,Author,Cond,Borrower);
					System.out.printf("---------------------------------------------------------------------\n");
					System.out.printf("%5d. %20s %20s %5d %10s\n", bkshelf.numBooks(),bkshelf.getBook(i).getTitle(),
							bkshelf.getBook(i).getAuthor(), bkshelf.getBook(i).getCondition(),
							bkshelf.getBook(i).getBorrower());
				}
			} else if (ch == 'Q' || ch == 'q') {
				System.out.println("Goodbye!");
				quit = true;
			} else {
				System.out.println("Input invalid. Please select a valid option!");
			}

		}
	}
}
