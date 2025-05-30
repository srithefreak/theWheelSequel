/* This time around I've decided to go for a more modular approach so I'm creating a main class that calls for specific functions
 * It's mainly just to practice for this kind of coding later since it's industry standard to make maintanable code, but I also
 * think I might want to add more options in the future like settings or gui or sth idk
 */
 
/* Comment header style guide:

1 Tab = 4 Spaces

    //  <-- 7 Tabs from // -->  ----------------------------- Method Header -----------------------------
^ 1 Tab       ^ 26 Characters            ^ 29 Hyphens Each Side
                            
        // ----- Submethod Subheader -----
^ 2 Tabs     ^ 5 Hyphens Each Side

Documentation / Information Blurbs:
/* Header Goes Here
 * Paragraph goes here
 *
 */

import java.util.Scanner;

public class theWheelSequel
{
    // Our main driver method, gonna call from the helper file and everything else   
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        twsSpinner.helperDriver(input);
    }
}