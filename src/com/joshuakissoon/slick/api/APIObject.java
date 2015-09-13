package com.joshuakissoon.slick.api;

import java.util.HashMap;

/**
 * Interface specifying the structure of any object that accesses the database
 *
 * @author Joshua Kissoon
 *
 * @param <IDType> Data type of the ID
 *
 * @since 20140831
 */
public interface APIObject<IDType>
{

    /**
     * @return Integer The id of this object from it's database table
     */
    public IDType getId();

    /**
     * Inserts a new row into the database with the data from this object
     *
     * @return Boolean Whether the operation was successful
     */
    public boolean insert();

    /**
     * Assuming this object data is already in the database,
     * update the current data.
     *
     * @return Boolean Whether the operation was successful
     */
    public boolean update();

    /**
     * Loads a row from the database with the data from this object
     *
     * @return Boolean Whether the operation was successful
     */
    public boolean load();

    /**
     * Given a hashmap with the data for the class, load it into the object
     *
     * @param data {HashMap<String, Object>} The data for the class
     */
    public void loadFromMap(final HashMap<String, Object> data);
}
