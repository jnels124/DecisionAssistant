
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DecisionAssistantTest.
 * -- Tests calculateFinalScore()
 * 
 * @author (Jesse Nelson) 
 * @version (11/9/2012 : Windows 8(x64) Java 1.7 U9) 
 */
public class DecisionAssistantTest {
    @Test
    public void calculateFinalScoresTest() {
        List<Choice> choicesTest = new ArrayList<Choice>();
        choicesTest.add(new Choice("lasagna"));
        choicesTest.add(new Choice("alfredo"));
        choicesTest.add(new Choice("pizza"));
        
        List<Characteristic> characsTest = new ArrayList<Characteristic>();
        characsTest.add(new Characteristic("likability"));
        characsTest.add(new Characteristic("price"));
        characsTest.add(new Characteristic("heartburn"));
        characsTest.add(new Characteristic("number of people"));
        
        characsTest.get(0).setRank(10);
        characsTest.get(1).setRank(5);
        characsTest.get(2).setRank(20);
        characsTest.get(3).setRank(10);
        
        double [][] crossRankingsTest = {
            { .4, .222222, .153846, .117647 },
            { .2, .333333, .384615, .411764 },
            { .4, .444444, .461538, .470588 } 
        };
        
        assertEquals(choicesTest.get(0).getFinalScore(), 0, 0);
        assertEquals(choicesTest.get(1).getFinalScore(), 0, 0);
        assertEquals(choicesTest.get(2).getFinalScore(), 0, 0);
        
        DecisionAssistant.calculateFinalScores(choicesTest, characsTest, crossRankingsTest);
        assertEquals(choicesTest.get(0).getFinalScore(), 46, 0);
        assertEquals(choicesTest.get(1).getFinalScore(), 76, 0);
        assertEquals(choicesTest.get(2).getFinalScore(), 100, 0);
    }
   
}
