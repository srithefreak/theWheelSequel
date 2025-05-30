// This helper file is the main function by choosing players, then spinning the wheel between chosen

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class twsSpinner
{

    
    /*                          ----------------------------- Driver / Main Method -----------------------------
     *                                  The Central Control Module; AKA the one that tells stuff to do things
     */
    public static void helperDriver(Scanner input)
    {           
        // ----- Segment 1: Getting Desired Players to Compare ------
        
        // Get the File Paths for Reference | See getFilePaths method for more documentation
        File filePaths = new File("fileNavInfo//filePathsInfo.txt");
        ArrayList<String> filePathsList = new ArrayList<>();
        filePathsList = twsTools.getFileArrayList(filePathsList, filePaths);
        
        // Gather chosen players from user to compare
        System.out.println("Player Files Found: ");
        printPlayerList(filePathsList);
        getCommandList();
        ArrayList<Integer> playerList = new ArrayList<>();
        playerList = summonPlayerPickerUI(playerList, filePathsList, input);
                
        // ----- Segment 2: Finding Common Games + Spinning -----
                
        // Get an ArrayList of Every Common Game
        System.out.println("Finding Common Games...");
        ArrayList<String> commonGames = new ArrayList<>();
        commonGames = getCommonGames(commonGames, playerList, filePathsList);

        // Decision Matrix Dependent on result of getCommonGames (Whether or not there are games or not)
        if(commonGames.size() == 1)
        {
            System.out.println("1 game found: " + commonGames);
        }
        else if(commonGames.isEmpty() != true)
        {
            System.out.println("Common Games Found! " + commonGames);
            goSpinTheWheel(input, commonGames);
        }
        else
        {
            System.out.println("No games found :(");
        }
        
        return;
    }

    
    /*                          ----------------------------- Path Arraylist to Player List -----------------------------
     *                                          Take the ArrayList w/ file paths and spit out a
     *                                          readable list for the user to pick from
     */
    public static void printPlayerList(ArrayList<String> filePathsList)
    {
        // Variable Declaration
        String currentName = "";
        int playerCount = 0;
        
        // Extract name from every file by stripping it to just the name; this is why the file name is important!!!
        for(int i = 0; i < filePathsList.size(); i++)
        {
            playerCount++;
            currentName = filePathIndexToName(i, filePathsList);            
            System.out.println(playerCount + ". " + currentName);
        }
    }
    
    
    /*                          ----------------------------- File Path Index to Player Name -----------------------------
     *                                      Takes an ArrayList number representation and converts that number
     *                                      to the name that it's supposed to represent
     */
    public static String filePathIndexToName(int desiredIndex, ArrayList<String> filePathsList)
    {
            String currentName = "";
            currentName = filePathsList.get(desiredIndex);
            currentName = currentName.substring(11, currentName.lastIndexOf("_"));
            return currentName;
    }
    
    
    /*                          ----------------------------- User Comparison Picking Process + UI -----------------------------
     *                                              Display a UI to the user to interact with the player pool
     *                                              and return the completed list once done
     */
    public static ArrayList<Integer> summonPlayerPickerUI(ArrayList<Integer> playerList, ArrayList<String> filePathsList, Scanner input)
    {
        // Variable Declaration
        boolean hasChosen = false;
        String currentInput = "";
        int currentInputAsInt = 0;
        int currentPlayerCount = 0;
        
        // Main Algorithm        
        while(hasChosen == false)
        {
            currentInput = input.nextLine();

            if(twsTools.isNumber(currentInput) == true)
            {
                currentInputAsInt = Integer.parseInt(currentInput);
            }
            
            // Enjoy the infamous stupidly long if-else chain; at least until I learn a better way at doing this
            if(twsTools.isNumberAndWithinBounds(currentInput, filePathsList) == true && playerList.contains(currentInputAsInt - 1) == false)
            {
                playerList.add(currentInputAsInt - 1);
                currentPlayerCount++;
                System.out.println("Successfully Added");
            }
            else if(currentInput.equalsIgnoreCase("clear") == true || currentInput.equalsIgnoreCase("b") == true)
            {
                playerList.clear();
                currentPlayerCount = 0;
                System.out.println("Successfully Cleared");
            }
            else if(currentInput.equalsIgnoreCase("confirm") == true || currentInput.equalsIgnoreCase("c") == true)
            {
                System.out.println("List Finalization Confirmed");
                return playerList;
            }
            else if(currentInput.equalsIgnoreCase("current") == true || currentInput.equalsIgnoreCase("d") == true)
            {
                System.out.println("Current Amount of Players: " + currentPlayerCount);
                for(int i = 0; i < playerList.size(); i++)
                {
                    System.out.println((i + 1) + ". (" + (playerList.get(i) + 1) + ") " + filePathIndexToName(playerList.get(i), filePathsList));
                }
            }
            else if(currentInput.equalsIgnoreCase("list") == true || currentInput.equalsIgnoreCase("e") == true)
            {
                printPlayerList(filePathsList);
            }
            else if(currentInput.equalsIgnoreCase("exit") == true || currentInput.equalsIgnoreCase("f") == true)
            {
                System.out.println("There will eventually be a menu to return to, but for now bye bye");
                System.exit(1); // bye bye
            }
            else if(currentInput.equalsIgnoreCase("help") == true || currentInput.equalsIgnoreCase("g") == true)
            {
                getCommandList();
            }
            else
            {
                System.out.println("Invalid Input; Do not type repeated player names nor unknown commands \nType \"help\" for the command list.");
            }
        }
        
        return playerList;
    }
    
    
    /*                          ----------------------------- ArrayList Common Games -----------------------------
     *                                      Grabs all the common games found in a file from each person
     *                                      and inserts them into a new array to return back to driver
     */
    public static ArrayList<String> getCommonGames(ArrayList<String> commonGames, ArrayList<Integer> playerList, ArrayList<String> filePathsList)
    {
        // Object Declarations
        ArrayList<String> currentCommonGames = new ArrayList<>();
        
        // Main Algorithm for File Snatching --> commonGames ArrayList
        for(int i = 0; i < playerList.size(); i++)
        {
            File currentFile = new File(filePathsList.get(playerList.get(i)));            
            currentCommonGames = twsTools.getFileArrayList(currentCommonGames, currentFile);
            
            // Go through every player's file and make a big list of all the possible games
            for(int j = 0; j < currentCommonGames.size(); j++)
            {
                if(commonGames.contains(currentCommonGames.get(j)) == false)
                {
                    commonGames.add(currentCommonGames.get(j));
                }
            }
            
            currentCommonGames.clear();
        }
                
        // File Snatching --> Compare vs. Big Game List --> Trim based on games of individual's list
        for(int i = 0; i < playerList.size(); i++)
        {
            File currentFile = new File(filePathsList.get(playerList.get(i)));            
            currentCommonGames = twsTools.getFileArrayList(currentCommonGames, currentFile);
            
            commonGames.retainAll(currentCommonGames);
            
            currentCommonGames.clear();
        }
                
        return commonGames;
    } 
    
    /*                          ----------------------------- Wheel Spinning + Flavor Text -----------------------------
     *                                          A prompt to pause and let the user decide if they want to
     *                                          activate the wheel + some flavor text for spice
     */
    public static void goSpinTheWheel(Scanner input, ArrayList<String> commonGames)
    {
        // Object Declaration
        Random rand = new Random();
        
        // Variable Declaration
        String currentInput = "";
        int currentNum = 0; 
        int passCondition = -1;
        
        // Confirmation Prompt
        System.out.println("\nSpin The Wheel?");
        
        while(passCondition == -1) // This decision block is determined by the isYesOrNo tool: For documentation on how to use its -1, 0, 1 index refer to method header
        {
            currentInput = input.nextLine();
        
            if(twsTools.isYesOrNo(currentInput) == 1)
            {
                passCondition = 1;
            }
            else if(twsTools.isYesOrNo(currentInput) == 0)
            {
                System.out.println("Cya");
                System.exit(1); // Another case of there not being a menu
            }
            else
            {
                System.out.println("Invalid Input; Only type a Yes/No response.");
            }
        }
        
        // Flavor Text
        for(int i = 0; i < 50; i++)
        {
            currentNum = rand.nextInt(commonGames.size());
            System.out.println(commonGames.get(currentNum));
        }
        
        for(int i = 0; i < 3; i++)
        {
            System.out.println(".");
        }
        
        // Final Output
        currentNum = rand.nextInt(commonGames.size());
        System.out.println("THE WHEEL COMMANDS: " + commonGames.get(currentNum) + " :BEGIN PLAYING IMMEDIATELY!!!");
    }
    
    
    /*                          ----------------------------- Helper SubMethods -----------------------------
     *                                  These methods are small use-cases only here to reduce repetition
     *                                  rather than be fully-fledged out algorithms/tools/methods
     */

    /* ----- Help Message -----
     *  Prompted when the user
     *  is inserting players
     *  into the pool
     */     
    public static void getCommandList()
    {
        System.out.println("\nCommands Available: \na. Type a number to add a player to the game pool \nb. Type \"clear\" to clear the current player pool \nc. Type \"confirm\" to confirm selection \nd. Type \"current\" to see the current player pool \ne. Type \"list\" to see the list of found players \nf. Type \"exit\" to return to the main menu (as of typing this there is no main menu it'll just boot you lmfao) \ng. Type \"help\" to see these commands again");
    }
}