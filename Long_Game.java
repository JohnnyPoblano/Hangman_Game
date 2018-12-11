import java.util.*;

public class Long_Game{
  //For Long game mode: Words 8-10+ letters. 
  //The user has until one turn after the head, neck, shoulders, body, left and right arm, 
  //left and right leg, left and right hand, left and right foot are added to guess the word. 
  //That is 13 chances. 
  
  
  //Constants////////////////////////////////////////////////////////////////////////////////////////
  //Scoring Constants
  final static int WON = 200;
  final static int LOST = 100;
  final static int LESS_THAN_9 = 50;
  final static int LESS_THAN_10 = 25;
  final static int LESS_THAN_11 = 10;
  
  final static int CORRECT_CONSONANT = 10;
  final static int CORRECT_VOWEL = 15;
  final static int CORRECT_SPECIAL = 30;
  
  //Guessing Constants
  final static int NUMBER_OF_GUESSES = 13;
  final static int GUESSES_MINUS_1 = 12;
  final static int GUESSES_MINUS_2 = 11;
  final static int GUESSES_MINUS_3 = 10;
  final static int GUESSES_MINUS_4 = 9;
  final static int GUESSES_MINUS_5 = 8;
  final static int GUESSES_MINUS_6 = 7;
  final static int GUESSES_MINUS_7 = 6;
  final static int GUESSES_MINUS_8 = 5;
  final static int GUESSES_MINUS_9 = 4;
  final static int GUESSES_MINUS_10 = 3;
  final static int GUESSES_MINUS_11 = 2;
  final static int GUESSES_MINUS_12 = 1;
  final static int ZERO_GUESSES = 0;
  
  //Word Length Constants
  final static int WORD_LENGTH_MIN = 8;
  final static int WORD_LENGTH_MAX = 13;
  
