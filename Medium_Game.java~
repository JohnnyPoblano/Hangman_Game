import java.util.*;

public class Medium_Game{
  //For Medium game mode: Words between 8 and 10 letters long. 
  //The user has until one turn after the head, neck, shoulders, body, left and right arm, 
  //left and right leg are added to guess the word. That is 9 chances. 
 

  //Constants////////////////////////////////////////////////////////////////////////////////////////
  //Scoring Constants
  final static int WON = 50;
  final static int LOST = 15;
  final static int LESS_THAN_4 = 50;
  final static int LESS_THAN_6 = 25;
  final static int LESS_THAN_8 = 10;
  
  final static int CORRECT_CONSONANT = 10;
  final static int CORRECT_VOWEL = 15;
  final static int CORRECT_SPECIAL = 30;
  
  //Guessing Constants
  final static int NUMBER_OF_GUESSES = 7;
  final static int GUESSES_MINUS_1 = 6;
  final static int GUESSES_MINUS_2 = 5;
  final static int GUESSES_MINUS_3 = 4;
  final static int GUESSES_MINUS_4 = 3;
  final static int GUESSES_MINUS_5 = 2;
  final static int GUESSES_MINUS_6 = 1;
  final static int ZERO_GUESSES = 0;
  
  //Word Length Constants
  final static int WORD_LENGTH_MIN = 2;
  final static int WORD_LENGTH_MAX = 4;
  
  //Hangman Display Constants/////////////////////////////////////////////////////////////////////////
  String[][] hangmanDisplay = {
      {" ", " ", " ", " ", " ", "_", "_", "_", "_", " "},
      {" ", " ", " ", " ", "|", " ", " ", " ", "|", " "},
      {" ", " ", " ", " ", "|", " ", " ", " ", "^", " "},
      {" ", " ", " ", " ", "|", " ", " ", " ", " ", " "},
      {" ", " ", " ", " ", "|", " ", " ", " ", " ", " "},
      {" ", " ", " ", " ", "|", " ", " ", " ", " ", " "},
      {" ", " ", " ", " ", "|", " ", " ", " ", " ", " "},
      {" ", " ", " ", " ", "|", " ", " ", " ", " ", " "},
      {"_", "_", "_", "_", "|", "_", "_", "_", "_", "_"},
    };
  
  String[] hangman1 = {" ", " ", " ", " ", "|", " ", " ", " ", "O", " "};//will switch with the 3rd index row
  String[] hangman2 = {" ", " ", " ", " ", "|", " ", " ", " ", "|", " "};//will switch with the 4th index row
  String[] hangman3 = {" ", " ", " ", " ", "|", " ", " ", "/", "|", " "};//will switch with the 4th index row
  String[] hangman4 = {" ", " ", " ", " ", "|", " ", " ", "/", "|", "\\"};//will switch with the 4th index row
  String[] hangman5 = {" ", " ", " ", " ", "|", " ", " ", "/ ", " ", " "};//will switch with the 5th index row
  String[] hangman6 = {" ", " ", " ", " ", "|", " ", " ", "/ ", "\\", ""};//will switch with the 5th index row
   
  //Theme Array, char's guessed Array, consonant array, vowel array, special letters array.
  String[] vowelArray = {"A", "E", "I", "O", "U"};
  String[] consonantArray = {"B", "C", "D", "F", "G", "H", "I", "J", "K", "L", "M", "N", "P", "R", "S", "T", "V", "W"};
  String[] specialLettersArray = {"Q", "X", "Y", "Z"};
  String[] guessedArray = new String[NUMBER_OF_GUESSES];
  String[] validLetters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
  //String theme = {}; This might be an ArrayList actually. Just call the appropriate ArrayList

  //fields////////////////////////////////////////////////////////////////////////////////////////////
  private boolean won;
  private boolean correctGuess;
  private boolean correctVowel;
  private boolean correctConsonant;
  private boolean correctSpecial;
  private int score;
  // private int theme;
  private int guessChances;
  private String guess;
  private String userName;
  private boolean complete;
  private int size;
  
  private boolean filledIndex0 = false;
  private boolean filledIndex1 = false;
  private boolean filledIndex2 = false;
  private boolean filledIndex3 = false;
  private boolean filledIndex4 = false;
  private boolean filledIndex5 = false;
  private boolean filledIndex6 = false;
  
