public class Player implements Comparable<Player> {
    
    private String name;
    private String password;
    private int highScore;
    private int numberOfTimesPlayed;
    
    public Player() { 
        setName("");
        setPassword("");
        setHighScore(0);
        setNumberOfTimesPlayed(0);        
    }
    
    public Player(String name, String password, int highScore, int numberOfTimesPlayed) { 
        this.name = name;
        this.password = password;
        this.highScore = highScore;
        this.numberOfTimesPlayed = numberOfTimesPlayed;
    }
   
    // Mutator methods
    public void setName(String x){
        this.name = x;
    }
    public void setPassword(String x){
        this.password = x;
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
    public String getPassword(){
        return password;
    }
    public int getHighScore (){
        return highScore;
    }
    public int getNumberOfTimesPlayed(){
        return numberOfTimesPlayed;
    }

    @Override     
    public int compareTo(Player player) {          
  
      return (this.getHighScore() < player.getHighScore() ? -1 : 
             (this.getHighScore() == player.getHighScore() ? 0 : 1));     
    }
  
  
    
}
