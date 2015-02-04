/**
 * Spencer Ollila
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
    public static final int CARD_HEIGHT = 97;
    public static final int CARD_WIDTH = 73;                    
    public static final int PANEL_HEIGHT = (2*PERIMETER_BEVEL) + (4*CARD_HEIGHT) + (3*INTERIOR_BEVEL);
    public static final int PANEL_WIDTH = (2*PERIMETER_BEVEL) + (14*CARD_WIDTH) + (13*INTERIOR_BEVEL);
    public static final String   BACKGROUND_COLOR = "#76ee00";  //window background color [hex] -SJO swapped to lime green
                //ranks in increasing order with 4 leading gray images


    /** - SJO
     * orderedCardName takes the info based on the current state of the deck and returns the name of
     * the next unused card to lay out on the canvas
     * @param counter - the current spot in the cards
     * @param type - the class of card
     * @return the name of the next unused ordered card
     */
    public static String orderedCardName(int counter, int type)
    {
        String name = "";
        if (counter == 0)
        {
            return "cardImages/gray.gif";
        }

        String[] suit = new String[4];
        suit[0] = "Clubs";
        suit[1] = "Diamonds";
        suit[2] = "Hearts";
        suit[3] = "Spades";

        String[] number = new String[14];
        number[1] = "ace";
        number[2] = "two";
        number[3] = "three";
        number[4] = "four";
        number[5] = "five";
        number[6] = "six";
        number[7] = "seven";
        number[8] = "eight";
        number[9] = "nine";
        number[10] = "ten";
        number[11] = "jack";
        number[12] = "queen";
        number[13] = "king";

        name = "cardImages/"+number[counter]+suit[type]+".gif";
        // System.out.println(name);
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
            public void paintComponent(Graphics g) {                     //find each rank of card in increasing
                super.paintComponent(g);                                 //order as specified in the array. All

                // reset counter because we want to start placing them at 0, not 56
                int counter = 0;
                int type = 0;
                // for each card collection, pull one out
                // and print it to the screen.
                while (type < 4)
                {
                	new ImageIcon(orderedCardName(counter, type)).paintIcon(this, g,
                			PERIMETER_BEVEL + counter * (CARD_WIDTH + INTERIOR_BEVEL),			    //counter/4 keeps track of the correct column
                  			PERIMETER_BEVEL + (3-(type%4)) *(CARD_HEIGHT + INTERIOR_BEVEL));		//3-(type%4) keeps track of the correct row
                    if (counter == 13)										                    	//in which to print the card image
                    {
                        counter = 0;
                        type++;
                    } else
                    {
                        counter++;
                    }
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
