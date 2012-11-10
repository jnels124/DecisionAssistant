import java.util.List;
import java.util.ArrayList;
/**
 * This class will contain the main method and manage
 * the DecisionAssistant
 * 
 * @author (Jesse Nelson) 
 * @version (November 9, 2012 : Windows 8(x64) Java 1.7 U9)
 */
public class DecisionAssistant {
    
    public static void main(String args []) {
        final int STANDARD = 10;
        UserInterface UI;
        List<Choice> choices;
        List<Characteristic> characteristics;
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
    
    public static void calculateFinalScores(List<Choice> choices,  
                            List<Characteristic> characteristics, 
                                        double [][]crossRankings) {
       double largestValue = 0;
       double value = 0.0;
       double [] tmpValues = new double[choices.size()];
       for(int i = 0; i < choices.size(); i++) {
           for(int j = 0; j < characteristics.size(); j++) {
               value += (crossRankings[j][i] * characteristics.get(j).getRank());
           }
           
           if(value > largestValue) {
               largestValue = value;
           }
           
           tmpValues[i] = value;
       }
       
       for(int i = 0; i < tmpValues.length; i++) {
           choices.get(i).setFinalScore((int)((tmpValues[i] / largestValue) * 100));
       }
       
    }
    
    public static double [][] normalizeCrossRankings(double [][] rankings, int sum, int row) {
        for(int i = 0; i < rankings.length; i++) {
            rankings[row][i] /= sum; 
        }
        return rankings;
    }

}
