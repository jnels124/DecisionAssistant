
/**
 * This class will contain information about a Characteristic
 * for the DecisionAssistant
 * 
 * @author (Jesse Nelson) 
 * @version (November 9, 2012 : Windows 8(x64) Java 1.7 U9)
 */
public class Characteristic {
    /**
     * Default rank for a characteristic
     */
    private static final int DEFAULT_RANK = 1;
    
    /**
     * The characteristic
     */
    private String characteristicName;
    
    /**
     * Rank of the characteristic
     */
    private int rank;
    
    /**
     * Initialize instance variables
     * 
     * @param characteristic Characteristic name
     */
    public Characteristic(String characteristic) {
        this.characteristicName = characteristic;
        this.rank = DEFAULT_RANK;
    }
    
    /**
     * Set the rank for this characteristic
     * 
     * @param newRank Value to be assigned to rank
     */
    public void setRank(int newRank) {
        this.rank = newRank;
    }
    
    /**
     * Get the characteristic 
     * 
     * @return characteristicName
     */
    public String getCharacteristicName() {
        return this.characteristicName;
    }
    
    /**
     * Get the rank for this Characteristic
     * 
     * @return rank
     */
    public int getRank() {
        return this.rank;
    }
    
}
