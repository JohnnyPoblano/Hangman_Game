public class Short_Game{
  //For Short game mode: Words between 5 and 7 letters long. 
  //The user has until one turn after the head, body, left and right arm, 
  //left and right leg are added to guess the word. That is 7 chances. 
 

  //Constants////////////////////////////////////////////////////////////////////////////////////////
  //Scoring Constants
  final static int WON = 10;
  final static int LOST = 5;
  final static int LESS_THAN_3 = 50;
  final static int LESS_THAN_4 = 25;
  final static int LESS_THAN_6 = 10;
  
  final static int CORRECT_CONSONANT = 10;
  final static int CORRECT_VOWEL = 15;
  final static int CORRECT_SPECIAL = 30;
  
  //Guessing Constants
  final static int NUMBER_OF_GUESSES = 7;
  
  //Word Length Constants
  final static int WORD_LENGTH_MIN = 5;
  final static int WORD_LENGTH_MAX = 7;
  
  //Hangman Display Constants: Maybe a 2D array that can be changed?
  
  //Theme Array, char's guessed Array, consonant array, vowel array, special letters array.
  char vowelArray = {'A', 'E', 'I', 'O', 'U'};
  char consonantArray = {'B','C','D','F','G','H','J','K','L','M','N','P','R','S','T','V','W'};
  char specialLettersArray = {'Q', 'X', 'Y', 'Z'};
  char guessedArray = new char[NUMBER_OF_GUESSES];
  String validLetters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
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
  private char guess;
  private String userName;
  private boolean complete;
  private int size;
  
  //The word arrays are down heret
  char wordArray = new char[size];//This is the actual word, compared to by the AI and the computer for correct/incorrect guesses
  char workingWordArray = new char[size];//this one is the array that is built on with the guesses. 
                              
  
  //default constructor///////////////////////////////////////////////////////////////////////////////
  Short_Game(int t, String name){
    won = false;
    correctGuess = false;
    correctVowel = false;
    correctConsonant = false;
    correctSpecial = false;
    score = 0;
   // theme = 0;
    guessChances = NUMBER_OF_GUESSES;//will minus down with every guess
    guess = '';
    userName = name;
    complete = false;
    
    for(int i = 0; i<workingWordArray.length; i++){
      workingWordArray[i] = '_';
    }
    
    size = IR4.getRandomNumber(WORD_LENGTH_MIN, WORD_LENGTH_MAX);
  }//end default constructor
  
  //main//////////////////////////////////////////////////////////////////////////////////////////////
  public static void main(String[] args){  
    //Get the appropriate theme read into an array for the AI.
    
    //Intro to game mode
    displayGameIntro();

    
    while(guessChances < 0 && !won){
      //displayHangman(); displayGuessedArray; displayChances();
      displayHangman();
      displayWord();
      displayGuessedArray();
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
      updateWord(); //This updates the word with filled in characters.
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
      guess = g.charAt(0);
    }//end setGuess
    
    private static boolean validGuess(String g){
      //true = invalid!
      boolean x = false;
      if(g.length > 1){ x = true;}
      else{
        for(int i = 0; i<validLetters.length;i++){
          if(g.equals(validLetters[i])){x = false;}
          else{x = true;}
        }
      }
      return x;    
          
    }//end validGuess
    
    public char getGuess(){
      return guess;
    }//end getGuess
    
  //methods///////////////////////////////////////////////////////////////////////////////////////////
    //DISPLAY METHODS ------------------------------------------------------------------
    private static void displayGameIntro(){
      System.out.println("*******************************************************************");
      System.out.println("       Welcome to the Short Game Mode of The Honest Hangman!");
      System.out.println("   The rules are simple: When prompted, guess a single letter.");
      System.out.println("              Goal: Don't let the hangman hang.");
      System.out.println("*******************************************************************\n");
    }//end displayShortGameIntro
    
    private static void displayHangMan(){
    }//end displayHangMan
    
    private static void displayWord(){
      for(int i = 0; i < workingWordArray.length; i++){
        System.out.print(" " + workingWordArray[i]);
      }
    }//end displayWord
    
    private static void displayGuessedArray(){
      for(int i = 0; i < guessedArray.length; i++){
        System.out.print(" " + guessedArray[i]);
      }
    }//end displayGuessedArray
    
    private static void displayChances(){
      System.out.println("Total Guesses Left:" + guessChances);
    }//end displayChances
    
    private static void displayWon(){
      System.out.println("Congratulations! You won!");
      System.out.println("You scored a total of " + score + " points.");
    }//end displayWon
    
    private static void displayLost(){
      System.out.println("And the hangman is hung. You have lost.");
      System.out.println("You scored a total of " + score + " points.");
    }//end displayLost
    
    //THE HANGMAN ----------------------------------------------------------------------
    private static void theHangman(){
      //initializeWordArray(); the actual wordArray also needs to be constantly updated when the AI changes it.
      //ON FIRST ROUND: picks a random word with appropriate word length.
      //After first round:
      // checks to see if word contains guess. 
      //if yes(checks to see if there are other words that contain guess.) 
      // if there are less than 10 words containing guess, AI will say that the word does not contain the guessed letter.
      // somehow the AI needs to check to see if there are other words containing the same letters in the same spaces too.
      //Will set correctGuess to true to false, depending on which one it settles on. 
    }//end theHangMan
    
    //CHECK GUESS ----------------------------------------------------------------------
    public static void checkGuess(){
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
    
    
    public static void checkWord(){
      if(wordArray[].equals(workingWordArray[])){ won = true;}
      else{ won = false;}
    }//end checkWord
    
    //CALCULATE SCORE ------------------------------------------------------------------
    private static int calculateScore(){
      if(correctVowel){ score = score + CORRECT_VOWEL;}
      if(correctConsonant){ score = score + CORRECT_CONSONANT;}
      if(correctSpecial){ score = score + CORRECT_SPECIAL;}
    }//end calculateScore
    
    private static int calculateFinalScore(){
      if(won){ score = score + WON;}
      else{ score = score + LOST;}
    }//end calculateFinalScore
    
    //UPDATING DISPLAYS ----------------------------------------------------------------
    private static void updateHangman(){
      if(!correctGuess){}//update according to counter.}
    }//end updateHangman
    
    private static void updateWord(){
      if(correctGuess){
        for(int i = 0; i < wordArray.length; i++){
          if(wordArray[i].equals(guess)){
            workingWordArray[i] = guess;
          }
        }
      }//put letter into appropriate word spaces}
    }//end updateWord
    
    //INITIALIZE WORD ARRAY ------------------------------------------------------------
    private static void initializeWordArray(){
      //takes the randomly chosen word, and initializes it to the word array
      //by putting each index of the word into the array
    }//end initializeWordArray
    
    
    
  
}//end short game class