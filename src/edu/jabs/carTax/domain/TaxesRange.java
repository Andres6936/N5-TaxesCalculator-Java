package edu.jabs.carTax.domain;

/**
 * Range of taxes for the vehicles
 */
public class TaxesRange
{
    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /** Start of range */
    private double start;
    /** End of range */
    private double end;
    /** Percent */
    private double percent;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * Creates a tax range.
     * @param aStart beginning of the range.
     * @param anEnd End of the range.
     * @param aPercent Percent of taxes that applies for that range.
     */
    public TaxesRange( double aStart, double anEnd, double aPercent )
    {
        start = aStart;
        end = anEnd;
        percent = aPercent;
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Indicates if the value is within the range.
     * @param value Value that were checking with the range.
     * @return True if the value is within the range, false otherwise.
     */
    public boolean isWithin( double value )
    {
        return ( value >= start && value < end );
    }

    /**
     * Returns the percent.
     * @return percent.
     */
    public double getPercent( )
    {
        return percent;
    }
}
