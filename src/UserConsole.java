import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 * This class uses the console to display information to, and obtain information from, the user
 * 
 * @author (Jesse Nelson) 
 * @version (11/9/2012 : Windows 8(x64) Java 1.7 U9)
 */
public class UserConsole implements UserInterface {
    /**
     * Uses console to get all of the choices being considered by the user
     * 
     * @return list of choices 
     */
    public List<Choice> getChoices() {
        List<Choice> choices = new ArrayList<Choice>();
        return choices;        
    }
    
    /**
     * Uses console to get all of the characteristics related to the choices
     * 
     * @return list of characteristics
     */
    public List<Characteristic> getCharacteristics() {
        List<Characteristic> characteristics = new ArrayList<Characteristic>();
        return characteristics;        
    }
    
    /**
     * Gets the ranking from the user and sets each characteristics' 
     * ranking
     * 
     * @param allChar A list of all the characteristics
     * @param defaultValue The default ranking
     */
    public void getCharacteristicRankings(ArrayList<Characteristic> allChar, int defaultValue) {
        
    }
    
    /**
     * Ranks the choices importance in relation to eachother 
     * for each of the specified characteristics.
     * 
     * @param choices A list of all the choices
     * @param characteristics A list of all the characteristics
     * @param defaultValue The default ranking
     * 
     * @return a 2D array containing the ranking of each choice related to 
     *         each characteristic
     */
    public double [][] getCrossRanking(ArrayList<Choice> choices,
                                ArrayList<Characteristic> characteristics, 
                                int defaultValue) {
        return new double [5][7];
        
    }
    
    /**
     * Displays the final result to the user in the console
     * 
     * @param choices A list of all the choices
     */
    public void showResults(ArrayList<Choice> choices) {
        
    }
}
