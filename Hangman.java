        /********************************************************************************************************************
        * Hangman.java
        * Authors: Downrightmike, Johnnypoblano, Vicksy
        * CIS 131
        * TODO: Ask for Name and Password for high scores.
        * 
        ********************************************************************************************************************/
        import java.util.Scanner;

        public class Hangman {
        private static Scanner keyboard = new Scanner(System.in);
        public static String[][] tempScores = new String[3][5];
        public static String[] playerScores = new String[3];
        public static void main(String[] args) { 
            //Create an array to hold the game type choices
            int[] gameType = new int[2];// putting this in main so that it can be accessed outside of the menu
           
            menu(gameType);

        }//end of main

        public static void menu(int[] gameType){

            //need a loop to play another round.
            String playerName = "";
            String s1 = ""; //Setting to 1 as the default so a player can just hit enter to play
            String s2 = "";
            int s_1 = 0;// Setting up temporary ints to be used to get user input with IR4
            int s_2 = 0;
            
            Scanner input = new Scanner(System.in);// Should replace with IR4

            System.out.println("********************************");
            System.out.println("      Welcome to Hangman ");
            System.out.println("********************************");
            System.out.println("");
            System.out.println("Please select an game option:");
            System.out.println("       1. Quick game");
            System.out.println("       2. Short game");
            System.out.println("       3. Medium game");
            System.out.println("       4. Long game");
            System.out.println("       5. High Scores");
            System.out.println("       6. Save File");
            s_1 = IR4.getIntegerBetweenLowAndHigh("Please select a game option number to play: ", 1, 5, "Please select a theme by number from the available options.");
            s1 = String.valueOf(s_1);
            //skip the next section if it is quick game
            if(s1.equals("1")){
                s2 = "1";
            } 
            else if(s1.equals("5")) {
                    s2 = "1";            
            } 
            else if(s1.equals("6")) {
                    s2 = "0";            
            } 
            else {
            System.out.println("Please select an theme option:");
            System.out.println("       1. Base Dictionary");
            System.out.println("       2. Western");
            System.out.println("       3. SciFi");
            s_2 = IR4.getIntegerBetweenLowAndHigh("Please select a theme number to play: ", 1, 3, "Please select a theme by number from the available options.");
            s2 = String.valueOf(s_2);
        }
            
            //Call highScores
            if(s1.equals("5")){
                playerName = IR4.getString("Please enter your name: ");
                highScores(tempScores, playerName);
            }
            if(s1.equals("6")){
                saveFile();
            }
            else{
            //set the game type into the array
            gameType[0] = Integer.parseInt(s1); 
            gameType[1] = Integer.parseInt(s2);

            // Verify the choices made
            System.out .println("gameType[0] " + gameType[0] );
            System.out .println("gameType[1] " + gameType[1] );
            }//end of highScores check
        }//end of menu method

        public static void highScores(String[][] tempScores, String playerName){//stub
            fillTempScores(tempScores, playerName);
            //Using player name 
            System.out .println("Welcome to High Scores " + playerName + "!\n" );
            for(int i = 0; i<tempScores.length; i++){
                for(int j = 0; j<tempScores[1].length; j++){
                    if(i == 0){                
                       System.out.println( tempScores[0][j]); //print a name
                        } else {
                            System.out.println(tempScores[i][j]); //print a score
                        } } }
        }//end of highScores

        public static void fillTempScores(String[][] tempScores, String playerName){
            for(int i = 0; i<tempScores.length; i++){
            for(int j = 0; j<tempScores[1].length; j++){
                if(i == 0){                
                tempScores[0][j] = playerName; //set a name
                } else {
                tempScores[i][j] = "30"; //set a score
                } } }
        } //end of fillTempScores
        public static void saveFile(){ //Stub for save file options.
            System.out.println("Save File Accessed!");
        }

        public static String getLetters(){//Stub to get the user's guess
            String letterEntered = "";
            letterEntered = IR4.getString("Please enter your guesss: ");
            return letterEntered;
        }//end of get letters

        public static void printPlayerScore(){
            //stub
        }

    }//end of Hangman class
