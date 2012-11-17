

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CharacteristicTest.
 *
 * @author (Jesse Nelson) 
 * @version (11/9/2012 : Windows 8(x64) Java 1.7 U9)
 */
public class CharacteristicTest {
    /**
     * Tests constructor with bad argument to verify the correct 
     * Exception is thrown.
     */
    @Test
    public void badArgConstructorTest() {
        try {
            Characteristic test = new Characteristic(null);
            fail("Characteristic constructor took a bad argument");
        } catch(NullPointerException e) {
            assertTrue(true);
          }
          catch(Exception e) {
              fail("Incorrect Exception thrown");
          }
    }
    
     /**
     * Proves a Characteristic object can be instantiated without error
     * if given the correct argument.
     */
    @Test
    public void goodArgConstructorTest() {
        try {
            Characteristic test = new Characteristic("price");
            assertTrue(test.getCharacteristicName() == "price");
            assertTrue(test.getRank() == 1);
        } catch(Exception e) {
            fail("Exception should not have been thrown");
          }
    }
    
    /**
     * Verifies the correct characteristic name is returned after a 
     * Characteristic object is created. 
     */
    @Test
    public void getCharactisticNameTest() {
        Characteristic test = new Characteristic("likability");
        assertNotNull(test.getCharacteristicName());
        assertTrue(test.getCharacteristicName().getClass() == String.class );
        assertEquals(test.getCharacteristicName(), "likability");
        
        Characteristic test2 = new Characteristic("price");
        assertNotNull(test2.getCharacteristicName());
        assertTrue(test2.getCharacteristicName().getClass() == String.class );
        assertEquals(test2.getCharacteristicName(), "price");
        
        Characteristic test3 = new Characteristic("number of people");
        assertNotNull(test3.getCharacteristicName());
        assertTrue(test3.getCharacteristicName().getClass() == String.class );
        assertEquals(test3.getCharacteristicName(), "number of people");
    }
    
     /**
     * Verifies the rank is correct after a new object
     * is created and after rank is set and retrieved
     * multiple times.
     */
    @Test
    public void setAndGetRankTest() {
        Characteristic test = new Characteristic("likability");
        assertTrue(test.getRank() == 1);
        test.setRank(10);
        assertTrue(test.getRank() == 10);
        test.setRank(100);
        assertTrue(test.getRank() == 100);
        test.setRank(25);
        assertTrue(test.getRank() == 25);
        
        Characteristic test2 = new Characteristic("number of people");
        assertTrue(test2.getRank() == 1);
        test2.setRank(5);
        assertTrue(test2.getRank() == 5);
        test2.setRank(15);
        assertTrue(test2.getRank() == 15);
        test2.setRank(17);
        assertTrue(test2.getRank() == 17);       
    }
}
