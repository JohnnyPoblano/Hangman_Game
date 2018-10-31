public class Player {
    
    private String name;
    private String password;
    private int highScore;
    private int numberOfTimesPlayed;
    
    public Player() { 
        //no-arg constructor        
    }
    
    public Player(String name, String password, int highScore, int numberOfTimesPlayed) { 
        this.name = name;
        this.password = password;
        this.highScore = highScore;
        this.numberOfTimesPlayed = numberOfTimesPlayed;
    }
   
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public int getHighScore (){
        return highScore;
    }
    public int getNumberOfTimesPlayed(){
        return numberOfTimesPlayed;
    }
    
}