  final static String WORD_HOLDER = "_";
  final static String GAME_TYPE = "Long Game";
  
//__Hangman Display Constants________________________________________________________________________
  String[][] hangmanDisplay = {
    {" ", " ", " ", " ", " ", " ", "_", "_", "_", "_", "_", " ", " "},
    {" ", " ", " ", " ", " ", "|", " ", " ", " ", " ", " ", "| ", " "},
    {" ", " ", " ", " ", " ", "|", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", "|", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", "|", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", "|", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", "|", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", "|", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", "|", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", "|", " ", " ", " ", " ", " ", " ", " "},
    {"_", "_", "_", "_", "_", "|", "_", "_", "_", "_", "_", "_", "_"},
  };
  
  String[] hangmanHead =       {" ", " ", " ", " ", " ", "| ", " ", " ", "(-", "_", "-)", " ", " "}; //switches at 2nd
  String[] hangmanNeck =       {" ", " ", " ", " ", " ", "| ", " ", " ", "  ", "|", "  ", " ", " "}; //switches at 3rd
  String[] hangmanShoulders =  {" ", " ", " ", " ", " ", "| ", " ", " ", " _", "|", "_ ", " ", " "}; //switches at 3rd
  String[] hangmanTorsoA =     {" ", " ", " ", " ", " ", "| ", " ", " ", "  ", "|", "  ", " ", " "}; //switches at 4th
  String[] hangmanTorsoB =     {" ", " ", " ", " ", " ", "|", "  ", "  ", "_", "|", "_", "  ", " "}; //switches at 5th
  String[] hangmanLeftArmA =   {" ", " ", " ", " ", " ", "| ", " ", " ", "/ ", "|", "  ", " ", " "}; //switches at 4th
  String[] hangmanLeftArmB =   {" ", " ", " ", " ", " ", "|", "  ", "/ ", "_", "|", "_", "  ", " "}; //switches at 5th
  String[] hangmanLeftHand =   {" ", " ", " ", " ", " ", "|", " _", "/ ", "_", "|", "_", "  ", " "}; //switches at 4th
  String[] hangmanRightArmA =  {" ", " ", " ", " ", " ", "| ", " ", " ", "/ ", "|", " \\", " ", " "}; //switches at 4th
  String[] hangmanRightArmB =  {" ", " ", " ", " ", " ", "|", " _", "/ ", "_", "|", "_", " \\ ", " "}; //switches at 5th
  String[] hangmanRightHand =  {" ", " ", " ", " ", " ", "|", " _", "/ ", "_", "|", "_", " \\_ ", " "}; //switches at 4th
  String[] hangmanLeftLegA =   {" ", " ", " ", " ", " ", "| ", " ", " ", "/ ", " ", " ", " ", " "}; //switches at 6th
  String[] hangmanLeftLegB =   {" ", " ", " ", " ", " ", "| ", " ", "/ ", " ", " ", " ", " ", " "}; //switches at 7th
  String[] hangmanLeftLegC =   {" ", " ", " ", " ", " ", "| ", "/ ", " ", " ", " ", " ", " ", " "}; //switches at 8th
  String[] hangmanLeftFoot =   {" ", " ", " ", " ", " ", "|_", "/ ", " ", " ", " ", " ", " ", " "}; //switches at 8th
  String[] hangmanRightLegA =  {" ", " ", " ", " ", " ", "| ", " ", " ", "/ ", " ", " \\", " ", " "}; //switches at 6th
  String[] hangmanRightLegB =  {" ", " ", " ", " ", " ", "| ", " ", "/ ", " ", " ", " ", " \\", " "}; //switches at 7th
  String[] hangmanRightLegC =  {" ", " ", " ", " ", " ", "|_", "/ ", " ", " ", " ", " ", " ", " \\"}; //switches at 8th
  String[] hangmanRightFoot = {" ", " ", " ", " ", " ", "|_", "/ ", " ", " ", " ", " ", " ", " \\_"}; //switches at 8th
    
  
//__guessed Array, consonant array, vowel array, special letters array_______________________________
  String[] vowelArray = {"A", "E", "I", "O", "U"};
  String[] consonantArray = {"B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "R", "S", "T", "V", "W"};
  String[] specialLettersArray = {"Q", "X", "Y", "Z"};
  String[] guessedArray = new String[NUMBER_OF_GUESSES+WORD_LENGTH_MAX];
  boolean[] matchedGuessed = new boolean[26];
  String[] validLetters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
  
//__FIELDS___________________________________________________________________________________________
  
  private boolean correctGuess;
  private boolean correctVowel;
  private boolean correctConsonant;
  private boolean correctSpecial;
  private int score;
  private int theme;
  private int guessChances;
  private int round;
  private String guess;
  private String userName;
  private boolean complete;
  private int size = 8; //IR4.getRandomNumber(WORD_LENGTH_MIN, WORD_LENGTH_MAX);
  
  
  
  
  ArrayList<String> list = new ArrayList<>();
  String[] wordArray = new String[size];
  boolean[] indexFilled = new boolean[size];
  
//__CONSTRUCTOR______________________________________________________________________________________
  Long_Game(int t, String name){
    
    correctGuess = false;
    correctVowel = false;
    correctConsonant = false;
    correctSpecial = false;
    score = 0;
    theme = t;
    guessChances = NUMBER_OF_GUESSES;//will minus down with every guess
    round = 0;
    guess = " ";
    userName = name;
    complete = false;   
    
    initializeMatchedGuessArray();
    initializeGuessedArray();
    initializeThemeArray();
    //clean word array list
    removeWords();
    
    
    
  }//end default constructor
  
  
  
  //__RUN GAME METHOD__________________________________________________________________________________
  public void runGame(){ 
    
    initializeIndexFilledArray();
    initializeWordArray();
    displayGameIntro();
    
    while(guessChances > 0 && (!complete)){ 
      round++;
      
      correctVowel = false;
      correctConsonant = false;
      correctSpecial = false;
      
      displayHangman();
      displayWordArray();
      //displayChances(); //For testing: The hangman display shows current status of guesses good enough.
      displayGuessedArray();
      displayScore();  
      System.out.println("Round: " + round); //for testing
      setGuess(); 

      guessedArray[round-1] = guess;
      updateMatchedGuessedArray();
      
 
      correctGuess = theHangman();//this is the AI control method. Will tell the computer if the guess is correct or not.
      
      if(correctGuess){
        checkGuess();//this checks for consonant/vowel/special letter status
        calculateScore(); //this method uses the four booleans (correct, vowel, consonant, special) to calculate score
        displayScore(); //for Testing    
        
      }else{
        guessChances--;
        updateHangman();//This updates the Hang Man display
        displayScore(); //For testing
      }//This should make it so the guessChances only goes down on incorrect guesses.
      System.out.println("\n\n\n\n");
      complete = checkCompletion();
      
      
    }//end guessChances while loop
    displayHangman();
    displayWordArray();
    displayGuessedArray();
    
    calculateFinalScore();
    if(guessChances == 0){ displayLost();}
    if(complete){displayWon();}
  

    //FileIO and return to main menu.
    userName = ProjectFileIO_v2.saveStats(userName, score);
    
    if (!userName.equals("")) {
      Menu.menu(userName);
    }
    else {
      Menu.menu();
    }
    
    
  }//end runGame
  
//__THEME ARRAY MANIPULATION METHODS_________________________________________________________________
  
public void initializeThemeArray(){
  // THIS STUFF IS FOR TESTING______________
 if(theme == 1){
   
   /*
   list.add("TO");
   list.add("AT");
   list.add("IF");
   list.add("NO");
   list.add("SO");
   list.add("FOR");
   list.add("YES");
   list.add("NEW");
   list.add("PAT");
   list.add("CAT");
   list.add("SAT");
   list.add("SIT");
   list.add("TOP");
   list.add("PUT");
   list.add("BUT");
   list.add("BAIN");
   list.add("PAIN");
   list.add("BANE");
   list.add("TAME");
   list.add("LAME");
   list.add("NEWS");
   */
   list = ProjectFileIO_v2.getDictionary("EnglishWordListNoDup.txt");
   
 }
 if(theme == 2){
   list = ProjectFileIO_v2.getDictionary("WesternWordListNoDup.txt");
 }
 if(theme == 3){
   list = ProjectFileIO_v2.getDictionary("SciFiWordListNoDup.txt");
 }
 if(theme == 4){
   list = ProjectFileIO_v2.getDictionary("FantasyWordListNoDup.txt");;
 }
}//end initializeThemeArray
  
  
//__SETTERS__________________________________________________________________________________________
  public void setGuess(){
    String g = IR4.getString("Please enter your guess.");
    g = g.toUpperCase();
    boolean valid = validGuess(g);
    while(valid){
      System.err.println("That is not a valid guess. \nPlease enter a single letter you have not previously guessed.");
      g = IR4.getString("Please enter your guess.");
      g = g.toUpperCase();
      valid = validGuess(g);
    }
    guess = g;
  }//end setGuess
  
  private boolean validGuess(String g){
    //true = invalid!
    boolean valid = false;
    boolean length = true;
    boolean validLetter = true;
    boolean notGuessed = false;

    
    length = checkGuessLength(g);//false = too long or too short
    validLetter = checkValidLetters(g);//false = not a valid letter
    notGuessed = checkPreviouslyGuessed(g);//true = guessed
    
    if(!length){
      valid = true;
    }
    if(!validLetter){
      valid = true;
    }
    if(notGuessed){
      valid = true;
    }
    
    System.out.println("Invalid guess?: " + valid); //for testing
    return valid;
  }//end validGuess
  
  private boolean checkGuessLength(String g){
    boolean valid = false;
    if(g.length() > 1){ valid = false;}
    else{valid = true;}  
    System.out.println("Length?: " + valid);//for testing
    return valid;
  }//end checkGuessLength
  
  private boolean checkValidLetters(String g){
    //If the guess is a valid letter, return true
    //If the guess is not a valid letter, return false
    boolean valid = false;
    
    for(int i = 0; i < validLetters.length; i++){
      if(validLetters[i].equals(g)){
        valid = true;
      }
    }
    System.out.println("Valid Letter?: " + valid);//for testing
    return valid;
  }//end checkValidLetters
  
  private boolean checkPreviouslyGuessed(String g){
    boolean valid = false;
    for(int x = 0; x < guessedArray.length; x++){
      if(g.equals(guessedArray[x])){
        valid = true;
      }
    }
    System.out.println("Previously guessed?: " + valid);//for testing
    return valid; 
  }//end checkPreviouslyGuessed
  
//__GETTERS__________________________________________________________________________________________    
  public String getGuess(){
    return guess;
  }//end getGuess
  
  public int getScore(){
    return score;
  }//end getScore
  
//__METHODS__________________________________________________________________________________________
  
  //__DISPLAY METHODS________________________________________________________________________________
  private void displayGameIntro(){
    System.out.println("*******************************************************************");
    System.out.println("  Welcome, "+ userName +" to the "+ GAME_TYPE +" Mode of The Honest Hangman!");
    System.out.println("   The rules are simple: When prompted, guess a single letter.");
    System.out.println("                Goal: Don't let the hangman hang.");
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
    System.out.println("Score: "+ score);
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
    //Possible expand on possible winning messages
    int random = IR4.getRandomNumber(0,5);
    if(random == 0){
      System.out.println("Congratulations! " + userName+ ", you won!");
    }
    if(random == 1){
      System.out.println(userName + ", you saved that poor man from a dreaful fate.");
    }
    if(random == 2){
      System.out.println("Another man walks free. Good job, " + userName + ".");
    }
    if(random == 3){
      System.out.println("That could have been... gruesome.");
    }
    if(random == 4){
      System.out.println("Congratualtions, "+ userName + ". You've won a boost in confidence!");
    }
    if(random == 5){
      System.out.println("You sure do know your vocabulary, " + userName + ".");
    }
    System.out.println("You scored a total of " + score + " points.");
  }//end displayWon
  
  private void displayLost(){
    //Possible expand on possible losing messages
     int random = IR4.getRandomNumber(0,6);
    if(random == 0){
      System.out.println("And the hangman is hung. You have lost.");
    }
    if(random == 1){
      System.out.println("You're not very good at this, are you, " + userName + "?\nYou have lost.");
    }
    if(random == 2){
      System.out.println("Not only did you lose the game, but you've lost the ability to smell anything red.");
    }
    if(random == 3){
      System.out.println("Congratualtions. You know as much as a fifth grader. Maybe less, considering you lost.");
    }
    if(random == 4){
      System.out.println("I told you he would hang, didn't I, " + userName + ".");
    }
    if(random == 5){
      System.out.println("I hope your other qualities are more attractive, " + userName + ".");
    }
    if(random == 6){
      System.out.println("I hope you don't want to play again, " + userName+ ". We'll need to find someone else to hang.");
    }
      System.out.println("You scored a total of " + score + " points.");
  }//end displayLost
  
  private void displayGuessedArray(){
    System.out.print("Previously Guessed: ");
    for(int i = 0; i < validLetters.length; i++){
      if(matchedGuessed[i]){
        System.out.print(validLetters[i] + " ");
      }
    }
    System.out.println(" ");
  }//end displayGuessedArray
  
//__THE HANGMAN______________________________________________________________________________________
  private boolean theHangman(){
    boolean valid = false;
    int index = 0;
    int count = 0;
    int highest = 0;
    boolean found = checkForFound();//checkForFound sees if the letter is even in any words.
    //System.out.println(found); // for testing
    
    if(found){
      for(int i = 0; i < size; i++){
        //System.out.println("Index" + i);//for testing
        count = countForIndex(i);
        if(count > highest && (!indexFilled[i])){
          highest = count;
          index = i;
        }
        else if(count == highest && (!indexFilled[i])){
          int num = IR4.getRandomNumber(0,1);
          if(num == 0){
            highest = count;
            index = i;
          }
        }
      }
      if(index != 9){
        wordArray[index] = guess;
        indexFilled[index] = true;
        //System.out.println(wordArray[index]+ " "+ index);//for testing
        removeWords(guess, index);
        //displayWords();//for testing
        valid = true;
      }
    }else{
      valid = false;
    }
    
    return valid;
  }//end theHangMan
  
  
  private boolean checkForFound(){
    boolean check = false;
    
    for(int x = 0; x < list.size(); x++){
      for(int i = 0; i < size; i++){
        
        if(guess.equals((list.get(x).charAt(i))+"")){
          check = true;
          //System.out.println(check);//for testing
        }
        //System.out.println(list.get(x).charAt(i));//for testing
      }
      
    }
    return check;
  }//end checkForFound
  
  private int countForIndex(int i){
    int count = 0;
    String word;
    
    for(int x = 0; x < list.size(); x++){
      word = list.get(x);
      //System.out.println(word);//for testing
      //System.out.println(word.charAt(i));// for testing
      //System.out.println(guess.equals((word.charAt(i))+""));//for testing
      if(guess.equals((word.charAt(i))+"")){
        count++;
      }
    }
    
    
    //System.out.println(count);//for testing
    return count;
  }//end countForIndex
  
  
  
  
  
  
//__CHECK GUESS______________________________________________________________________________________
  public void checkGuess(){
    correctConsonant = false;
    correctVowel = false;
    correctSpecial = false;
    
    checkForConsonant();
    if(!correctConsonant){
      checkForVowel();
    }
    if(!correctConsonant && !correctVowel){
      checkForSpecial();
    }
  }//end checkGuess 
  
  private void checkForVowel(){
    for(int i = 0; i<vowelArray.length;i++){
      if(guess.equals(vowelArray[i])){correctVowel = true;
      }
    }
  }//end checkForVowel
  
  private void checkForConsonant(){
    for(int j = 0; j<consonantArray.length;j++){
      if(guess.equals(consonantArray[j])){correctConsonant = true;
      }
    }
  }//end checkForConsonant
  
  private void checkForSpecial(){
    for(int k = 0; k<specialLettersArray.length;k++){
      if(guess.equals(specialLettersArray[k])){ correctSpecial = true;
      }
    }
  }//end checkForSpecial
  
  private boolean checkCompletion(){
    boolean valid = true;
    int checkCounter = 0;
    for(int i = 0; i < wordArray.length; i++){
      if(wordArray[i].equals(WORD_HOLDER)){
        valid = true;
      }else{
        checkCounter++;
      }
    }
    //System.out.println(checkCounter); //for testing
    if(checkCounter == size){
      return true;
    }else{
      return false;
    }
  }//end checkCompletion
  
//__CALCULATE SCORE__________________________________________________________________________________
  private void calculateScore(){
    if(correctVowel){ score = score + CORRECT_VOWEL;}
    
    if(correctConsonant){ score = score + CORRECT_CONSONANT;}
    
    if(correctSpecial){ score = score + CORRECT_SPECIAL;}
    
  }//end calculateScore
  
  private void calculateFinalScore(){
    
    if(round < 9){
      score = score + LESS_THAN_9;
    }
    else if(round < 10){
      score = score + LESS_THAN_10;
    }
    else if(round < 11){
      score = score + LESS_THAN_11;
    }
    
    if(complete){ 
      score = score + WON;
    }else{ 
      score = score + LOST;
    }
  }//end calculateFinalScore
  
//__UPDATING DISPLAYS________________________________________________________________________________
  private void updateHangman(){
  
    if(!correctGuess){
      
      if(guessChances == GUESSES_MINUS_1){
        //read hangmanHead into the 2nd index row of hangmanDisplay
        int r = 2;
        for(int c = 0; c < hangmanDisplay[0].length; c++){
          hangmanDisplay[r][c] = hangmanHead[c];
        }
      }
      if(guessChances == GUESSES_MINUS_2){//reads in head
        //read hangmaNeck into the 3rd index row of hangmanDisplay
        int r = 3;
        for(int c = 0; c < hangmanDisplay[0].length; c++){
          hangmanDisplay[r][c] = hangmanNeck[c];
        }
      }
      if(guessChances == GUESSES_MINUS_3){//reads in shoulders
        //read hangmanShoulders into the 3rd index row of hangmanDisplay
        int r = 3;
        for(int c = 0; c < hangmanDisplay[0].length; c++){
          hangmanDisplay[r][c] = hangmanShoulders[c];
        }
      }
      if(guessChances == GUESSES_MINUS_4){//reads in torso
        //read hangmanTorsoA into the 4th index row of hangmanDisplay
        //read hangmanTorsoB into the 5th index row of hangmanDisplay
        int r = 4;
        int x = 5;
        for(int c = 0; c < hangmanDisplay[0].length; c++){
          hangmanDisplay[r][c] = hangmanTorsoA[c];
          hangmanDisplay[x][c] = hangmanTorsoB[c];
        }
      }
      if(guessChances == GUESSES_MINUS_5){//reads in left arm
        //read hangmanLeftArmA into the 4th index row of hangmanDisplay
        //read hangmanLeftArmB into 5th index row of hangmanDisplay
        int r = 4;
        int x = 5;
        for(int c = 0; c < hangmanDisplay[0].length; c++){
          hangmanDisplay[r][c] = hangmanLeftArmA[c];
          hangmanDisplay[x][c] = hangmanLeftArmB[c];
        }
      }
      if(guessChances == GUESSES_MINUS_6){//reads in left hand
        //read hangmanLeftHand into the 5th index row of hangmanDisplay
        int r = 5;
        for(int c = 0; c < hangmanDisplay[0].length; c++){
          hangmanDisplay[r][c] = hangmanLeftHand[c];
        }
      }
      if(guessChances == GUESSES_MINUS_7){//reads in right arm
        //read hangmanRightArmA into the 4th index row of hangmanDisplay
        //read hangmanRightArmB into the 5th index row of hangmanDisplay
        int r = 4;
        int x = 5;
        for(int c = 0; c < hangmanDisplay[0].length; c++){
          hangmanDisplay[r][c] = hangmanRightArmA[c];
          hangmanDisplay[x][c] = hangmanRightArmB[c];
        }
      }
      if(guessChances == GUESSES_MINUS_8){//reads in right hand
        //read handmanRightHand into the 5th index row of hangmanDisplay
        int r = 5;
        for(int c = 0; c < hangmanDisplay[0].length; c++){
          hangmanDisplay[r][c] = hangmanRightHand[c];
        }
      }
      if(guessChances == GUESSES_MINUS_9){//reads in left leg
        //read hangmanLeftLegA into 6th index row of hangmanDisplay
        //read hangmanLeftLegB into 7th index row of hangmanDisplay
        //read hangmanLeftLegC into 8th index row of hangmanDisplay
        int r = 6;
        int x = 7;
        int y = 8;
        for(int c = 0; c < hangmanDisplay[0].length; c++){
          hangmanDisplay[r][c] = hangmanLeftLegA[c];
          hangmanDisplay[x][c] = hangmanLeftLegB[c];
          hangmanDisplay[y][c] = hangmanLeftLegC[c];
        }
      }
      if(guessChances == GUESSES_MINUS_10){//reads in left foot
        //read hangmanLeftFoot into 8th index row of hangmanDisplay
        int r = 8;
        for(int c = 0; c < hangmanDisplay[0].length; c++){
          hangmanDisplay[r][c] = hangmanLeftFoot[c];
        }
      }
      if(guessChances == GUESSES_MINUS_11){//reads in right leg
        //read hangmanRightLegA into 6th index row of hangmanDisplay
        //read hangmanRightLegB into 7th index row of hangmanDisplay
        //read hangmanRightLegC into 8th index row of hangmanDisplay
        int r = 6;
        int x = 7;
        int y = 8;
        for(int c = 0; c < hangmanDisplay[0].length; c++){
          hangmanDisplay[r][c] = hangmanRightLegA[c];
          hangmanDisplay[x][c] = hangmanRightLegB[c];
          hangmanDisplay[y][c] = hangmanRightLegC[c];
        }
      }
      if(guessChances == GUESSES_MINUS_12){//reads in right foot
        //read hangmanRightFoot into 8th index row of hangmanDisplay
        int r = 8;
        for(int c = 0; c < hangmanDisplay[0].length; c++){
          hangmanDisplay[r][c] = hangmanRightFoot[c];
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
  
  private void displayWords(){
    for(int i = 0; i < list.size(); i++){
      System.out.println(list.get(i));
    }
  }//end displayWords
  
  private void initializeGuessedArray(){
    for(int i = 0; i < guessedArray.length; i++){
      guessedArray[i] = WORD_HOLDER;
    }
  }//end initializeGuessedArray
  
  private void removeWords(){
    for(int i = list.size()-1; i >= 0; i--){
      if(list.get(i).length() > size || list.get(i).length() < size){
        list.remove(i);
      }
    }
  }//end removeWords
  
  private void removeWords(String guess, int index){
    boolean valid;
    for(int x = list.size()-1; x >= 0; x--){
      if(guess.equals((list.get(x).charAt(index)+""))){
        valid = true;
      }else{
        list.remove(x);
      }
    }
  }//end removeWords(String, int)
  
  private void initializeIndexFilledArray(){
    for(int i = 0; i < indexFilled.length; i++){
      indexFilled[i] = false;
    }
  }//end initializeIndexFilledArray
  
  private void initializeMatchedGuessArray(){
    for(int i = 0; i < matchedGuessed.length; i++){
      matchedGuessed[i] = false;
    }
  }//end initializeMatchedGuessArray
  
  private void updateMatchedGuessedArray(){
    for(int i = 0; i < validLetters.length; i++){
      if(guess.equals(validLetters[i])){
        matchedGuessed[i] = true;
      }
    }
  }//end updatedMatchedGuessedArray
  
  
  
}//end Long Game