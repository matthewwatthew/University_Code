// Matthew Jordan (mrj3dd)
// Homework 4
// Talked to TAs in Thorton for help on the equals method

public class Song implements Playable, Comparable<Song>{
	private String artist;
	private String title;
	private int minutes;
	private int seconds;

	public String getArtist() {
		return artist;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getMinutes() {
		return minutes;
	}
	
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
	public int getSeconds() {
		return seconds;
	}
	
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	
	public Song(String artist, String title) { /** A constructor for a simple song containing only an artist and a title*/
		this.artist = artist;
		this.title = title;
	}
	
	public Song(String artist, String title, int minutes, int seconds) { /** A constructor for a complex song containing an artist, title, minutes and seconds*/
		this.artist = artist;
		this.title = title;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	public Song(Song s) { /** A constructor that takes an existing song and creates a new song from its variables */
		this.title = s.getTitle();
		this.artist = s.getArtist();
		this.minutes = s.getMinutes();
		this.seconds = s.getSeconds();
	}	

	@Override
	public boolean equals(Object o) { /** An equals method that returns equal only if the two song objects have the same artist, title, minutes and seconds*/ 
		if (o instanceof Song) {
			Song song1 = (Song)o;
			if (this.getArtist().equals(song1.getArtist()) && this.getTitle().equals(song1.getTitle()) && this.getMinutes() == song1.getMinutes() && this.getSeconds() == song1.getSeconds()) {
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
	public String toString(){ /**A toString method that overrides the original toSong. Prints the Song and its title and its artist*/
		return "{Song: title = " + title + " artist = " + artist + "}";
	}

	public void play() { /** A method that prints what song is currently being played*/
		System.out.printf("Playing Song: artist = %-20s title = %s\n", artist, title);
	}
 
	public int compareTo(Song a) { /**A compareTo method that compares songs based on their artist first, and their title second */
		int value = artist.compareTo(a.artist);
		if (value == 0) {
			value = title.compareTo(a.title);
		}
		return value;
	}

	public String getName() { /** Returns the given song's title */
		return this.title;
	}
	
	public int getPlayTimeSeconds() {/** Returns the total amount of seconds within a given song */ 
		int value = 0;
		value = value + (this.minutes * 60);
		value = value + (this.seconds);
		return value;
	}
	public int numberOfSongs() { /** Returns the number of Songs in a given song, so it will always be 1*/
		return 1;
	}

}
