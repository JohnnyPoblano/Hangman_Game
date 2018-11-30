import java.util.ArrayList;

public class Quick_Game{
  
  //For Quick game mode: Words between 2 and 4 letters long. 
  //The user has until one turn after the head, body, left and right arm, 
  //left and right leg are added to guess the word. That is 7 chances. 
  
  //TO DO:
  //- Make static letter index if correct
  //   - Make sure that letter is in an index that is actually the same as a word
  //   - Account for a letter being in multiple indexes
  //- Make ai check for static letters/indexes
  
  
  //Constants////////////////////////////////////////////////////////////////////////////////////////
  //Scoring Constants
  final static String GAME_TYPE = "Quick Play";
  final static int WON = 5;
  final static int LOST = 3;
  final static int LESS_THAN_2 = 20;
  final static int LESS_THAN_3 = 10;
  final static int LESS_THAN_4 = 5;
  
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
  
  final static String WORD_HOLDER = "_";
  
//__Hangman Display Constants________________________________________________________________________
  String[][] hangmanDisplay = {
    {" ", " ", " ", " ", " ", "_", "_", "_", " ", " "},
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
  
//__guessed Array, consonant array, vowel array, special letters array_______________________________
  String[] vowelArray = {"A", "E", "I", "O", "U"};
  String[] consonantArray = {"B", "C", "D", "F", "G", "H", "I", "J", "K", "L", "M", "N", "P", "R", "S", "T", "V", "W"};
  String[] specialLettersArray = {"Q", "X", "Y", "Z"};
  String[] guessedArray = new String[NUMBER_OF_GUESSES];
  String[] validLetters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
  
//__FIELDS___________________________________________________________________________________________
  private boolean won;
  private boolean correctGuess;
  private boolean correctVowel;
  private boolean correctConsonant;
  private boolean correctSpecial;
  private int score;
  // private int theme;
  private int guessChances;
  private int round;
  private String guess;
  private String userName;
  private boolean complete;
  //private int size = IR4.getRandomNumber(WORD_LENGTH_MIN, WORD_LENGTH_MAX);
  private int size = 3;
  private int wordListSize;
  
  ArrayList<String> list = new ArrayList<>();
  String[] copiedWords = new String[3];
  String[] wordArray = new String[size];//This is where letters will be stored when the computer can no longer find words without them.
  boolean[] wordMatchArray = new boolean[3];//paralell to wordList. Check true/false
  int[] matchedGuess = new int[NUMBER_OF_GUESSES];
//__CONSTRUCTOR______________________________________________________________________________________
  Quick_Game(int t, String name){
    won = false;
    correctGuess = false;
    correctVowel = false;
    correctConsonant = false;
    correctSpecial = false;
    score = 0;
    //theme = t;
    guessChances = NUMBER_OF_GUESSES;//will minus down with every guess
    round = 0;
    guess = " ";
    userName = name;
    complete = false;   
    
    initializeGuessedArray();
    initializeThemeArray();
    //clean word array list
    removeWords();
    //copy words from arrayList into a normal array
    copiedWords = new String[list.size()];
    copyWords();
    

    wordMatchArray = new boolean[list.size()];
    
  }//end default constructor
  
  
  
  //__RUN GAME METHOD__________________________________________________________________________________
  public void runGame(){ 
    
    
    initializeWordArray();
    displayGameIntro();
    
    while(guessChances > 0){
      displayHangman();
      displayWordArray();
      displayChances();
      displayScore();
      round++;
      
      setGuess(); 
      guessedArray[round-1] = guess;
      
      correctGuess = theHangman();//this is the AI control method. Will tell the computer if the guess is correct or not.
      
      if(correctGuess){
        checkGuess();//this checks for consonant/vowel/special letter status
        calculateScore(); //this method uses the four booleans (correct, vowel, consonant, special) to calculate score
        findStaticWord();//Will put last guess into a permanent index
        initializeGuessedArray();
      }
      else{
        guessChances--;
        updateHangman();//This updates the Hang Man display
      }//This should make it so the guessChances only goes down on incorrect guesses.
    }//end guessChances while loop
    
    calculateFinalScore();
    if(guessChances == 0){ displayLost();}
    else{displayWon();}
    
    
    //Save score and other stats. 
    //return to main menu.
    
    
  }//end runGame
  
//__THEME ARRAY MANIPULATION METHODS_________________________________________________________________
  
  public void initializeThemeArray(){
    list.add("APP");
    list.add("SNAP");
    list.add("RAID");
    list.add("CAP");
    list.add("TOO");
    list.add("ZOO");
    list.add("TAPE");
    list.add("MAIN");
    list.add("TAME");
    list.add("LOSE");
    list.add("LAME");
    list.add("BAIT");
    list.add("NEW");
    list.add("NEWS");
    list.add("TO");
    list.add("AT");
    list.add("CAT");
    list.add("TAP");
    list.add("NO");
    list.add("YES");
    list.add("ZAP");
    list.add("BAIN");
    list.add("BANE");
    list.add("PUT");
    list.add("PUTT");
    list.add("BUT");
    list.add("BUTT");
  }//end initializeThemeArray
  
  
//__SETTERS__________________________________________________________________________________________
  public void setGuess(){
    String g = IR4.getString("Please enter your guess.");
    g = g.toUpperCase();
    while(validGuess(g)){
      System.err.println("That is not a valid guess. Please enter ONE letter.");
      g = IR4.getString("Please enter your guess.");
    }
    guess = g;
  }//end setGuess
  
  private boolean validGuess(String g){
    //true = invalid!
    if(g.length() > 1){ return true;}
    else{return false;}             
  }//end validGuess
  
//__GETTERS__________________________________________________________________________________________    
  public String getGuess(){
    return guess;
  }//end getGuess
  
  public int getScore(){
    return score;
  }//end getScore
  
//__METHODS__________________________________________________________________________________________
  
  //__DISPLAY METHODS________________________________________________________________________________
  private static void displayGameIntro(){
    System.out.println("*******************************************************************");
    System.out.println("       Welcome to the"+ GAME_TYPE +" Mode of The Honest Hangman!");
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
  
  private void displayScore(){
    System.out.println(score);
  }//end displayScore
  
  private void displayWordArray(){
    for(int i = 0; i < wordArray.length; i++){
      System.out.print(" " + wordArray[i] + " ");
    }
    System.out.println(" ");
  }//end displayWordArray
  
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
  
//__THE HANGMAN______________________________________________________________________________________
  private boolean theHangman(){
    initializeMatchedGuesses();
    initializeWordMatchArray();
    
    boolean correct = testGuess();
    
    return correct;
  }//end theHangMan
  
  
  private boolean testGuess(){ 

    boolean guessInWord = true;
    boolean valid = false;
    int validCounter = 0;
    
    
    if(checkForFound()){//checkForFound sees if the letter is even in any words.
      for(int w = 0; w < copiedWords.length; w++){//If so:
        if(wordMatchArray[w]){
          guessInWord = testGuessesInWord(w);
          if(guessInWord){
            wordMatchArray[w] = true;
          }else{
            wordMatchArray[w] = false;
          }
          System.out.println(guessInWord +" "+ wordMatchArray[w]);
        }
      }
      validCounter = checkForFalseWords();
      if(validCounter == copiedWords.length || validCounter == 0){
        valid = true;
      }else{
        valid = false;
      }
    }
    if(!checkForFound()){//If not:
      initializeGuessedArray();
    }
    System.out.println(checkForFound());
    System.out.println(valid+" "+validCounter);
    return valid;
  }//end testGuess
  
  private boolean checkForFound(){
    boolean check = false;
    
    for(int x = 0; x < copiedWords.length; x++){
      for(int i = 0; i < size; i++){
        if(guess.equals((copiedWords[x].charAt(i)+""))){
          check = true;
          //System.out.println(check);
        }
        //System.out.println(copiedWords[x].charAt(i));
      }
    }
    return check;
  }//end checkForFound
  
  private int checkForFalseWords(){
    int sum = 0; 
    for(int i = 0; i < copiedWords.length; i++){
      if(wordMatchArray[i]){
        sum++;
      }
    }
    return sum;
  }//end checkForFalseWords
  
  private boolean testGuessesInWord(int w){
    initializeMatchedGuesses();  
    if(wordMatchArray[w]){
      for(int x = 0; x < guessedArray.length; x++){
        for(int i = 0; i < size; i++){
          if(!guessedArray[x].equals(WORD_HOLDER)){
            if(guessedArray[x].equals((copiedWords[w].charAt(i))+"")){
              matchedGuess[x] = 1;
              System.out.println(guessedArray[x]);
              System.out.println(matchedGuess[x]);
            }
          }
        }
      }
      int sum = countMatchedGuesses();
      System.out.println(sum);
      if(sum == round-1){
        return false;
      }else{
        return true;
      }
    }else{
      return false;
    }
  }//end testGuessesInWord
  
  private int countMatchedGuesses(){
    int sum = 0;
    for(int i = 0; i < matchedGuess.length; i++){
      sum += matchedGuess[i];
    }
    return sum;
  }//end countMatchingGuess
  
  private void initializeMatchedGuesses(){
    for(int i = 0; i < matchedGuess.length; i++){
      matchedGuess[i] = 0;
    }
  }//end initializeMatchingGuess
  
  private void initializeWordMatchArray(){
    for(int i = 0; i< wordMatchArray.length; i++){
      wordMatchArray[i] = true;
    }
  }//end initializeWordMatchArray
  
  private void findStaticWord(){
    int i = IR4.getRandomNumber(0, size-1);
    wordArray[i] = guess;
    while(invalidIndex(i)){
      i = IR4.getRandomNumber(0, size-1);
      wordArray[i] = guess;
    }      
  }//end findStaticWord
  
  private boolean invalidIndex(int i){
    boolean valid = true;
    for(int x = 0; x < copiedWords.length; x++){
      if(wordArray[i].equals(copiedWords[x].charAt(i))){
        valid = true;
      }else{
        valid = false;
      }
    }
    return valid;
  }//end invalidIndex
  
  
  
//__CHECK GUESS______________________________________________________________________________________
  public void checkGuess(){
    checkForVowel();
    if(!correctVowel){
      checkForConsonant();
    }
    if(!correctVowel && !correctConsonant){
      checkForSpecial();
    }
    
  }//end checkGuess 
  
  private void checkForVowel(){
    for(int i = 0; i<vowelArray.length;i++){
      if(guess.equals(vowelArray[i])){correctVowel = true;}
    }
  }//end checkForVowel
  
  private void checkForConsonant(){
    for(int j = 0; j<consonantArray.length;j++){
      if(guess.equals(vowelArray[j])){correctConsonant = true;}
    }
  }//end checkForConsonant
  
  private void checkForSpecial(){
    for(int k = 0; k<specialLettersArray.length;k++){
      if(guess.equals(specialLettersArray[k])){ correctSpecial = true;}
    }
  }//end checkForSpecial
  
//__CALCULATE SCORE__________________________________________________________________________________
  private void calculateScore(){
    if(correctVowel){ score = score + CORRECT_VOWEL;}
    
    if(correctConsonant){ score = score + CORRECT_CONSONANT;}
    
    if(correctSpecial){ score = score + CORRECT_SPECIAL;}
    
  }//end calculateScore
  
  private void calculateFinalScore(){
    if(won){ score = score + WON;}
    else{ score = score + LOST;}
  }//end calculateFinalScore
  
//__UPDATING DISPLAYS________________________________________________________________________________
  private void updateHangman(){
    if(!correctGuess){
      
      if(guessChances == GUESSES_MINUS_1){
        //read hangman1 into the 3rd index row of hangmanDisplay
        int x = 3;
        for(int c = 0; c < hangmanDisplay[0].length; c++){
          hangmanDisplay[x][c] = hangman1[c];
        }
      }
      if(guessChances == GUESSES_MINUS_2){
        //read hangman2 into the 4th index row of hangmanDisplay
        int r = 4;
        for(int c = 0; c < hangmanDisplay[0].length; c++){
          hangmanDisplay[r][c] = hangman2[c];
        }
      }
      if(guessChances == GUESSES_MINUS_3){
        //read hangman3 into the 4th index row of hangmanDisplay
        int r = 4;
        for(int c = 0; c < hangmanDisplay[0].length; c++){
          hangmanDisplay[r][c] = hangman3[c];
        }
      }
      if(guessChances == GUESSES_MINUS_4){
        //read hangmand4 into the 4th index row of hangmanDisplay
        int r = 4;
        for(int c = 0; c < hangmanDisplay[0].length; c++){
          hangmanDisplay[r][c] = hangman4[c];
        }
      }
      if(guessChances == GUESSES_MINUS_5){
        //read hangman5 into the 5th index row of hangmanDisplay
        int r = 5;
        for(int c = 0; c < hangmanDisplay[0].length; c++){
          hangmanDisplay[r][c] = hangman5[c];
        }
      }
      if(guessChances == GUESSES_MINUS_6){
        //read hangman6 into the 5th index row of hangmanDisplay
        int r = 5;
        for(int c = 0; c < hangmanDisplay[0].length; c++){
          hangmanDisplay[r][c] = hangman6[c];
        }
      }
    }//update according to guessChances counter. Remember: It's backwards. 7 is good, 0 is bad.}
  }//end updateHangman
  
//__INITIALIZE ARRAYS________________________________________________________________________________
  private void initializeWordArray(){
    for(int i = 0; i < wordArray.length;i++){
      wordArray[i] = "_";
    }
  }//end initializeWordArray
  
  private void initializeGuessedArray(){
    for(int i = 0; i < guessedArray.length; i++){
      guessedArray[i] = WORD_HOLDER;
    }
  }//end initializeGuessedArray
  
  private void removeWords(){
    for(int i = list.size()-1; i > 0; i--){
      if(list.get(i).length() > size || list.get(i).length() < size){
        list.remove(i);
      }
    }
  }//end removeWords
  
  private void copyWords(){
    for(int i = 0; i < list.size(); i++){
      copiedWords[i] = list.get(i);
    }
  }//end copyWords
  
}//end Quick Game