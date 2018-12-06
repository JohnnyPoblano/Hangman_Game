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
           // Menu.menu(gameType);
            Quick_Game quick = new Quick_Game(1, "");
            quick.runGame();
 
        }//end of main 
    }//end of Hangman class 
 
        