/* This helper file is for tools made for various parts of the program
 * The "tools" are mainly just input processing though but are meant
 * to be reused throughout the entire prgoram.
 */

import java.util.*;
import java.io.*;

public class twsTools
{
    /* ----- File Information in a List --> String ArrayList -----
     *  Try to copy the file path information stored in text file
     *  into an array list for later usage/reference.
     */
    public static ArrayList<String> getFileArrayList(ArrayList<String> desiredArrayList, File fileToCheck)
    {
        // Try block to copy info of .text --> ArrayList
        try
        {
            // Segmented Scanner Declaration to scan the text file for file navigation
            Scanner fileScan = new Scanner(fileToCheck);
            
            // Get the amount of lines (people/paths)
            while(fileScan.hasNextLine() == true)
            {   
                desiredArrayList.add(fileScan.nextLine());
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("If you see this message that means that the file navigation text file is borked.");
        }
        
        // Catch edge-case if literally no file paths are present
        if(desiredArrayList.isEmpty() == true)
        {
            System.out.println("File information not found; Check if the path to this file is correct. \n| Terminating Wheel Roll |");
            System.exit(1); // There has to be a better way of doing this lmfao
        }
        
        return desiredArrayList;

    }

    // ----- Determine whether or not the String is an integer ----- 
    public static boolean isNumber(String currentNumber)
    {
        // Variable Declaration
        boolean isNumber = false;
        int currentNumberAsInt = 0;
        
        // Determine if it's a number
        try // This try block is not mine but it's way more efficient than what I did (brute force through every character) 
        {
            currentNumberAsInt = Integer.parseInt(currentNumber);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        
        return true;
    }
    
    // ----- Determine whether or not the number is a number and within ArrayList bounds ----- 
    public static boolean isNumberAndWithinBounds(String currentNumber, ArrayList<String> currentArray)
    {
        // Variable Declaration
        boolean isNumber = false;
        int currentNumberAsInt = 0;
        
        // Determine if it's a number
        try 
        {
            currentNumberAsInt = Integer.parseInt(currentNumber);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        
        // Determine if it's in the ArrayList bounds
        if(currentNumberAsInt >= 1 && currentNumberAsInt <= currentArray.size())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /* ----- Check input to make sure it's a yes/no adjacent String -----
     * Documentation for Usage:
     * 0 = No
     * 1 = Yes
     * -1 = Invalid
     */ 
    public static int isYesOrNo(String currentString)
    {
        if(currentString.equalsIgnoreCase("n") || currentString.equalsIgnoreCase("no"))
        {
            return 0;
        }
        else if(currentString.equalsIgnoreCase("y") || currentString.equalsIgnoreCase("yes"))
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}