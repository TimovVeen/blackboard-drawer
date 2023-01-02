package com.example;
import javax.swing.*;
import java.awt.*;

public class Settings extends JPanel {

    public Settings() {
        setBounds(0, 0, 300, 900);
        setBackground(new Color(230, 0, 0));
    }

    @Override
        public Dimension getPreferredSize() {
            return new Dimension(300, 900);
        }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
}
