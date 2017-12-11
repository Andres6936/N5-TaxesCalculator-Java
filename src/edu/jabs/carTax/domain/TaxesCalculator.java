package edu.jabs.carTax.domain;

import java.io.*;
import java.util.*;

/**
 * Taxes Calculator
 */
public class TaxesCalculator
{
    //-----------------------------------------------------------------
    // Constants
    //-----------------------------------------------------------------

    /** Percent of discount for an immediate pay */
    public static final double PRCNT_DISC_IMMEDIATE_PAY = 10.0;
    /** total discount for public services */
    public static final double TOTAL_DISC_PUBLIC_SERVICES = 50000.0;
    /** Percent of discount for a wire transfer */
    public static final double PRCNT_DISC_WIRE_TRANSFER = 5.0;

    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /** Brands of vehicles that the calculator knows */
    private ArrayList brands;
    /** Tax ranges */
    private ArrayList taxRanges;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * Creates a tax calculator, loading data from 2 files <br>
     * <b>post: </b> the information on vehicles and taxes was uploaded correctly and both were successfully initialized.
     * @throws Exception when loading the files.
     */
    public TaxesCalculator( ) throws Exception
    {
        brands = new ArrayList( );
        taxRanges = new ArrayList( );
        loadVehicles( "data/vehicles.txt" );
        loadTaxesTable( "data/taxes.properties" );
    }

    //----------------------------------------------------------------
    // Methods
    //----------------------------------------------------------------

    /**
     * Searches for a brand given its name. <b>pre: </b> the set of brands has already been created and initialized.
     * @param name Name of the brand. name != null.
     * @return the brand if it is found, null otherwise.
     */
    private Brand searchForBrand( String name )
    {
        Brand brand = null;
        for( int i = 0; i < brands.size( ) && brand == null; i++ )
        {
            Brand auxBrand = ( Brand )brands.get( i );
            if( auxBrand.getName( ).equalsIgnoreCase( name ) )
                brand = auxBrand;
        }
        return brand;
    }

    /**
     * Adds a new brand of vehicles for which the calculator can calculate the taxes. if it already exists it isn't added. <br>
     * <b>post: </b> if it doesn't exist, the brand is added to the set.
     * @param aBrand Brand to be added. aBrand != null.
     */
    private void addBrand( Brand aBrand )
    {
        if( searchForBrand( aBrand.getName( ) ) == null )
            brands.add( aBrand );
    }

    /**
     * Loads the information about the vehicles that the calculator can calculate taxes for. <br>
     * <b>post: </b> The information on all the vehicles is loaded from the files.
     * @param theFIle Name of the file where the information on the vehicles is stored. theFIle != null.
     * @throws Exception if there is an error while loading the vehicles.
     */
    private void loadVehicles( String theFIle ) throws Exception
    {
        Brand brand;
        Model model;
        Year year;
        String text, values[], sBrand, sModel, sYear;
        double price;
        BufferedReader reader;
        try
        {
            File theData = new File( theFIle );
            reader = new BufferedReader( new FileReader( theData ) );
            text = reader.readLine( );
        }
        catch( Exception e )
        {
            throw new Exception( "error while loading the information of the vehicles from the file" );
        }

        while( text != null )
        {
            //if the line starts with # its a commentary and should be ignored
            if( !text.startsWith( "#" ) && !text.equals( "" ) )
            {
                //Reads the data
                values = text.split( "," );

                if( values.length < 3 )
                    throw new Exception( "There is information missing on the line: " + text );

                sBrand = values[ 0 ];
                sModel = values[ 1 ];
                sYear = values[ 2 ];
                try
                {
                    price = Double.parseDouble( values[ 3 ] );
                }
                catch( Exception e )
                {
                    throw new Exception( "the price should be a numeric value: " + values[ 3 ] );
                }
                //Creates the configuration for a vehicle
                //looks for or creates the brand
                brand = searchForBrand( sBrand );
                if( brand == null )
                {
                    brand = new Brand( sBrand );
                    addBrand( brand );
                }
                //the method searches for the model within the brand and creates it if it isn't found
                model = brand.searchForModel( sModel );
                if( model == null )
                {
                    model = new Model( sModel );
                    brand.addModel( model );
                }
                //the method searches for the year within the model and creates it if it isn't found
                year = model.searchForYearl( sYear );
                if( year == null )
                {
                    year = new Year( sYear, price );
                    model.addYear( year );
                }
            }
            try
            {
                //next line
                text = reader.readLine( );
            }
            catch( Exception e )
            {
                throw new Exception( "Error while loading the data from the vehicles file" );
            }
        }
    }

