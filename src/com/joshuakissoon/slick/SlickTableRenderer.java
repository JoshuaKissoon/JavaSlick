package com.joshuakissoon.slick;

import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

/**
 * A class that handles rendering tables properly to allow text to stretch to fit fields
 *
 * @author Joshua Kissoon
 * @since 20140904
 */
public class SlickTableRenderer extends JLabel implements TableCellRenderer
{

    public SlickTableRenderer()
    {
        
    }

    /**
     * Setting up a new table renderer with a different font size
     *
     * @param fontSize
     */
    public SlickTableRenderer(final int fontSize)
    {
        Font f = new Font(this.getFont().getName(), this.getFont().getStyle(), fontSize);
        this.setFont(f);
    }

    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, boolean isSelected, boolean hasFocus, final int row, int column)
    {
        JLabel cellSpacingLabel = (JLabel) (this);
        if (hasFocus)
        {
            setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
            cellSpacingLabel = null;
        }
        else
        {
            setBackground(table.getBackground());
            setBorder(null);
        }

        if (isSelected)
        {
            setBackground(table.getSelectionBackground());
            setBorder(null);
        }
        else
        {
            setBackground(table.getBackground());
            setBorder(null);
        }

        if (cellSpacingLabel != null)
        {
            cellSpacingLabel.setBorder(new CompoundBorder(new EmptyBorder(new Insets(10, 10, 10, 10)), cellSpacingLabel.getBorder()));
        }

        this.setOpaque(true);
        setText(value + "");
        return this;
    }
}
