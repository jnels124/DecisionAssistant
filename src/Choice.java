
/**
 * This class will contain information about a choice
 * for the DecisionAssistant
 * 
 * @author (Jesse Nelson) 
 * @version (November 9, 2012 : Windows 8(x64) Java 1.7 U9)
 */
public class Choice {
    /**
     * Default ranking 
     */
    private static final int DEFAULT_SCORE = 0;
    
    /**
     * The choice
     */
    private String choice;
    
    /**
     * Final ranking of this choice
     */
    private int finalScore;
    
    /**
     * Initializes instance variables
     */
    public Choice(String choice) {
        this.choice = choice;
        this.finalScore = DEFAULT_SCORE;
    }
    
    /**
     * Sets finalScore 
     * 
     * @param score the value to be assigned to finalScore
     */
    public void setFinalScore(int score) {
        this.finalScore = score;
    }
    
    /**
     * Gets the name of this choice
     * 
     * @return the name of this choice
     */
    public String getName() {
        return this.choice;    
    }
    
    /**
     * Gets the final ranking of this choice
     * 
     * @return final rank of this choice
     */
    public int getFinalScore() {
        return this.finalScore;
    }
    
}
