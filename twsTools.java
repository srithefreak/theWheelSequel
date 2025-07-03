/* This helper file is for tools made for various parts of the program
 * The "tools" are mainly just input processing though but are meant
 * to be reused throughout the entire prgoram.
 */

import java.util.*;
import java.io.*;

public class twsTools
{
    /* ----- File Information in a List --> String ArrayList -----
     *  Try to copy the file information stored in text file
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
    
    /* ----- Yes or No Gate With Prompt -----
     * Documentation for Prompt Usage:
     * 0 = Respin Wheel
     * 1 = Return Main or Exit
     * 2 = Unsaved Changes (Game Pool Settings)
     *
     * Return True = Yes
     * Return False = No
     *
     * A gate version that utilizes isYesOrNo;
     * can accept multiple prompt values to output
     * different messages dependent on the need.
     */ 
    public static boolean askYesOrNoMasterTerminal(Scanner input, int desiredPrompt)
    {
        // Variable Declaration
        String currentInput = "";
        int passCondition = -1;

        // Prompting
        if(desiredPrompt == 0)
        {
            System.out.println("\nRespin The Wheel? \nYes (Spin Again) / No (Return to Main Menu)");
        }
        else if(desiredPrompt == 1)
        {
            System.out.println("\nReturn to Main Menu? \nYes (Return to Main Menu) / No (Exit Program)");
        }
        else if(desiredPrompt == 2)
        {
            System.out.println("\nYou have unsaved changes, are you sure you want to return? \nYes (Return to Settings Menu) / No (Stay on Page)");
        }
        
        // This decision block is determined by the isYesOrNo tool: For documentation on how to use its -1, 0, 1 index refer to method header
        while(passCondition == -1)
        {
            currentInput = input.nextLine();
        
            if(isYesOrNo(currentInput) == 1)
            {
                passCondition = 1;
            }
            else if(isYesOrNo(currentInput) == 0)
            {
                return false;
            }
            else
            {
                System.out.println("Invalid Input; Only type a Yes/No response.");
            }
        }
        
        return true;
    }    
    
    /* ----- Pause Timer -----
     * Reusable Try-Catch block for Thread.sleep
     * Time passed measured in milliseconds
     */
    public static void pauseTimer(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    /* ----- Search This ArrayList For X String -----
     * A search algorithm that looks through an array (Not caps sensitive)
     * Then it returns the index of the array
     *
     * -1 Return means it could not find it
     * 0+ Return means the index that it was found at
     *
     * It is very brute-forcey and I can prob spend ages iterating on this
     * design but I've only taken a beginner Java course so we'll have to
     * deal with whatever the fuck this is later
     */
    public static int searchArrayListForString(ArrayList<String> arrayListToSearch, String desiredString) 
    {
        for(int i = 0; i < arrayListToSearch.size(); i++)
        {
            if(arrayListToSearch.get(i).equalsIgnoreCase(desiredString) == true)
            {
                return i;
            }
        }
        
        return -1;
    }
    /* ----- Send an ArrayList's Contents to a File -----
     * Given an arrayList and a file location it will go through the list
     * and write everything into the text file.
     * 
     * It will put the text file in list format e.g.
     * listItem1
     * listItem2
     * listItem3
     */
    public static void arrayListToFile(ArrayList<String> arrayListToSend, String desiredFileLocation)
    {
        try
        {
            BufferedWriter arrayListWriter = new BufferedWriter(new FileWriter(desiredFileLocation));
            
            for(int i = 0; i < arrayListToSend.size(); i++)
            {
                arrayListWriter.write(arrayListToSend.get(i) + "\n");
            }
            
            arrayListWriter.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("If you see this message it means the writer couldn't find the file to set your settings properly");
        }
    }
}