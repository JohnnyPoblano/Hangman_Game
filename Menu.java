        /* Menu.java 
        * Authors: Downrightmike, Johnnypoblano, Vicksy 
        * CIS 131 
        * 
        *  
        ********************************************************************************************************************/ 
        import java.util.Scanner; 
 
        public class Menu { 
            
            // Not logged in menu 
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
                System.out.println("Please select a game option:"); 
                System.out.println("              1. Quick game"); 
                System.out.println("              2. Short game"); 
                System.out.println("              3. Medium game"); 
                System.out.println("              4. Long game"); 
                System.out.println("              5. High Scores");  
                System.out.println("              6. Sign in"); 
                System.out.println("              7. Exit program");

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
                else if(s1.equals("7")){ 
                    System.out.println("Thank you for playing Hangman!!!"); 
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
                 
                //Call highScores (Display top ten)
                if(s1.equals("5")){ 
                    ProjectFileIO_v2.displayTopTen(); // Get top ten from saved file 
                    System.out.println("Press enter to continue...");
                    String tempString = input.nextLine();
                    tempString = null;
                    menu(gameType); // Recursive method call gets menu back afterwards
                    return; // Prevents multiple instances of menu from stacking
                } 
                
                // Sign in
                if(s1.equals("6")) {
                    String username = ProjectFileIO_v2.runPlayerLogin();
                    if (!username.equals("")) {
                        menu(gameType, username);// Call overloaded logged in user menu
                        return; // Prevents multiple instances of menu from stacking
                    }
                    else {
                        menu(gameType); // Recursive method call gets menu back afterwards
                        return; // Prevents multiple instances of menu from stacking
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
     
            // Logged in menu 
            public static void menu(int[] gameType, String username){ 
 
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
                System.out.println("             Signed in as " + username + "."); 
                System.out.println("**************************************************"); 
                System.out.println("Please select a game option:"); 
                System.out.println("              1. Quick game"); 
                System.out.println("              2. Short game"); 
                System.out.println("              3. Medium game"); 
                System.out.println("              4. Long game"); 
                System.out.println("              5. High Scores");  
                System.out.println("              6. Sign out"); 
                System.out.println("              7. Exit program");

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
                else if(s1.equals("7")){ 
                    System.out.println("Thank you for playing Hangman!!!"); 
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
                 
                //Call highScores (Display top ten)
                if(s1.equals("5")){ 
                    ProjectFileIO_v2.displayTopTen(); // Get top ten from saved file 
                    System.out.println("Press enter to continue...");
                    String tempString = input.nextLine();
                    tempString = null;
                    menu(gameType, username);// Call overloaded logged in user menu
                    return; // Prevents multiple instances of menu from stacking
                } 
                
                // Sign out
                if(s1.equals("6")) {
                    menu(gameType); // Recursive method call gets menu back afterwards
                    return; // Prevents multiple instances of menu from stacking
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
            }//end of logged in menu method
     
     
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
     
       

        }