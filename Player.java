public class Player {
    
    private String name;
    private int highScore;
    private int numberOfTimesPlayed;
    
    public Player() { 
        //no-arg constructor        
    }
    
    public Player(String name, int highScore, int numberOfTimesPlayed) { 
        this.name = name;
        this.highScore = highScore;
        this.numberOfTimesPlayed = numberOfTimesPlayed;
    }
   
    public String getName(){
        return name;
    }
    public int getHighScore (){
        return highScore;
    }
    public int getNumberOfTimesPlayed(){
        return numberOfTimesPlayed;
    }
    
}
