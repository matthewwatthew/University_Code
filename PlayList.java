/* Matthew Jordan (mrj3dd)
 * Assignment 4- Interfaces
 * Resources Used: Online1: (https://stackoverflow.com/questions/9108781/how-to-remove-leading-and-trailing-whitespace-from-the-string-in-java)
 * 				   Online2: (https://stackoverflow.com/questions/5585779/how-to-convert-a-string-to-an-int-in-java)
 * 				   Online3: (https://stackoverflow.com/questions/18448671/how-to-avoid-concurrentmodificationexception-while-removing-elements-from-arr)
 * Talked to TAs for help with the LoadSong(), getPlayTimeSeconds() and getPlayable() methods
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class PlayList implements Playable {

	private String name;
	private ArrayList<Playable> playableList = new ArrayList<Playable>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Playable> getPlayableList() {
		return playableList;
	}

	public void setPlayableList(ArrayList<Playable> playableList) {
		this.playableList = playableList;
	}

	public String toString() { /**Returns a string of the name of the playlist and the songs artist/title found within the playlist*/
		String finalString = "";
		for (Playable a : playableList) {
			name = name + a.toString();
			finalString = finalString + name;
		}
		return finalString;
	}

	public PlayList() { /**Constructor that creates a new playlist. It takes in no playlist name, so it titles it untitled and creates a new playable list */
		this.name = "Untitled";
		playableList = new ArrayList<Playable>();
	}

	public PlayList(String newName) { /** Constructor: creates a new playlist. It takes in a playlist name and titles it appropriately. It creates a new playable list */
		this.name = newName;
		playableList = new ArrayList<Playable>();
	}

	public boolean loadSongs(String fileName) { /** Loads a song(s) from a text file and adds the songs to the playableList*/
		File readData = new File(fileName);
		Scanner inputFile;
		try {
			inputFile = new Scanner(readData);
			while (inputFile.hasNextLine()) {
				String title = "";
				String artist = "";
				int time1 = 0;
				int time2 = 0;
				title = inputFile.nextLine().trim(); //Online 1
				artist = inputFile.nextLine().trim(); //Online 1
				String strs = inputFile.nextLine();
				int indexSemi = strs.indexOf(":");
				String minute = strs.substring(0, indexSemi).trim().replaceAll("\\s+",""); //Online 1
				time1 = time1 + Integer.parseInt(minute); // Online 2
				String seconds = strs.substring(indexSemi + 1).trim().replaceAll("\\s+",""); //Online 1
				time2 = time2 + Integer.parseInt(seconds); // Online 2
				while (time2 > 60) {
					time2 = time2 - 60;
					time1 = time1 + 1;
				}
				String nextLine = inputFile.nextLine();

				Song s1 = new Song(artist, title, time1, time2);
				addSong(s1);
			}
		} catch (FileNotFoundException e) {
			return false;
		}
		inputFile.close();
		return true;
	}

	public boolean clear() { /** Deletes everything from the playableList and makes a new empty one*/
		playableList = new ArrayList<Playable>();
		return true;
	}

	public boolean addSong(Song s) { /** Adds a song to the playableList*/
		if (playableList.add(s) == true) {
			return true;
		} else {
			return false;
		}
	}

	public Playable removePlayable(int index) { /** Removes a playable object from a given index */
		return playableList.remove(index);
	}

	public Playable removePlayable(Playable p) { /** Removes all instances of the playable element from the playableList*/
		ArrayList<Playable> toRemove = new ArrayList<Playable>(); //Online 3
		if (playableList.contains(p) == false) {
			return null;
		}
		for (Playable element : playableList) {
			if (element.equals(p)) {
				toRemove.add(element); //Online 3
			}
		}
		playableList.removeAll(toRemove);
		return p;
	}

	public Playable getPlayable(int index) { /** Gets the playable object at a given index*/
		if (index >= playableList.size() || index == -1) {
			return null;
		}
		else if (playableList.contains(playableList.get(index)) == true) {
			return playableList.get(index);
		} else {
			return null;
		}
	}

	public boolean addPlayList(PlayList pl) { /** Adds a new playlist to the playableList */
		if (playableList.contains(pl)) {
			return false;
		} else {
			playableList.add(pl);
			return true;
		}
	}

	public void play() { /** Plays the playlist*/
	}
	
	public int getPlayTimeSeconds() { /** Returns the total seconds from each song in each playlist in the playableList*/
		int totalSeconds = 0;
		for (Playable element : playableList) {
			totalSeconds = totalSeconds + element.getPlayTimeSeconds();
		}
		return totalSeconds;
	}

	@Override
	public int numberOfSongs() { /** Returns the number of songs within a given playlist's playable list */
		int numberOfSongs = 0;
		for (Playable element : playableList) {
			if (element instanceof Song) {
				numberOfSongs = numberOfSongs + 1;
			}
			if (element instanceof PlayList) {
				for (Playable elements : ((PlayList) element).getPlayableList()) {
					if (elements instanceof Song) {
						numberOfSongs = numberOfSongs + 1;
					}
				}
			}
		}
		return numberOfSongs;
	}

	public void sortByName() { /** Uses the comparator CmpByName to sort the playable list by Name */
		Collections.sort(playableList, new CmpByName());
	}

	public void sortByTime() { /** Uses the comparator CmpByTime to sort the playable list by Time */
		Collections.sort(playableList, new CmpByTime());
	}

}