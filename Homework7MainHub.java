import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComponent;
/*Sources Used:
 * 1) Source 1: Code from Erin Griffiths's Slides, namely for Bubble, Selection and Insertion Sort
 * 2) Source 2: Copying an Array: https://stackoverflow.com/questions/5785745/make-copy-of-array-java
 * 3) Source 3: Sorting an Array: https://stackoverflow.com/questions/2450954/how-to-randomize-shuffle-a-javascript-array
 */


/* DISCUSSION QUESTIONS:
 * 1) I chose not to use locks in the above assignment because the threads no not share a common object, since each of the threads
 *   only manipulates 3 separate arrays. Therefore, there was no need for locks in this assignment.
 * 2) A. The Thread.sleep() method will suspend the current thread for a given amount of time,
 *       and continue once the alloted amount in milliseconds has been reached.
 *    B. The Thread.await() method puts the Thread into a blocked state and won't be activated until it
 *       is unblocked. signalAll must be called for it to return to an unblocked state.
 * 3) It will forever be blocked, since it will always be waiting for a signal() or signalAll() to unblock it. 
 * 
 */

public class Homework7MainHub extends JComponent{
	private static int[] fiftyArray;
	private static int[] fiftycopy1;
	private static int[] fiftycopy2;
	private static String[] counter;
	private static String[] newCounter;
	private static int x;
	private JButton SortAll;
	private JPanel panel1;
	private JPanel panel2;
	private static JPanel panel2B;
	private static JPanel panel3B;
	private static JPanel panel4B;
	private static JPanel linePanel1;
	private static JPanel linePanel2;
	private static JPanel linePanel3;
	private JPanel panel3;
	public static String[] getCounter() {
		return counter;
	}

	public static void setCounter(String[] counter) {
		Homework7MainHub.counter = counter;
	}

	public static int[] getFiftyArray() {
		return fiftyArray;
	}

	public static void setFiftyArray(int[] fiftyArray) {
		Homework7MainHub.fiftyArray = fiftyArray;
	}

	public static int[] getFiftycopy1() {
		return fiftycopy1;
	}

	public static void setFiftycopy1(int[] fiftycopy1) {
		Homework7MainHub.fiftycopy1 = fiftycopy1;
	}

	public static int[] getFiftycopy2() {
		return fiftycopy2;
	}

	public static void setFiftycopy2(int[] fiftycopy2) {
		Homework7MainHub.fiftycopy2 = fiftycopy2;
	}
	private JPanel panel4;
	private JPanel panel5;
	public static JLabel getSwitched3() {
		return switched3;
	}

	public static void setSwitched3(JLabel switched3) {
		Homework7MainHub.switched3 = switched3;
	}
	private JPanel panel6;
	private static JLabel arrayLabel;
	private static JLabel arrayLabel2;
	private static JLabel counter1;
	private static JLabel arrayLabel3;
	private static JLabel switched;
	private static JLabel switched2;
	private static JLabel switched3;
	private static JLabel line1;
	private static JLabel line2;
	private static JLabel line;
	public static JLabel getArrayLabel() {
		return arrayLabel;
	}

	public static void setArrayLabel(JLabel arrayLabel) {
		Homework7MainHub.arrayLabel = arrayLabel;
	}

	public static JLabel getArrayLabel2() {
		return arrayLabel2;
	}

	public static void setArrayLabel2(JLabel arrayLabel2) {
		Homework7MainHub.arrayLabel2 = arrayLabel2;
	}

	public static JLabel getArrayLabel3() {
		return arrayLabel3;
	}

	public static void setArrayLabel3(JLabel arrayLabel3) {
		Homework7MainHub.arrayLabel3 = arrayLabel3;
	}

	public static JLabel getSwitched() {
		return switched;
	}

	public static void setSwitched(JLabel switched) {
		Homework7MainHub.switched = switched;
	}

	public static JLabel getSwitched2() {
		return switched2;
	}

	public static void setSwitched2(JLabel switched2) {
		Homework7MainHub.switched2 = switched2;
	}

