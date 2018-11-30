import java.util.*;

public class WordList{
 
 ArrayList<String> wordList = new ArrayList<String>();
  
//__CONSTRUCTORS______________________________________________________________________________________

//__GETTERS___________________________________________________________________________________________
 
 public int getSize(){
   return wordList.size();
 }//end getSize
 
 public int getIndex(int x, int y){
   return wordList.get(x).charAt(y);
 }//end getIndex
 
 public String getWord(int x){
   return wordList.get(x);
 }
 
//__SETTERS___________________________________________________________________________________________
  
//__METHODS___________________________________________________________________________________________
  
  public void addWord(String e){
    wordList.add(e);
  }//end addWord
  
  public void displayList(){
    for(int i = 0; i < wordList.size(); i++){
      System.out.println(wordList.get(i));
    }
  }//end displayList
 
  public void removeWords(int s){
    for(int i = (wordList.size()-1); i >= 0; i--){
      if(wordList.get(i).length() != s){
        removeWord(i);
      }
    }
  }//end removeWords
  
  public void removeWord(int i){
    wordList.remove(i);
  }//end removeWord
                                
  
}//end wordList