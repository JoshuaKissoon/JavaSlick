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
    
    public NumbersJComboBox(final Integer startValue, final Integer endValue)
    {
        
        this.addItem(new KeyValue<>(0, "-- Select --"));
        
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
