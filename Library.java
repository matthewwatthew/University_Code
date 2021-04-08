// Matthew Jordan (MRJ3dd)
// Homework 3- Book, Person, Library
// Used the following Sources for help on this assignment. 
// Source 1- https://stackoverflow.com/questions/20165564/calculating-days-between-two-dates-with-in-java. To calculate the number of days between currentDate and dueDate.

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Library {

private ArrayList<Book> libraryBooks = new ArrayList<Book>();
private ArrayList<Person> patrons = new ArrayList<Person>();
private String name;
private int numBooks;
private int numPeople;
private String currentDate;

public Library(String name) {
	this.name = name;
}

public ArrayList<Book> getLibraryBooks() {
	return libraryBooks;
}

public void setLibraryBooks(ArrayList<Book> libraryBooks) {
	this.libraryBooks = libraryBooks;
}

public ArrayList<Person> getPatrons() {
	return patrons;
}

public void setPatrons(ArrayList<Person> patrons) {
	this.patrons = patrons;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getCurrentDate() {
	return currentDate;
}

public void setCurrentDate(String currentDate) {
	this.currentDate = currentDate;
}

public int getNumBooks() {
	int checkedOutBooks = 0;
	for (Book b : libraryBooks) {
		if (b.isCheckedOut() == true) {
			checkedOutBooks = checkedOutBooks + 1;
		}
	}
	numBooks = totalNumBooks() - checkedOutBooks;
	return numBooks;
}

public int getNumPeople() {
	numPeople = patrons.size();
	return numPeople;
}


public int checkNumCopies(String title, String author) {
	int copiesOfBook = 0;
	for (Book value : libraryBooks) {
		if (value.getTitle() == title && value.getAuthor() == author) {
			copiesOfBook ++;
		}
		else {
			continue;
		}
}
	return copiesOfBook;
}	

public int totalNumBooks() {
return libraryBooks.size();
}

public boolean checkOut(Person p, Book b, String dueDate) {
	boolean isCheckedOut = false;
	if (libraryBooks.contains(b)){
		if (patrons.contains(p)) {
			if (b.isCheckedOut() == true) {
				isCheckedOut = false;
			}
			else {
				for (Book bookers : libraryBooks) {
					if (bookers.getBookId() == b.getBookId()) {
						bookers.setCheckedOut(true);
						bookers.setDueDate(dueDate);
						isCheckedOut = true;
						p.addBook(bookers);
					}	
				}
			}
		}
		}
	return isCheckedOut;
}
public ArrayList<Book> booksDueOnDate(String date) {
	ArrayList<Book> dueOnDate = new ArrayList<Book>();
	for (Book b : libraryBooks) {
		if (b.getDueDate() == date) {
			dueOnDate.add(b);
		}
	}
return dueOnDate;
}
public double lateFee(Person p) {
	double finalValue = 0;
	double percent = 0.01;
	SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy"); //SOURCE 1
    for (Book b : p.getCheckedOut()) {
    	  int days = -1; // SOURCE 1
    	  try {
    	    Date currTime = sdf.parse(currentDate); //SOURCE 1
    	    Date dueDate = sdf.parse(b.getDueDate()); //SOURCE 1
    	    days = Math.round((currTime.getTime() - dueDate.getTime()) / (int) 86400000); //SOURCE 
    	  } catch (Exception e) { //SOURCE 1
    	  }
    	  if (days > 0) { //SOURCE 1
    		  int counter = 0;
    		  while (days != counter) {
    			  finalValue = finalValue + (b.getBookValue() * percent);
    			  finalValue = (finalValue * 100) / 100;
    			  counter = counter + 1;
    		  }
    	  }
    }
	return finalValue;
    }  
}