	public static void GUISystem() {
		JFrame frame = new JFrame("Sorting Methods");
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel2B = new JPanel();
		JPanel panel3B = new JPanel();
		JPanel panel4B = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JPanel panel6 = new JPanel();
		JButton sortAll = new JButton("SORT ALL!");
		sortAll.addActionListener(new BubbleSortListener());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1600, 800);
		frame.setLayout(new GridLayout(10,1));
		fiftyArray = new int[50];
		counter = new String[50];
		fiftycopy1 = new int[50];
		fiftycopy2 = new int[50];
		newCounter = new String[50];
		x = 0;
		while (x != 51) {
			if (x != 0){
			fiftyArray[x-1] = x;
			x = x + 1;}
			if (x == 0) {
				fiftyArray[0] = x;
				x = x+ 1;
			}
		}
		counter[0] = "^    ";
		int y = 2;
		while(y != 51) {
			counter[y-1] = "        ";
			y = y + 1;
		}
		shuffleArray(fiftyArray);
		System.arraycopy(fiftyArray, 0, fiftycopy1, 0, fiftyArray.length); //SOURCE 2
		System.arraycopy(fiftyArray, 0, fiftycopy2, 0, fiftyArray.length); //SOURCE 2
		System.arraycopy(counter, 0, newCounter, 0, counter.length);
		arrayLabel = new JLabel(Arrays.toString(fiftyArray));
		counter1 = new JLabel(Arrays.toString(counter));
		arrayLabel2 = new JLabel(Arrays.toString(fiftycopy1));
		arrayLabel3 = new JLabel(Arrays.toString(fiftycopy2));
		switched = new JLabel("     ");
		switched.setForeground(Color.gray);
		switched2 = new JLabel("     ");
		switched3 = new JLabel("     ");
		switched3.setForeground(Color.gray);
		JLabel bubbleLabel = new JLabel("Bubble Sort:            (Starts at Index 0, Swaps if the current item is < the next item in the array, repeats for all items. Ends when no swaps are left.)                               ");
		bubbleLabel.setForeground(Color.blue);
		JLabel InsertionLabel = new JLabel("Insertion Sort:          (Assume first item is sorted, grabs the next unsorted item, insets it into the sorted portion of the list, moving items in sorted list as necessary)        ");
		InsertionLabel.setForeground(Color.blue);
		JLabel SelectionLabel = new JLabel("Selection Sort:           (Selects the lowest number from the unsorted section, swaps with the next items in the sorted section, repeats until no unsorted items remain)");
		SelectionLabel.setForeground(Color.blue);
		arrayLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		arrayLabel2.setFont(new Font("Serif", Font.PLAIN, 20));
		arrayLabel3.setFont(new Font("Serif", Font.PLAIN, 20));
		bubbleLabel.setFont(new Font("Serif",Font.BOLD, 20));
		InsertionLabel.setFont(new Font("Serif", Font.BOLD, 20));
		SelectionLabel.setFont(new Font("Serif",Font.BOLD, 20));
		panel1.add(bubbleLabel);
		panel2.add(arrayLabel);
		panel2B.add(switched);
		panel3B.add(switched2);
		panel4B.add(switched3);
		panel3.add(InsertionLabel);
		panel4.add(arrayLabel2);
		panel5.add(SelectionLabel);
		panel6.add(arrayLabel3);
		frame.add(panel1);
		frame.add(panel2);
		frame.add(panel2B);
		frame.add(panel3);
		frame.add(panel4);
		frame.add(panel3B);
		frame.add(panel5);
		frame.add(panel6);
		frame.add(panel4B);
		frame.add(sortAll);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	
	}

	public static JLabel getCounter1() {
		return counter1;
	}

	public static void setCounter1(JLabel counter1) {
		Homework7MainHub.counter1 = counter1;
	}

	public static void main(String[] args) {
		int x = 1;
		int[] fiftyArray;
		fiftyArray = new int[50];
		while (x != 51) {
			fiftyArray[x-1] = x;
			x = x+1;
		}
		shuffleArray(fiftyArray);
		GUISystem();
	}
	
	
	private static void shuffleArray(int[] array) //SOURCE 3
	{
	    int index;
	    Random random = new Random();
	    for (int i = array.length - 1; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        if (index != i)
	        {
	            array[index] ^= array[i];
	            array[i] ^= array[index];
	            array[index] ^= array[i];
	        }
	    }
	}
	public static String[] getNewCounter() {
		return newCounter;
	}

	public static void setNewCounter(String[] newCounter) {
		Homework7MainHub.newCounter = newCounter;
	}
	//inner class
	private static class BubbleSortListener extends Thread implements ActionListener{ 
		
		@Override
		public void actionPerformed(ActionEvent e) {
		BubbleSortRunnable BSR = new BubbleSortRunnable();
		InsertionRunnable ISR = new InsertionRunnable();
		SelectionSortRunnable SSR = new SelectionSortRunnable();
		Thread t = new Thread(BSR);
		Thread s = new Thread(ISR);
		Thread r = new Thread(SSR);
		t.start();
		s.start();
		r.start();
	}
	}
}	