package com.example;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Hello world!
 *
 */
public class MainFrame extends JFrame
{
    public MainFrame() {
        setTitle("Blackboard Drawer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & PNG Images", "jpg", "png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                chooser.getSelectedFile().getName());
        }
    }
    public static void main( String[] args )
    {
        new MainFrame().setVisible(true);
    }
}
