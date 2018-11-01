public class Player {
    
    private String name;
    private int highScore;
    private int numberOfTimesPlayed;
    
    public Player() { 
        setName("");
        setHighScore(0);
        setNumberOfTimesPlayed(0);        
    }
    
    public Player(String name, int highScore, int numberOfTimesPlayed) { 
        this.name = name;
        this.highScore = highScore;
        this.numberOfTimesPlayed = numberOfTimesPlayed;
    }
   
    // Mutator methods
    public void setName(String x){
        this.name = x;
    }
    public void setHighScore (int x){
        this.highScore = x;
    }
    public void setNumberOfTimesPlayed(int x){
        this.numberOfTimesPlayed = x;
    }

    // Accessor methods
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
