

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ChoiceTest.
 *
 * @author (Jesse Nelson) 
 * @version (11/9/2012 : Windows 8(x64) Java 1.7 U9)
 */
public class ChoiceTest {
    /**
     * Tests constructor with bad argument to verify the correct 
     * Exception is thrown.
     */
    @Test
    public void badArgConstructorTest() {
        try {
            Choice test = new Choice(null);
            fail("Choice constructor took a bad argument");
        } catch(NullPointerException e) {
            assertTrue(true);
          }
          catch(Exception e) {
              fail("Incorrect Exception thrown");
          }
    }
    
    /**
     * Proves a Choice object can be instantiated without error
     * if given the correct argument
     */
    @Test
    public void goodArgConstructorTest() {
        try {
            Choice test = new Choice("pizza");
            assertTrue(test.getName() == "pizza");
            assertTrue(test.getFinalScore() == 0);
        } catch(Exception e) {
            fail("Exception should not have been thrown");
          }
    }
    
    /**
     * Verifies the correct name is returned after a 
     * Choice is created 
     */
    @Test
    public void getNameTest() {
        Choice test = new Choice("pizza");
        assertNotNull(test.getName());
        assertTrue(test.getName().getClass() == String.class );
        assertEquals(test.getName(), "pizza");
        
        Choice test2 = new Choice("chicken Alfredo");
        assertNotNull(test2.getName());
        assertTrue(test2.getName().getClass() == String.class );
        assertEquals(test2.getName(), "chicken Alfredo");
        
        Choice test3 = new Choice("chicken con broccli");
        assertNotNull(test3.getName());
        assertTrue(test3.getName().getClass() == String.class );
        assertEquals(test3.getName(), "chicken con broccli");
    }
    
    /**
     * Verifies the final score is correct after a new object
     * is created and after final score is set and retrieved
     * multiple times
     */
    @Test
    public void setAndGetFinalScoreTest() {
        Choice test = new Choice("likability");
        assertTrue(test.getFinalScore() == 0);
        test.setFinalScore(15);
        assertTrue(test.getFinalScore()== 15);
        test.setFinalScore(100);
        assertTrue(test.getFinalScore() == 100);
        test.setFinalScore(25);
        assertTrue(test.getFinalScore() == 25);
        
        Choice test2 = new Choice("number of people");
        assertTrue(test2.getFinalScore() == 0);
        test2.setFinalScore(5);
        assertTrue(test2.getFinalScore() == 5);
        test2.setFinalScore(15);
        assertTrue(test2.getFinalScore() == 15);
        test2.setFinalScore(17);
        assertTrue(test2.getFinalScore() == 17);   
    }
}
