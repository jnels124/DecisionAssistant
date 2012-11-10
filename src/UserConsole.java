import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class uses the console to display information to, and obtain information from, the user
 * 
 * @author (Jesse Nelson) 
 * @version (11/9/2012 : Windows 8(x64) Java 1.7 U9)
 */
public class UserConsole implements UserInterface {
    private String userInput;
    private List<Choice> choices;
    private List<Characteristic> characteristics;
    private Scanner console;
    
    public UserConsole() {
        this.choices = new ArrayList<Choice>();
        this.characteristics = new ArrayList<Characteristic>();
        this.userInput = "";
        this.console = new Scanner(System.in);
    }
    
    /**
     * Uses console to get all of the choices being considered by the user
     * 
     * @return list of choices 
     */
    public List<Choice> getChoices() {
        System.out.println("What is one of your choices?" +
                         "\nType d if you are finished entering choices.");
        this.userInput = console.nextLine();
        
        if(this.userInput.toLowerCase().charAt(0) == 'd') {
            return this.choices;
        } 
        
        this.choices.add(new Choice(userInput));
        return getChoices();        
    }
    
    /**
     * Uses console to get all of the characteristics related to the choices
     * 
     * @return list of characteristics
     */
    public List<Characteristic> getCharacteristics() {
        System.out.println("What is one of the characteristics you are considering?" +
                         "\nType d if you are finished entering characteristics.");
        this.userInput = console.nextLine();
        
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
        for(int i = 1; i < allChar.size(); i++) {
            System.out.println("If " + allChar.get(0).getCharacteristicName() + " has an importance of " +
                                defaultValue + ".\nWhat importance does " + 
                                allChar.get(i).getCharacteristicName() + " have?");
            userInput = console.nextLine();
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
        double [][] crossRankings = new double [characteristics.size()][choices.size()];
        
        for(int i = 0; i < characteristics.size(); i++) {
            int sum = 0;
            for(int j = 0; j < choices.size(); j++) { 
                System.out.println("Considering " + characteristics.get(i).getCharacteristicName() + 
                                    " only, if " + choices.get(0).getName() + " is ranked " + defaultValue + 
                                    " what would you rank " + choices.get(j).getName() + "?");
                userInput = console.nextLine(); 
                sum += Integer.parseInt(userInput);
                crossRankings[i][j] = Integer.parseInt(userInput);
            } 
            crossRankings = DecisionAssistant.normalizeCrossRankings(crossRankings, sum, i);
        }
        return crossRankings;        
    }
    
    /**
     * Displays the final result to the user in the console
     * 
     * @param choices A list of all the choices
     */
    public void showResults(List<Choice> choices) {
        System.out.println("The final results are: \n");
        for(int i = 0; i < choices.size(); i++ ) {
            System.out.println(choices.get(i).getName() + " = " + choices.get(i).getFinalScore()) ;    
        }
           
    }
}
