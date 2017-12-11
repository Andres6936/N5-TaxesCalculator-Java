package edu.jabs.carTax.domain;

import java.util.*;

/**
 * The Vehicles Brand
 */
public class Brand
{
    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /** Name of the brand*/
    private String name;
    /** vehicle models for this brand */
    private ArrayList models;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * Creates a brand with the given name <br>
     * <b>post: </b> the brand is created with an empty set of models.
     * @param theName - name of the brand.
     */
    public Brand( String theName )
    {
        name = theName;
        models = new ArrayList( );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Returns the name of the brand.
     * @return name of the brand.
     */
    public String getName( )
    {
        return name;
    }

    /**
     * Returns the set of models for the brand
     * @return set of models.
     */
    public ArrayList getModels( )
    {
        return models;
    }

    /**
     * Searches for a model in the brand given its name.
     * @param modelName Name of the model. modelName != null.
     * @return the model if it is found, null if it isn't.
     */
    public Model searchForModel( String modelName )
    {
        Model Model = null;
        for( int i = 0; i < models.size( ) && Model == null; i++ )
        {
            Model auxModel = ( Model )models.get( i );
            if( auxModel.getName( ).equalsIgnoreCase( modelName ) )
                Model = auxModel;
        }
        return Model;
    }

    /**
     * Adds a new model to the brand if it doesn't already exist. <br>
     * <b>post: </b> A new model is added to the brand.
     * @param aModel Model to be added. aModel != null.
     */
    public void addModel( Model aModel )
    {
        if( searchForModel( aModel.getName( ) ) == null )
        {
            models.add( aModel );
        }
    }
}