    /**
     * loads the table with the taxes information discriminated by ranges. <br>
     * <b>post: </b> the values of taxes, according to their ranges, is loaded.
     * @param theFile location of the file to be read. theFile != null.
     * @throws Exception if there is a problem loading the file.
     */
    private void loadTaxesTable( String theFile ) throws Exception
    {
        Properties data = new Properties( );
        int ranges = 0;
        String text, values[];
        double start, end, percent;
        TaxesRange range;
        try
        {
            FileInputStream input = new FileInputStream( theFile );
            data.load( input );
        }
        catch( Exception e )
        {
            throw new Exception( "EError while loading the tax ranges" );
        }

        try
        {
            ranges = Integer.parseInt( data.getProperty( "number.ranges" ) );
        }
        catch( Exception e )
        {
            throw new Exception( "the number of tax ranges is invalid" );
        }

        //Loads all the ranges
        for( int i = 1; i <= ranges; i++ )
        {
            text = data.getProperty( "range" + i );
            if( text == null )
                throw new Exception( "The definition of the range is missing" + i );
            values = text.split( "," );
            try
            {
                start = Double.parseDouble( values[ 0 ] );
                end = Double.parseDouble( values[ 1 ] );
                percent = Double.parseDouble( values[ 2 ] );
            }
            catch( Exception e )
            {
                throw new Exception( "Error in the definition of the range" + i );
            }

            range = new TaxesRange( start, end, percent );
            //Adds the range
            taxRanges.ensureCapacity( 1 );
            taxRanges.add( i - 1, range );
        }
    }

    /**
     * finds the range that contains the given value
     * @param value Value that were looking for.
     * @return Taxes range that contains the value, or null if no range contains it.
     */
    private TaxesRange findTaxesRange( double value )
    {
        TaxesRange range = null;
        for( int i = 0; i < taxRanges.size( ) && range == null; i++ )
        {
            TaxesRange auxRange = ( TaxesRange )taxRanges.get( i );
            if( auxRange.isWithin( value ) )
                range = auxRange;
        }
        return range;
    }

    /**
     * Returns the price of a vehicle given its brand, model and year. <br>
     * <b>pre: </b> The information about the vehicles and tax ranges was already successfully initialized.
     * @param aBrand Brand of the vehicle. aBrand != null.
     * @param aModel Model of the vehicle. aModel != null.
     * @param aYear Year of the vehicle. aYear != null.
     * @return price of the vehicle.
     * @throws Exception if the brand or the model or the year aren't registered.
     */
    public double findVehiclePrice( String aBrand, String aModel, String aYear ) throws Exception
    {
        Brand brand = searchForBrand( aBrand );
        if( brand == null )
            throw new Exception( "The brand " + aBrand + " isn't registered" );
        Model model = brand.searchForModel( aModel );
        if( model == null )
            throw new Exception( "The model " + aModel + " isn't registered" );
        Year year = model.searchForYearl( aYear );
        if( year == null )
            throw new Exception( "The year" + aYear + " isn't registered" );
        return year.getPrice( );
    }

    /**
     * Calculates the amount owed due to taxes of the given vehicle with the given brand, model, and year. if there is no range for that year return 0.<br>
     * <b>pre: </b> the information about brands, models and years has already been loaded
     * @param aBrand Brand of the vehicle. aBrand != null.
     * @param aModel Model of the vehicle. aModel != null.
     * @param aYear Year of the vehicle. aYear != null.
     * @param discImmediatePayment Indicates if the discount for immediate payment applies.
     * @param discPublicservices Indicates if the discount for public services applies.
     * @param discWireTransfer Indicates if the discount for wire transfer applies.
     * @return value Amount owed for taxes for that brand, model and year including the discounts that may apply
     * @throws Exception if the vehicle is not found using its brand, model and year.
     */
    public double calculatePayment( String aBrand, String aModel, String aYear, boolean discImmediatePayment, boolean discPublicservices, boolean discWireTransfer ) throws Exception
    {
        double payment = 0.0;
        double price = findVehiclePrice( aBrand, aModel, aYear );
        //Calculates the taxes for the vehicle
        TaxesRange range = findTaxesRange( price );
        if( range != null )
            payment = price * ( range.getPercent( ) / 100.0 );
        // applies the discount for immediate payment
        if( discImmediatePayment )
            payment -= payment * ( PRCNT_DISC_IMMEDIATE_PAY / 100 );
        // applies the discount for public services
        if( discPublicservices )
            payment -= TOTAL_DISC_PUBLIC_SERVICES;
        // applies the discount for wire transfer
        if( discWireTransfer )
            payment -= payment * ( PRCNT_DISC_WIRE_TRANSFER / 100 );
        //if the value is negative it is turned into a 0
        if( payment < 0 )
            payment = 0;
        return payment;
    }

    //----------------------------------------------------------------
    // Extension Points
    //----------------------------------------------------------------

    /**
     * Method for the extension 1 of the exercise
     * @return Answer
     */
    public String method1( )
    {
        return "Answer 1";
    }

    /**
     * Method for the extension 1 of the exercise
     * @return Answer
     */
    public String method2( )
    {
        return "Answer 2";
    }
}