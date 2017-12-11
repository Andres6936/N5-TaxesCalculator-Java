package edu.jabs.carTax.gui;

import java.awt.*;
import java.awt.event.*;
import java.text.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Image pane that displays the results of the tax calculations
 */
public class ResultsImagePane extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constants
    //-----------------------------------------------------------------

    /** initialization command */
    public final static String CLEAR = "clear";
    /** Command that orders the calculation of the taxes for the car */
    public final static String CALCULATE = "calculate";
    /** Constant OPTION_1, used for the option of extension 1 */
    private final static String OPTION_1 = "OPTION_1";
    /** Constant OPTION_2, used for the option of extension 2 */
    private final static String OPTION_2 = "OPTION_2";

    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /** Main window of the application */
    private CarTaxesGui main;

    //-----------------------------------------------------------------
    // Gui Attributes
    //-----------------------------------------------------------------

    /** Label for the word "Total" */
    private JLabel labTotal;
    /** text field to display the total amount owed */
    private JTextField txtTotal;
    /** button that clears all the fields in this image pane */
    private JButton clearButton;
    /** Button used to calculate the total amount owed */
    private JButton calculateButton;
    /** Button that handles the extension1 */
    private JButton option1;
    /** Button that handles the extension2 */
    private JButton option2;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * Creates the image pane attached to the main window, considering that it is an active image pane
     * @param mainW - Main window of the application
     */
    public ResultsImagePane( CarTaxesGui mainW )
    {
        //Stores the reference to the main window
        main = mainW;

        //SEts the layout with a grid of 4 rows and 3 columns
        setLayout( new GridLayout( 4, 3 ) );

        //sets the height of the image pane
        setPreferredSize( new Dimension( 0, 105 ) );

        //adds a titled border
        TitledBorder border = BorderFactory.createTitledBorder( "Results" );
        border.setTitleColor( Color.BLUE );
        setBorder( border );

        //Creates the objects for the image pane
        labTotal = new JLabel( "Total amount owed" );
        txtTotal = new JTextField( "$ 0" );
        txtTotal.setEditable( false );
        txtTotal.setForeground( Color.BLUE );
        txtTotal.setBackground( Color.WHITE );

        clearButton = new JButton( "Clear" );
        clearButton.setActionCommand( CLEAR );
        clearButton.addActionListener( this );

        calculateButton = new JButton( "Calculate" );
        calculateButton.setActionCommand( CALCULATE );
        calculateButton.addActionListener( this );

        option1 = new JButton( "Option 1" );
        option1.setActionCommand( OPTION_1 );
        option1.addActionListener( this );
        //opcion1.setVisible( false );

        option2 = new JButton( "Option 2" );
        option2.setActionCommand( OPTION_2 );
        option2.addActionListener( this );
        //opcion2.setVisible( false );

        //adds the objects to the image pane (blank texts in unused fields)
        add( new JLabel( "" ) );
        add( new JLabel( "" ) );
        add( clearButton );
        add( labTotal );
        add( txtTotal );
        add( calculateButton );

        // Adds the extension buttons at the bottom of the frame
        // uses 4 blank labels to handle the spacing between them
        add( new JLabel( "" ) );
        add( new JLabel( "" ) );
        add( new JLabel( "" ) );
        add( new JLabel( "" ) );
        add( option1 );
        add( option2 );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Changes the pay that is being displayed
     * @param pay New pay to display.
     */
    public void updatePayment( double pay )
    {
        //Displays the amount owed
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( "$ ###,###.##" );
        txtTotal.setText( df.format( pay ) );
    }

    /**
     * Clears all the fields
     */
    public void clear( )
    {
        txtTotal.setText( "$ 0" );
    }

    //-----------------------------------------------------------------
    // Event Management
    //-----------------------------------------------------------------

    /**
     * Responds to the events in the gui
     * @param event event that the user did. event != null.
     */
    public void actionPerformed( ActionEvent event )
    {
        String command = event.getActionCommand( );
        if( command.equals( CLEAR ) )
        {
            main.clear( );
        }
        else if( command.equals( CALCULATE ) )
        {
            main.calculateTaxes( );
        }
        else if( command.equals( OPTION_1 ) )
        {
            main.funcReqOption1( );
        }
        else if( command.equals( OPTION_2 ) )
        {
            main.funcReqOption2( );
        }
    }
}