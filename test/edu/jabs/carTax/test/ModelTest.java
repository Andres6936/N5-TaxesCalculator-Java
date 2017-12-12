package edu.jabs.carTax.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import edu.jabs.carTax.domain.Model;
import edu.jabs.carTax.domain.Year;

/**
 * Test class for the model of a car
 */
public class ModelTest extends TestCase
{
    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /**
     * Name of the model
     */
    private String name;
    /**
     * Model of the vehicle
     */
    private Model model;
    /**
     * Year of the vehicle 1
     */
    private Year year1;
    /**
     * Year of the vehicle 2
     */
    private Year year2;

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Scenario with one model and no years
     */
    private void setupScenario1( )
    {
        name = "allegro";
        model = new Model( name );
    }

    /**
     * Scenario with one model and two years
     */
    private void setupScenario2( )
    {
        setupScenario1( );
        year1 = new Year( "2005", 43000000 );
        year2 = new Year( "2004", 40000000 );
        model.addYear( year1 );
        model.addYear( year2 );
    }

    /**
     * Tests the get name method
     */
    public void testGetName( )
    {
        //Sets up the test scenario
        setupScenario1( );

        //Tests that the given name is correct
        assertEquals( name, model.getName( ) );
    }

    /**
     * tests the add year method
     */
    public void testAddYear( )
    {
        ArrayList years;
        Year newYear;
        int before;

        //Sets up the test scenario
        setupScenario2( );

        //Tests the method
        years = model.getYears( );
        before = years.size( );
        newYear = new Year( "2000", 30000000 );
        model.addYear( newYear );
        assertEquals( before + 1, years.size( ) );
        assertEquals( newYear, years.get( before ) );
    }

    /**
     * Tests the get years method when there are no years to give
     */
    public void testGetYearsEmpty( )
    {
        ArrayList years;

        //Sets up the test scenario
        setupScenario1( );

        //tests that there are no years returned
        years = model.getYears( );
        assertEquals( 0, years.size( ) );
    }

    /**
     * tests the get years method when there are years to return
     */
    public void testDarModelos( )
    {
        ArrayList years;
        Year aYear;

        //Sets up the test scenario
        setupScenario2( );

        //tests that the test years are there
        years = model.getYears( );
        assertEquals( 2, years.size( ) );
        aYear = ( Year )years.get( 0 );
        assertEquals( year1, aYear );
        aYear = ( Year )years.get( 1 );
        assertEquals( year2, aYear );
    }

    /**
     * Tests the search for year
     */
    public void testSearchForYearsExisting( )
    {
        Year yearFound;

        //Sets up the test scenario
        setupScenario2( );

        //tests that an existing year is found
        yearFound = model.searchForYearl( year1.getYear( ) );
        assertEquals( year1, yearFound );
    }

    /**
     * tests the search for year when the year doesn't exist
     */
    public void testSearchForYearsNotExisting( )
    {
        Year yearFound;

        //Sets up the test scenario
        setupScenario1( );

        //tests that no model is found
        yearFound = model.searchForYearl( "1800" );
        assertNull( yearFound );
    }
}