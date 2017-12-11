package edu.jabs.carTax.gui;

import java.awt.*;
import java.awt.event.*;
import java.text.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Image pane to display the vehicle's information
 */
public class VehiclesImagePane extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constants
    //-----------------------------------------------------------------

    /** search command */
    public final static String SEARCH = "search";

    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /** Main window of the application */
    private CarTaxesGui main;

    //-----------------------------------------------------------------
    // Gui Attributes
    //-----------------------------------------------------------------

    /** Text field to display the brand of the car  */
    private JTextField txtBrand;
    /** Text field to display and type the model of the car   */
    private JTextField txtModel;
    /** Text field to display and type the year of the car  */
    private JTextField txtYear;
    /** Text field to display the price of the car  */
    private JTextField txtPrice;
    /** Label to write the word Brand before the brand text field */
    private JLabel labBrand;
    /** Label to write the word Model before the model text field */
    private JLabel labModel;
    /** Label to write the word Year before the year text field */
    private JLabel labYear;
    /** Label to write the word Price before the price text field*/
    private JLabel labPrice;
    /** Button to search for the price of a vehicle */
    private JButton searchButton;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------
    /**
     * Creates the image pane for the vehicle with the root frame.
     * @param mainW Main Window. mainW != null.
     */
    public VehiclesImagePane( CarTaxesGui mainW )
    {
        //stores the reference to the main window
        main = mainW;

        //sets the layout with a grid of 5 rows and 2 columns
        setLayout( new GridLayout( 5, 2 ) );

        //sets the size
        setPreferredSize( new Dimension( 0, 130 ) );

        //Adds a border and title
        TitledBorder border = BorderFactory.createTitledBorder( "Info on the Vehicle" );
        border.setTitleColor( Color.BLUE );
        setBorder( border );

        //Creates and initializes the objects in the image pane
        labBrand = new JLabel( "Brand" );
        labModel = new JLabel( "Model" );
        labYear = new JLabel( "Year" );
        labPrice = new JLabel( "Price" );
        txtBrand = new JTextField( );
        txtModel = new JTextField( );
        txtYear = new JTextField( );

        txtPrice = new JTextField( "$ 0" );
        txtPrice.setEditable( false );
        txtPrice.setForeground( Color.BLUE );
        txtPrice.setBackground( Color.WHITE );

        searchButton = new JButton( "Search" );
        searchButton.setActionCommand( SEARCH );
        searchButton.addActionListener( this );

        //Adds the objects to the image pane
        add( labBrand );
        add( txtBrand );
        add( labModel );
        add( txtModel );
        add( labYear );
        add( txtYear );
        add( labPrice );
        add( txtPrice );
        add( new JLabel( "" ) );
        add( searchButton );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Changes the price that is being displayed
     * @param price New price to display.
     */
    public void updatePrice( double price )
    {
        //Displays the new price
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( "$ ###,###.##" );
        txtPrice.setText( df.format( price ) );
    }

    /**
     * returns the brand that has been displayed
     * @return brand.
     */
    public String getBrand( )
    {
        return txtBrand.getText( );
    }

    /**
     * Returns the car's displayed model
     * @return model.
     */
    public String getModel( )
    {
        return txtModel.getText( );
    }

    /**
     * Returns the year displayed
     * @return year.
     */
    public String getYear( )
    {
        return txtYear.getText( );
    }

    /**
     * CLears all the fields
     */
    public void clear( )
    {
        txtBrand.setText( "" );
        txtModel.setText( "" );
        txtYear.setText( "" );
        txtPrice.setText( "$ 0" );
    }

    //-----------------------------------------------------------------
    // Event management
    //-----------------------------------------------------------------

    /**
     * Responds to the events in the gui
     * @param event event that the user did. event != null.
     */
    public void actionPerformed( ActionEvent event )
    {
        String command = event.getActionCommand( );
        if( command.equals( SEARCH ) )
        {
            main.calculateVehiclePrice( );
        }
    }
}