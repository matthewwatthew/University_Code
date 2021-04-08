import java.awt.Color;
import java.util.Arrays;

public class SelectionSortRunnable implements Runnable {

	@Override
	public void run() {
		Homework7MainHub red = new Homework7MainHub();
		int[] fiftycopy2 = Homework7MainHub.getFiftycopy2();
		
		int i, j, first, temp;
		for (i = fiftycopy2.length - 1; i > 0; i--) {
			Homework7MainHub.getSwitched3().setText("Verifing the next lowest value");
			first = 0;
			for(j = 1; j <= i; j ++) {
				if(fiftycopy2[j] < fiftycopy2[first]) {
					first = j;
				}
				temp = fiftycopy2[first];
				fiftycopy2[first] = fiftycopy2[i];
				fiftycopy2[i] = temp;
				Homework7MainHub.getArrayLabel3().setText(Arrays.toString(fiftycopy2));
				
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Homework7MainHub.getSwitched3().setForeground(Color.black);
			Homework7MainHub.getSwitched3().setText("THE NEXT LOWEST ELEMENT HAS BEEN SORTED");
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Homework7MainHub.getSwitched3().setText("Verifing next lowest value");
			Homework7MainHub.getSwitched3().setForeground(Color.gray);
		}
		
		Homework7MainHub.getArrayLabel3().setForeground(Color.green);
		Homework7MainHub.getSwitched3().setForeground(Color.green);
		Homework7MainHub.getSwitched3().setText("ALL ITEMS ARE SORTED");
	}
	}