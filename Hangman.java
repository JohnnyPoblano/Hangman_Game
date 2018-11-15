        /* Hangman.java 
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
            String s1 = "0"; //Setting to 1 as the default so a player can just hit enter to play 
            String s2 = "0"; 
            int s_1 = 0;// Setting up temporary ints to be used to get user input with IR4 
            int s_2 = 0; 
             
            Scanner input = new Scanner(System.in);// Should replace with IR4 
 
            System.out.println(" _                                             "); 
            System.out.println("| |                                            "); 
            System.out.println("| |__   __ _ _ __   __ _ _ __ ___   __ _ _ __  "); 
            System.out.println("| '_ \\ / _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\ "); 
            System.out.println("| | | | (_| | | | | (_| | | | | | | (_| | | | |"); 
            System.out.println("|_| |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_|"); 
            System.out.println("                    __/ |                      "); 
            System.out.println("                   |___/                       "); 
            System.out.println("**************************************************"); 
            System.out.println("             Welcome to Hangman "); 
            System.out.println("**************************************************"); 
            System.out.println("Please select an game option:"); 
            System.out.println("              1. Quick game"); 
            System.out.println("              2. Short game"); 
            System.out.println("              3. Medium game"); 
            System.out.println("              4. Long game"); 
            System.out.println("              5. High Scores"); 
            System.out.println("              6. Save File"); 
            System.out.println("              7. Sign in"); 
            System.out.println("              9. Exit program"); 
            s_1 = IR4.getIntegerBetweenLowAndHigh("Please select a game option number to play: ", 1, 9, "Please select a theme by number from the available options."); 
            s1 = String.valueOf(s_1); 
            //skip the next section if it is quick game 
            if(s1.equals("1")){ 
                s2 = "1"; 
            }  
            else if(s1.equals("5")) { 
                    s2 = "0";             
            }  
            else if(s1.equals("6")) { 
                    s2 = "0";             
            }  
            else if(s1.equals("7")) { 
                    s2 = "0";             
            }  
            else if(s1.equals("9")){ 
                System.out.println("Thank you for playing Hangman!!!!!"); 
                System.exit(1); 
                return; 
            } 
            else { 
            System.out.println("Please select an theme option:"); 
            System.out.println("              1. Base Dictionary"); 
            System.out.println("              2. Western"); 
            System.out.println("              3. SciFi"); 
            s_2 = IR4.getIntegerBetweenLowAndHigh("Please select a theme number to play: ", 1, 3, "Please select a theme by number from the available options."); 
            s2 = String.valueOf(s_2); 
        } 
             
            //Call highScores 
            if(s1.equals("5")){ 
                ///////////////////Display Top Ten///////////////////////////////////////////////////////////////// 
                ProjectFileIO_v2.displayTopTen(); //get top ten from saved file 
                String tempString = IR4.getString("Press enter to continue...");
                tempString = null;
                menu(gameType);
            } 
            // Save File 
            if(s1.equals("6")){ 
                //saveFile();//my stub method 
                //Get username 
                String username = IR4.getString("Please enter your name: "); 
                //Get Password 
                String password = IR4.getString("Please enter your password: "); 
                //Pass to FileIO 
                //Here I'm going to try to verify if the user exists and if they dosay welcome 
                //If not add player to the arraylist 
 
                //@Johnny: The getPlayer and addNewPlayer both want a password, but in the runFileIO, there is prompt for user to give password. 
                if(//ProjectFileIO_v2.getPlayer(username, password) 
                true){ 
                    System.out.println("Signed in as " + username + "."); 
                    System.out.println("Welcome back " + username + "!"); 
 
                }else if(//ProjectFileIO_v2.addNewPlayer(username, password) 
                false){ 
                    System.out.println("Signed in as " + username + "."); 
                    System.out.println("Welcome " + username + "!"); 
                } 
 
            } 
            else{ 
            //set the game type into the array 
            gameType[0] = Integer.parseInt(s1);  
            gameType[1] = Integer.parseInt(s2); 
 
            // Verify the choices made 
           // System.out .println("gameType[0] " + gameType[0] ); 
           // System.out .println("gameType[1] " + gameType[1] ); 
            switch(gameType[0]){ 
                case 1: System.out.println("Quick Game"); break; 
                case 2: System.out.println("Short Game"); break; 
                case 3: System.out.println("Medium Game"); break; 
                case 4: System.out.println("Long Game"); break; 
                case 5: System.out.println("High Score"); break; 
                case 6: System.out.println("Save File"); break; 
                case 7: System.out.println("Sign In"); break; 
                case 8: System.out.println(""); break; 
                case 9: System.out.println("Exit"); break; 
            } 
 
            switch(gameType[1]){ 
                case 0: System.out.println("System Option Selected, no game type selected."); break; 
                case 1: System.out.println("Theme: Base Dictionary"); break; 
                case 2: System.out.println("Theme: Western"); break; 
                case 3: System.out.println("Theme: SciFi"); break; 
            } 
            }//end of choices check 
        }//end of menu method 
 
 
 
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