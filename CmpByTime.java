// Matthew Jordan (mrj3dd)
// Homework 4
import java.util.Comparator;

public class CmpByTime implements Comparator<Playable>{
	@Override
	public int compare(Playable o1, Playable o2) { /**Compares two playable objects first by their total amount of seconds. */
		if (o1.getPlayTimeSeconds() < o2.getPlayTimeSeconds()) {
			return -1;
		}
		else if (o1.getPlayTimeSeconds() > o2.getPlayTimeSeconds()) {
			return 1;
		}
		else if (o1.getPlayTimeSeconds() == o2.getPlayTimeSeconds()) {
			return 0;
		}
		return 0;
	}
	}
