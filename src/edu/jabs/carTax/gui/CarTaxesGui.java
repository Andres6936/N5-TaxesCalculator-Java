package edu.jabs.carTax.gui;

import java.awt.*;

import javax.swing.*;

import edu.jabs.carTax.domain.*;

/**
 * Main window for the car taxes calculator
 */
public class CarTaxesGui extends JFrame
{
    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /** Taxes calculator */
    private TaxesCalculator calculator;

    //-----------------------------------------------------------------
    // GUI Attributes
    //-----------------------------------------------------------------

    /** Vehicles Image Pane */
    private VehiclesImagePane vehiclesImagePane;
    /** Discounts Image Pane */
    private DiscountsImagePane discountsImagePane;
    /** Results Image Pane */
    private ResultsImagePane resultsImagePane;

    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------

    /**
     * creates the gui for the car taxes application, throws an exception if the file with the initial data can not be uploaded.
     * @throws Exception Error when loading the files
     */
    public CarTaxesGui( ) throws Exception
    {
        // Creates the car taxes calculator
        calculator = new TaxesCalculator( );

        // Configures the window
        setTitle( "Taxes calculator" );
        setSize( 290, 350 );
        setResizable( false );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLayout( new BorderLayout( ) );

        // Creates the image panes and adds them to the window
        // Vehicles Image Pane: active Image Pane
        vehiclesImagePane = new VehiclesImagePane( this );
        add( vehiclesImagePane, BorderLayout.NORTH );
        // Discounts Image Pane: passive Image Pane
        discountsImagePane = new DiscountsImagePane( );
        add( discountsImagePane, BorderLayout.CENTER );
        // Results Image Pane: active Image Pane
        resultsImagePane = new ResultsImagePane( this );
        add( resultsImagePane, BorderLayout.SOUTH );
    }

    //-----------------------------------------------------------------
    // Functional Requirements
    //-----------------------------------------------------------------

    /**
     * searches for the vehicle using the cars, brand, model and year. if it finds it, the car's price is returned
     */
    public void calculateVehiclePrice( )
    {
        // Retrieves the information that the user provided for the search
        String aBrand = vehiclesImagePane.getBrand( );
        String aModel = vehiclesImagePane.getModel( );
        String aYear = vehiclesImagePane.getYear( );
        // Makes sure that the information is complete
        if( aBrand.equals( "" ) || aModel.equals( "" ) || aYear.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "please provide all the information", "Taxes calculator", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            try
            {
                double price = calculator.findVehiclePrice( aBrand, aModel, aYear );
                // Tells the image pane to update the displayed price
                vehiclesImagePane.updatePrice( price );
            }
            catch( Exception e )
            {
                // Presents the exception message
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Taxes calculator", JOptionPane.WARNING_MESSAGE );
            }
        }
    }

    /**
     * Calculates the amount owed in taxes for the vehicle
     */
    public void calculateTaxes( )
    {
        // Retrieves the information that the user provided for the search
        String aBrand = vehiclesImagePane.getBrand( );
        String aModel = vehiclesImagePane.getModel( );
        String aYear = vehiclesImagePane.getYear( );
        // Makes sure that the information is complete
        if( aBrand.equals( "" ) || aModel.equals( "" ) || aYear.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "please provide all the information", "Taxes calculator", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            // Retrieves the information on the discounts from the image pane
            boolean disImmediatePay = discountsImagePane.isImmediatePayDiscount( );
            boolean disPublicServices = discountsImagePane.isPublicServicesDiscount( );
            boolean disWireTransfer = discountsImagePane.isWireTransferDiscount( );
            try
            {
                //Calculates the amount owed due to taxes
                double payment = calculator.calculatePayment( aBrand, aModel, aYear, disImmediatePay, disPublicServices, disWireTransfer );
                // Tells the image pane to update the information on the payments
                // for taxes
                resultsImagePane.updatePayment( payment );
            }
            catch( Exception e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Taxes calculator", JOptionPane.WARNING_MESSAGE );
            }
        }
    }

    /**
     * clears all the image panes
     */
    public void clear( )
    {
        vehiclesImagePane.clear( );
        discountsImagePane.clear( );
        resultsImagePane.clear( );
    }

    //----------------------------------------------------------------
    // Extension points
    //----------------------------------------------------------------

    /**
     * Is called upon to execute the method for extension 1
     */
    public void funcReqOption1( )
    {
        String answer = calculator.method1( );
        JOptionPane.showMessageDialog( this, answer, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Is called upon to execute the method for extension 2
     */
    public void funcReqOption2( )
    {
        String answer = calculator.method2( );
        JOptionPane.showMessageDialog( this, answer, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    //-----------------------------------------------------------------
    // Main method
    //-----------------------------------------------------------------
    /**
     * Method that executes the entire application
     * @param args - there are no arguments for the execution.
     */
    public static void main( String[] args )
    {
        try
        {
            CarTaxesGui gui = new CarTaxesGui( );
            gui.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}