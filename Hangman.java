        /********************************************************************************************************************
        * Hangman.java
        * Authors: Downrightmike, Johnnypoblano, Vicksy
        * CIS 131
        * 
        * 
        ********************************************************************************************************************/
        import java.util.Scanner;

        public class Hangman {
        private static Scanner keyboard = new Scanner(System.in);
        
        public static void main(String[] args) { 
            //Create an array to hold the game type choices
            int[] gameType = new int[2];// putting this in main so that it can be accessed outside of the menu
            menu(gameType);

        }//end of main

        public static void menu(int[] gameType){
            String s1 = ""; //Setting to 1 as the default so a player can just hit enter to play
            String s2 = "";
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
            s1 = input.nextLine();// Should replace with IR4 and require a input

            //skip the next section if it is quick game
            if(s1.equals("1")){
                s2 = "1";
            }else {
            System.out.println("Please select an theme option:");
            System.out.println("       1. Theme 1");
            System.out.println("       2. Theme 2");
            System.out.println("       3. Theme 3");
            s2 = input.nextLine();// Should replace with IR4
            }
            
            //set the game type into the array
            gameType[0] = Integer.parseInt(s1); 
            gameType[1] = Integer.parseInt(s2);

            // Verify the choices made
            System.out .println("gameType[0] " + gameType[0] );
            System.out .println("gameType[1] " + gameType[1] );

        }//end of menu method

    }//end of Hangman class
