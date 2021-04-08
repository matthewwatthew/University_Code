import java.awt.Color;
import java.util.Arrays;

public class InsertionRunnable implements Runnable {

	@Override
	public void run() {
		Homework7MainHub red = new Homework7MainHub();
		int[] fiftycopy1 = Homework7MainHub.getFiftycopy1();		
		int n = fiftycopy1.length;
		for (int k = 1; k < n; k++) {
			int key = fiftycopy1[k];
			int l = k-1;
			while((l > -1) && (fiftycopy1 [l] > key)){
				fiftycopy1[l + 1] = fiftycopy1[l];
				l--;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Homework7MainHub.getArrayLabel2().setText(Arrays.toString(fiftycopy1));	
			}
			fiftycopy1[l+1] = key;
			Homework7MainHub.getArrayLabel2().setText(Arrays.toString(fiftycopy1));
			Homework7MainHub.getSwitched2().setText("A NEW ITEM HAS BEEN SORTED IN THE FRONT");
			Homework7MainHub.getSwitched2().setForeground(Color.black);
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Homework7MainHub.getSwitched2().setForeground(Color.gray);
			Homework7MainHub.getSwitched2().setText("Unsorted item looking for its spot");
		}
		Homework7MainHub.getArrayLabel2().setForeground(Color.green);
		Homework7MainHub.getSwitched2().setForeground(Color.green);
		Homework7MainHub.getSwitched2().setText("ALL ITEMS ARE SORTED");
		
		
	}

}
