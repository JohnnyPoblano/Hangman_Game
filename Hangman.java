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
        public static void main(String[] args) { 
            //Create an array to hold the game type choices
            int[] gameType = new int[2];// putting this in main so that it can be accessed outside of the menu
           
            menu(gameType);

        }//end of main

        public static void menu(int[] gameType){

            //need a loop to play another round.
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
            System.out.println("       5. High Scores");
            s1 = input.nextLine();// Should replace with IR4 and require a input

            //skip the next section if it is quick game
            if(s1.equals("1")){
                s2 = "1";
            } 
            else if(s1.equals("5")) {
                    s2 = "1";            
            } 
            else {
            System.out.println("Please select an theme option:");
            System.out.println("       1. Theme 1");
            System.out.println("       2. Theme 2");
            System.out.println("       3. Theme 3");
            s2 = input.nextLine();// Should replace with IR4
            }
            
            //Call highScores
            if(s1.equals("5")){
                highScores(tempScores);
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

        public static void highScores(String[][] tempScores){//stub
            fillTempScores(tempScores);
            System.out .println("High Scores!" );
            for(int i = 0; i<tempScores.length; i++){
                for(int j = 0; j<tempScores[1].length; j++){
                    if(i == 0){                
                       System.out.println( tempScores[0][j]); //print a name
                        } else {
                            System.out.println(tempScores[i][j]); //print a score
                        } } }
        }//end of highScores

        public static void fillTempScores(String[][] tempScores){
            for(int i = 0; i<tempScores.length; i++){
            for(int j = 0; j<tempScores[1].length; j++){
                if(i == 0){                
                tempScores[0][j] = "Mike"; //set a name
                } else {
                tempScores[i][j] = "30"; //set a score
                } } }
        } //end of fillTempScores

    }//end of Hangman class
