package com.example;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.datatransfer.*;
import java.io.*;

/**
 * Hello world!
 *
 */
public class MainFrame extends JFrame
{
    public static Color[] colors;
    GridPixel pixel;

    public MainFrame() {
        colors = new Color[16];
        colors[0] = new Color(0x000000);
        colors[1] = new Color(0xffffff);
        colors[2] = new Color(0xc64fbd);
        colors[3] = new Color(0x3ab3da);
        colors[4] = new Color(0xffd83d);
        colors[5] = new Color(0xffd83d);
        colors[6] = new Color(0xf38caa);
        colors[7] = new Color(0x474f52);
        colors[8] = new Color(0x9c9d97);
        colors[9] = new Color(0x169c9d);
        colors[10] = new Color(0x8932b7);
        colors[11] = new Color(0x3c44a9);
        colors[12] = new Color(0x825432);
        colors[13] = new Color(0x5d7c15);
        colors[14] = new Color(0xb02e26);
        colors[15] = new Color(0xf9801d);

        

        setTitle("Blackboard Drawer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1500, 900);

        int xOffset = 200;
        int yOffset = 20;
        pixel = new GridPixel(xOffset, yOffset);
        add(pixel);
        
        JMenuBar bar = new JMenuBar();
        JMenu options = new JMenu("Options");
        JMenuItem print = new JMenuItem("Print Unicode");
        print.addActionListener(e -> printText());
        print.setAccelerator(KeyStroke.getKeyStroke("control P"));
        options.add(print);
        bar.add(options);

        setJMenuBar(bar);
        //add(settings);

        
        // JFileChooser chooser = new JFileChooser();
        // FileNameExtensionFilter filter = new FileNameExtensionFilter(
        // "JPG & PNG Images", "jpg", "png");
        // chooser.setFileFilter(filter);
        // int returnVal = chooser.showOpenDialog(this);
        // if(returnVal == JFileChooser.APPROVE_OPTION) {
        //     System.out.println("You chose to open this file: " +
        //         chooser.getSelectedFile().getName());
        // }
    }

    private void printText() {
        
        String finalString = new String();

        for (int i = 0; i < pixel.grid.length - 3; i += 4) {
            int code = (pixel.grid[i + 3] << 12) | (pixel.grid[i + 2] << 8) | (pixel.grid[i + 1] << 4) | (pixel.grid[i]);
            finalString += new String(Character.toChars(code));
            System.out.print("u+" + Integer.toHexString(code)+ " ");
        }
        System.out.println(finalString);

        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection testData;

        testData = new StringSelection(finalString);

        c.setContents(testData, testData);
    }

    public Color getColor(int i) {
        return colors[i];
    }

    public static void main( String[] args )
    {
        
        new MainFrame().setVisible(true);
    }
}
