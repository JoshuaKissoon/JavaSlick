package com.joshuakissoon.slick.ui;

import java.util.NoSuchElementException;
import javax.swing.JComboBox;
import com.joshuakissoon.slick.KeyValue;

/**
 * Combo Box with a set of numbers
 *
 * @author Joshua Kissoon
 * @since 20150723
 */
public class NumbersJComboBox extends JComboBox<KeyValue<Integer, String>>
{
    
    private final KeyValue<Integer, String> baseItem = new KeyValue<>(0, "-- Select --");
    
    public NumbersJComboBox(final Integer startValue, final Integer endValue)
    {
        this(startValue, endValue, true);
    }
    
    public NumbersJComboBox(final Integer startValue, final Integer endValue, final Boolean showBaseValue)
    {
        if (showBaseValue)
        {
            this.addItem(baseItem);
        }
        
        for (int i = startValue; i <= endValue; i++)
        {
            this.addItem(new KeyValue<>(i, i + ""));
        }
    }
    
    public void setSelectedNumber(final Integer selectedItem)
    {
        KeyValue<Integer, String> kv = new KeyValue<>(selectedItem, selectedItem + "");
        this.setSelectedItem(kv);
    }
    
    public Integer getSelectedNumber() throws NoSuchElementException
    {
        KeyValue<Integer, String> selected = (KeyValue) this.getSelectedItem();
        
        if (0 == selected.getKey())
        {
            throw new NoSuchElementException("No Item Selected");
        }
        
        return selected.getKey();
    }
}
