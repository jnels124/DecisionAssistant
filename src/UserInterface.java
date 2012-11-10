import java.util.List;
import java.util.ArrayList;
/**
 * This interface will contain the methods needed to coummunicate with the user
 * 
 * @author (Jesse Nelson) 
 * @version (November 9, 2012 : Windows 8(x64) Java 1.7 U9)
 */
public interface UserInterface {
   
    /**
     * Gets all of the choices being considered by the user
     * 
     * @return list of choices 
     */
    List<Choice> getChoices();
    
    /**
     * Gets all of the characteristics related to the choices
     */
    List<Characteristic> getCharacteristics();
    
    /**
     * Ranks the charactersitics importance in relation to eachother
     */
    void getCharacteristicRankings(List<Characteristic> allChar, 
                                   int defaultValue);
    
    /**
     * Ranks the choices importance in relation to eachother 
     * for each of the specified characteristics.
     * 
     * @param choices A list of all the choices
     * @param characteristics A list of all the characteristics
     * @param defaultValue The default ranking
     * 
     * @return a 2D array containing the ranking of each choice related to 
     * each characteristic
     */
    double [][] getCrossRanking(List<Choice> choices,
                                List<Characteristic> characteristics,
                                int defaultValue);
    
    /**
     * Displays the final result to the user
     * 
     * @param choices A list of all the choices
     */
    void showResults(List<Choice> choices);
    
}
