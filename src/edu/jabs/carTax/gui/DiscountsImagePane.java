package edu.jabs.carTax.gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Image pane that displays the information of the tax discounts
 */
public class DiscountsImagePane extends JPanel
{
    //-----------------------------------------------------------------
    // Gui Elements
    //-----------------------------------------------------------------

    /** Check box to indicate that you wish to pay with an immediate payment*/
    private JCheckBox cbIPayment;
    /** CheckBox to indicate that you wish to pay with a public services discount */
    private JCheckBox cbPublicS;
    /** CheckBox to indicate that you wish to pay with a wire transfer from another account */
    private JCheckBox cbTWire;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * Creates the discount image pane
     *
     */
    public DiscountsImagePane( )
    {
        //Sets the layout with a grid of 2 rows and 2 columns
        setLayout( new GridLayout( 2, 2 ) );

        //adds a titled border
        TitledBorder border = BorderFactory.createTitledBorder( "Discounts" );
        border.setTitleColor( Color.BLUE );
        setBorder( border );

        //Creates the objects in the image pane
        cbIPayment = new JCheckBox( "Immediate payment" );
        cbPublicS = new JCheckBox( "Public Services" );
        cbTWire = new JCheckBox( "Wire transfer" );

        //Adds the objects to the image pane
        add( cbIPayment );
        add( cbTWire );
        add( cbPublicS );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Indicates if there is a discount for immediate pay
     * @return true if there is a discount, false otherwise
     */
    public boolean isImmediatePayDiscount( )
    {
        return cbIPayment.isSelected( );
    }

    /**
     * Indicates if there is a discount for public services
     * @return true if there is a discount, false otherwise
     */
    public boolean isPublicServicesDiscount( )
    {
        return cbPublicS.isSelected( );
    }

    /**
     * Indicates if there is a discount for wire transfer
     * @return true if there is a discount, false otherwise
     */
    public boolean isWireTransferDiscount( )
    {
        return cbTWire.isSelected( );
    }

    /**
     * Clears the selections
     */
    public void clear( )
    {
        cbIPayment.setSelected( false );
        cbPublicS.setSelected( false );
        cbTWire.setSelected( false );
    }
}