/*********************************************************************************************************************
  * ProjectFileIO.java
  *********************************************************************************************************************/
import java.io.*;
import java.util.*;

public class ProjectFileIO_v2 {
    //Global Constants
    private static String FILE_NAME = "FileTest-1.txt";
    
    private static String EOF_MARKER             = "-";
    private static String PLAYER_MARKER          = "=";
    private static String GLOBAL_SETTINGS_MARKER = ">";
    private static String COMMENT_MARKER         = "*";
    
    private static String VERSION_NUMBER = "1.0";
    
    //Global Variables related to file IO 
    private static FileReader fr;
    private static BufferedReader br;
    
    private static FileWriter  fw;
    private static PrintWriter pw;
    
    //Data related to the Player objects
    private static ArrayList<Player> playerArrayList = new ArrayList<Player>();

    //================================================================================================================

    public static void main(String[] args) throws IOException {
        readFile();   
        writeFile(); 
    }
    
    //================================================================================================================
    
    public static void readFile() throws IOException {
        System.out.println("Reading File...\n");
        playerArrayList.clear();
        try {
            boolean keepGoing = true;
            
            fr = new FileReader(FILE_NAME);
            br = new BufferedReader(fr);
            
            //The Priming Read 
            String entireLine = getLine();
            
            //Loop through the File Header data ------------------------------
            while (entireLine.substring(0,1).equals(COMMENT_MARKER)){ 
                //System.out.println(entireLine);                
                entireLine = getLine();  
            }
            
            //Loop through the Global Settings -------------------------------
            if (entireLine.equals(GLOBAL_SETTINGS_MARKER)){
                keepGoing = true;
                do {
                    readAllGlobalSettings(entireLine);
                    
                    entireLine = getLine();
                    //Look for last line of Global Settings
                    if (entireLine.substring(0,1).equals(GLOBAL_SETTINGS_MARKER)){
                        keepGoing = false;
                    }
                } while (keepGoing);
            }
            
            //Loop through the Player data ------------------------------------
            keepGoing = true;
            do {
                if (entireLine.substring(0,1).equals(PLAYER_MARKER)) {
                    readPlayerData(entireLine);
                }
                
                entireLine = getLine();
                //Look for last line in the file
                if (entireLine.substring(0,1).equals(EOF_MARKER)){
                    keepGoing = false;
                }
            } while (keepGoing);
            
            br.close();       
        }
        catch (FileNotFoundException e) {
            System.out.println(FILE_NAME + " not found. Creating new file with sample player data.");
            writeNewPlayer("Test", "pw123", 0, 0);
                
            writeFile();
            //readFile();
        }
        catch (EOFException e) {
            System.out.println("Unexpected End of File found");
        }
        catch (Exception e) {
            System.out.println("Unexpected Exception found");
        }
    }
    
    //ADJUST AS NECESSARY!
    private static void readAllGlobalSettings(String entireLine) {
        //Add a file IO read of all the global settings for the game. 1 for each line        
        String setting1 = getLine();
        String setting2 = getLine();     
    }
    
    //ADJUST AS NECESSARY!
    //In this example, the player has 3 pieces of data. You will change this to suite your game. 
    private static void readPlayerData(String entireLine){
        //Get 1st line. 
        String name = getLine();
        
        //Get 2nd line. 
        String password = getLine();
        
        //Get 3rd line
        String highScoreString = getLine();
        
        //Get 4th line
        String numberOfTimesPlayedString = getLine();
        
        writeNewPlayer(name, password, Integer.parseInt(highScoreString), Integer.parseInt(numberOfTimesPlayedString));
    }
    
    //ADJUST AS NECESSARY!
    private static void writeNewPlayer(String name, String password, int highScore, int numberOfTimesPlayed){
        Player playerNew = new Player(name, password, highScore, numberOfTimesPlayed);
        playerArrayList.add(playerNew);
    }
    
    //----------------------------------------------------------------------------------------------------------------
    
    private static String getLine(){
        String entireLine ="";
        try {
            entireLine = br.readLine();
            System.out.println(entireLine);  
        }
        catch (FileNotFoundException e) {
            System.out.println(FILE_NAME + " not found. Creating new file.");
        }
        catch (EOFException e) {
            System.out.println("Unexpected End of File found");
        }
         catch (Exception e) {
            System.out.println("Unexpected Exception found");
        }
        return entireLine;        
    }
    
