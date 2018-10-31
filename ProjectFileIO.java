/*********************************************************************************************************************
  * ProjectFileIO.java
  *********************************************************************************************************************/
import java.io.*;

public class ProjectFileIO {
    //Global Constants
    private static String FILE_NAME = "PlayerData.txt";
    
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
    private static int MAX_PLAYERS = 100;
    private static int playerCount = 0;
    private static Player playerArray [] = new Player[MAX_PLAYERS];

    //================================================================================================================

    public static void main(String[] args) throws IOException {
        readFile();   
        //writeFile();
        String name = playerArray[0].getName();
        int highScore = playerArray[0].getHighScore();
        System.out.println("Player name: " + name);
        System.out.println("Player high score: " + highScore);
    }
    
    //================================================================================================================
    
    public static void readFile() throws IOException {
        System.out.println("Reading File...\n");
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
            System.out.println(FILE_NAME + " not found. Creating new file.");
            writeNewPlayer(0, "Test", 0, 0);
                
            writeFile();
            readFile();
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
        
        //Get 2nd line
        String highScoreString = getLine();
        
        //Get 3rd line
        String numberOfTimesPlayedString = getLine();
        
        writeNewPlayer(playerCount, name, Integer.parseInt(highScoreString), Integer.parseInt(numberOfTimesPlayedString));
        playerCount++;
    }
    
    //ADJUST AS NECESSARY!
    private static void writeNewPlayer(int index, String name, int highScore, int numberOfTimesPlayed){
        Player playerNew = new Player(name, highScore, numberOfTimesPlayed);
        playerArray[index] = playerNew;     
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
        pw.println("*****************************************************");
        pw.println("* Honest Hangman " + getVersionNumber());
        pw.println("* Authors: Michael Bennett, John Gumm, Victoria Isles");
        pw.println("* Add as many lines of comments as you want...");
        pw.println("*****************************************************");
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
        if (playerArray [0] == null) {
            System.out.println("The playerArray is empty!");
            return;
        }
        
        for (int i = 0; i < MAX_PLAYERS; i++){
            if (playerArray[i] != null){
                String playerMarker = PLAYER_MARKER + PLAYER_MARKER + PLAYER_MARKER + PLAYER_MARKER + PLAYER_MARKER;
                pw.println(playerMarker + " Player#" + i + " " + playerMarker);
                pw.println(playerArray[i].getName());
                pw.println(playerArray[i].getHighScore());
                pw.println(playerArray[i].getNumberOfTimesPlayed());
                pw.flush();
            }
        }
    }    
    
    private static void writeEOFline() {
        pw.println(EOF_MARKER + EOF_MARKER + EOF_MARKER + " End of File " + EOF_MARKER + EOF_MARKER + EOF_MARKER);
    }
    
    //================================================================================================================
    
    public static String getVersionNumber() {
        return VERSION_NUMBER;
    }    
    
    public static Player[] getPlayerArray(){
        return playerArray;
    }
    
    public static void setPlayerArray(Player [] newPlayerArray){
        playerArray = newPlayerArray;
    }
}