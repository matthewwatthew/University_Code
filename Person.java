// Matthew Jordan (mrj3dd)
// Homework 3- Book, Person, Assignment

import java.util.ArrayList;

public class Person {
	
private String name;
private ArrayList<Book> checkedOut = new ArrayList<Book>();
private String address;
private int libraryCardNum;


public Person(String name, String address, int libraryCardNum) {
	this.name = name;
	this.address = address;
	this.libraryCardNum = libraryCardNum;
}

public String getAddress() {
	return address;
}


public void setAddress(String address) {
	this.address = address;
}


public String getName() {
	return name;
}


public ArrayList<Book> getCheckedOut() {
	return checkedOut;
}


public int getLibraryCardNum() {
	return libraryCardNum;
}


public boolean addBook(Book b) {
	if (!checkedOut.contains(b)) {
		checkedOut.add(b);
		return true;
	}
	else {
		return false;
	}
}

@Override 
public boolean equals(Object o) {
	if (o instanceof Person) {
		Person person1 = (Person)o;
		if (this.getLibraryCardNum() == person1.getLibraryCardNum()) {
			return true;
		}
		else {
			return false;
		}
	}
	else {
		return false;
	}
}
			
@Override	
public String toString() {
	return name + " has an address of " + address + " and has checked out" + checkedOut; 
}
}

