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

    /**
     * Input obtained from user
     */
    private String userInput;
    
    /**
     * Initialize instance variables
     */
    public UserGUI() {
        this.userInput = "";
    }
    
    /**
     * Uses a GUI to get all of the choices being considered by the user
     * 
     * --Runs an infinite loop until ending conditions are met
     * 
     * @return list of choices 
     */
    public List<Choice> getChoices() {
        List<Choice> choices = new ArrayList<Choice> ();
        
        do {
            this.userInput = JOptionPane.showInputDialog
            ("What is one of your choices? \nType d if you are finished entering choices.");
            
            if(this.userInput == null || this.userInput.length() == 0) {
                this.userInput = verifyNotEmptyOrNull
                ("What is one of your choices? \nType d if you are finished entering choices.",
                this.userInput);
            }
            
            if(this.userInput.toLowerCase().charAt(0) == 'd' && this.userInput.length() == 1) {
               return choices; 
            }
            
            choices.add(new Choice(this.userInput));
            
        } while(true); // Gets choices until user is finished
      
    }
    
    /**
     * Uses a GUI to get all of the characteristics related to the choices
     * --Runs an infinite loop until ending conditions are met
     * 
     * @return list of characteristics
     */
    public List<Characteristic> getCharacteristics() {
       List<Characteristic> characteristics = new ArrayList<Characteristic>();
       
       do {
           this.userInput = JOptionPane.showInputDialog
            ("What is one of the characteristics?" +
             "\nType d if you are finished entering characteristics.");
            
           if(this.userInput == null || this.userInput.length() == 0) {
                this.userInput = verifyNotEmptyOrNull
                ("What is one of the characteristics?" +
                  "\nType d if you are finished entering characteristics.", 
                   this.userInput);
            }
            
           if(this.userInput.toLowerCase().charAt(0) == 'd' && this.userInput.length() == 1) {
               return characteristics; 
            }
            
           characteristics.add(new Characteristic(this.userInput));
            
       } while(true); // Gets choices until user is finished
                          
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
                                 
            if(this.userInput == null || this.userInput.length() == 0){
                this.userInput = verifyNotEmptyOrNull
                ("If " + allChar.get(0).getCharacteristicName() +
                  " has an importance of " + defaultValue + ".\nWhat importance does " + 
                  allChar.get(i).getCharacteristicName() + " have?", this.userInput);
            }
                    
            if(!Character.isDigit(this.userInput.charAt(0))) {
                this.userInput = verifyStringIsNumber
                ("If " + allChar.get(0).getCharacteristicName() +
                  " has an importance of " + defaultValue + ".\nWhat importance does " + 
                  allChar.get(i).getCharacteristicName() + " have?", this.userInput);         
            }
            
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
                                          
        double [][] crossRankings = new double [choices.size()][characteristics.size()];
        
        for(int i = 0; i < characteristics.size(); i++) {
            int sum = 0;
            for(int j = 0; j < choices.size(); j++) { 
                if(j == 0) {
                    crossRankings[j][i] = defaultValue;
                    sum += defaultValue;
                }
                
                else {
                    this.userInput = JOptionPane.showInputDialog(
                    "Considering " + characteristics.get(i).getCharacteristicName() +
                    " only, if " + choices.get(0).getName()  + " is ranked " + defaultValue +
                    " what would you rank " +  choices.get(j).getName() + "?");                                                               
                                                  
                    if(this.userInput == null || this.userInput.length() == 0){
                        this.userInput = verifyNotEmptyOrNull
                        ("Considering " +  characteristics.get(i).getCharacteristicName() +
                         " only, if " + choices.get(0).getName()  + " is ranked " + defaultValue +
                         " what would you rank " + choices.get(j).getName() + "?", this.userInput); 
                    }
                    
                    if(!Character.isDigit(this.userInput.charAt(0))) {      
                        this.userInput = verifyStringIsNumber
                        ("Considering " +  characteristics.get(i).getCharacteristicName() +
                         " only, if " + choices.get(0).getName()  + " is ranked " + defaultValue +
                         " what would you rank " + choices.get(j).getName() + "?", this.userInput);  
                    }
                    
                    sum += Integer.parseInt(userInput);
                    crossRankings[j][i] = Integer.parseInt(userInput);
                }
            }
       
            crossRankings = normalizeCrossRankings(crossRankings, sum, i);
            
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
        
        JOptionPane.showMessageDialog(null, "The final results are: \n" + results);
    }
    
    /**
     * Normalizes the cross rankings
     * 
     * @param rankings Cross rankings of attributes and choices
     * @param sum Sum of all rankings for a single attribute
     * @param column Current column being 
     * 
     * @return 2D array of normalized rankings
     */
    private double [][] normalizeCrossRankings(double [][] rankings, int sum, int column) {
        
        for(int i = 0; i < rankings.length; i++) {
            rankings[i][column] /= sum; 
        }
       
        return rankings;
        
    }
    
    /**
     * Verifies that the user has proivded usable input
     * 
     * @param msg Message to display
     * @param userInput current bad value
     * 
     * @return usable input
     */
    private String verifyNotEmptyOrNull(String msg, String userInput) {
        do {
            int n = JOptionPane.showConfirmDialog( 
                            null,  "Would you like to quit the current program?", 
                            "Quit", JOptionPane.YES_NO_OPTION);
                            
            if (n == JOptionPane.NO_OPTION) {
                userInput = JOptionPane.showInputDialog(msg);                                         
            } 
            
            else {
                System.exit(0);                                                     
            }        
                     
        } while (userInput == null || userInput.length() == 0);
        
        return userInput;
    
    }
    
    /**
     * Verifies that the user has proivded usable input as a digit
     * 
     * @param msg Message to display
     * @param userInput current bad value
     * 
     * @return usable input
     */
    private String verifyStringIsNumber(String msg, String userInput) {
        do {
            int n = JOptionPane.showConfirmDialog( 
                            null,  "Would you like to quit the current program?", 
                            "Quit", JOptionPane.YES_NO_OPTION);
                            
            if (n == JOptionPane.NO_OPTION) {
                userInput = JOptionPane.showInputDialog(msg);                                         
            } 
            
            else {
                System.exit(0);                                                     
            }        
                     
        } while (userInput == null || userInput.length() == 0 ||
                 !Character.isDigit(userInput.charAt(0)));
        
        return userInput;
    
    }
}
