
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class will test UserConsole objects
 *
 * @author (Jesse Nelson) 
 * @version (11/9/2012 : Windows 8(x64) Java 1.7 U9)
 */
public class UserConsoleTest {
    /**
     * Verifies correct list is created after tester is told what to enter
     */
    @Test
    public void getChoicesTest() {
        UserConsole UI = new UserConsole();
        System.out.println("Enter lasagna for your first choice");
        System.out.println("\nEnter alfredo for your second choice");
        System.out.println("\nEnter pizza for your third choice");
        System.out.println("\nEnter d when asked for your fourth choice");
        System.out.println("\n\nWhile entering any of these choices feel free to " +
                            "test the integrity of user error input by not providing " +
                            "the requested info, and pressing enter instead");
        
        List<Choice> test = UI.getChoices();
        assertTrue("There should be tree items in the test list", test.size() == 3);
        assertEquals("First value should be lasagna", test.get(0).getName() , "lasagna"); // Get name could fail but will be tested in the choice class
        assertEquals("Second value should be alfredo", test.get(1).getName() , "alfredo");
        assertEquals("Third value should be pizza", test.get(2).getName() , "pizza");
    }
    
    /**
     * Verifies correct list is created after tester is told what to enter
     */
    @Test
    public void getCharacteristicsTest() {
        UserConsole UI = new UserConsole();
        System.out.println("\nEnter likability for your first characteristic");
        System.out.println("\nEnter price for your second characteristic");
        System.out.println("\nEnter heartburn for your third characteristic");
        System.out.println("\nEnter number of people for your fourth characteristic");
        System.out.println("\nEnter d when asked for your fifth characteristic");
        System.out.println("\n\nWhile entering any of these characteristic feel free to " +
                            "test the integrity of user error input by not providing " +
                            "the requested info, and pressing enter instead");
        
        List<Characteristic> test = UI.getCharacteristics();
        assertTrue("There should be four items in the test list", test.size() == 4);
        
        assertEquals("First value should be likability", 
        test.get(0).getCharacteristicName() , "likability"); // Get name could fail but will be tested in the characteristic class
        
        assertEquals("Second value should be price", 
        test.get(1).getCharacteristicName() , "price");
        
        assertEquals("Third value should be heartburn",
        test.get(2).getCharacteristicName() , "heartburn");
        
        assertEquals("Fourth value should be number of people",
        test.get(3).getCharacteristicName() , "number of people");
    }
    
    /**
     * This test covers the correctness of getCrossRanking() and private method
     * normalizeCrossrankings(). 
     */
    @Test
    public void getCrossRankingTest() {
        UserConsole UI = new UserConsole();
        
        List<Characteristic> characsTest = new ArrayList<Characteristic>();
        characsTest.add(new Characteristic("likability"));
        characsTest.add(new Characteristic("price"));
        characsTest.add(new Characteristic("heartburn"));
        characsTest.add(new Characteristic("number of people"));
        
        List<Choice> choicesTest = new ArrayList<Choice>();
        choicesTest.add(new Choice("lasagna"));
        choicesTest.add(new Choice("alfredo"));
        choicesTest.add(new Choice("pizza"));
        
        
        System.out.println("\nEnter 5 for the first value and increase each of " +
                            "the following values by increments of 5");
        double [][] test = UI.getCrossRanking(choicesTest, characsTest, 10);
        
        assertEquals(test[0][0], .4, .00);
        assertEquals(test[1][0], .2, .00);
        assertEquals(test[2][0], .4, .00);
        
        assertEquals(test[0][1], .222222, .00002);
        assertEquals(test[1][1], .333333, .00003);
        assertEquals(test[2][1], .444444, .00004);
        
        assertEquals(test[0][2], .153846, .00001);
        assertEquals(test[1][2], .384615, .00001);
        assertEquals(test[2][2], .461538, .00001);
        
        assertEquals(test[0][3], .117647, .00001);
        assertEquals(test[1][3], .411764, .00001);
        assertEquals(test[2][3], .470588, .00001);   
    }
    
    /**
     * This tests the void method getCharacteristicRanking()
     */
    @Test
    public void getCharacteristicRankingsTest() {
        UserConsole UI = new UserConsole();
        List<Characteristic> characsTest = new ArrayList<Characteristic>();
        characsTest.add(new Characteristic("likability"));
        characsTest.add(new Characteristic("price"));
        characsTest.add(new Characteristic("heartburn"));
        characsTest.add(new Characteristic("number of people"));
        
        System.out.println("\nenter 5 for price when compared to likability");
        System.out.println("\nenter 20 for heartburn compared to likability");
        System.out.println("\nenter 10 for number of people compared to likability");
        System.out.println("\n\nWhile entering any of these rankings feel free to " +
                            "test the integrity of user error input by not providing " +
                            "the requested info, and pressing enter or providing an alpha instead");  
        
        UI.getCharacteristicRankings(characsTest, 10);
        assertTrue("Characteristic rankings failed ", characsTest.get(0).getRank() == 10);
        assertTrue("Characteristic rankings failed ", characsTest.get(1).getRank() == 5);
        assertTrue("Characteristic rankings failed ", characsTest.get(2).getRank() == 20);
        assertTrue("Characteristic rankings failed ", characsTest.get(3).getRank() == 10);
    }
    
}
