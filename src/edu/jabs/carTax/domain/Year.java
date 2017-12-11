package edu.jabs.carTax.domain;
/**
 * The Vehicle's year
 */
public class Year
{
    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /** the year*/
    private String cYear;
    /** Price */
    private double price;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * Creates a Year with the corresponding year and the price for cars of that year
     * @param theYear Car's year.
     * @param thePrice car's price.
     */
    public Year( String theYear, double thePrice )
    {
        cYear = theYear;
        price = thePrice;
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Returns the year
     * @return year.
     */
    public String getYear( )
    {
        return cYear;
    }

    /**
     * Returns the price.
     * @return price.
     */
    public double getPrice( )
    {
        return price;
    }
}