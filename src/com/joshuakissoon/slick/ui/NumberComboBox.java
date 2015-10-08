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
        this.getItems().add(new KeyValue<>(Integer.MIN_VALUE, "-- Select -- "));

        for (int i = startValue; i <= endValue; i++)
        {
            this.getItems().add(new KeyValue<>(i, i + ""));
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
