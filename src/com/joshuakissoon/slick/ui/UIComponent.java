package com.joshuakissoon.slick.ui;

/**
 * An interface component is any component that is shown to the end user
 *
 * @author Joshua Kissoon
 * @since 20140831
 */
public interface UIComponent
{
    /**
     * Create the component
     * 
     * @return Boolean Whether the creation was successful
     */
    public boolean create();
    
    /**
     * Display this component to the end user
     */
    public void display();
}
