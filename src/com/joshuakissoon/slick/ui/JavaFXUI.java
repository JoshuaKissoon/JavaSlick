package com.joshuakissoon.slick.ui;

import javafx.scene.Node;

/**
 * A class that produces a JavaFX Ui
 *
 * @author Joshua Kissoon
 * @since 20150914
 */
public interface JavaFXUI
{

    public void create();

    public Node getUI();
}
