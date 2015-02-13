/**
 * First Last
 */

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.io.File;

import java.util.ArrayList;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Main
{
    public static final int PERIMETER_BEVEL = 20;               //space between panel border and perimeter cards
    public static final int INTERIOR_BEVEL = 5;                 //space between cards
    public static final String CARD_FOLDER="cardImages";
    public static final int CARD_HEIGHT = 97;
    public static final int CARD_WIDTH = 73; 
    public static final String[] RANKS = { "gray","gray","gray","gray","ace","two","three","four","five","six","seven",
					   "eight","nine","ten","jack","queen","king"};
    public static final int PANEL_HEIGHT = (2*PERIMETER_BEVEL) + (4*CARD_HEIGHT) + (3*INTERIOR_BEVEL);
    public static final int PANEL_WIDTH = (2*PERIMETER_BEVEL) + (14*CARD_WIDTH) + (13*INTERIOR_BEVEL);
    public static final String   BACKGROUND_COLOR = "#76ee00";  //window background color [hex] -SJO swapped to lime green
                //ranks in increasing order with 4 leading gray images


    /**
     * randCardName takes the info based on the current state of the deck and returns the name of
     * the next unused card to lay out on the canvas
     * much help taken from:
     * http://stackoverflow.com/questions/19125530/how-to-choose-a-random-element-in-this-array-only-once-accros-all-declared-objec
     * @param cards - the array of all the cards
     * @param counter - the current index of cards played
     * @param used - the cards that have been used so far
     * @return the name of the next unused card
     */
    public static String randCardName(String[] cards, int counter, ArrayList<String> used)
    {
    	String name = "";
    	String checkedFile = "";
    	if (counter < 4)
    	{
    			// then pull out a gray one
    			checkedFile = cards[counter];
    	}
    	else
    	{
    		do
    	 	{
    	 		checkedFile = cards[(int) (Math.random() * (cards.length - 4)) + 4];
    	 		if (!used.contains(checkedFile)) break;
    	 	} while (true);
    	}
    	name = checkedFile;
    	used.add(name);
    	return name;
    }
    
    /**
     * main method which prints out the deck of cards in rank order gray image
     * through king with aces low. Printing is done in 14 columns of 4 cards each.
     * each row is a unique suit in order spades, hearts, diamonds, and clubs.
     * @param args, the arguments for the main method [ignored]
     */
     public static void main(String[] args)
     {
         JFrame window = new JFrame("Deck");
	 JPanel panel = new JPanel() {
	 public void paintComponent(Graphics g) { //find each rank of card in increasing
	     super.paintComponent(g); //order as specified in the array. All
	     File[] files = new File(CARD_FOLDER).listFiles(); //ranks appear in the same suit order in
	     int counter = 0; //the filesystem so suits will automatically
	     String[] cards; //be in order when printing in groups of four
	     ArrayList<String> used = new ArrayList<String>(); //cards.
	     //Allocates memory for all cards
	     cards = new String[56];
	     for(String rank : RANKS) {
	         for(File filename : files) {
		     if(filename.getName().contains(rank)) {
		          // Add file name to an array of file names for the cards.
			  cards[counter] = (String) filename.getPath();
			  System.out.print(cards[counter] + "\n");
			  //new ImageIcon(filename.getPath()).paintIcon(this, g,
			  // PERIMETER_BEVEL + (counter/4) * (CARD_WIDTH + INTERIOR_BEVEL),
			  // PERIMETER_BEVEL + (3-(counter%4)) * (CARD_HEIGHT + INTERIOR_BEVEL));
			  counter++;			     
		      }
		  }
	      }
	      // reset counter because we want to start placing them at 0, not 56
	      counter = 0;
	      // for each card name in the array, pull one out (using randCardName)
	      // and print it to the screen.
	      while (counter < 56)
	      {
	          new ImageIcon(randCardName(cards, counter, used)).paintIcon(this, g,
				PERIMETER_BEVEL + (counter/4) * (CARD_WIDTH + INTERIOR_BEVEL), //counter/4 keeps track of the correct column
				PERIMETER_BEVEL + (3-(counter%4)) *(CARD_HEIGHT + INTERIOR_BEVEL)); //3-(counter%4) keeps track of the correct row
		  counter++; //in which to print the card image
	      }
	  }
      };
      panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
      panel.setBackground(Color.decode(BACKGROUND_COLOR));
      window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      window.add(panel);
      window.setVisible(true);
      window.pack();
  }
}
