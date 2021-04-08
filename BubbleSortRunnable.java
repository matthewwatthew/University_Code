import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;

import javax.swing.JLabel;

public class BubbleSortRunnable implements Runnable{	
	@Override
	public void run() {
	Homework7MainHub red = new Homework7MainHub();
	int[] fiftyArray = Homework7MainHub.getFiftyArray();
	JLabel switched = Homework7MainHub.getSwitched();
	switched.setForeground(Color.gray);
		boolean swapped = true;
		int j = 0;
		int tmp;
		String tmps;
		while(swapped) {
			swapped = false;
			j++;
		for (int i = 0; i < fiftyArray.length - j; i++) {
			if(fiftyArray[i] > fiftyArray[i + 1]) {
				tmp = fiftyArray[i];
				fiftyArray[i] = fiftyArray[i + 1];
				fiftyArray[i + 1] = tmp;
				swapped = true;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Homework7MainHub.getSwitched().setText("Items are being swapped!");
				Homework7MainHub.getArrayLabel().setText(Arrays.toString(fiftyArray));
			}
			
	}
		Homework7MainHub.getSwitched().setForeground(Color.black);
		Homework7MainHub.getSwitched().setText("A NEW ITEM HAS BEEN SORTED IN ITS SPOT");
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Homework7MainHub.getSwitched().setForeground(Color.gray);
		}
		Homework7MainHub.getArrayLabel().setForeground(Color.green);
		Homework7MainHub.getSwitched().setText("ALL ITEMS ARE SORTED!");
		Homework7MainHub.getSwitched().setForeground(Color.green);
	}
}	