// Matthew Jordan (mrj3dd)
// Homework 4

public interface Playable {
	
	public void play(); /** Returns what Song/PlayList is currently being played*/

	public String getName(); /** Returns the name of the song or the name of the playlist*/

	public int getPlayTimeSeconds(); /** Returns the seconds in a song/the total seconds of all songs within a playlist */

	public int numberOfSongs(); /** Returns the number of songs within a song or the number of songs within a playlist*/

}
