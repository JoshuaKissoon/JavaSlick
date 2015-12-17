package com.joshuakissoon.slick.ui;

import com.joshuakissoon.slick.KeyValue;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

/**
 * A Time Picker for JavaFX
 *
 * @author Joshua Kissoon
 * @since 20151104
 */
public class TimePicker extends GridPane
{

    private final ComboBox<KeyValue<String, String>> hourPicker, minutePicker;

    public TimePicker()
    {
        this.hourPicker = new ComboBox<>();

        for (int i = 0; i < 24; i++)
        {
            String value = i < 10 ? "0" + i : i + "";
            KeyValue<String, String> kv = new KeyValue<>(value, value + " hrs");
            hourPicker.getItems().add(kv);

            if (hourPicker.getSelectionModel().isEmpty())
            {
                hourPicker.getSelectionModel().select(kv);
            }
        }

        this.minutePicker = new ComboBox<>();
        for (int i = 0; i < 60; i++)
        {
            String value = i < 10 ? "0" + i : i + "";
            KeyValue<String, String> kv = new KeyValue<>(value, value + "");
            minutePicker.getItems().add(kv);

            if (minutePicker.getSelectionModel().isEmpty())
            {
                minutePicker.getSelectionModel().select(kv);
            }
        }

        this.add(this.hourPicker, 0, 0);
        this.add(this.minutePicker, 1, 0);
    }

    public String getSelectedTime()
    {
        KeyValue<String, String> hour = hourPicker.getSelectionModel().getSelectedItem();
        KeyValue<String, String> minute = minutePicker.getSelectionModel().getSelectedItem();

        return hour.getKey() + ":" + minute.getKey() + ":00";
    }

    /**
     * @param time Time in the format 00:00:00 - hour:minute:second
     */
    public void setTime(final String time)
    {
        String[] data = time.split(":");
        String hour = data[0];
        String minute = data[1];

        KeyValue<String, String> hourkv = new KeyValue<>(hour, hour + " hrs");
        hourPicker.getSelectionModel().select(hourkv);

        KeyValue<String, String> minutekv = new KeyValue<>(minute, minute + "");
        minutePicker.getSelectionModel().select(minutekv);
    }
}
