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
    //                          ----------------------------- Driver / Main Method -----------------------------
    public static void main(String[] args)
    {
        // Object Declaration
        Scanner input = new Scanner(System.in);
        
        // Variable Declaration
        boolean onStateMainMenu = true;
        String currentInput = "";
        
        // Main Menu Loop
        while(onStateMainMenu == true)
        {
            System.out.println("\n\n\n\n\n\n\nTHE WHEEL SEQUEL \n\nType a letter or input: \na. Spin \nb. Settings \nc. Exit\n");
            currentInput = input.nextLine();
            
            if(currentInput.equalsIgnoreCase("Spin") == true || currentInput.equalsIgnoreCase("a") == true)
            {
                twsSpinner.spinDriver(input);
            }
            else if(currentInput.equalsIgnoreCase("Settings") == true || currentInput.equalsIgnoreCase("b") == true)
            {
                twsSettings.settingsDriver(input, currentInput);
            }
            else if(currentInput.equalsIgnoreCase("Exit") == true || currentInput.equalsIgnoreCase("c") == true)
            {
                onStateMainMenu = false;
                System.out.println("Cya");
            }
        }
    }
}