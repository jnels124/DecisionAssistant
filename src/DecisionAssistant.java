
import java.util.List;
import java.util.ArrayList;

/**
 * This class will contain the main method and manage
 * the DecisionAssistant
 * 
 * A conditional checks to determine if character c was passed in 
 * from the command line. If so, the display will be through console.
 * Otherwise display will be through dialog boxes;
 * 
 * @author (Jesse Nelson) 
 * @version (November 9, 2012 : Windows 8(x64) Java 1.7 U9)
 */
public class DecisionAssistant {
    /**
     * Obtains choices, characteristics, and cross rankings amongst these
     * values. Then calculates the final result and displays the information
     * to the user.     
     */
    public static void main(String args []) {
        /**
         * Base value for rankings
         */
        final int STANDARD = 10;
        
        /**
         * Input/output display
         */
        UserInterface UI;
        
        /**
         * List of choices being considered
         */
        List<Choice> choices;
        
        /**
         * List of characteristics being considered
         */
        List<Characteristic> characteristics;
        
        /**
         * Cross rankings of choices and characteristics
         */
        double [][] crossRankings;
        
        if(args.length == 1 && args[0].toLowerCase().charAt(0) == 'c') {
            UI = new UserConsole();
        } 
        
        else {
            UI = new UserGUI();
        }
        
        choices = UI.getChoices();
        characteristics = UI.getCharacteristics();
        UI.getCharacteristicRankings(characteristics, STANDARD);
        crossRankings = UI.getCrossRanking(choices, characteristics, STANDARD); 
        calculateFinalScores(choices, characteristics, crossRankings);
        UI.showResults(choices);
        
    }
    
    /**
     * Calculates the final score and sets the
     * finalScore attribute for each choice 
     */
    public static void calculateFinalScores(List<Choice> choices,  
                            List<Characteristic> characteristics, 
                                        double [][]crossRankings) {
       double largestValue= 0;
       double value = 0.0;
       double [] tmpValues = new double[choices.size()];
       
       for(int i = 0; i < choices.size(); i++) {
           value = 0.0;
           // calculate the sum of all values for each row
           for(int j = 0; j < characteristics.size(); j++) {
               value += (crossRankings[i][j] * characteristics.get(j).getRank());
           }
           
           if(value > largestValue) { // Keep track of largest row sum
               largestValue = value;
           }
           
           tmpValues[i] = value;
       }
       
       // calculates and sets final score for each choice
       for(int i = 0; i < tmpValues.length; i++) {
           choices.get(i).setFinalScore((int)((tmpValues[i] / largestValue) * 100));
       }
       
    }
}
