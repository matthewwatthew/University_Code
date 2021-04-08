// Matthew Jordan (mrj3dd)
// Homework 3- Book, Person, Library
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class LibraryTest {
	@Test
	public void testCheckNumCopies() {
		Library library1 = new Library("String");
		Book b1 = new Book("Harry Potter and the Sorcerer's Stone", "Jk Rowling",123, 12.5);
		Book b2 = new Book("Harry Potter and the Sorcerer's Stone", "Jk Rowling", 5, 12.5);
		Book b3 = new Book("Name of the Wind", "Patrick Roffuss", 7, 12.5);
		Book b4 = new Book("The Sun Also Rises", "Ernest Hemmingway", 10, 12.5);
		Book b5 = new Book("The Sun Also Rises", "Ernest Hemmingway", 11, 12.5);
		Book b6 = new Book("Harry Potter and the Sorcerer's Stone", "Jk Rowling", 16, 12.5);
		ArrayList<Book> librayBooks = new ArrayList<Book>();
		librayBooks.add(b1);
		librayBooks.add(b2);
		librayBooks.add(b3);
		librayBooks.add(b4);
		librayBooks.add(b5);
		librayBooks.add(b6);
		library1.setLibraryBooks(librayBooks);
		
		assertTrue(library1.checkNumCopies("Harry Potter and the Sorcerer's Stone", "Jk Rowling") == 3);
	}
	@Test 
	public void testCheckNumCopiesFalse() {
		Library library1 = new Library("String");
		Book b1 = new Book("Harry Potter and the Sorcerer's Stone", "Jk Rowling",123, 12.5);
		Book b2 = new Book("Harry Potter and the Sorcerer's Stone", "Jk Rowling", 5, 12.5);
		Book b3 = new Book("Name of the Wind", "Patrick Roffuss", 7, 12.5);
		Book b4 = new Book("The Sun Also Rises", "Ernest Hemmingway", 10, 12.5);
		Book b5 = new Book("The Sun Also Rises", "Ernest Hemmingway", 11, 12.5);
		Book b6 = new Book("Harry Potter and the Sorcerer's Stone", "Jk Rowling", 16, 12.5);
		ArrayList<Book> librayBooks = new ArrayList<Book>();
		librayBooks.add(b1);
		librayBooks.add(b2);
		librayBooks.add(b3);
		librayBooks.add(b4);
		librayBooks.add(b5);
		librayBooks.add(b6);
		library1.setLibraryBooks(librayBooks);
		
		assertFalse(library1.checkNumCopies("The Sun Also Rises", "Ernest Hemmingway") == 1);
}
	
	@Test
	public void testCheckOutNotAPatron(){
		Library library1 = new Library("String");
		Book b1 = new Book("Harry Potter and the Sorcerer's Stone", "Jk Rowling",123, 12.5);
		Person p1 = new Person("Matthew Jordan","1105 Juniper Drive", 1234);
		Person p2 = new Person("Not A Patron Jones","1104 Knockturn Alley",1235);
		ArrayList<Book> librayBooks = new ArrayList<Book>();
		ArrayList<Person> patrons = new ArrayList<Person>(); 
		library1.setPatrons(patrons);
		library1.setLibraryBooks(librayBooks);
		librayBooks.add(b1);
		patrons.add(p1);
		assertTrue(library1.checkOut(p2, b1, "11 10 1940") == false);
		
	}
	@Test
	public void testCheckOutAlreadyCheckedOut() {
		Library library1 = new Library("String");
		Book b1 = new Book("Harry Potter and the Sorcerer's Stone", "Jk Rowling",123, 12.5);
		Book b3 = new Book("Name of the Wind", "Patrick Roffuss", 7, 12.5);
		Person p1 = new Person("Matthew Jordan","1105 Juniper Drive", 1234);
		Person p2 = new Person("Mattjew Jordan","1105 Juniper Drive", 1334);
		ArrayList<Book> librayBooks = new ArrayList<Book>();
		ArrayList<Person> patronz = new ArrayList<Person>(); 
		library1.setLibraryBooks(librayBooks);
		librayBooks.add(b1);
		librayBooks.add(b3);
		patronz.add(p1);
		patronz.add(p2);
		library1.setPatrons(patronz);
		assertTrue(library1.checkOut(p2,b3,"12 12 1234") == true);	
	}
	@Test public void BooksDueOnDateMultiple() {
		Library library1 = new Library("MFRL");
		Book b1 = new Book("Harry Potter and the Sorcerer's Stone", "Jk Rowling",123, 12.5);
		Book b3 = new Book("Name of the Wind", "Patrick Roffuss", 7, 12.5);
		Person p1 = new Person("Matthew Jordan","1105 Juniper Drive", 1234);
		Person p2 = new Person("Mattjew Jordan","1105 Juniper Drive", 1334);
		ArrayList<Book> librayBooks = new ArrayList<Book>();
		ArrayList<Person> patronz = new ArrayList<Person>(); 
		library1.setLibraryBooks(librayBooks);
		librayBooks.add(b1);
		librayBooks.add(b3);
		patronz.add(p1);
		patronz.add(p2);
		library1.setPatrons(patronz);
		library1.checkOut(p2,b3,"31 10 2017");
		library1.checkOut(p2, b1, "31 10 2017");
		ArrayList<Book> testCase = new ArrayList<Book>();
		testCase.add(b1);
		testCase.add(b3);
		assertTrue(library1.booksDueOnDate("31 10 2017").equals(testCase));
	}
	@Test public void BooksDueOnDateEmpty() {
		Library library1 = new Library("MFRL");
		Book b1 = new Book("Harry Potter and the Sorcerer's Stone", "Jk Rowling",123, 12.5);
		Person p1 = new Person("Matthew Jordan","1105 Juniper Drive", 1234);
		ArrayList<Book> librayBooks = new ArrayList<Book>();
		ArrayList<Person> patronz = new ArrayList<Person>(); 
		library1.setLibraryBooks(librayBooks);
		librayBooks.add(b1);
		patronz.add(p1);
		library1.setPatrons(patronz);
		library1.checkOut(p1,b1,"31 10 2017");
		ArrayList<Book> testCase = new ArrayList<Book>();
		assertTrue(library1.booksDueOnDate("30 10 2017").equals(testCase));
	}
	@Test public void singleLateFee() {
		Library library1 = new Library("MFRL");
		Book b1 = new Book("Harry Potter and the Sorcerer's Stone", "Jk Rowling",123, 20);
		Person p1 = new Person("Matthew Jordan","1105 Juniper Drive", 1234);
		ArrayList<Book> librayBooks = new ArrayList<Book>();
		ArrayList<Person> patronz = new ArrayList<Person>(); 
		library1.setLibraryBooks(librayBooks);
		librayBooks.add(b1);
		patronz.add(p1);
		library1.setPatrons(patronz);
		library1.setCurrentDate("05 11 2017");
		library1.checkOut(p1, b1, "04 11 2017");
		assertTrue(library1.lateFee(p1) == 0.2);
		
	}
	@Test public void DoubleLateFees() {
		Library library5 = new Library("MFRL");
		Book b1 = new Book("Harry Potter and the Sorcerer's Stone", "Jk Rowling",123, 20.0);
		Book b3 = new Book("Name of the Wind", "Patrick Roffuss", 7, 20.0);
		Person p4 = new Person("Matthew Jordan","1105 Juniper Drive", 1234);
		ArrayList<Book> librayBooks = new ArrayList<Book>();
		ArrayList<Person> patronz = new ArrayList<Person>(); 
		library5.setLibraryBooks(librayBooks);
		librayBooks.add(b1);
		librayBooks.add(b3);
		patronz.add(p4);
		library5.setPatrons(patronz);
		library5.setCurrentDate("05 11 2017");
		library5.checkOut(p4,b3,"01 11 2017");
		library5.checkOut(p4, b1, "03 11 2017");
		assertTrue(library5.lateFee(p4) == 1.2);
		
	}		
	}
