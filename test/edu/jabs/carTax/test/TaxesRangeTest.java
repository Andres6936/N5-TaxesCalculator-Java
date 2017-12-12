package edu.jabs.carTax.test;

import junit.framework.TestCase;
import edu.jabs.carTax.domain.TaxesRange;

/**
 * Test class for the taxes range
 */
public class TaxesRangeTest extends TestCase
{

    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /**
     * TAxes range
     */
    private TaxesRange range;
    /**
     * Starting value
     */
    private double start;
    /**
     * End value
     */
    private double end;
    /**
     * Percentage applicable to that range
     */
    private double percent;
    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Creates a test scenario configuring a range
     */
    private void setupScenario1( )
    {
        start = 0;
        end = 100000000;
        percent = 5;
        range = new TaxesRange( start, end, percent );
    }

    /**
     * tests the lower limit of the range
     */
    public void testHasLowerLimit( )
    {
        //Configures the test scenario
        setupScenario1( );

        //Tests that the lower limit is within the range
        assertTrue( range.isWithin( start ) );
    }

    /**
     * tests the upper limit of the range
     */
    public void testHasUpperLimit( )
    {
        //Configures the test scenario
        setupScenario1( );

        //Tests that the upper limit is not within the range
        assertFalse( range.isWithin( end ) );
    }

    /**
     * tests that the range contains the value in the middle
     */
    public void testHasMiddleValue( )
    {
        //Configures the test scenario
        setupScenario1( );

        //Tests that the middle value is within the range
        assertTrue( range.isWithin( ( start + end ) / 2 ) );
    }

    /**
     * tests the get percent method
     */
    public void testGetPercent( )
    {
        //Configures the test scenario
        setupScenario1( );

        //Tests that the percent is correct
        assertEquals( percent, range.getPercent( ), 0 );
    }

}
