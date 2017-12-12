package edu.jabs.carTax.test;

import junit.framework.TestCase;
import edu.jabs.carTax.domain.TaxesCalculator;

/**
 * Test class for the taxes calculator
 */
public class TaxesCalculatorTest extends TestCase
{
    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /**
     * Taxes Calculator
     */
    private TaxesCalculator calculator;
    /**
     * Test brand
     */
    private String brand;
    /**
     * test model
     */
    private String model;
    /**
     * test year
     */
    private String year;
    /**
     * test price
     */
    private double price;
    /**
     * test payment
     */
    private double payment;

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Sets up a test scenario with the calculator
     */
    private void setupScenario1( )
    {
        try
        {
            //Creates and prepares the test calculator
            calculator = new TaxesCalculator( );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }

    /**
     * Sets up a test scenario with the info of a non existent vehicle
     */
    private void setupScenario2( )
    {
        setupScenario1( );
        brand = "a brand";
        model = "a model";
        year = "1800";
        price = -1;
        payment = -1;
    }

    /**
     * Sets up a test scenario with the info of an existent vehicle
     */
    private void setupScenario3( )
    {
        setupScenario1( );
        brand = "Mazda";
        model = "Allegro";
        year = "2005";
        price = 43000000;
        payment = 860000;
    }

    /**
     * Tests the find price method with a car that doesn't exist
     */
    public void testFindPriceNotExistantVehicle( )
    {
        double foundPrice;

        //Sets up the test scenario
        setupScenario2( );

        try
        {
            foundPrice = calculator.findVehiclePrice( brand, model, year );
            fail( "Must have thrown an exception because the vehicle doesn't exist. the given price was" + foundPrice );
        }
        catch( Exception e )
        {
            assertTrue( true );
        }

    }

    /**
     * Tests the find price method with a car that exists
     */
    public void testFindPriceExistantVehicle( )
    {
        double foundPrice = -1;

        //Sets up the test scenario
        setupScenario3( );

        try
        {
            foundPrice = calculator.findVehiclePrice( brand, model, year );
            assertEquals( "Prices equal", price, foundPrice, 0 );
        }
        catch( Exception e )
        {
            fail( "Error searching for the vehicle " + brand + "-" + model + "-" + year );
        }

    }

    /**
     * Tests the calculate payment method with a car that doesn't exist
     */
    public void testCalculatePriceNotExistentVehicle( )
    {
        double foundPrice = -1;

        //Sets up the test scenario
        setupScenario2( );

        try
        {
            foundPrice = calculator.calculatePayment( brand, model, year, false, false, false );
            fail( "ust have thrown an exception because the vehicle doesn't exist. the given payment was" + foundPrice );
        }
        catch( Exception e )
        {
            assertTrue( true );
        }
    }

    /**
     *  Tests the calculate payment method with a car that exists without applying the discounts
     */
    public void testCalculatePriceExistentVehicleNoDiscounts( )
    {
        double foundPrice = -1;

        //Sets up the test scenario
        setupScenario3( );

        try
        {
            foundPrice = calculator.calculatePayment( brand, model, year, false, false, false );
            assertEquals( "Equal payments", payment, foundPrice, 0 );
        }
        catch( Exception e )
        {
            assertTrue( true );
        }
    }

    /**
     * Tests the calculate payment method with a car that exists with the immediate payment discount
     */
    public void testCalculatePriceExistentVehicleImmediatePay( )
    {
        double foundPrice = -1;

        //Sets up the test scenario
        setupScenario3( );

        payment -= payment * TaxesCalculator.PRCNT_DISC_IMMEDIATE_PAY / 100;

        try
        {
            foundPrice = calculator.calculatePayment( brand, model, year, true, false, false );
            assertEquals( "Equal payments", payment, foundPrice, 0 );
        }
        catch( Exception e )
        {
            assertTrue( true );
        }
    }

    /**
     * Tests the calculate payment method with a car that exists with the public services discount;
     */
    public void testCalculatePriceExistentVehiclePublicServices( )
    {
        double foundPrice = -1;

        //Sets up the test scenario
        setupScenario3( );

        payment -= TaxesCalculator.TOTAL_DISC_PUBLIC_SERVICES;

        try
        {
            foundPrice = calculator.calculatePayment( brand, model, year, false, true, false );
            assertEquals( "Equal payments", payment, foundPrice, 0 );
        }
        catch( Exception e )
        {
            assertTrue( true );
        }
    }

    /**
     * Tests the calculate payment method with a car that exists with the wire transfer discount;
     */
    public void testCalculatePriceExistentVehicleWireTransfer( )
    {
        double foundPrice = -1;

        //Sets up the test scenario
        setupScenario3( );

        payment -= payment * TaxesCalculator.PRCNT_DISC_WIRE_TRANSFER / 100;

        try
        {
            foundPrice = calculator.calculatePayment( brand, model, year, false, false, true );
            assertEquals( "Equal payments", payment, foundPrice, 0 );
        }
        catch( Exception e )
        {
            assertTrue( true );
        }
    }

    /**
     * Tests the calculate payment method with a car that exists with all discounts
     */
    public void testCalculatePriceExistentVehicleAllDiscounts( )
    {
        double foundPrice = -1;

        //Sets up the test scenario
        setupScenario3( );

        payment -= payment * TaxesCalculator.PRCNT_DISC_IMMEDIATE_PAY / 100;
        payment -= TaxesCalculator.TOTAL_DISC_PUBLIC_SERVICES;
        payment -= payment * TaxesCalculator.PRCNT_DISC_WIRE_TRANSFER / 100;

        try
        {
            foundPrice = calculator.calculatePayment( brand, model, year, true, true, true );
            assertEquals( "Equal payments", payment, foundPrice, 0 );
        }
        catch( Exception e )
        {
            assertTrue( true );
        }
    }
}
