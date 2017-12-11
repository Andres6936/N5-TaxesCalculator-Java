package edu.jabs.carTax.domain;

import java.util.*;

/**
 * The vehicle's model
 */
public class Model
{
    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /** Name of the model */
    private String name;
    /** Years of the model */
    private ArrayList years;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * Creates a model of vehicles with the given name.
     * @param theName Name of the model.
     */
    public Model( String theName )
    {
        name = theName;
        years = new ArrayList( );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Returns the name of the model.
     * @return name of the model.
     */
    public String getName( )
    {
        return name;
    }

    /**
     * Returns the years for that model.
     * @return years.
     */
    public ArrayList getYears( )
    {
        return years;
    }

    /**
     * Searches for the year of a model given the date.
     * @param theYear Year we are interested in. theYear != null.
     * @return The year if it is found, null otherwise.
     */
    public Year searchForYearl( String theYear )
    {
        Year year = null;
        for( int i = 0; i < years.size( ) && year == null; i++ )
        {
            Year auxYear = ( Year )years.get( i );
            if( auxYear.getYear( ).equals( theYear ) )
                year = auxYear;
        }
        return year;
    }

    /**
     * Adds a new year to the line. <br>
     * <b>post: </b> A new year is added to the list of years for the car.
     * @param aYear year to be added. aYear != null.
     */
    public void addYear( Year aYear )
    {
        years.add( aYear );
    }
}