    //================================================================================================================
    
    public static void writeFile() throws IOException {
        System.out.println("\nWriting File...");
        
        fw = new FileWriter(FILE_NAME);
        pw = new PrintWriter(fw);
        
        writeHeaderLines();
        writeGlobalSettingsLines();
        writePlayerLines();
        writeEOFline();
        
        pw.close();
        System.out.println("File written successfully.");
    }
    
    //ADJUST AS NECESSARY!
    private static void writeHeaderLines(){
        pw.println("***********************************");
        pw.println("* My Game " + getVersionNumber());
        pw.println("* Authors: ...");
        pw.println("* Add as many lines of comments as you want...");
        pw.println("***********************************");
        pw.flush();
    }
    
    //ADJUST AS NECESSARY!
    private static void writeGlobalSettingsLines(){
        String settingsMarker = GLOBAL_SETTINGS_MARKER + GLOBAL_SETTINGS_MARKER + GLOBAL_SETTINGS_MARKER;
        pw.println(settingsMarker + " Global Settings " + settingsMarker);
        pw.println("The 1st setting would go here...");
        pw.println("The 2nd setting would go here...");
        pw.println(settingsMarker + " End of Global Settings " + settingsMarker);
        pw.flush();
    }

    //ADJUST AS NECESSARY!
    private static void writePlayerLines(){
        if (playerArrayList.size() == 0) {
            System.out.println("The playerArrayList is empty!");
            return;
        }
        
        for (int i = 0; i < playerArrayList.size(); i++){
            String playerMarker = PLAYER_MARKER + PLAYER_MARKER + PLAYER_MARKER + PLAYER_MARKER + PLAYER_MARKER;
            pw.println(playerMarker + " Player#" + i + " " + playerMarker);
            pw.println(playerArrayList.get(i).getName());
            pw.println(playerArrayList.get(i).getPassword());
            pw.println(playerArrayList.get(i).getHighScore());
            pw.println(playerArrayList.get(i).getNumberOfTimesPlayed());
            pw.flush();
        }
    }    
    
    private static void writeEOFline() {
        pw.println(EOF_MARKER + EOF_MARKER + EOF_MARKER + " End of File " + EOF_MARKER + EOF_MARKER + EOF_MARKER);
    }
    
    //================================================================================================================
    
    public static String getVersionNumber() {
        return VERSION_NUMBER;
    }    
    
    public static ArrayList getPlayerArrayList(){
        return playerArrayList;
    }
    
    public static void setPlayerArrayList(ArrayList<Player> newPlayerArrayList){
        playerArrayList = newPlayerArrayList;
    }
    
    //Returns a Player object
    public static Player getPlayer(String name, String password){
       for (int i = 0; i < playerArrayList.size(); i++){
           if (playerArrayList.get(i).getName().equals(name)
            && playerArrayList.get(i).getPassword().equals(password))
           {
               return playerArrayList.get(i);
           }
       }
       return null; //player was not found
    }
    
    //Finds the specific Player object, deletes it, and adds the new Player object
    public static void updatePlayer(Player newPlayer){
        for (int i = 0; i < playerArrayList.size(); i++){
           if (playerArrayList.get(i).getName().equals(newPlayer.getName())
            && playerArrayList.get(i).getPassword().equals(newPlayer.getPassword()))
           {
               playerArrayList.remove(i);
               playerArrayList.add(newPlayer); 
               return;
           }
       }
        System.out.println("Error in updatePlayer. Player not found.");
        System.exit(-1);
    }    
    
    //Adds the new Player object if there is no duplicate name and password 
    public static boolean addNewPlayer(Player newPlayer){
        for (int i = 0; i < playerArrayList.size(); i++){
           if (playerArrayList.get(i).getName().equals(newPlayer.getName())
            && playerArrayList.get(i).getPassword().equals(newPlayer.getPassword()))
           {
               return false;  //indicates the player could not be added due to a duplcate player name and password. 
           }
       }
        playerArrayList.add(newPlayer); 
        return true; //the player was added successfully. 
    }    
}