  private String index0; //These indexes will be how the AI checks for words 
  private String index1; //with certain letters in certain indexes of the word. 
  private String index2;
  private String index3;
  private String index4;
  private String index5;
  private String index6;
  
  //The word arrays are down heret
  String[] wordArray = new String[size];//This is where letters will be stored when the computer can no longer find words without them.
  private String word;
 
                              
  
  //default constructor///////////////////////////////////////////////////////////////////////////////
  Medium_Game(int t, String name){
    won = false;
    correctGuess = false;
    correctVowel = false;
    correctConsonant = false;
    correctSpecial = false;
    score = 0;
   // theme = 0;
    guessChances = NUMBER_OF_GUESSES;//will minus down with every guess
    guess = " ";
    userName = name;
    complete = false;   
    //size = IR4.getRandomNumber(WORD_LENGTH_MIN, WORD_LENGTH_MAX);
    size = 5;
    initializeWordArray(wordArray);
  }//end default constructor
  
  //main//////////////////////////////////////////////////////////////////////////////////////////////
  public void main(String[] args){  
    //Get the appropriate theme read into an array for the AI.
    
    //Intro to game mode
    displayGameIntro();

    
    //while(guessChances < 0 && !won){
      //displayHangman();  displayChances();
      displayHangman();
      /* This stuff is blocked off so that we can work out the main menu
      displayWord();
      displayChances();
      //getGuess();
      
      setGuess(); //has validateGuess inside, dependent on guessedArray
      //put guess into guessArray
      guessedArray[guessChances] = guess;
      //theHangman();//this is the AI control method. Will tell the computer if the guess is correct or not.

      if(correctGuess){
        checkGuess();//this checks for consonant/vowel/special letter status
      }
      //calculateScore();
      calculateScore(); //this method uses the four booleans (correct, vowel, consonant, special) to calculate score
      //updateHangman();
      updateHangman();//This updates the Hang Man display
      checkWord(); //This will check to see if the word is complete or not. If so, it will turn won to true.
      if(!correctGuess){
        guessChances--;
      }//This should make it so the guessChances only goes down on incorrect guesses. 
    }//end guessChances while loop
    
    //Repeat third step through seventh step until WON or LOST
    
    //calculateScore(); this one is dependent on won status
    calculateFinalScore();
    //displayWon(); or displayLost(); These will display the user's final score too.
    if(won){ displayWon();}
    else{displayLost();
    
    //Save score and other stats. 
    //return to main menu.
    
    */
  }//end main
    
  //getters and setters///////////////////////////////////////////////////////////////////////////////
  
    public void setGuess(){
      String g = IR4.getString("Please enter your guess.");
      g = g.toUpperCase();
      while(validGuess(g)){
        System.err.println("That is not a valid guess. Please enter ONE letter.");
        g = IR4.getString("Please enter your guess.");
      }
      
      //turn g into a character
      guess = g;
    }//end setGuess
    
    private boolean validGuess(String g){
      //true = invalid!
      if(g.length() > 1){ return true;}
      else{return false;}             
    }//end validGuess
    
    public String getGuess(){
      return guess;
    }//end getGuess
    
  //methods///////////////////////////////////////////////////////////////////////////////////////////
    //DISPLAY METHODS ------------------------------------------------------------------
    private void displayGameIntro(){
      System.out.println("*******************************************************************");
      System.out.println("       Welcome to the Quick Game Mode of The Honest Hangman!");
      System.out.println("   The rules are simple: When prompted, guess a single letter.");
      System.out.println("    You have "+NUMBER_OF_GUESSES+" chances to guess the word.");
      System.out.println("              Goal: Don't let the hangman hang.");
      System.out.println("*******************************************************************\n");
    }//end displayShortGameIntro
    
    private void displayHangman(){
      for(int r = 0; r<hangmanDisplay.length;r++){
        for(int c = 0; c<hangmanDisplay[0].length;c++){
          System.out.print(hangmanDisplay[r][c]);
        }
        System.out.println();
      }
    }//end displayHangMan
    
    private void displayWord(){
      for(int i = 0; i < wordArray.length; i++){
        System.out.print(" " + wordArray[i]);
      }
    }//end displayWord
    
    private void displayChances(){
      System.out.println("Total Guesses Left:" + guessChances);
    }//end displayChances
    
    private void displayWon(){
      System.out.println("Congratulations! You won!");
      System.out.println("You scored a total of " + score + " points.");
    }//end displayWon
    
