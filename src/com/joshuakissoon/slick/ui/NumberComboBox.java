package com.joshuakissoon.slick.ui;

import java.util.NoSuchElementException;
import com.joshuakissoon.slick.KeyValue;
import javafx.scene.control.ComboBox;

/**
 * Combo Box with a set of numbers
 *
 * @author Joshua Kissoon
 * @since 20151008
 */
public class NumberComboBox extends ComboBox<KeyValue<Integer, String>>
{

    public NumberComboBox(final Integer startValue, final Integer endValue)
    {
        this(startValue, endValue, true);
    }

    public NumberComboBox(final Integer startValue, final Integer endValue, final Boolean showBaseValue)
    {
        if (showBaseValue)
        {
            KeyValue base = new KeyValue<>(Integer.MIN_VALUE, "-- Select -- ");
            this.getItems().add(base);
            this.getSelectionModel().select(base);
        }

        for (int i = startValue; i <= endValue; i++)
        {
            KeyValue kv = new KeyValue<>(i, i + "");
            
            if(this.getSelectionModel().isEmpty())
            {
                this.getSelectionModel().select(kv);
            }
            
            this.getItems().add(kv);
        }
    }

    public void setSelectedNumber(final Integer selectedItem)
    {
        KeyValue<Integer, String> kv = new KeyValue<>(selectedItem, selectedItem + "");
        this.getSelectionModel().select(kv);
    }

    public Integer getSelectedNumber() throws NoSuchElementException
    {
        KeyValue<Integer, String> selected = this.getSelectionModel().getSelectedItem();

        if (Integer.MIN_VALUE == selected.getKey())
        {
            throw new NoSuchElementException("No Item Selected");
        }

        return selected.getKey();
    }
}
