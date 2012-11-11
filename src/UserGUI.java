import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
 
/**
 * This class uses a GUI to display information to, and obtain information from, the user
 * 
 * @author (Jesse Nelson) 
 * @version (11/9/2012 : Windows 8(x64) Java 1.7 U9)
 */
public class UserGUI implements UserInterface  {
    private String userInput;
    private List<Choice> choices;
    private List<Characteristic> characteristics;
    
    public UserGUI() {
        this.choices = new ArrayList<Choice>();
        this.characteristics = new ArrayList<Characteristic>();
        this.userInput = "";
    }
    
    /**
     * Uses a GUI to get all of the choices being considered by the user
     * 
     * @return list of choices 
     */
    public List<Choice> getChoices() {
        this.userInput = JOptionPane.showInputDialog("What is one of your choices?" +
                                     "\nType d if you are finished entering choices.");
                                     
        if(this.userInput.toLowerCase().charAt(0) == 'd') {
            return this.choices;
        } 
        
        this.choices.add(new Choice(userInput));
        return getChoices();  
    }
    
    /**
     * Uses a GUI to get all of the characteristics related to the choices
     * 
     * @return list of characteristics
     */
    public List<Characteristic> getCharacteristics() {
        this.userInput = JOptionPane.showInputDialog("What is one of the characteristics?" +
                                     "\nType d if you are finished entering characteristics.");
                                     
        if(this.userInput.toLowerCase().charAt(0) == 'd') {
            return this.characteristics;
        } 
        
        this.characteristics.add(new Characteristic(userInput));
        return getCharacteristics();
    }
    
    /**
     * Gets the ranking from the user and sets each characteristics' 
     * ranking
     * 
     * @param allChar A list of all the characteristics
     * @param defaultValue The default ranking
     */
    public void getCharacteristicRankings(List<Characteristic> allChar, int defaultValue) {
        allChar.get(0).setRank(defaultValue);
        for(int i = 1; i < allChar.size(); i++) {
            userInput = JOptionPane.showInputDialog("If " + allChar.get(0).getCharacteristicName() +
                                " has an importance of " + defaultValue + ".\nWhat importance does " + 
                                 allChar.get(i).getCharacteristicName() + " have?");
           
            allChar.get(i).setRank(Integer.parseInt(userInput)); 
        }
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
    public double [][] getCrossRanking(List<Choice> choices,
                                       List<Characteristic> characteristics, 
                                       int defaultValue) {
                                           
        //double [][] crossRankings = new double [characteristics.size()][choices.size()];
        double [][] crossRankings = new double [choices.size()][characteristics.size()];
        
        System.out.println(choices.size());
        /*for(int i = 0; i < choices.size(); i++) {
            for(int j = 0; j < characteristics.size(); j++) {
                
            }
        }*/
        
        for(int i = 0; i < characteristics.size(); i++) {
            int sum = 0;
            for(int j = 0; j < choices.size(); j++) { 
                if(j == 0) {
                    crossRankings[j][i] = 10;
                    sum += defaultValue;
                }
                else {
                    userInput = JOptionPane.showInputDialog("Considering " + 
                                            characteristics.get(i).getCharacteristicName() +
                                                 " only, if " + choices.get(0).getName()  + 
                                                  " is ranked " + defaultValue +
                                                  " what would you rank " +
                                                  choices.get(j).getName() + "?");
                    sum += Integer.parseInt(userInput);
                    crossRankings[j][i] = Integer.parseInt(userInput);
                    System.out.print(crossRankings[j][i]);
                }
            }
            for(int k = 0; k < crossRankings.length; k++) {
            for(int p = 0; p < crossRankings[0].length; p++) {
                
                System.out.print("before" + crossRankings[k][p]);
            }
        }
            //System.out.print(crossRanking[j][i]);
            /*System.out.println("row length" + crossRankings[0].length);
            System.out.println("column length" + crossRankings.length);*/
            crossRankings = normalizeCrossRankings(crossRankings, sum, i);
            
            //System.out.print("afternormalization"+crossRankings);
        }
        
        return crossRankings;    
    }
    
    /**
     * Displays the final result to the user in a GUI
     * 
     * @param choices A list of all the choices
     */
    public void showResults(List<Choice> choices) {
        String results = "";
        for(int i = 0; i < choices.size(); i++ ) {
            results += choices.get(i).getName() + " = " + 
                       choices.get(i).getFinalScore() + "\n";  
        }
        
        JOptionPane.showMessageDialog(null, results);
    }
    
    private double [][] normalizeCrossRankings(double [][] rankings, int sum, int column) {
        //rankings[row][0] = 10 / sum;
        for(int i = 0; i < rankings.length; i++) {
            System.out.println("column length" + rankings[0].length);
            System.out.println("column " + column + " i " + i);
            System.out.println("row length" + rankings.length);
            rankings[i][column] /= sum; 
            //System.out.print("afternormalization" + rankings[i][column]);
        }
        for(int i = 0; i < rankings.length; i++) {
            for(int j = 0; j < rankings[0].length; j++) {
                
                System.out.println("afternormalization" + rankings[i][j]);
            }
        }
        return rankings;
    }
}