    private void displayLost(){
      System.out.println("And the hangman is hung. You have lost.");
      System.out.println("You scored a total of " + score + " points.");
    }//end displayLost
    
    //THE HANGMAN ----------------------------------------------------------------------
    private void theHangman(){
      //checks userGuess and all previous guesses against words in the array list.
      //Tells user their guess is WRONG if:
      //           - The computer finds a word that does not contain any of the guesses. 
        //Tells the user their guess is RIGHT if:
      //           - The computer cannot find a word that does not contain any of the guesses.
      //   -> When this occures:
      //       - The computer will take the most recent user guess and locate a word containing it.
      //           -> The computer will set the index of the guess from that word into a variable
      //           -> If that index has already been filled in from a previous guess, it will substring the word to find the next index of the character in the word.
      //       - It will take the index that the letter shows up that word and set that index to that letter.
      //       - This will allow the program to then check all words with that letter in that index, against new guesses.
      //       - The computer will reset the guessedArray every time a guess is correct. 
      //       - If the guess is labled as correct, it will then activate checkGuess, for scoring reasons. 
    }//end theHangMan
    
    
        
    //CHECK GUESS ----------------------------------------------------------------------
    public void checkGuess(){
      for(int i = 0; i<vowelArray.length;i++){
        if(guess.equals(vowelArray[i])){correctVowel = true;}
      }
      for(int i = 0; i<consonantArray.length;i++){
        if(guess.equals(vowelArray[i])){correctConsonant = true;}
      }
      for(int i = 0; i<specialLettersArray.length;i++){
        if(guess.equals(specialLettersArray[i])){ correctSpecial = true;}
      }
    }//end checkGuess 
    
    public void checkWord(){
      
      
    }//end checkWord
    
    //CALCULATE SCORE ------------------------------------------------------------------
    private void calculateScore(){
      if(correctVowel){ score = score + CORRECT_VOWEL;}
      
      if(correctConsonant){ score = score + CORRECT_CONSONANT;}
      
      if(correctSpecial){ score = score + CORRECT_SPECIAL;}
      
    }//end calculateScore
    
    private void calculateFinalScore(){
      if(won){ score = score + WON;}
      else{ score = score + LOST;}
    }//end calculateFinalScore
    
    //UPDATING DISPLAYS ----------------------------------------------------------------
    private void updateHangman(){
      if(!correctGuess){
        
       if(guessChances == GUESSES_MINUS_1){
           //read hangman1 into the 3rd index row of hangmanDisplay
           int r = 3;
           for(int c = 0; c < hangmanDisplay[0].length; r++){
               hangmanDisplay[r][c] = hangman1[c];
           }
         }
         if(guessChances == GUESSES_MINUS_2){
           //read hangman2 into the 4th index row of hangmanDisplay
           int r = 4;
           for(int c = 0; c < hangmanDisplay[0].length; r++){
               hangmanDisplay[r][c] = hangman2[c];
           }
         }
         if(guessChances == GUESSES_MINUS_3){
           //read hangman3 into the 4th index row of hangmanDisplay
           int r = 4;
           for(int c = 0; c < hangmanDisplay[0].length; r++){
               hangmanDisplay[r][c] = hangman3[c];
           }
         }
         if(guessChances == GUESSES_MINUS_4){
           //read hangmand4 into the 4th index row of hangmanDisplay
           int r = 4;
           for(int c = 0; c < hangmanDisplay[0].length; r++){
               hangmanDisplay[r][c] = hangman4[c];
           }
         }
         if(guessChances == GUESSES_MINUS_5){
           //read hangman5 into the 5th index row of hangmanDisplay
           int r = 5;
           for(int c = 0; c < hangmanDisplay[0].length; r++){
               hangmanDisplay[r][c] = hangman5[c];
           }
         }
         if(guessChances == GUESSES_MINUS_6){
           //read hangman6 into the 5th index row of hangmanDisplay
           int r = 5;
           for(int c = 0; c < hangmanDisplay[0].length; r++){
               hangmanDisplay[r][c] = hangman6[c];
           }
         }
      }//update according to guessChances counter. Remember: It's backwards. 7 is good, 0 is bad.}
    }//end updateHangman
    
    //INITIALIZE WORD ARRAY ------------------------------------------------------------
    private void initializeWordArray(String[] word){
      for(int i = 0; i<word.length;i++){
        word[i] = "_";
      }
    }//end initializeWordArray
    
}//end medium game