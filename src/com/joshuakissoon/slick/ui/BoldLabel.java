package com.joshuakissoon.slick.ui;

import javafx.scene.control.Label;

/**
 * A FX Label that's bolded 
 * 
 * @author Joshua Kissoon
 * @since 20160215
 */
public class BoldLabel extends Label
{

    public BoldLabel(final String text)
    {
        super(text);
        this.setStyle("-fx-font-weight: bold");
    }
}
