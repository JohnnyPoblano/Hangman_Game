        /* Menu.java 
        * Authors: Downrightmike, Johnnypoblano, Vicksy 
        * CIS 131 
        * 
        *  
        ********************************************************************************************************************/ 
        import java.util.Scanner; 
 
        public class Menu { 
            
            // Not logged in menu 
            public static void menu(){ 

                String s1; 
                int s2;
                 
                Scanner input = new Scanner(System.in);
     
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
                System.out.println("              1. Short game"); 
                System.out.println("              2. Medium game"); 
                System.out.println("              3. Long game"); 
                System.out.println("              4. High Scores");  
                System.out.println("              5. Sign in / Create Account"); 
                System.out.println("              6. Exit program");
 
                s1 = String.valueOf(IR4.getIntegerBetweenLowAndHigh("Please select a game option number to play: ", 1, 6, "Please select an option by number from the available options."));

                // Get theme selection and run game
                if(s1.equals("1")){ 
                    System.out.println("Please select an theme option:"); 
                    System.out.println("              1. Base Dictionary"); 
                    System.out.println("              2. Western"); 
                    System.out.println("              3. SciFi");
                    System.out.println("              4. Fantasy");
                    s2 = IR4.getIntegerBetweenLowAndHigh("Please select a theme number to play: ", 1, 4, "Please select a theme by number from the available options.");

                    // Start game
                    Short_Game game = new Short_Game(s2, "");
                    prettyTitle(s2);
                    game.runGame();
                }  
                else if(s1.equals("2")) { 
                    System.out.println("Please select an theme option:"); 
                    System.out.println("              1. Base Dictionary"); 
                    System.out.println("              2. Western"); 
                    System.out.println("              3. SciFi");
                    System.out.println("              4. Fantasy"); 
                    s2 = IR4.getIntegerBetweenLowAndHigh("Please select a theme number to play: ", 1, 4, "Please select a theme by number from the available options.");
                       
                    // Start game
                    Medium_Game game = new Medium_Game(s2, "");
                    prettyTitle(s2);
                    game.runGame();
                }  
                else if(s1.equals("3")) { 
                    System.out.println("Please select an theme option:"); 
                    System.out.println("              1. Base Dictionary"); 
                    System.out.println("              2. Western"); 
                    System.out.println("              3. SciFi");
                    System.out.println("              4. Fantasy"); 
                    s2 = IR4.getIntegerBetweenLowAndHigh("Please select a theme number to play: ", 1, 4, "Please select a theme by number from the available options.");

                    // Start game
                    Long_Game game = new Long_Game(s2, "");
                    prettyTitle(s2);
                    game.runGame();
                }  
                else if(s1.equals("6")){ 
                    System.out.println("Thank you for playing Hangman!!!"); 
                  
                    return;
                } 
                 
                //Call highScores (Display top ten)
                if(s1.equals("4")){ 
                    ProjectFileIO_v2.displayTopTen(); // Get top ten from saved file 
                    System.out.println("Press enter to continue...");
                    String tempString = input.nextLine();
                    tempString = null;
                    menu(); // Recursive method call gets menu back afterwards
                    return; // Prevents multiple instances of menu from stacking
                } 
                
                // Sign in
                if(s1.equals("5")) {
                    String username = ProjectFileIO_v2.runPlayerLogin();
                    if (!username.equals("")) {
                        menu(username);// Call overloaded logged in user menu
                        return; // Prevents multiple instances of menu from stacking
                    }
                    else {
                        menu(); // Recursive method call gets menu back afterwards
                        return; // Prevents multiple instances of menu from stacking
                    }
                }

            }//end of menu method 
     
            // Logged in menu 
            public static void menu(String username){ 

                String s1; 
                int s2; 
                 
                Scanner input = new Scanner(System.in); 
     
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
                System.out.println("              1. Short game"); 
                System.out.println("              2. Medium game"); 
                System.out.println("              3. Long game"); 
                System.out.println("              4. High Scores");  
                System.out.println("              5. Sign out"); 
                System.out.println("              6. Exit program");

                s1 = String.valueOf(IR4.getIntegerBetweenLowAndHigh("Please select a game option number to play: ", 1, 9, "Please select a theme by number from the available options."));

                // Get theme selection and run game
                if(s1.equals("1")){ 
                    System.out.println("Please select an theme option:"); 
                    System.out.println("              1. Base Dictionary"); 
                    System.out.println("              2. Western"); 
                    System.out.println("              3. SciFi");
                    System.out.println("              4. Fantasy");
                    s2 = IR4.getIntegerBetweenLowAndHigh("Please select a theme number to play: ", 1, 4, "Please select a theme by number from the available options.");

                    // Start game
                    Short_Game game = new Short_Game(s2, username);
                    prettyTitle(s2);
                    game.runGame();
                }  
                else if(s1.equals("2")) { 
                    System.out.println("Please select an theme option:"); 
                    System.out.println("              1. Base Dictionary"); 
                    System.out.println("              2. Western"); 
                    System.out.println("              3. SciFi");
                    System.out.println("              4. Fantasy"); 
                    s2 = IR4.getIntegerBetweenLowAndHigh("Please select a theme number to play: ", 1, 4, "Please select a theme by number from the available options.");
                    
                    // Start game
                    Medium_Game game = new Medium_Game(s2, username);
                    prettyTitle(s2);
                    game.runGame();
                }  
                else if(s1.equals("3")) { 
                    System.out.println("Please select an theme option:"); 
                    System.out.println("              1. Base Dictionary"); 
                    System.out.println("              2. Western"); 
                    System.out.println("              3. SciFi");
                    System.out.println("              4. Fantasy"); 
                    s2 = IR4.getIntegerBetweenLowAndHigh("Please select a theme number to play: ", 1, 4, "Please select a theme by number from the available options.");
                    
                    // Start game
                    Long_Game game = new Long_Game(s2, username);
                    prettyTitle(s2);
                    game.runGame();
                }  
                else if(s1.equals("6")){ 
                    System.out.println("Thank you for playing Hangman!!!");  
                    return; 
                } 
                 
                //Call highScores (Display top ten)
                if(s1.equals("4")){ 
                    ProjectFileIO_v2.displayTopTen(); // Get top ten from saved file 
                    System.out.println("Press enter to continue...");
                    String tempString = input.nextLine();
                    tempString = null;
                    menu(username);// Call overloaded logged in user menu
                    return; // Prevents multiple instances of menu from stacking
                } 
                
                // Sign out
                if(s1.equals("5")) {
                    menu(); // Recursive method call gets menu back afterwards
                    return; // Prevents multiple instances of menu from stacking
                }
 
            }//end of logged in menu method 

            private static void prettyTitle(int i){
   // This will print out some pretty title bars for the game style
   String base = 
   "     __...--~~~~~-._   _.-~~~~~--...__          .-. | | | \n" +
   "    //   ENGLISH     `V'  WORDS        \\\\       |M|_|A|N|\n" +
   "   //                 |                 \\\\      |A|a|.|.|<\\ \n" +
   "  //__...--~~~~~~-._  |  _.-~~~~~~--...__\\\\     |T|r| | | \\\\  \n" +
   " //__.....----~~~~._\\ | /_.~~~~----.....__\\\\    |H|t|M|Z|  \\\\  \n" +
   " ====================\\\\|//====================  | |!| | |   \\> \n" +
   "                     `---`	\n" +
   "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ \n";

        String west = "";
        west = ".-'` |___________WESTERN___________________________________////// \n" +
        "`'-._|                                                    \\\\\\\\\\\\	 \n" +
        
        "           x        +               (')) ^v^  _           (`)_   \n" +
        ".-. _______|        A               (__)_) ,--j j-------, (__)_)  \n" +
        "|=|/     /  \\      /\\-\\	                /_.-.___.-.__/ \\		  \n" +
        "| |_____|_\"\"_|    _||\\\"\\|\\_            ,8| [_],-.[_] | oOo	\n" +
        "|_|_[X]_|____|   ~^~^~^~^          ,,,oO8|_o8_|_|_8o_|&888o,,,\n" +
        "        _       _         _____        							\n" +
        "     _-(_)-  _-(_)-   ^..^     \\9								\n" +
        "   `(___)  `(___)     (oo)_____/                 |---|---|---|---|  \n" +
        "    // \\\\   // \\\\        WW  WW		         |---|---|---|---|  \n" +
        "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ \n";

        String scifi = "";
         scifi = "   SPACE        +  .        _      .-.         .^.  \n" +
         "          |       3==({)_    .    (   \\        | |            ___ \n" +
         "+  .     -o-      _  )`\\-|      *  )   \\      |^^^| + .     ,',.('\n" +
         "      .   |      /_`' // |\\     .-'     `-----|O==|.  *    :  `'    \n" +
         ".               B'/`-'M\\_| )   /       .      |===|  +  .  :) _  ( \n" +
         "    . *        //       (     /      .        |===|   .  '  `:_)_, \n" +
         "              B'         `-'''              /.[__N].\\   \n" ;
      
        
       String fantasy = "";
        fantasy = "               />__________________________________________________ \n" +
        "[OOOOOOOOOOOOO[]________FANTASY____________________________________> \n" +
        "               \\>  \n" +
        "  | 	                 |>>>                        |>>>  \n" +
        "  |                  _  _|_  _                   _  _|_  _\n" +
        "  + \\               | |_| |_| |                 | |_| |_| | \n" +
        "  \\\\.G_.*=.         \\  .      /                 \\ .    .  /\n" +
        "   `(#'/.\\|          \\    ,  /                   \\    .  / \n" +
        "    .>' (_--.         | .   |_   _   _   _   _   _| ,   |\n" +
        " _=/d   ,^\\           |    .| |_| |_| |_| |_| |_| |  .  |  \n" +
        "~~ \\)-'   '   _---~   |     |  .   /+++++++\\    . | .   | \n" +
        "   / |                |.    | .    |+++++++| .    |   . |  \n" +
        "  '  '     ____--`~   '--~~__ .    |++++ __|----~    ~`---, \n" +
        "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n" ;
      ////////////////////////////////////////////////////////////////////////////  

      if(i == 1){
        System.out.print(base);
      }
       if(i == 2) {
        System.out.print(west);
      } 
       if(i == 3) {
        System.out.print(scifi);
       }
       if(i == 4){
        System.out.print(fantasy);
       }

                

            }

        }