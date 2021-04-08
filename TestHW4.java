/*Matthew Jordan (Mrj3dd)
 * Assignment 4- Interfaces
 */

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class TestHW4 {

	Song song1 = new Song("Rolling Stones","Honky Tonk Woman",3,1);
	Song song2 = new Song("Rolling Stones","Honky Tonk Woman");
	Song song3 = new Song("ACDC", "Back in Black",0,0);
	Song song4 = new Song("Rip Tide Blues","Jazz Man",10,10);
	Song song5 = new Song("Rip Tide Blues","Jazz Man",10,10);
	Song song6 = new Song("MisterWives","Same Drugs",1,0);
	PlayList p1 = new PlayList();
	PlayList p3 = new PlayList();
	
	
	//Tests for song
	@Test
	public void testSongEquals() {
		assertTrue(song4.equals(song5));
	}
	
	@Test
	public void testSongEquals2() {
		assertFalse(song2.equals(song3));
	}
	
	@Test
	public void testSongToString1() {
		String toStringExpected = "{Song: title = Honky Tonk Woman artist = Rolling Stones}";
		assertTrue(toStringExpected.equals(song1.toString()));
	}
	
	@Test
	public void testSongToString2() {
		String toStringExpected2 = "{Song: title = Same Drugs artist = MisterWives}";
		assertTrue(toStringExpected2.equals(song6.toString()));
	}
	
	@Test
	public void testSongCompareTo() {
		assertTrue(song3.compareTo(song1) < song1.compareTo(song3));
	}
	
	@Test
	public void testSongCompareTo2() {
		assertTrue(song4.compareTo(song2) < song2.compareTo(song4));
	}
	
	@Test
	public void testSongGetPlayTimeSecond() {
		assertTrue(song1.getPlayTimeSeconds() == 181);
	}
	
	@Test
	public void testSongGetPlayTimeSeconds2() {
		assertTrue(song2.getPlayTimeSeconds() == 0);
	}
	
	@Test
	public void testSongNumberOfSongs() {
		assertTrue(song1.numberOfSongs() == 1);
	}
	
	@Test
	public void testSongNumberOfSongs2() {
		assertTrue(song1.numberOfSongs() == 1);
	}
	
	@Test
	public void testSongGetSeconds() {
		assertTrue(song1.getPlayTimeSeconds() == 181);
	}
	
	@Test public void testSongGetSeconds2() {
		assertTrue(song6.getPlayTimeSeconds() == 60);
	}
	
	@Test
	public void testPlayListToString() {
		PlayList playlist1 = new PlayList();
		playlist1.addSong(song1);
		String expectedToStrings = "Untitled{Song: title = Honky Tonk Woman artist = Rolling Stones}";
		assertTrue(playlist1.toString().equals(expectedToStrings));
	}
	
	@Test
	public void testPlayListToString2() {
		PlayList playlist2 = new PlayList("Matt's Mix");
		playlist2.addSong(song6);
		String expectedToString = "Matt's Mix{Song: title = Same Drugs artist = MisterWives}";
		assertTrue(playlist2.toString().equals(expectedToString));
	}
	
	@Test
	public void testPlayListClear() {
		PlayList playlist3 = new PlayList("Matt's Mix");
		playlist3.addSong(song6);
		playlist3.addSong(song3);
		ArrayList test = new ArrayList<Playable>();
		playlist3.clear();
		assertTrue(playlist3.getPlayableList().equals(test));
	}
	
	@Test
	public void testPlayListClear2() {
		PlayList playlist4 = new PlayList();
		playlist4.addSong(song1);
		ArrayList test = new ArrayList<Playable>();
		playlist4.clear();
		assertTrue(playlist4.getPlayableList().equals(test));
	}
	
	@Test
	public void testAddSong() {
		PlayList playlist5 = new PlayList();
		assertTrue(playlist5.addSong(song1) == true);
	}
	
	@Test
	public void testAddSong2() {
		PlayList playlist = new PlayList("Wow");
		assertTrue(playlist.addSong(song5) == true);
	}
	
	@Test
	public void testRemovePlayableIndex() {
		PlayList playlist = new PlayList("Matt's");
		playlist.addSong(song1);
		playlist.addSong(song2);
		playlist.addSong(song3);
		playlist.removePlayable(0);
		ArrayList test = new ArrayList<Playable>();
		test.add(song2);
		test.add(song3);
		assertTrue(playlist.getPlayableList().equals(test));
	}
	
	@Test
	public void testRemovePlayableIndex2() {
		PlayList playlist = new PlayList("Matt's");
		playlist.addSong(song1);
		PlayList playlist2 = new PlayList("Wow");
		playlist2.addSong(song4);
		playlist2.addPlayList(playlist2);
		playlist2.addSong(song6);
		ArrayList test = new ArrayList<Playable>();
		test.add(song4);
		test.add(song6);
		playlist2.removePlayable(1);
		assertTrue(playlist2.getPlayableList().equals(test));
	}
	
	@Test
	public void testRemovePlayableObject() {
		PlayList playlist = new PlayList("Matt's");
		playlist.addSong(song1);
		playlist.addSong(song1);
		playlist.addSong(song2);
		assertTrue(playlist.removePlayable(song3) == null);
	}
	
	@Test
	public void testRemovePlayableObjecr2() {
		PlayList playlist = new PlayList("Matt's");
		playlist.addSong(song1);
		assertTrue(playlist.removePlayable(song1) == song1);
	}
	
	@Test
	public void testGetPlayable() {
		PlayList playlist = new PlayList("Matt's");
		playlist.addSong(song1);
		playlist.addSong(song2);
		assertTrue(playlist.getPlayable(0).equals(song1));
	}
	
	@Test
	public void testGetPlayable2() {
		PlayList playlist = new PlayList("Matt's");
		PlayList playlist2 = new PlayList("Wow");
		playlist.addSong(song1);
		playlist.addPlayList(playlist2);
		assertTrue(playlist.getPlayable(1).equals(playlist2));
	}
	
	@Test
	public void testAddPlayList() {
		PlayList playlist = new PlayList("Matt's");
		PlayList playlist2 = new PlayList("Wow");
		assertTrue(playlist.addPlayList(playlist2) == true);
	}
	
	@Test
	public void testAddPlayList2() {
		PlayList playlist = new PlayList("Matt's");
		PlayList playlist2 = new PlayList("Ian's");
		playlist.addPlayList(playlist2);
		assertTrue(playlist.addPlayList(playlist2) == false);
	}
	
	@Test
	public void testPlayListGetPLayTimeSeconds() {
		PlayList playlist = new PlayList("Matt's");
		PlayList playlist2 = new PlayList("Ian's");
		playlist.addSong(song1);
		playlist2.addSong(song6);
		playlist.addPlayList(playlist2);
		assertTrue(playlist.getPlayTimeSeconds() == 241);
		
	}
	
	@Test
	public void testPlayListGetPLayTimeSeconds2() {
		PlayList playlist = new PlayList("Matt's");
		PlayList playlist2 = new PlayList("Ian's");
		playlist.addSong(song1);
		playlist.addSong(song6);
		assertTrue(playlist.getPlayTimeSeconds() == 241);
	}
	
	@Test
	public void testPlayListGetSongs() {
		PlayList playlist = new PlayList("Matt's");
		playlist.addSong(song1);
		playlist.addSong(song3);
		playlist.addSong(song2);
		assertTrue(playlist.numberOfSongs() == 3);
	}
	
	@Test
	public void testPlayListGetSongs2() {
		PlayList playlist = new PlayList("Matt's");
		PlayList playlist2 = new PlayList("Ian's");
		playlist.addSong(song1);
		playlist2.addSong(song3);
		playlist2.addSong(song2);
		playlist.addPlayList(playlist2);
		assertTrue(playlist.numberOfSongs() == 3);
	}
	
	@Test
	public void testSortByName() {
		PlayList playlist = new PlayList("Matt's");
		playlist.addSong(song1);
		playlist.addSong(song3);
		playlist.addSong(song6);
		playlist.sortByName();
		ArrayList<Playable> list = new ArrayList<Playable>();
		list.add(song3);
		list.add(song1);
		list.add(song6);
		assertTrue(playlist.getPlayableList().equals(list));
	}
	
	@Test
	public void testSortByName2() {
		PlayList playlist = new PlayList("Matt's");
		playlist.addSong(song4);
		playlist.addSong(song3);
		playlist.addSong(song6);
		playlist.sortByName();
		ArrayList<Playable> list = new ArrayList<Playable>();
		list.add(song3);
		list.add(song4);
		list.add(song6);
		assertTrue(playlist.getPlayableList().equals(list));
	}
	
	@Test
	public void testSortByTime() {
		PlayList time = new PlayList("Matt's");
		time.addSong(song1);
		time.addSong(song3);
		time.addSong(song6);
		time.sortByTime();
		System.out.println(time.getPlayableList());
		ArrayList<Playable> list = new ArrayList<Playable>();
		list.add(song3);
		list.add(song6);
		list.add(song1);
		assertTrue(time.getPlayableList().equals(list));
	}
	
	@Test
	public void testSortByTime2() {
		PlayList playlist = new PlayList("Matt's");
		playlist.addSong(song4);
		playlist.addSong(song3);
		playlist.addSong(song6);
		playlist.sortByTime();
		ArrayList<Playable> list = new ArrayList<Playable>();
		list.add(song3);
		list.add(song6);
		list.add(song4);
		assertTrue(playlist.getPlayableList().equals(list));
	}
	
}
	