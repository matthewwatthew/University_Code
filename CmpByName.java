// Matthew Jordan (mrj3dd)
// Homework 4
import java.util.Comparator;

public class CmpByName implements Comparator<Playable>{
	@Override
	public int compare(Playable o1, Playable o2) { /** Compares two playable objects and compares them by their name. */
		return o1.getName().compareTo(o2.getName());}
	}
