import java.util.ArrayList;

public class QuickGame{
    
    // Scoring Constants
    final static int WON = 5;
    final static int LOST = 3;
    final static int LESS_THAN_2 = 20;
    final static int LESS_THAN_3 = 10;
    final static int LESS_THAN_4 = 5;
    
    final static int CORRECT_CONSONANT = 10;
    final static int CORRECT_VOWEL = 15;
    final static int CORRECT_SPECIAL = 30;
    
    // Guessing Constants
    final static int NUMBER_OF_GUESSES = 7;
    final static int GUESSES_MINUS_1 = 6;
    final static int GUESSES_MINUS_2 = 5;
    final static int GUESSES_MINUS_3 = 4;
    final static int GUESSES_MINUS_4 = 3;
    final static int GUESSES_MINUS_5 = 2;
    final static int GUESSES_MINUS_6 = 1;
    final static int ZERO_GUESSES = 0;
    
    // Word Length Constants
    final static int WORD_LENGTH_MIN = 2;
    final static int WORD_LENGTH_MAX = 4;

    final static String[] VALID_LETTERS = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    final static String[][] HANGMAN_DISPLAY = {
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
    
    final static String[] HANGMAN1 = {" ", " ", " ", " ", "|", " ", " ", " ", "O", " "};//will switch with the 3rd index row
    final static String[] HANGMAN2 = {" ", " ", " ", " ", "|", " ", " ", " ", "|", " "};//will switch with the 4th index row
    final static String[] HANGMAN3 = {" ", " ", " ", " ", "|", " ", " ", "/", "|", " "};//will switch with the 4th index row
    final static String[] HANGMAN4 = {" ", " ", " ", " ", "|", " ", " ", "/", "|", "\\"};//will switch with the 4th index row
    final static String[] HANGMAN5 = {" ", " ", " ", " ", "|", " ", " ", "/ ", " ", " "};//will switch with the 5th index row
    final static String[] HANGMAN6 = {" ", " ", " ", " ", "|", " ", " ", "/ ", "\\", ""};//will switch with the 5th index row

    public static void main(String args[]) {

        // Initialize hangman
        String[][] hangman = new String[HANGMAN_DISPLAY.length][HANGMAN_DISPLAY[0].length];
        hangman = HANGMAN_DISPLAY;

        // Get size of word
        int size = IR4.getRandomNumber(WORD_LENGTH_MIN, WORD_LENGTH_MAX);

        // Instantiate correct word array
        String[] correctWord = new String[size];

        // Initialize correct word with "_"
        initializeWordArray(correctWord);

        // Read library into arraylist
        ArrayList<String> library = new ArrayList<>();
        library = ProjectFileIO_v2.getDictionary("EnglishWordList.txt");

        // Trim library to words of selected size
        library = trimLibraryToSize(library, size);

        displayGameIntro();
        
        for (int i = 0; i < 10; i++) {
        
            displayHangman(hangman);
            displayWord(correctWord);

            // Show library
            for (int c = 0; c < library.size(); c++) {
                System.out.println(library.get(c));
            }

            // Get guess from user
            String userGuess = getGuess();

            // If the AI can continue to cheat
            if (checkIfWordsAvailable(library, userGuess)) {
                library = narrowDownList(library, userGuess);
            }
            // If the AI has to settle on a letter at an index
            else {
                int indexForLetter = chooseIndexForLetter(library, userGuess, size);
                correctWord[indexForLetter] = userGuess;
            }

        }

    }

    // Intro game description
    private static void displayGameIntro(){
        System.out.println("*******************************************************************");
        System.out.println("       Welcome to the Quick Game Mode of The Honest Hangman!");
        System.out.println("   The rules are simple: When prompted, guess a single letter.");
        System.out.println("    You have "+NUMBER_OF_GUESSES+" chances to guess the word.");
        System.out.println("              Goal: Don't let the hangman hang.");
        System.out.println("*******************************************************************\n");
    }
      
    // Returns a string as the user's guess
    public static String getGuess(){
        String g = IR4.getString("Please enter your guess.");
        g = g.toUpperCase();
        while(!validGuess(g)){
          System.err.println("That is not a valid guess. Please enter ONE letter.");
          g = IR4.getString("Please enter your guess.");
          g = g.toUpperCase();
        }

        return g;
    }

    // Returns true if a guess is valid
    public static boolean validGuess(String g){
        if(g.length() > 1 || !isAlpha(g) || /*guessed already*/ false) {
            return false;
        }
        else {
            return true;
        }             
    }

    // Checks if a String is alpha
    public static boolean isAlpha(String g) {
        for (int i = 0; i < VALID_LETTERS.length; i++) {
            if (VALID_LETTERS[i].equals(g)) {
                return true;
            }
        }
        return false;
    }

    // Initialize word array with underscores
    public static void initializeWordArray(String[] word){
        for(int i = 0; i < word.length; i++){
          word[i] = "_";
        }
    }

    // Display current state of the hangman
    public static void displayHangman(String[][] hangmanArray){
        for (int r = 0; r < hangmanArray.length;r++){
          for (int c = 0; c<hangmanArray[0].length;c++){
            System.out.print(hangmanArray[r][c]);
          }
          System.out.println();
        }
    }

    // Display current guessed state of word
    public static void displayWord(String[] word){
        for(int i = 0; i < word.length; i++){
          System.out.print(" " + word[i]);
        }
        System.out.println();
    }

    // Display letters already guessed
    public static void displayGuesses(String[] array) {
        if (array.length == 0) {
            return;
        }
        
        System.out.print("Already guessed: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
    }

    // Trim word library to words of a certain size
    public static ArrayList<String> trimLibraryToSize(ArrayList<String> lib, int size) {
        ArrayList<String> newLibrary = new ArrayList<>();
        
        for (int i = 0; i < lib.size(); i++) {
            if (lib.get(i).length() == size) {
                newLibrary.add(lib.get(i));
            }
        }

        return newLibrary;
    }

    // --------- Hangman AI ------------------------ //

    // This function searches the word arraylist and will return true
    // if there are words in the arraylist that do not contain the
    // guessed letter. Basically a check to see if AI can still cheat
    public static boolean checkIfWordsAvailable(ArrayList<String> arrayList, String letter) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (!arrayList.get(i).toUpperCase().contains(letter)) {
                return true;
            }
        }
        return false;
    }

    // Removes all words that contain a guess from the user
    // To be called after the previous function discovers
    // there are words that do not contain a specific guess
    public static ArrayList<String> narrowDownList(ArrayList<String> arrayList, String letter) {
        ArrayList<String> newArrayList = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            if (!arrayList.get(i).toUpperCase().contains(letter)) {
                newArrayList.add(arrayList.get(i));
            }
        }

        return newArrayList;
    }

    // Chooses an index to place a letter at if the AI
    // has to settle on a letter
    public static int chooseIndexForLetter(ArrayList<String> arrayList, String letter, int wordLength) {
        int counterArray[] = new int[wordLength];

        // initialize to zero
        for (int i = 0; i < counterArray.length; i++) {
            counterArray[i] = 0;
        }

        // Add one to counter index for each occurence in each letter
        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = 0; j < wordLength; j++) {
                if (arrayList.get(i).substring(j, j+1).toUpperCase().equals(letter)) {
                    counterArray[j]++;
                }
            }
        }

        // Choose and return the index where the letter occurs the most
        int highestIndex = 0;

        for (int i = 0; i < counterArray.length; i++) {
            if (counterArray[i] > highestIndex) {
                highestIndex = i;
            }
        }

        return highestIndex;
    }
}