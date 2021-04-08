// Matthew Jordan (Mrj3dd)
// Homework 3- Book, Person, Library.

public class Book {
	

private String title;
private String author; 
private String dueDate;
private boolean checkedOut;
private int bookId;
private double bookValue;


public String getTitle() {
	return title;
}

public String getAuthor() {
	return author;
}

public Book(String title, String author, int bookId, double bookValue) {
	this.title = title;
	this.author = author;
	this.bookId = bookId;
	this.bookValue = bookValue;
}
public String getDueDate() {
	return dueDate;
}

public void setDueDate(String dueDate) {
	this.dueDate = dueDate;
}

public boolean isCheckedOut() {
 	return checkedOut;
}

public void setCheckedOut(Boolean checkedOut) {
	this.checkedOut = checkedOut;
}

public Double getBookValue() {
	return bookValue;
}

public void setBookValue(Double bookValue) {
	this.bookValue = bookValue;
}

public int getBookId() {
	return bookId;
}

@Override 
public boolean equals(Object o) {
	if (o instanceof Book) {
		Book book1 = (Book)o;
		if (this.getBookId() == book1.getBookId()) {
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
	 return ("The book is " + title + ", and its author is " + author + ". It has an id number of " + bookId + "and has a value of " + bookValue + ".");
}
}

