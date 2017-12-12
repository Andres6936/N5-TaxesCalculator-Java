package edu.jabs.carTax.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import edu.jabs.carTax.domain.Brand;
import edu.jabs.carTax.domain.Model;
import edu.jabs.carTax.domain.Year;

/**
 * Test class for a vehicle brand
 */
public class BrandTest extends TestCase
{

    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /**
     * Name of the test brand
     */
    private String name;
    /**
     * test brand
     */
    private Brand brand;
    /**
     * model of the test brand
     */
    private Model model;
    /**
     * test year
     */
    private Year year;

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Scenario for a brand without models
     */
    private void setupScenario1( )
    {
        name = "mazda";
        brand = new Brand( name );
    }

    /**
     * Test Scenario for a brand with a model and no years
     */
    private void setupScenario2( )
    {
        setupScenario1( );
        model = new Model( "allegro" );
        brand.addModel( model );
    }

    /**
     * Test Scenario for a brand with a model and a year
     */
    private void setupScenario3( )
    {
        setupScenario2( );
        year = new Year( "2005", 43000000 );
        model.addYear( year );
    }

    /**
     * Tests the get name method
     */
    public void testGetName( )
    {
        //Sets up the test Scenario
        setupScenario1( );

        //test that the right name is returned
        assertEquals( name, brand.getName( ) );
    }

    /**
     * tests the add model method
     */
    public void testAddModel( )
    {
        ArrayList models;
        Model newModel;
        int before;

        //Sets up the test Scenario
        setupScenario1( );

        //Tests that the model is added
        models = brand.getModels( );
        before = models.size( );
        newModel = new Model( "mazda3" );
        brand.addModel( newModel );
        assertEquals( before + 1, models.size( ) );
        assertEquals( newModel, models.get( before ) );
    }

    /**
     * tests the get model method when there are no models
     */
    public void testGetModelsEmpty( )
    {
        ArrayList models;

        //Sets up the test Scenario
        setupScenario1( );

        //tests that there are no models returned
        models = brand.getModels( );
        assertEquals( 0, models.size( ) );
    }

    /**
     * tests the get model method when there are models
     */
    public void testGetModels( )
    {
        ArrayList models;
        Model aModel;
        Year aYear;

        //Sets up the test Scenario
        setupScenario3( );

        //tests that the test model is found
        models = brand.getModels( );
        assertEquals( 1, models.size( ) );
        aModel = ( Model )models.get( 0 );
        assertEquals( model, aModel );
        aYear = aModel.searchForYearl( year.getYear( ) );
        assertEquals( year, aYear );
    }

    /**
     * tests the search for model method
     */
    public void testSearchForModelExists( )
    {
        Model foundModel;

        //Sets up the test Scenario
        setupScenario2( );

        //tests that the test model is in the brand
        foundModel = brand.searchForModel( model.getName( ) );
        assertEquals( model, foundModel );
    }

    /**
     * tests the search for model method when the model doesn't exist
     */
    public void testSearchForModelNotExists( )
    {
        Model foundModel;

        //Sets up the test Scenario
        setupScenario1( );

        //Tests that the model isn't found
        foundModel = brand.searchForModel( "blablabla" );
        assertNull( foundModel );
    }
}
