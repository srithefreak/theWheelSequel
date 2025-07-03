// This helper file is for the settings menu which contains some options along with being able to adjust players and such
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class twsSettings
{
    /*                          ----------------------------- Driver / Main Method -----------------------------
     *                                  The Central Control Module; AKA the one that tells stuff to do things
     */
    public static void settingsDriver(Scanner input, String currentInput)
    {
        // Variable Declaration
        boolean onStateSettingsMain = true;
        
        
        // Main While Loop
        while(onStateSettingsMain == true)
        {
            System.out.println("\n\n\n\n\n\n\nSettings \n\nType a letter or input: \na. General \nb. Add & Delete Players \nc. Change Player Games \nd. Adjust Game Pool \ne. Back\n");
            currentInput = input.nextLine();
            
            if(currentInput.equalsIgnoreCase("a") == true || currentInput.equalsIgnoreCase("General") == true)
            {
                settingsGeneral(input, currentInput);
            }
            else if(currentInput.equalsIgnoreCase("b") == true || currentInput.equalsIgnoreCase("Add & Delete Players") == true)
            {
                settingsAddDeletePlayers(input, currentInput);
            }
            else if(currentInput.equalsIgnoreCase("c") == true || currentInput.equalsIgnoreCase("Change Player Games") == true)
            {
                settingsPlayerGames(input, currentInput);
            }
            else if(currentInput.equalsIgnoreCase("d") == true || currentInput.equalsIgnoreCase("Adjust Game Pool") == true)
            {
                settingsGamePool(input, currentInput);
            }

            else if(currentInput.equalsIgnoreCase("e") == true || currentInput.equalsIgnoreCase("Back") == true)
            {
                onStateSettingsMain = false;
            }
        }
        
        return;
    }
    
    
    /*                          ----------------------------- General Settings -----------------------------
     *                                              Contains all general/misc. settings
     */
    public static void settingsGeneral(Scanner input, String currentInput)
    {
        // ----- Object Declaration -----
        File settingsGeneralFile = new File("settings//generalSettings.txt");
        ArrayList<String> settingsGeneralList = new ArrayList<>();
        
        
        // ----- Variable Declaration -----
        boolean onStateSettingsGeneral = true;
        String stateOfFlavorText = "";
        
        
        // ----- Main While Loop ------
        while(onStateSettingsGeneral == true)
        {
            // Load Current Settings
            settingsGeneralList = twsTools.getFileArrayList(settingsGeneralList, settingsGeneralFile);
            stateOfFlavorText = settingsGeneralList.get(0).substring(11, settingsGeneralList.get(0).length());
                        
            // General Settings UI
            System.out.println("\n\n\n\n\n\n\nSettings (General) \n\nType a letter or input to change a setting: \na. Toggle Flavor Text: [" + stateOfFlavorText + "] \nb. Back\n");
            currentInput = input.nextLine();
            
            // Decision Matrix for intaking input
            if(currentInput.equalsIgnoreCase("a") == true || currentInput.equalsIgnoreCase("Toggle Flavor Text") == true)
            {
                try
                {
                    BufferedWriter settingsGeneralFileWriter = new BufferedWriter(new FileWriter("settings//generalSettings.txt"));
                    
                    if(stateOfFlavorText.equals("Disabled") == true)
                    {
                        settingsGeneralFileWriter.write("flavorText=Enabled");
                    }
                    else
                    {
                        settingsGeneralFileWriter.write("flavorText=Disabled");
                    }
                    
                    settingsGeneralFileWriter.close();
                    settingsGeneralList.clear();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                    System.out.println("If you see this message it means the writer couldn't find the file to set your settings properly");
                }
            }
            else if(currentInput.equalsIgnoreCase("b") == true || currentInput.equalsIgnoreCase("Back") == true)
            {
                onStateSettingsGeneral = false;
            }
        }
        
        return;
    }
    
    
    /*                          ----------------------------- Adding / Deleting Players (ADP) -----------------------------
     *                                          Contains all functions regarding player file manipulation
     *                                          Player files can be found in theWheelSequel\\gameLists
     *
     */
    public static void settingsAddDeletePlayers(Scanner input, String currentInput)
    {
        // ----- Object Declaration -----
        File filePathsNavInfo = new File("fileNavInfo//filePathsInfo.txt");
        ArrayList<String> playerListNavInfo = new ArrayList<>();
        
        
        // ----- Variable Declaration -----
        
        // Main Loop
        boolean onStateADP = true;
        String mostRecentResponse = "Type any letter or input to get started. Upon deleting a person you will be unable to recover their file so be careful";
        
        // Adding Players
        boolean onStateADPAdding = false;
        String onStateADPAddingUI = "";
        
        // Deleting Players
        boolean onStateADPDeleting = false;
        String onStateADPDeletingUI = "";
        
        
        // ----- Loading Current Player List -----
        playerListNavInfo = twsTools.getFileArrayList(playerListNavInfo, filePathsNavInfo);
        
        
        // ----- Main Decision Matrix Loop -----
        while(onStateADP == true)
        {
            // ADP Settings UI
            System.out.println("\n\n\n\n\n\n\nSettings (Add and Delete Players) \n\nType a letter or input: \na. Add Player " + onStateADPAddingUI + "\nb. Delete Player " + onStateADPDeletingUI + "\nc. List Players \nd. Back\n\n[Most Recent Response]\n" + mostRecentResponse + "\n");
            currentInput = input.nextLine();
            
            // Input Validator
            if(currentInput.startsWith(" ") == true || currentInput.isEmpty() == true || currentInput.contains("_") == true)
            {
                mostRecentResponse = "Invalid input: Empty input, contains underscore, or input with space start (Don't do that)";
            }
            //
            // ----- Main Input Decision Matrix -----
            else if(currentInput.equalsIgnoreCase("a") == true || currentInput.equalsIgnoreCase("Add Player") == true)
            {
                if(onStateADPAdding == false) // Enable the Player Adding Module
                {
                    mostRecentResponse = "Adding Enabled, please type the name of the player to add; No underscores \nType \"a\" or \"Add Player\" again to Disable";
                    onStateADPAdding = true;
                    onStateADPAddingUI = "[Enabled]";
                    onStateADPDeleting = false;
                    onStateADPDeletingUI = "";
                }
                else // Disable the Game Adding Module
                {
                    mostRecentResponse = "Adding Disabled, you are now free to type anything";
                    onStateADPAdding = false;
                    onStateADPAddingUI = "";
                }                
            }
            else if(currentInput.equalsIgnoreCase("b") == true || currentInput.equalsIgnoreCase("Delete Player") == true)
            {
                if(onStateADPDeleting == false) // Enable the Player Deleting Module
                {
                    mostRecentResponse = "Deleting Enabled, type in a player name to delete that player, type \"b\" or \"Delete Player\" again to Disable. Be careful: The files are unrecoverable!";
                    onStateADPAdding = false;
                    onStateADPAddingUI = "";
                    onStateADPDeleting = true;
                    onStateADPDeletingUI = "[Enabled]";
                }
                else // Disable the Player Deleting Module
                {
                    mostRecentResponse = "Deleting Disabled, you are now free to type anything";
                    onStateADPDeleting = false;
                    onStateADPDeletingUI = "";
                }                
            }
            else if(currentInput.equalsIgnoreCase("c") == true || currentInput.equalsIgnoreCase("List Players") == true)
            {
                mostRecentResponse = "Player List:";
                
                for(int i = 0; i < playerListNavInfo.size(); i++)
                {
                    // Note: currentInput is used here as a tempVariable
                    currentInput = playerListNavInfo.get(i);
                    mostRecentResponse = mostRecentResponse + "\n" + currentInput.substring(11, currentInput.length() - 10);
                }
            }
            else if(currentInput.equalsIgnoreCase("d") == true || currentInput.equalsIgnoreCase("Back") == true)
            {
                onStateADP = false;
            }
            //
            // ----- Enable Adding / Deleting ------ 
            else if(onStateADPAdding == true)
            {
                // Sanatizing the Input for Testing (Make it match the player list arrayList since it uses navInfo to make the list)
                currentInput = "gameLists\\\\" + currentInput + "_games.txt";
            
                // Input Gate to make sure it's not already in the playerList
                if(twsTools.searchArrayListForString(playerListNavInfo, currentInput) == -1) 
                {
                    try
                    {
                        // Creating the New File
                        File newPlayerFile = new File(currentInput);
                        newPlayerFile.createNewFile();
                        
                        playerListNavInfo.add(currentInput);
                        
                        // Applying the New File
                        playerListNavInfo.sort(String.CASE_INSENSITIVE_ORDER); // This is not a homebrewed comparator and I have no idea how .sort comparators work.
                        twsTools.arrayListToFile(playerListNavInfo, "fileNavInfo\\filePathsInfo.txt");
                        
                        // Confirmation Message
                        mostRecentResponse = currentInput.substring(11, currentInput.length() - 10) + " successfully added";
                        System.out.println(playerListNavInfo);
                        
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                        System.out.println("File creation error");
                    }
                }
                else
                {
                    mostRecentResponse = "Player with that name currently exists, please type another";
                }
            }
            else if(onStateADPDeleting == true)
            {
                // Sanatizing the Input for Testing (Make it match the player list arrayList since it uses navInfo to make the list)
                currentInput = "gameLists\\\\" + currentInput + "_games.txt";
            
                // Input Gate to make sure it's not already in the playerList
                if(twsTools.searchArrayListForString(playerListNavInfo, currentInput) != -1) 
                {
                    // Deleting the file
                    File newPlayerFile = new File(currentInput);
                    newPlayerFile.delete();
                    
                    playerListNavInfo.remove(currentInput);
                    
                    // Applying deletion
                    twsTools.arrayListToFile(playerListNavInfo, "fileNavInfo\\filePathsInfo.txt");
                    
                    // Confirmation Message
                    mostRecentResponse = currentInput.substring(11, currentInput.length() - 10) + " successfully removed";
                }
                else
                {
                    mostRecentResponse = "Could not find a player with that name, please type another";
                }
            }
            //
            // ----- Help Message -----
            else
            {
                mostRecentResponse = "Type any letter or input to get started. Upon deleting a player you will be unable to recover their file so be careful";
            }
        }
        
        return;
    }
    
    
    /*                          ----------------------------- Change Player Games -----------------------------
     *                                          Adjusts what games each text file contains. 
     *                                          Player files can be found in theWheelSequel\\gameLists
     *
     */
    public static void settingsPlayerGames(Scanner input, String currentInput)
    {
        // ----- Object Declaration -----
        File filePathsNavInfo = new File("fileNavInfo//filePathsInfo.txt");
        ArrayList<String> playerListNavInfo = new ArrayList<>();
        
        
        // ----- Variable Declaration -----
        
        // Main Loop
        boolean onStatePlayerGames = true;
        String mostRecentResponse = "Type any letter or input to get started";
        
        // Selecting Players
        boolean onStateSelectPlayer = false;
        String onStateSelectPlayerUI = "";
        
        
        // ----- Load Current Playerbase -----
        playerListNavInfo = twsTools.getFileArrayList(playerListNavInfo, filePathsNavInfo);
        
        
        // ----- Main Input While Loop Decision Matrix -----
        while(onStatePlayerGames == true)
        {
            // PlayerGames Settings UI
            System.out.println("\n\n\n\n\n\n\nSettings (Change Player Games) \n\nType a letter or input: \na. Select Player " + onStateSelectPlayerUI + "\nb. List Players \nc. Back \n\n[Most Recent Response]\n" + mostRecentResponse + "\n");
            currentInput = input.nextLine();
            
            if(currentInput.equalsIgnoreCase("a") == true || currentInput.equalsIgnoreCase("Select Player") == true)
            {
                if(onStateSelectPlayer == false)
                {
                    onStateSelectPlayer = true;
                    onStateSelectPlayerUI = "[Enabled]";
                    
                    mostRecentResponse = "Player Selection Enabled: Type the number correspondant to the player you wish to modify";
                }
                else
                {
                    onStateSelectPlayer = false;
                    onStateSelectPlayerUI = "";
                    
                    mostRecentResponse = "Player Selection Disabled: You are now free to type anything";
                }
            }
            else if(currentInput.equalsIgnoreCase("b") == true || currentInput.equalsIgnoreCase("List Players") == true)
            {
                /* Stuff like this really makes me want to remake the codebase for the settings, 
                 * since I was ill-prepared and didn't integrate twsTools as much as I should have
                 * The result is stuff like this where it's a very repeated part of the program but
                 * there doesn't exist a twsTool because I cba going back to every instance of "mostRecentResponse"
                 * and integrating such a tool. It should be done one of these days though if I ever come back to this
                 * project: A better idea would be to come back and remake it in its entirety with new/more efficient knowledge
                 * rather than try to salvage it
                 */
                
                mostRecentResponse = "Player List:";
                
                for(int i = 0; i < playerListNavInfo.size(); i++)
                {
                    // Note: currentInput is used here as a tempVariable
                    currentInput = playerListNavInfo.get(i);
                    mostRecentResponse = mostRecentResponse + "\n" + (i + 1) + ". " + currentInput.substring(11, currentInput.length() - 10);
                }
            }
            else if(currentInput.equalsIgnoreCase("c") == true || currentInput.equalsIgnoreCase("Back") == true)
            {
                onStatePlayerGames = false;
            }
            //
            // ----- Enabled Selection Module -----
            else if(onStateSelectPlayer == true)
            {
                if(twsTools.isNumberAndWithinBounds(currentInput, playerListNavInfo) == true)
                {
                    settingsPlayerGameSelectedPlayer(input, currentInput, playerListNavInfo.get(Integer.parseInt(currentInput) - 1));
                }
                else
                {
                    mostRecentResponse = "Invalid input: Must be a number and correspond to a person on the list";
                }
            }
            //
            // ----- Help Message -----
            else
            {
                mostRecentResponse = "Type any letter or input to get started";
            }
        }
    }
    
    
    /*----- Player Games Submethod -----
     * A submethod for when a player has been selected for game modification
     * This entire method has been copy+pasted+modified from by
     * Game Pool method, since they're so similar this SHOULD
     * be optimized to just one method but this is literally the last
     * thing I needa do so I'm just tryna get it over with lmao
     * In future iterations stuff like this has been for the most part
     * marked in lengthy comments so you'll find instances like this easily(ish)
     */
    public static void settingsPlayerGameSelectedPlayer(Scanner input, String currentInput, String selectedPlayer)
    {
        // ----- Object Declaration -----
        File settingsGamePoolFile = new File(selectedPlayer);        
        ArrayList<String> settingsGamePoolListDefault = new ArrayList<>();
        ArrayList<String> settingsGamePoolListCurrent = new ArrayList<>();

        
        // ----- Variable Declaration -----
                
        // Main Loop
        boolean onStateGamePool = true;
        String mostRecentResponse = "Type any letter or input to get started";
        
        // Main Loop - Adding If - Branch
        boolean onStateGamePoolAdding = false;
        String onStateGamePoolAddingUI = "";
        
        // Main Loop - Deleting If - Branch
        boolean onStateGamePoolDeleting = false;
        String onStateGamePoolDeletingUI = "";
        int tempNum = 0; // Used for temporary indexing
        
        // Main Loop - Saving
        String onStateUnsavedProgress = "";
        
        
        /* ----- Load Current GamePool ------
         * This is a more optimized version of the general settings one; 
         * I've iterated by putting it outside the while loop to make it more efficient; 
         * Consider updating the general settings to match this format
         */
        settingsGamePoolListDefault = twsTools.getFileArrayList(settingsGamePoolListDefault, settingsGamePoolFile);
        settingsGamePoolListCurrent.addAll(settingsGamePoolListDefault); // I didn't know mirroring ArrayLists was this easy but good to know
        
        
        /* ----- Main UI Loop -----
         * Here is the main loop for the Game Pool UI, it's modular based on the Enabling/Disabling modules that also effect how inputs are recieved 
         */
        while(onStateGamePool == true)
        {
            System.out.println("\n\n\n\n\n\n\nSettings (" + selectedPlayer.substring(11, selectedPlayer.length() - 10) + ") \n\nType a letter or input: \na. Add Game " + onStateGamePoolAddingUI + "\nb. Delete Game " + onStateGamePoolDeletingUI + "\nc. List Games \nd. Revert Changes \ne. Save Changes " + onStateUnsavedProgress + "\nf. Back\n\n[Most Recent Response]\n" + mostRecentResponse + "\n");
            currentInput = input.nextLine();
            
            // ----- Main Input Decision Matrix -----
            if(currentInput.startsWith(" ") == true || currentInput.isEmpty() == true)
            {
                mostRecentResponse = "Empty input or input with space start (Don't do that)";
            }
            else if(currentInput.equalsIgnoreCase("a") == true || currentInput.equalsIgnoreCase("Add Game") == true)
            {
                if(onStateGamePoolAdding == false) // Enable the Game Adding Module
                {
                    mostRecentResponse = "Adding Enabled, type in a game name to add that game, type \"a\" or \"Add Game\" again to Disable";
                    onStateGamePoolAdding = true;
                    onStateGamePoolAddingUI = "[Enabled]";
                    onStateGamePoolDeleting = false;
                    onStateGamePoolDeletingUI = "";
                }
                else // Disable the Game Adding Module
                {
                    mostRecentResponse = "Adding Disabled, you are now free to type anything";
                    onStateGamePoolAdding = false;
                    onStateGamePoolAddingUI = "";
                }
            }
            else if(currentInput.equalsIgnoreCase("b") == true || currentInput.equalsIgnoreCase("Delete Game") == true)
            {
                if(onStateGamePoolDeleting == false) // Enable the Game Deleting Module
                {
                    mostRecentResponse = "Deleting Enabled, type in a game name to add that game, type \"b\" or \"Delete Game\" again to Disable";
                    onStateGamePoolAdding = false;
                    onStateGamePoolAddingUI = "";
                    onStateGamePoolDeleting = true;
                    onStateGamePoolDeletingUI = "[Enabled]";
                }
                else // Disable the Game Deleting Module
                {
                    mostRecentResponse = "Deleting Disabled, you are now free to type anything";
                    onStateGamePoolDeleting = false;
                    onStateGamePoolDeletingUI = "";
                }
            }
            else if(currentInput.equalsIgnoreCase("c") == true || currentInput.equalsIgnoreCase("List Games") == true)
            {
                mostRecentResponse = "";
                
                for(int i = 0; i < settingsGamePoolListCurrent.size(); i++)
                { 
                    if(i == settingsGamePoolListCurrent.size() - 1)
                    {
                        mostRecentResponse = mostRecentResponse + settingsGamePoolListCurrent.get(i);
                    }
                    else
                    {
                        mostRecentResponse = mostRecentResponse + settingsGamePoolListCurrent.get(i) + "\n";
                    }
                }
            }
            else if(currentInput.equalsIgnoreCase("d") == true || currentInput.equalsIgnoreCase("Revert Changes") == true)
            {
                settingsGamePoolListCurrent.clear();
                settingsGamePoolListCurrent.addAll(settingsGamePoolListDefault);
                onStateUnsavedProgress = "";
                mostRecentResponse = "Reverted all changes to previous save/default";
            }
            else if(currentInput.equalsIgnoreCase("e") == true || currentInput.equalsIgnoreCase("Save Changes") == true)
            {
                twsTools.arrayListToFile(settingsGamePoolListCurrent, selectedPlayer);
                settingsGamePoolListDefault.clear();
                settingsGamePoolListDefault.addAll(settingsGamePoolListCurrent);
                onStateUnsavedProgress = "";
                mostRecentResponse = "Successfully Saved New Default";

            }
            else if(currentInput.equalsIgnoreCase("f") == true || currentInput.equalsIgnoreCase("Back") == true)
            {
                if(onStateUnsavedProgress.equals("") == true)
                {
                    onStateGamePool = false;
                }
                else if(twsTools.askYesOrNoMasterTerminal(input, 2) == true)
                {
                    onStateGamePool = false;
                }
            } 
            //
            // ----- Enabled Adding / Deleting -----
            else if(onStateGamePoolAdding == true) // Adding Games
            {                
                if(twsTools.searchArrayListForString(settingsGamePoolListCurrent, currentInput) == -1)
                {
                    settingsGamePoolListCurrent.add(currentInput);
                    mostRecentResponse = "Added: " + currentInput;
                    onStateUnsavedProgress = "[Unsaved]";
                }
                else
                {
                    mostRecentResponse = "Player already has this game";
                }
            }
            else if(onStateGamePoolDeleting == true) // Deleting Games
            {
                tempNum = twsTools.searchArrayListForString(settingsGamePoolListCurrent, currentInput);
                
                if(tempNum != -1)
                {
                    settingsGamePoolListCurrent.remove(tempNum);
                    mostRecentResponse = "Deleted: " + currentInput;
                    onStateUnsavedProgress = "[Unsaved]";
                }
                else
                {
                    mostRecentResponse = "Player does not have this game";
                }
            }
            //
            // ----- Help Message -----
            else
            {
                mostRecentResponse = "Type any letter or input to get started";
            }
        }
    }

    
    /*                          ----------------------------- Adjusting Game Pool -----------------------------
     *                                  Contains functions that modify and change the total game pool
     *                                  Game list text file can be found in theWheelSequel\\gamePool
     *                                  One day this might have a tagging system, but by then I'd rather
     *                                  just use Steam API
     *                                  
     *                                  Despite this implementing to pre-load efficiency boost; there are
     *                                  still a lot of optimization stuff that I want to tackle later:
     *                                  - Text redundancies: See "onStateUnsavedProgress = "[Disabled]"" 
     *                                    at multiple instances
     */
    public static void settingsGamePool(Scanner input, String currentInput)
    {        
        // ----- Object Declaration -----
        File settingsGamePoolFile = new File("settings//gamePool.txt");        
        ArrayList<String> settingsGamePoolListDefault = new ArrayList<>();
        ArrayList<String> settingsGamePoolListCurrent = new ArrayList<>();
        
        
        // ----- Variable Declaration -----
                
        // Main Loop
        boolean onStateGamePool = true;
        String mostRecentResponse = "Type any letter or input to get started";
        
        // Main Loop - Adding If - Branch
        boolean onStateGamePoolAdding = false;
        String onStateGamePoolAddingUI = "";
        
        // Main Loop - Deleting If - Branch
        boolean onStateGamePoolDeleting = false;
        String onStateGamePoolDeletingUI = "";
        int tempNum = 0; // Used for temporary indexing
        
        // Main Loop - Saving
        String onStateUnsavedProgress = "";
        
        
        /* ----- Load Current GamePool ------
         * This is a more optimized version of the general settings one; 
         * I've iterated by putting it outside the while loop to make it more efficient; 
         * Consider updating the general settings to match this format
         */
        settingsGamePoolListDefault = twsTools.getFileArrayList(settingsGamePoolListDefault, settingsGamePoolFile);
        settingsGamePoolListCurrent.addAll(settingsGamePoolListDefault); // I didn't know mirroring ArrayLists was this easy but good to know
        
        
        /* ----- Main UI Loop -----
         * Here is the main loop for the Game Pool UI, it's modular based on the Enabling/Disabling modules that also effect how inputs are recieved 
         */
        while(onStateGamePool == true)
        {
            System.out.println("\n\n\n\n\n\n\nSettings (Adjust Game Pool) \n\nType a letter or input: \na. Add Game " + onStateGamePoolAddingUI + "\nb. Delete Game " + onStateGamePoolDeletingUI + "\nc. List Games \nd. Revert Changes \ne. Save Changes " + onStateUnsavedProgress + "\nf. Back\n\n[Most Recent Response]\n" + mostRecentResponse + "\n");
            currentInput = input.nextLine();
            
            // ----- Main Input Decision Matrix -----
            if(currentInput.startsWith(" ") == true || currentInput.isEmpty() == true)
            {
                mostRecentResponse = "Empty input or input with space start (Don't do that)";
            }
            else if(currentInput.equalsIgnoreCase("a") == true || currentInput.equalsIgnoreCase("Add Game") == true)
            {
                if(onStateGamePoolAdding == false) // Enable the Game Adding Module
                {
                    mostRecentResponse = "Adding Enabled, type in a game name to add that game, type \"a\" or \"Add Game\" again to Disable";
                    onStateGamePoolAdding = true;
                    onStateGamePoolAddingUI = "[Enabled]";
                    onStateGamePoolDeleting = false;
                    onStateGamePoolDeletingUI = "";
                }
                else // Disable the Game Adding Module
                {
                    mostRecentResponse = "Adding Disabled, you are now free to type anything";
                    onStateGamePoolAdding = false;
                    onStateGamePoolAddingUI = "";
                }
            }
            else if(currentInput.equalsIgnoreCase("b") == true || currentInput.equalsIgnoreCase("Delete Game") == true)
            {
                if(onStateGamePoolDeleting == false) // Enable the Game Deleting Module
                {
                    mostRecentResponse = "Deleting Enabled, type in a game name to add that game, type \"b\" or \"Delete Game\" again to Disable";
                    onStateGamePoolAdding = false;
                    onStateGamePoolAddingUI = "";
                    onStateGamePoolDeleting = true;
                    onStateGamePoolDeletingUI = "[Enabled]";
                }
                else // Disable the Game Deleting Module
                {
                    mostRecentResponse = "Deleting Disabled, you are now free to type anything";
                    onStateGamePoolDeleting = false;
                    onStateGamePoolDeletingUI = "";
                }
            }
            else if(currentInput.equalsIgnoreCase("c") == true || currentInput.equalsIgnoreCase("List Games") == true)
            {
                mostRecentResponse = "";
                
                for(int i = 0; i < settingsGamePoolListCurrent.size(); i++)
                { 
                    if(i == settingsGamePoolListCurrent.size() - 1)
                    {
                        mostRecentResponse = mostRecentResponse + settingsGamePoolListCurrent.get(i);
                    }
                    else
                    {
                        mostRecentResponse = mostRecentResponse + settingsGamePoolListCurrent.get(i) + "\n";
                    }
                }
            }
            else if(currentInput.equalsIgnoreCase("d") == true || currentInput.equalsIgnoreCase("Revert Changes") == true)
            {
                settingsGamePoolListCurrent.clear();
                settingsGamePoolListCurrent.addAll(settingsGamePoolListDefault);
                onStateUnsavedProgress = "";
                mostRecentResponse = "Reverted all changes to previous save/default";
            }
            else if(currentInput.equalsIgnoreCase("e") == true || currentInput.equalsIgnoreCase("Save Changes") == true)
            {
                twsTools.arrayListToFile(settingsGamePoolListCurrent, "settings\\gamePool.txt");
                settingsGamePoolListDefault.clear();
                settingsGamePoolListDefault.addAll(settingsGamePoolListCurrent);
                onStateUnsavedProgress = "";
                mostRecentResponse = "Successfully Saved New Default";

            }
            else if(currentInput.equalsIgnoreCase("f") == true || currentInput.equalsIgnoreCase("Back") == true)
            {
                if(onStateUnsavedProgress.equals("") == true)
                {
                    onStateGamePool = false;
                }
                else if(twsTools.askYesOrNoMasterTerminal(input, 2) == true)
                {
                    onStateGamePool = false;
                }
            } 
            //
            // ----- Enabled Adding / Deleting -----
            else if(onStateGamePoolAdding == true) // Adding Games
            {                
                if(twsTools.searchArrayListForString(settingsGamePoolListCurrent, currentInput) == -1)
                {
                    settingsGamePoolListCurrent.add(currentInput);
                    mostRecentResponse = "Added: " + currentInput;
                    onStateUnsavedProgress = "[Unsaved]";
                }
                else
                {
                    mostRecentResponse = "Game already added/in Game Pool";
                }
            }
            else if(onStateGamePoolDeleting == true) // Deleting Games
            {
                tempNum = twsTools.searchArrayListForString(settingsGamePoolListCurrent, currentInput);
                
                if(tempNum != -1)
                {
                    settingsGamePoolListCurrent.remove(tempNum);
                    mostRecentResponse = "Deleted: " + currentInput;
                    onStateUnsavedProgress = "[Unsaved]";
                }
                else
                {
                    mostRecentResponse = "Game is not in the Game Pool";
                }
            }
            //
            // ----- Help Message -----
            else
            {
                mostRecentResponse = "Type any letter or input to get started";
            }
        }
    }
}