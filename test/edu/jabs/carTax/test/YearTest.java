package edu.jabs.carTax.test;

import junit.framework.TestCase;
import edu.jabs.carTax.domain.Year;

/**
 * Test class for the year of a vehicle
 */
public class YearTest extends TestCase
{
    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /**
     * the year
     */
    private String theYear;
    /**
     * the price
     */
    private double price;
    /**
     * the year object
     */
    private Year year;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Test scenario for the year
     */
    private void setupScenario1( )
    {
        theYear = "2005";
        price = 43000000;
        year = new Year( theYear, price );
    }

    /**
     * tests the get year method
     */
    public void testGetYear( )
    {
        //sets up the test scenario
        setupScenario1( );

        //Validates the result
        assertEquals( theYear, year.getYear( ) );
    }

    /**
     * Prueba la obtención válida del precio de un modelo
     */
    public void testGetPrice( )
    {
        //sets up the test scenario
        setupScenario1( );

        //Validates the result
        assertEquals( price, year.getPrice( ), 0 );
    }
}
